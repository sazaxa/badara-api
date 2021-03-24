package com.sazaxa.shipmentapi.order.dto;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.order.OrderStatus;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.recipient.Recipient;

import java.util.List;

public class OrderResponseDto {
    private String orderNumber;
    private String expectedOrderPrice;
    private String orderPrice;
    private String invoice;
    private String shippingCompany;
    private String adminMemo;
    private String userMemo;
    private OrderStatus orderStatus;
    private List<Product> products;
    private List<Box> boxes;
    private Recipient recipient;
}
