package com.sazaxa.shipmentapi.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.product.Product;
import lombok.*;

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
    private List<Product> products;
}
