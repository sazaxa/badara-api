package com.sazaxa.shipmentapi.haporder.dto;

import com.sazaxa.shipmentapi.order.Order;
import lombok.Getter;

import java.util.List;

@Getter
public class HapOrderUpdateRequestDto {
    private double hapOrderPrice;
    private List<Order> orders;
}
