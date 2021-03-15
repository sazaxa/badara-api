package com.sazaxa.shipmentapi.excel.shipping.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShippingCountryResponseDto {
    double price;
    double weight;
}
