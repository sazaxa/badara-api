package com.sazaxa.shipmentapi.faq.dto;

import lombok.Getter;

@Getter
public class ImgResponseDto {
    private String imgUrl;

    public ImgResponseDto(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
