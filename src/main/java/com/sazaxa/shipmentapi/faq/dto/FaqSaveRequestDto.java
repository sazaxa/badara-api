package com.sazaxa.shipmentapi.faq.dto;

import com.github.dozermapper.core.Mapping;
import com.sazaxa.shipmentapi.faq.Faq;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqSaveRequestDto {

    @Mapping("title")
    private String title;
    @Mapping("content")
    private String content;

    @Builder
    public FaqSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Faq toEntity(){
        return Faq.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }

}
