package com.sazaxa.shipmentapi.point.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PointConfigRequestDto {
    private Long id;
    private Boolean isRegisterActive;
    private Double registerAmount;

    @Builder
    public PointConfigRequestDto(Long id, Boolean isRegisterActive, Double registerAmount) {
        this.id = id;
        this.isRegisterActive = isRegisterActive;
        this.registerAmount = registerAmount;
    }
}
