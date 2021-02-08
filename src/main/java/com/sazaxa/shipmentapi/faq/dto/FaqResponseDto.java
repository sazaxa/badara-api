package com.sazaxa.shipmentapi.faq.dto;

import com.sazaxa.shipmentapi.faq.Faq;

public class FaqResponseDto {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public FaqResponseDto(Faq entity){
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

}
