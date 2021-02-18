package com.sazaxa.shipmentapi.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private String koreanInvoice;
    private String koreanShippingCompany;
    private String abroadInvoice;
    private String status;
    private String orderNumber;
    private String country;
    private double orderWeight;
    private String userMemo;
    private String adminMemo;
    private double orderPrice;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Product product;

    public OrderResponseDto of(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .recipientName(order.getRecipientName())
                .recipientAddress(order.getRecipientAddress())
                .recipientPhoneNumber(order.getRecipientPhoneNumber())
                .koreanInvoice(order.getKoreanInvoice())
                .koreanShippingCompany(order.getKoreanShippingCompany())
                .abroadInvoice(order.getAbroadInvoice())
                .status(order.getStatus())
                .orderNumber(order.getOrderNumber())
                .country(order.getCountry())
                .orderWeight(order.getOrderWeight())
                .userMemo(order.getUserMemo())
                .adminMemo(order.getAdminMemo())
                .orderPrice(order.getOrderPrice())
                .product(order.getProduct())
                .build();
     }

}
