package com.sazaxa.shipmentapi.box.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoxUpdateRequestDto {
    private String koreanInvoice;
    private String koreanShippingCompany;
}
