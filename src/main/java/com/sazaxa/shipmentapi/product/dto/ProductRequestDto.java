package com.sazaxa.shipmentapi.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductRequestDto {
    private Long id;
    private String productDetail;
    private Integer quantity;
    private Double price;
    private Double weight;

    @Builder
    public ProductRequestDto(Long id, String productDetail, Integer quantity, Double price, Double weight) {
        this.id = id;
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
    }
}
