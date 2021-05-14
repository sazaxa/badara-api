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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

        List<Box> boxes = Arrays.asList(box1, box2);

        Double orderWeight = boxes.stream().mapToDouble(Box::getResultWeight).sum();
        assertThat(orderWeight).isEqualTo(3D);
    }

    @Test
    public void convertStringToLocalDateTime(){
        String date = "2021-04-14 10:30:33";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        System.out.println(date);
        System.out.println(dateTime);
    }

    @Test
    public void convertISO_OFFSET_DATE_TIMEToLocalDateTime(){
        String date = "2021-04-14T10:30:33+09:00";
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        System.out.println(dateTime);
    }

}
