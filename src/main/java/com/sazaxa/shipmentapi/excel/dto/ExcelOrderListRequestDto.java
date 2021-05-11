package com.sazaxa.shipmentapi.excel.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ExcelOrderListRequestDto {
    private List<String> orderNumbers;
}
