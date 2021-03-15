package com.sazaxa.shipmentapi.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class OrderResponseDto {
    private Long id;
    private String orderNumber;
    private double orderPrice;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Member member;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<Product> products;

}
