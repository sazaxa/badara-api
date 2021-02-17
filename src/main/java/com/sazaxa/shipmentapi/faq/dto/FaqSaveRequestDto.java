package com.sazaxa.shipmentapi.faq.dto;

import com.sazaxa.shipmentapi.faq.Faq;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FaqSaveRequestDto {

    private String title;
    private String content;

    public FaqSaveRequestDto(Faq faq){
        this.title = faq.getTitle();
        this.content = faq.getContent();
    }

    public Faq toEntity(){
        return new Faq(this.title, this.content);
    }

}
