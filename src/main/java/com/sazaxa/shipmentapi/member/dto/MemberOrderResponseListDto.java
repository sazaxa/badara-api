package com.sazaxa.shipmentapi.member.dto;

import com.sazaxa.shipmentapi.member.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MemberOrderResponseListDto {
    private Member member;
    private List<MemberOrderResponseDto> hapOrders;
}
