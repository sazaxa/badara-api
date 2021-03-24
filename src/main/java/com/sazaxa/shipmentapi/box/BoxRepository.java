package com.sazaxa.shipmentapi.box;

import com.sazaxa.shipmentapi.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoxRepository extends JpaRepository<Box, Long> {
    List<Box> findAllByOrder(Order order);
}
