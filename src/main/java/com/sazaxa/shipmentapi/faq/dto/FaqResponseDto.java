package com.sazaxa.shipmentapi.faq.dto;

import com.sazaxa.shipmentapi.faq.Faq;

public class FaqResponseDto {

    private Long id;
    private String title;
    private String content;

    public FaqResponseDto(Faq entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
