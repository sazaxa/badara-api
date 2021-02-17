package com.sazaxa.shipmentapi.order.dto;

import lombok.Getter;

@Getter
public class OrderSaveRequestDto {

    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private String productName;
    private String koreanInvoice;
    private String country;
    private double width;
    private double depth;
    private double height;
    private double volumeWeight;
    private double netWeight;

    public OrderSaveRequestDto() {
    }

}
