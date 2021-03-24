package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
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

    public OrderService(OrderRepository orderRepository, MemberRepository memberRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public Order saveOrder(OrderSaveRequestDto request, UserPrincipalCustom currentUser) {
        String orderNumber = makeOrderNumber(request, currentUser);
        Member member = memberRepository.findById(currentUser.getId()).orElseThrow(()-> new MemberNotFoundException("no member id : " + currentUser.getId()));

        Order order = Order.builder()
                .orderNumber(orderNumber)
                .expectedOrderPrice(request.getExpectedOrderPrice())
                .userMemo(request.getUserMemo())
                .member(member)
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



        return null;
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
        // 국가-수취인-랜덤값
        return new SimpleDateFormat("yyMMdd").format(country + "-" + name + "-" + new Date()) + "-" + RandomStringUtils.randomAlphanumeric(4).toUpperCase();
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
