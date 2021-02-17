package com.sazaxa.shipmentapi.faq.dto;

import com.sazaxa.shipmentapi.faq.Faq;
import lombok.Getter;

@Getter
public class FaqResponseDto {

    private Long id;
    private String title;
    private String content;

    public FaqResponseDto(Faq entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

}
