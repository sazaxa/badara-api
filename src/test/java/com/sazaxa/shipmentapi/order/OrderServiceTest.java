package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.box.BoxRepository;
import com.sazaxa.shipmentapi.excel.shipping.ShippingService;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.recipient.RecipientRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    @Mock
    private BoxRepository boxRepository;
    @Mock
    private ShippingService shippingService;

    @Test
    void testMakeOrderNumber(){
        String country = "mexico";
        String fullName = "Sienna Kim HeHe";
        String name = fullName.split(" ")[0];
        String orderNumber = country.toUpperCase() + "-" + name.toUpperCase() + "-" + new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(4).toUpperCase();

        // 국가-수취인-랜덤값
        System.out.println(orderNumber);
    }

    @Test
    public void calculateOrderPrice(){

        Box box1 = Box.builder()
                .resultWeight(1D)
                .build();

        Box box2 = Box.builder()
                .resultWeight(2D)
                .build();

        List<Box> boxes = new ArrayList<>();
        boxes.add(box1);
        boxes.add(box2);

        System.out.println(boxes.get(0).getResultWeight());
        System.out.println(boxes.get(1).getResultWeight());

        Double orderWeight = boxes.stream().mapToDouble(Box::getResultWeight).sum();
        assertThat(orderWeight).isEqualTo(3D);

    }

}
