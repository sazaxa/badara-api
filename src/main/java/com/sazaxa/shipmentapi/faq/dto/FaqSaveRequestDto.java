package com.sazaxa.shipmentapi.faq.dto;

import com.sazaxa.shipmentapi.faq.Faq;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqSaveRequestDto {

    private String title;
    private String content;

    @Builder
    public FaqSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Faq toEntity(){
        return new Faq(this.title, this.content);
    }

}
