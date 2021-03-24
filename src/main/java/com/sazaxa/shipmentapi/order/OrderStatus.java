package com.sazaxa.shipmentapi.order;

import java.util.Arrays;

public enum OrderStatus {
    INVOICE("송장입력"),
    CENTER_INCOME("센터입고중"),
    PAYMENT_HOLDING("결제대기"),
    PAYMENT_REQUEST("결제요청"),
    PAYMENT_COMPLETE("결제완료"),
    GLOBAL_DELIVERY("해외배송중"),
    GLOBAL_DELIVERY_COMPLETED("해외배송완료"),
    ERROR("상태오류");

    public String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus findByKorean(String korean){
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.status.equals(korean))
                .findFirst()
                .orElse(ERROR);
    }

}
