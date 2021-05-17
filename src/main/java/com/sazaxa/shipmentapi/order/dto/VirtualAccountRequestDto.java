package com.sazaxa.shipmentapi.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VirtualAccountRequestDto {
    private String secret;
    private String status;
    private String orderId;

    @Builder
    public VirtualAccountRequestDto(String secret, String status, String orderId) {
        this.secret = secret;
        this.status = status;
        this.orderId = orderId;
    }
}
