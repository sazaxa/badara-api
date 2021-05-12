package com.sazaxa.shipmentapi.point.service;

import com.sazaxa.shipmentapi.point.dto.PointConfigRequestDto;
import com.sazaxa.shipmentapi.point.dto.PointConfigResponseDto;
import com.sazaxa.shipmentapi.point.entity.Point;
import com.sazaxa.shipmentapi.point.errors.PointNotFoundException;
import com.sazaxa.shipmentapi.point.repository.PointRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PointService {
    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public PointConfigResponseDto configurePoint(PointConfigRequestDto pointConfigRequestDto) {
        Point point = pointRepository.findById(1L).orElseThrow(()-> new PointNotFoundException("no point id : " + "1"));
        point.update(pointConfigRequestDto);
        return PointConfigResponseDto.of(point);
    }

    public Point getPointInfo(){
        return pointRepository.findById(1L).orElseThrow(()-> new PointNotFoundException("no point id : " + "1"));
    }
}
