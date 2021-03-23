package com.sazaxa.shipmentapi.order;

public enum OrderStatus {
    INVOICE("송장입력"),
    CENTER_INCOME("센터입고중"),
    PAYMENT_REQUEST("결제요청"),
    PAYMENT_COMPLETE("결제완료"),
    GLOBAL_DELIVERY("해외배송중"),
    GLOBAL_DELIVERY_COMPLETED("해외배송완료");

    public String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
