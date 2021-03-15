package com.sazaxa.shipmentapi.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class MemberOrderResponseDto {
    private Long id;
    private String orderNumber;
    private double orderPrice;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<Product> products;
}
