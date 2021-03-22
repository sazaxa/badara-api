package com.sazaxa.shipmentapi.order.dto;

import lombok.Getter;

@Getter
public class OrderInvoiceRequestDto {
    private String invoice;
    private String koreanShippingCompany;
}
