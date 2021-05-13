package com.sazaxa.shipmentapi.point.repository;

import com.sazaxa.shipmentapi.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
