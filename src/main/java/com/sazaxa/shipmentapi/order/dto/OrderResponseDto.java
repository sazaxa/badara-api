package com.sazaxa.shipmentapi.order.dto;

import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private String koreanInvoice;
    private String abroadInvoice;
    private String status;
    private String orderNumber;
    private String country;
    private double orderWeight;
    private String memo;
    private double orderPrice;
    private Product product;

    public Long getId() {
        return id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getKoreanInvoice() {
        return koreanInvoice;
    }

    public String getAbroadInvoice() {
        return abroadInvoice;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCountry() {
        return country;
    }

    public double getOrderWeight() {
        return orderWeight;
    }

    public String getMemo() {
        return memo;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public Product getProduct() {
        return product;
    }

    public OrderResponseDto of(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .recipientName(order.getRecipientName())
                .recipientAddress(order.getRecipientAddress())
                .koreanInvoice(order.getKoreanInvoice())
                .abroadInvoice(order.getAbroadInvoice())
                .status(order.getStatus())
                .orderNumber(order.getOrderNumber())
                .country(order.getCountry())
                .orderWeight(order.getOrderWeight())
                .memo(order.getMemo())
                .orderPrice(order.getOrderPrice())
                .product(order.getProduct())
                .build();
     }


//    public Order toEntity(){
//        return Order.builder()
//                .id(id)
//                .recipientName(recipientName)
//                .recipientAddress(recipientAddress)
//                .koreanInvoice(koreanInvoice)
//                .abroadInvoice(abroadInvoice)
//                .status(status)
//                .orderNumber(orderNumber)
//                .country(country)
//                .orderWeight(orderWeight)
//                .memo(memo)
//                .orderPrice(orderPrice)
//                .product(product)
//                .build();
//    }
}
