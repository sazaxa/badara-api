package com.sazaxa.shipmentapi.faq.dto;

import com.github.dozermapper.core.Mapping;
import com.sazaxa.shipmentapi.faq.Faq;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqResponseDto {

    @Mapping("id")
    private Long id;
    @Mapping("tile")
    private String title;
    @Mapping("content")
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
