package com.sazaxa.shipmentapi.order.dto;

import lombok.Getter;

@Getter
public class OrderUpdateRequestDto {

    private String status;
    private String country;
    private String recipientAddress;
    private String abroadShippingCompany;
    private String abroadInvoice;
    private double orderPrice;
    private String adminMemo;

}
