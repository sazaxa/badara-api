package com.sazaxa.shipmentapi.order.dto;

import com.sazaxa.shipmentapi.product.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderUpdateRequestDto {
    private double orderPrice;
    private List<Product> products;
}
