package com.sazaxa.shipmentapi.point.controller;

import com.sazaxa.shipmentapi.point.dto.PointConfigRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/point")
@RestController
public class PointController {

    @PostMapping("/config")
    public void configurePoint(@RequestBody PointConfigRequestDto pointConfigRequestDto){
    }
}
