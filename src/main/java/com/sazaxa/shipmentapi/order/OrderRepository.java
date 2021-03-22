package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.haporder.HapOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByHapOrder(HapOrder hapOrder);
}
