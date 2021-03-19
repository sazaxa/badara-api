package com.sazaxa.shipmentapi.faq.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FaqUpdateRequestDto {
    private String title;
    private String content;
}
