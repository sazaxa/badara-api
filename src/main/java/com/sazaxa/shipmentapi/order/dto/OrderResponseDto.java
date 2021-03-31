package com.sazaxa.shipmentapi.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.box.dto.BoxResponseDto;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.product.dto.ProductResponseDto;
import com.sazaxa.shipmentapi.recipient.Recipient;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class OrderResponseDto {
    private Long id;
    private String orderNumber;
    private String expectedOrderPrice;
    private String orderPrice;
    private String invoice;
    private String shippingCompany;
    private String adminMemo;
    private String userMemo;
    private String orderStatus;
    private String depositName;
    private List<ProductResponseDto> productResponses;
    private List<BoxResponseDto> boxResponses;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Recipient recipient;

    public static OrderResponseDto of(Order order, List<ProductResponseDto> productResponses, List<BoxResponseDto> boxResponses){
        return OrderResponseDto.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .expectedOrderPrice(order.getExpectedOrderPrice())
                .orderPrice(order.getOrderPrice())
                .invoice(order.getInvoice())
                .shippingCompany(order.getShippingCompany())
                .adminMemo(order.getAdminMemo())
                .userMemo(order.getUserMemo())
                .orderStatus(order.getOrderStatus().status)
                .depositName(order.getDepositName())
                .productResponses(productResponses)
                .boxResponses(boxResponses)
                .recipient(order.getRecipient())
                .build();
    }
}
