package com.sazaxa.shipmentapi.haporder.dto;

import lombok.Getter;

@Getter
public class HapOrderSaveRequestDto {
    private String orderName;
    private double width;
    private double depth;
    private double height;
    private double volumeWeight;
    private double netWeight;
    private double expectedPrice;
    private String koreanShippingCompany;
    private String koreanInvoice;
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private String country;
    private String userMemo;
}
