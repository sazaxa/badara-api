package com.sazaxa.shipmentapi.order.dto;

import lombok.Getter;

@Getter
public class OrderStatusRequestDto {
    private String paymentMethod;
    private String depositName;
    private String cardType;
    private String cardCompany;
    private String cardNumber;
    private String cardOwnerType;
    private String paymentKey;
    private String cardRequestedDate;
}
