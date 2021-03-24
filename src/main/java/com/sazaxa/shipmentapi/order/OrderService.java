package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.box.BoxRepository;
import com.sazaxa.shipmentapi.box.exception.BoxNotFoundException;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.product.exception.ProductNotFoundException;
import com.sazaxa.shipmentapi.recipient.Recipient;
import com.sazaxa.shipmentapi.recipient.RecipientRepository;
import com.sazaxa.shipmentapi.recipient.exception.RecipientNotFoundException;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final RecipientRepository recipientRepository;
    private final BoxRepository boxRepository;

    public OrderService(OrderRepository orderRepository, MemberRepository memberRepository, ProductRepository productRepository, RecipientRepository recipientRepository, BoxRepository boxRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.recipientRepository = recipientRepository;
        this.boxRepository = boxRepository;
    }

    public List<OrderResponseDto> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> responses = new ArrayList<>();
        for (Order order : orders){
            List<Product> products = productRepository.findAllByOrder(order);
            List<Box> boxes = boxRepository.findAllByOrder(order);

            OrderResponseDto response = OrderResponseDto.builder()
                    .id(order.getId())
                    .orderNumber(order.getOrderNumber())
                    .expectedOrderPrice(order.getExpectedOrderPrice())
                    .orderPrice(order.getOrderPrice())
                    .invoice(order.getInvoice())
                    .shippingCompany(order.getShippingCompany())
                    .adminMemo(order.getAdminMemo())
                    .userMemo(order.getUserMemo())
                    .orderStatus(order.getOrderStatus().status)
                    .products(products)
                    .boxes(boxes)
                    .recipient(order.getRecipient())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    public OrderResponseDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no order id : " + id));
        List<Product> products = productRepository.findAllByOrder(order);
        List<Box> boxes = boxRepository.findAllByOrder(order);

        OrderResponseDto response = OrderResponseDto.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .expectedOrderPrice(order.getExpectedOrderPrice())
                .orderPrice(order.getOrderPrice())
                .invoice(order.getInvoice())
                .shippingCompany(order.getShippingCompany())
                .adminMemo(order.getAdminMemo())
                .userMemo(order.getUserMemo())
                .orderStatus(order.getOrderStatus().status)
                .products(products)
                .boxes(boxes)
                .recipient(order.getRecipient())
                .build();

        return response;
    }

    /**
     * 주문을 처리합니다.
     * @param request
     * @param currentUser
     * @return
     */
    public OrderResponseDto saveOrder(OrderSaveRequestDto request, UserPrincipalCustom currentUser) {

        Member member = memberRepository.findById(currentUser.getId()).orElseThrow(()-> new MemberNotFoundException("no member id : " + currentUser.getId()));
        Recipient recipient = Recipient.builder()
                .name(request.getRecipient().getName())
                .email(request.getRecipient().getEmail())
                .country(request.getRecipient().getCountry())
                .state(request.getRecipient().getState())
                .city(request.getRecipient().getCity())
                .address1(request.getRecipient().getAddress1())
                .address2(request.getRecipient().getAddress2())
                .address3(request.getRecipient().getAddress3())
                .zipcode(request.getRecipient().getZipcode())
                .phoneNumber(request.getRecipient().getPhoneNumber())
                .member(member)
                .build();
        recipientRepository.save(recipient);

        String orderNumber = makeOrderNumber(request, currentUser);
        Order order = Order.builder()
                .orderNumber(orderNumber)
                .expectedOrderPrice(request.getExpectedOrderPrice())
                .userMemo(request.getUserMemo())
                .orderStatus(OrderStatus.PAYMENT_HOLDING)
                .member(member)
                .recipient(recipient)
                .build();
        orderRepository.save(order);

        List<Product> products = new ArrayList<>();
        for (Product product : request.getProducts()){
            Product newProduct = Product.builder()
                    .productDetail(product.getProductDetail())
                    .price(product.getPrice())
                    .weight(product.getWeight())
                    .order(order)
                    .build();
            products.add(newProduct);
        }
        productRepository.saveAll(products);

        List<Box> boxes = new ArrayList<>();
        for (Box box : request.getBoxes()){
            Box newBox = Box.builder()
                    .expectedWidth(box.getExpectedWidth())
                    .expectedDepth(box.getExpectedDepth())
                    .expectedHeight(box.getExpectedHeight())
                    .expectedVolumeWeight(box.getExpectedVolumeWeight())
                    .expectedNetWeight(box.getExpectedNetWeight())
                    .expectedPrice(box.getExpectedPrice())
                    .koreanInvoice(box.getKoreanInvoice())
                    .koreanShippingCompany(box.getKoreanShippingCompany())
                    .koreanShippingStatus(OrderStatus.CENTER_INCOME)
                    .build();
            if (newBox.getKoreanInvoice().isBlank() || newBox.getKoreanInvoice() == null){
                newBox.updateKoreanShippingStatus(OrderStatus.INVOICE);
            }
        }
        boxRepository.saveAll(boxes);

        OrderResponseDto response = OrderResponseDto.builder()
                .orderNumber(order.getOrderNumber())
                .expectedOrderPrice(order.getExpectedOrderPrice())
                .userMemo(order.getUserMemo())
                .orderStatus(order.getOrderStatus().status)
                .products(products)
                .boxes(boxes)
                .recipient(recipient)
                .build();

        return response;
    }


    /**
     * OrderNumber를 생성합니다.
     * @param request
     * @aram currentUser
     * @return
     */
    private String makeOrderNumber(OrderSaveRequestDto request, UserPrincipalCustom currentUser) {
        String country = request.getRecipient().getCountry();
        String name = currentUser.getUsername().split(" ")[0];
        String orderNumber = country.toUpperCase() + "-" + name.toUpperCase() + "-" + new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(4).toUpperCase();
        return orderNumber;
    }

    public OrderResponseDto updateOrder(Long id, OrderSaveRequestDto request) {
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("no order id" + id));
        Recipient recipient = recipientRepository.findById(order.getRecipient().getId()).orElseThrow(()-> new  RecipientNotFoundException("no recipient id : " + order.getRecipient().getId()));
        List<Box> boxes = boxRepository.findAllByOrder(order);

        order.updateOrder(request.getOrderPrice(),
                request.getInvoice(),
                request.getShippingCompany(),
                request.getAdminMemo(),
                request.getOrderStatus());

        recipient.updateRecipient(request.getRecipient().getName(),
                request.getRecipient().getEmail(),
                request.getRecipient().getCountry(),
                request.getRecipient().getState(),
                request.getRecipient().getCity(),
                request.getRecipient().getAddress1(),
                request.getRecipient().getAddress2(),
                request.getRecipient().getAddress3(),
                request.getRecipient().getZipcode(),
                request.getRecipient().getCountryCode(),
                request.getRecipient().getPhoneNumber()
        );

        for (Product newProduct : request.getProducts()){
            Product product = productRepository.findById(newProduct.getId()).orElseThrow(()-> new ProductNotFoundException("no product id : " + newProduct.getId()));
            product.updateProduct(newProduct.getProductDetail(), newProduct.getQuantity(), newProduct.getPrice(), newProduct.getWeight());
            productRepository.save(product);
        }

        for (Box newBox : request.getBoxes()){
            Box box = boxRepository.findById(newBox.getId()).orElseThrow(()-> new BoxNotFoundException("no box id : " + newBox.getId()));
//            box.updateBox();
        }

        return null;
    }
}
