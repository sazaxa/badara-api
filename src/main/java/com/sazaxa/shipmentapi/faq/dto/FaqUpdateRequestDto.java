package com.sazaxa.shipmentapi.faq.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public FaqUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
