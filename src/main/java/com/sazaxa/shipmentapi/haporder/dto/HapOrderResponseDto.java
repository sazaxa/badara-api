package com.sazaxa.shipmentapi.haporder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.order.Order;
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
public class HapOrderResponseDto {
    private Long id;
    private String hapOrderNumber;
    private double hapOrderPrice;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Member member;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<Order> orders;

}
