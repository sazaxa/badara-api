package com.sazaxa.shipmentapi.notice.dto;

import lombok.Getter;

@Getter
public class NoticeRequestDto {
    private String title;
    private String content;
    private String orderStatus;
}
