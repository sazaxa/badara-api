package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.box.BoxRepository;
import com.sazaxa.shipmentapi.box.dto.BoxRequestDto;
import com.sazaxa.shipmentapi.box.dto.BoxResponseDto;
import com.sazaxa.shipmentapi.box.exception.BoxNotFoundException;
import com.sazaxa.shipmentapi.excel.shipping.ShippingService;
import com.sazaxa.shipmentapi.excel.shipping.dto.ShippingRequestDto;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderStatusRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.product.dto.ProductResponseDto;
import com.sazaxa.shipmentapi.product.exception.ProductNotFoundException;
import com.sazaxa.shipmentapi.recipient.Recipient;
import com.sazaxa.shipmentapi.recipient.RecipientRepository;
import com.sazaxa.shipmentapi.recipient.exception.RecipientNotFoundException;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final ShippingService shippingService;

    public OrderService(OrderRepository orderRepository, MemberRepository memberRepository, ProductRepository productRepository, RecipientRepository recipientRepository, BoxRepository boxRepository, ShippingService shippingService) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.recipientRepository = recipientRepository;
        this.boxRepository = boxRepository;
        this.shippingService = shippingService;
    }

    public List<OrderResponseDto> getAllOrder() {
        List<Order> orders = orderRepository.findAllByOrderByCreatedDateDesc();
        List<OrderResponseDto> responses = new ArrayList<>();

        for (Order order : orders){

            List<Product> products = productRepository.findAllByOrder(order);

            List<ProductResponseDto> productResponses = ProductResponseDto.ofList(products);

            List<Box> boxes = boxRepository.findAllByOrder(order);
            List<BoxResponseDto> boxResponses = BoxResponseDto.ofList(boxes);

            OrderResponseDto response = OrderResponseDto.of(order, productResponses, boxResponses);
            responses.add(response);
        }
        return responses;
    }

    public OrderResponseDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no order id : " + id));

        List<Product> products = productRepository.findAllByOrder(order);
        List<ProductResponseDto> productResponses = ProductResponseDto.ofList(products);

        List<Box> boxes = boxRepository.findAllByOrder(order);
        List<BoxResponseDto> boxResponses = BoxResponseDto.ofList(boxes);

        OrderResponseDto response = OrderResponseDto.of(order, productResponses, boxResponses);
        return response;
    }

    public OrderResponseDto getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber).orElseThrow(() -> new OrderNotFoundException(("no orderNumber :" + orderNumber)));
        List<Product> products = productRepository.findAllByOrder(order);
        List<ProductResponseDto> productResponses = ProductResponseDto.ofList(products);

        List<Box> boxes = boxRepository.findAllByOrder(order);
        List<BoxResponseDto> boxResponses = BoxResponseDto.ofList(boxes);

        OrderResponseDto response = OrderResponseDto.of(order, productResponses, boxResponses);
        return response;
    }

    /**
     * 주문을 처리합니다.
     * @param request
     * @param currentUser
     * @return order를 돌려줍니다.
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
                .countryCode(request.getRecipient().getCountryCode())
                .phoneNumber(request.getRecipient().getPhoneNumber())
                .memo(request.getRecipient().getMemo())
                .member(member)
                .build();
        recipientRepository.save(recipient);

        String orderNumber = makeOrderNumber(request);
        Order order = Order.builder()
                .orderNumber(orderNumber)
                .expectedOrderPrice(request.getExpectedOrderPrice())
                .userMemo(request.getUserMemo())
                .orderStatus(OrderStatus.PAYMENT_HOLDING)
                .member(member)
                .recipient(recipient)
                .depositName("")
                .build();
        orderRepository.save(order);

        List<Product> products = new ArrayList<>();
        for (Product product : request.getProducts()){
            Product newProduct = Product.builder()
                    .productDetail(product.getProductDetail())
                    .quantity(product.getQuantity())
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
                    .userMemo(box.getUserMemo())
                    .order(order)
                    .build();

            if (newBox.getKoreanInvoice() == null){
                newBox.updateKoreanShippingStatus(OrderStatus.INVOICE);
            }

            boxes.add(newBox);
        }
        boxRepository.saveAll(boxes);

        OrderResponseDto response = OrderResponseDto.builder()
                .orderNumber(order.getOrderNumber())
                .expectedOrderPrice(order.getExpectedOrderPrice())
                .userMemo(order.getUserMemo())
                .orderStatus(order.getOrderStatus().status)
                .productResponses(ProductResponseDto.ofList(products))
                .boxResponses(BoxResponseDto.ofList(boxes))
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
    private String makeOrderNumber(OrderSaveRequestDto request) {
        String country = request.getRecipient().getCountry();
        String name = request.getRecipient().getName().split(" ")[0];
        String orderNumber = country.toUpperCase() + "-" + name.toUpperCase() + "-" + new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(4).toUpperCase();
        return orderNumber;
    }

    public OrderResponseDto updateOrder(Long id, OrderUpdateRequestDto request) {

        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("no order id" + id));
        Recipient recipient = recipientRepository.findById(order.getRecipient().getId()).orElseThrow(()-> new  RecipientNotFoundException("no recipient id : " + order.getRecipient().getId()));

        recipient.updateRecipient(
                request.getRecipient().getName() == null ? recipient.getName() : request.getRecipient().getName(),
                request.getRecipient().getEmail() == null ? recipient.getEmail() : request.getRecipient().getEmail(),
                request.getRecipient().getCountry() == null ? recipient.getCountry() : request.getRecipient().getCountry(),
                request.getRecipient().getState() == null ? recipient.getState() : request.getRecipient().getState(),
                request.getRecipient().getCity() == null ? recipient.getCity() : request.getRecipient().getCity(),
                request.getRecipient().getAddress1() == null ? recipient.getAddress1() : request.getRecipient().getAddress1(),
                request.getRecipient().getAddress2() == null ? recipient.getAddress2() : request.getRecipient().getAddress2(),
                request.getRecipient().getAddress3() == null ? recipient.getAddress3() : request.getRecipient().getAddress3(),
                request.getRecipient().getZipcode() == null ? recipient.getZipcode() : request.getRecipient().getZipcode(),
                request.getRecipient().getCountryCode() == null ? recipient.getCountryCode() : request.getRecipient().getCountryCode(),
                request.getRecipient().getPhoneNumber() == null ? recipient.getPhoneNumber() : request.getRecipient().getPhoneNumber(),
                request.getRecipient().getMemo() == null ? recipient.getMemo() : request.getRecipient().getMemo()
        );
        recipientRepository.save(recipient);


        for (Product newProduct : request.getProducts()){
            Product product = productRepository.findById(newProduct.getId()).orElseThrow(()-> new ProductNotFoundException("no product id : " + newProduct.getId()));
            product.updateProduct(newProduct.getProductDetail(), newProduct.getQuantity(), newProduct.getPrice(), newProduct.getWeight());
            productRepository.save(product);
        }

        List<Box> boxList = BoxRequestDto.toEntityList(request.getBoxes());

        for (Box newBox : boxList){
            Box box = boxRepository.findById(newBox.getId()).orElseThrow(()-> new BoxNotFoundException("no box id : " + newBox.getId()));
            box.updateBox(
                    newBox.getExpectedWidth(),
                    newBox.getExpectedDepth(),
                    newBox.getExpectedHeight(),
                    newBox.getExpectedVolumeWeight(),
                    newBox.getExpectedNetWeight(),
                    newBox.getExpectedPrice(),
                    newBox.getWidth(),
                    newBox.getDepth(),
                    newBox.getHeight(),
                    weightVolumeWeight(newBox.getWidth(), newBox.getDepth(), newBox.getHeight()),
                    newBox.getNetWeight(),
                    newBox.getPrice(),
                    newBox.getKoreanInvoice(),
                    newBox.getKoreanShippingCompany(),
                    isVolumeWeight(weightVolumeWeight(newBox.getWidth(), newBox.getDepth(), newBox.getHeight()), newBox.getNetWeight()),
                    compareWeight(weightVolumeWeight(newBox.getWidth(), newBox.getDepth(), newBox.getHeight()), newBox.getNetWeight()),
                    newBox.getUserMemo(),
                    OrderStatus.findByKorean(request.getOrderStatus())
                    );
            boxRepository.save(box);
        }

        List<Product> products = productRepository.findAllByOrder(order);
        List<Box> boxes = boxRepository.findAllByOrder(order);

        order.updateOrder(
                request.getExtraPrice(),
                calculateOrderPrice(boxes, recipient.getCountry()),
                request.getInvoice(),
                request.getShippingCompany(),
                request.getAdminMemo(),
                OrderStatus.findByKorean(request.getOrderStatus()));
        orderRepository.save(order);

        OrderResponseDto response = OrderResponseDto.builder()
                .orderNumber(order.getOrderNumber())
                .expectedOrderPrice(order.getExpectedOrderPrice())
                .userMemo(order.getUserMemo())
                .orderStatus(order.getOrderStatus().status)
                .productResponses(ProductResponseDto.ofList(products))
                .boxResponses(BoxResponseDto.ofList(boxes))
                .recipient(recipient)
                .build();

        return response;
    }

    public OrderResponseDto updateStatus(String orderNumber, OrderStatusRequestDto request) {
        Order order = orderRepository.findByOrderNumber(orderNumber).orElseThrow(()->new OrderNotFoundException("no ordernumber : " + orderNumber));

        List<Box> boxes = boxRepository.findAllByOrder(order);
        List<Product> products = productRepository.findAllByOrder(order);
        Recipient recipient = recipientRepository.findById(order.getRecipient().getId()).orElseThrow(()-> new  RecipientNotFoundException("no recipient id : " + order.getRecipient().getId()));

        if (request.getPaymentMethod().equals(OrderStatus.PAYMENT_BANK.status)){
            order.updateOrderStatus(OrderStatus.PAYMENT_BANK);
            order.updateDepositName(request.getDepositName());
            for (Box box : boxes){
                box.updateKoreanShippingStatus(OrderStatus.PAYMENT_BANK);
                boxRepository.save(box);
            }
        }

        if (request.getPaymentMethod().equals(OrderStatus.PAYMENT_COMPLETE.status)){

            order.updateOrderStatus(OrderStatus.PAYMENT_COMPLETE);
            order.updateOrderPayment(request.getCardType(),
                    request.getCardCompany(),
                    request.getCardOwnerType(),
                    request.getPaymentKey(),
                    LocalDateTime.parse(request.getCardRequestedDate(),DateTimeFormatter.ISO_OFFSET_DATE_TIME));

            for (Box box : boxes){
                box.updateKoreanShippingStatus(OrderStatus.PAYMENT_COMPLETE);
                boxRepository.save(box);
            }
        }
        if (request.getPaymentMethod().equals(OrderStatus.REFUND_WAITING.status)){
            order.updateOrderStatus(OrderStatus.REFUND_WAITING);
            for (Box box : boxes){
                box.updateKoreanShippingStatus(OrderStatus.REFUND_WAITING);
                boxRepository.save(box);
            }
        }
        if (request.getPaymentMethod().equals(OrderStatus.CANCEL.status)){
            order.updateOrderStatus(OrderStatus.CANCEL);
            for (Box box : boxes){
                box.updateKoreanShippingStatus(OrderStatus.CANCEL);
                boxRepository.save(box);
            }
        }
        orderRepository.save(order);

        OrderResponseDto response = OrderResponseDto.builder()
                .orderNumber(order.getOrderNumber())
                .expectedOrderPrice(order.getExpectedOrderPrice())
                .userMemo(order.getUserMemo())
                .orderStatus(order.getOrderStatus().status)
                .productResponses(ProductResponseDto.ofList(products))
                .boxResponses(BoxResponseDto.ofList(boxes))
                .recipient(recipient)
                .build();

        return response;
    }

//    public OrderResponseDto cancelOrder(Long id) {
//        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("no order id :" + id));
//        order.updateOrderStatus(OrderStatus.CANCEL);
//
//        List<Product> products = productRepository.findAllByOrder(order);
//        List<Box> boxes = boxRepository.findAllByOrder(order);
//        Recipient recipient = recipientRepository.findById(order.getRecipient().getId()).orElseThrow(()-> new  RecipientNotFoundException("no recipient id : " + order.getRecipient().getId()));
//
//        return OrderResponseDto.builder()
//                .orderNumber(order.getOrderNumber())
//                .expectedOrderPrice(order.getExpectedOrderPrice())
//                .userMemo(order.getUserMemo())
//                .orderStatus(order.getOrderStatus().status)
//                .productResponses(ProductResponseDto.ofList(products))
//                .boxResponses(BoxResponseDto.ofList(boxes))
//                .recipient(recipient)
//                .build();
//    }

    public Double weightVolumeWeight(Double width, Double depth, Double height ) {
        if (width == null || depth == null || height == null){
            return null;
        }
        return width * depth * height / 5000;
    }

    public Boolean isVolumeWeight(Double volumeWeight, Double netWeight){
        if (volumeWeight == null || netWeight == null){
            return null;
        }

        if (volumeWeight >= netWeight){
            return true;
        }
        return false;
    }

    public Double compareWeight(Double volumeWeight, Double netWeight){
        if (volumeWeight == null && netWeight == null){
            return null;
        }

        if (volumeWeight == null && netWeight != null ){
            return netWeight;
        }

        if (volumeWeight != null && netWeight == null ){
            return volumeWeight;
        }

        if (volumeWeight >= netWeight){
            return volumeWeight;
        }

        return netWeight;
    }

    public Double calculateOrderPrice( List<Box> boxes, String country){

        for (Box box : boxes){
            if (box.getResultWeight() == null){
                return null;
            }
        }

        Double orderWeight = boxes.stream().mapToDouble(Box::getResultWeight).sum();
        return shippingService.getPrice(ShippingRequestDto.builder()
                .weight(orderWeight)
                .country(country)
                .build());
    }


}
