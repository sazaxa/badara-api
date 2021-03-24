package com.sazaxa.shipmentapi.order.dto;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.order.OrderStatus;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.recipient.Recipient;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
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

    @Builder
    public OrderResponseDto(String orderNumber, String expectedOrderPrice, String orderPrice, String invoice, String shippingCompany, String adminMemo, String userMemo, OrderStatus orderStatus, List<Product> products, List<Box> boxes, Recipient recipient) {
        this.orderNumber = orderNumber;
        this.expectedOrderPrice = expectedOrderPrice;
        this.orderPrice = orderPrice;
        this.invoice = invoice;
        this.shippingCompany = shippingCompany;
        this.adminMemo = adminMemo;
        this.userMemo = userMemo;
        this.orderStatus = orderStatus;
        this.products = products;
        this.boxes = boxes;
        this.recipient = recipient;
    }
}
