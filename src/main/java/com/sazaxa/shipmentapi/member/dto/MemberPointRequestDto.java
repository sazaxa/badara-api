package com.sazaxa.shipmentapi.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberPointRequestDto {
    private Double point;
    private String detail;
}
