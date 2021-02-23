package com.sazaxa.shipmentapi.faq.dto;

import com.sazaxa.shipmentapi.faq.Faq;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public FaqResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public FaqResponseDto(Faq entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

}
