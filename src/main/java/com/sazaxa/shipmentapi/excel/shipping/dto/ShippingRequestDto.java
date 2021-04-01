package com.sazaxa.shipmentapi.excel.shipping.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ShippingRequestDto {
    String country;
    Double weight;

    @Builder
    public ShippingRequestDto(String country, Double weight) {
        this.country = country;
        this.weight = weight;
    }
}
