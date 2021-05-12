package com.sazaxa.shipmentapi.excel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ExcelOrderListRequestDto {
    private List<String> orderNumbers;

    @Builder
    public ExcelOrderListRequestDto(List<String> orderNumbers) {
        this.orderNumbers = orderNumbers;
    }
}
