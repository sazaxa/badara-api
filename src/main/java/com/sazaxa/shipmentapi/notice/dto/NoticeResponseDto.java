package com.sazaxa.shipmentapi.notice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeResponseDto {
    private String title;
    private String content;
    private String orderStatus;

    @Builder
    public NoticeResponseDto(String title, String content, String orderStatus) {
        this.title = title;
        this.content = content;
        this.orderStatus = orderStatus;
    }
}
