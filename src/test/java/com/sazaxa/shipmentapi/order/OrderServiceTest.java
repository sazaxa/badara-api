package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.recipient.RecipientRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.Date;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private RecipientRepository recipientRepository;


    @Test
    void testMakeOrderNumber(){
        String country = "mexico";
        String fullName = "Sienna Kim HeHe";
        String name = fullName.split(" ")[0];
        String orderNumber = country.toUpperCase() + "-" + name.toUpperCase() + "-" + new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(4).toUpperCase();

        // 국가-수취인-랜덤값
        System.out.println(orderNumber);
    }

}
