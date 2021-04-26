package com.sazaxa.shipmentapi.excel.dto;

import lombok.Getter;

@Getter
public class ExcelSuccessResponseDto {
    private String message;
    public ExcelSuccessResponseDto() {
        this.message = "upload success";
    }
}
