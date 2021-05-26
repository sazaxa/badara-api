package com.sazaxa.shipmentapi.order.dto;

import com.sazaxa.shipmentapi.box.dto.BoxRequestDto;
import com.sazaxa.shipmentapi.recipient.Recipient;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderSaveRequestDto {
    private String orderNumber;
    private String expectedOrderPrice;
    private String orderPrice;
    private String invoice;
    private String shippingCompany;
    private String adminMemo;
    private String userMemo;
    private String orderStatus;
    private List<BoxRequestDto> boxes;
    private Recipient recipient;
}
