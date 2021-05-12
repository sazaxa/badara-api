package com.sazaxa.shipmentapi.point.controller;

import com.sazaxa.shipmentapi.point.dto.PointConfigRequestDto;
import com.sazaxa.shipmentapi.point.service.PointService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
1. 저장된 point 설정값 return하는 controller 필요
 */
@RequestMapping("/api/v1/point")
@RestController
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping("/config")
    public void configurePoint(@RequestBody PointConfigRequestDto pointConfigRequestDto){
    }
}
