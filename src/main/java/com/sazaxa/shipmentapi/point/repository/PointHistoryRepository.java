package com.sazaxa.shipmentapi.point.repository;

import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    Optional<PointHistory> findByOrder(Order order);
}
