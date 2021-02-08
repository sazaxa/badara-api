package com.sazaxa.shipmentapi.faq.dto;

import com.sazaxa.shipmentapi.faq.Faq;

public class FaqSaveRequestDto {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Faq toEntity(){
        return new Faq(this.title, this.content);
    }

}
