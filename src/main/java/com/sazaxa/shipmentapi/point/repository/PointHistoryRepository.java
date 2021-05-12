package com.sazaxa.shipmentapi.point.repository;

import com.sazaxa.shipmentapi.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}
