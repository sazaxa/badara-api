package com.sazaxa.shipmentapi.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class OrderExcelData {
    private String date;
    private String name;
    private String orderNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String cardType;
    private String price;
    private List<ProductExcelData> productExcelDataList;
}
