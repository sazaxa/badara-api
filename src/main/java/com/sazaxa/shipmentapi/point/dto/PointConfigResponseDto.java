package com.sazaxa.shipmentapi.point.dto;

import com.sazaxa.shipmentapi.point.entity.Point;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PointConfigResponseDto {
    private Long id;
    private Boolean isRegisterActive;
    private Double RegisterAmount;

    @Builder
    public PointConfigResponseDto(Long id, Boolean isRegisterActive, Double registerAmount) {
        this.id = id;
        this.isRegisterActive = isRegisterActive;
        RegisterAmount = registerAmount;
    }

    public static PointConfigResponseDto of(Point point) {
        return PointConfigResponseDto.builder()
                .id(point.getId())
                .isRegisterActive(point.getIsRegisterActive())
                .registerAmount(point.getRegisterAmount())
                .build();
    }
}
