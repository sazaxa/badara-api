package com.sazaxa.shipmentapi.point.entity;

import com.sazaxa.shipmentapi.point.dto.PointConfigRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean isRegisterActive;

    @Column
    private Double registerAmount;

    @Builder
    public Point(Long id, Boolean isRegisterActive, Double registerAmount) {
        this.id = id;
        this.isRegisterActive = isRegisterActive;
        this.registerAmount = registerAmount;
    }

    public void update(PointConfigRequestDto pointConfigRequestDto) {
        this.isRegisterActive = pointConfigRequestDto.getIsRegisterActive();
        this.registerAmount = pointConfigRequestDto.getRegisterAmount();
    }
}
