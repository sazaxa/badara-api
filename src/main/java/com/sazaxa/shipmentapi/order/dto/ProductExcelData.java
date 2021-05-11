package com.sazaxa.shipmentapi.order.dto;

import com.sazaxa.shipmentapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class ProductExcelData {
    private String name;
    private String quantity;
    private String price;

    public static List<ProductExcelData> ofList(List<Product> productList){
        List<ProductExcelData> returnData = new ArrayList<>();
        for (Product product : productList){
            returnData.add(ProductExcelData.builder()
                    .name(product.getProductDetail())
                    .quantity(String.valueOf(product.getQuantity()))
                    .price(String.valueOf(product.getPrice()))
                    .build());
        }
        return returnData;
    }
}
