package com.sazaxa.shipmentapi.product.dto;


import com.sazaxa.shipmentapi.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ProductResponseDto {
    private Long id;
    private String productDetail;
    private Integer quantity;
    private Double price;
    private Double weight;

    public static ProductResponseDto of(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .productDetail(product.getProductDetail())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .weight(product.getWeight())
                .build();
    }

    public static List<ProductResponseDto> ofList(List<Product> products) {
        List<ProductResponseDto> productResponses = new ArrayList<>();
        for (Product product : products){
            productResponses.add(ProductResponseDto.of(product));
        }
        return productResponses;
    }

//    public static List<Product> ofList(List<Product> products) {
//        List<ProductResponseDto> productResponses = new ArrayList<>();
//        for (Product product : products){
//            productResponses.add(ProductResponseDto.of(product));
//        }
//        return productResponses;
//    }

}
