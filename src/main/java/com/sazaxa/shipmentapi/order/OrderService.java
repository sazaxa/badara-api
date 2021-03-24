package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.box.BoxRepository;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.recipient.Recipient;
import com.sazaxa.shipmentapi.recipient.RecipientRepository;
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
                .orderStatus(order.getOrderStatus())
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

//    public Order getProduct(Long id) {
//        return orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no product id : " + id));
//    }
//
//    public Order updateProductKoreanInvoice(Long id, OrderInvoiceRequestDto request) {
//        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no product id : " + id));
//        order.proceedInvoice(request.getInvoice(), request.getKoreanShippingCompany());
//        return order;
//    }
}
