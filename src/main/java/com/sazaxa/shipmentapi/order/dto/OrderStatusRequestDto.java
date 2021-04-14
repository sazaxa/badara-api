package com.sazaxa.shipmentapi.order.dto;

import lombok.Getter;

@Getter
public class OrderStatusRequestDto {
    private String paymentMethod;
    private String depositName;
}
