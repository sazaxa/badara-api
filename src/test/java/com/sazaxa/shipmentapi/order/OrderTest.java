package com.sazaxa.shipmentapi.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp(){
        order = Order.builder()
                .orderStatus(OrderStatus.PAYMENT_BANK)
                .build();
    }

    @Test
    void 무통장입금인상태에서_결제요청이왔을때_OrderStatus와비교하는_테스트(){
        assertThat(order.getOrderStatus().name()).isEqualTo(OrderStatus.PAYMENT_BANK.name());
    }

}
