package com.sazaxa.shipmentapi.order;

public enum OrderStatus {
    INVOICE("송장입렵"),
    KOREA_SHIPPING("배송중");

    String status;

    OrderStatus(String status) {
        this.status = status;
    }

}
