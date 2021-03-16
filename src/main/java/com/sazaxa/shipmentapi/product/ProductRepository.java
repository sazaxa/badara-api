package com.sazaxa.shipmentapi.product;

import com.sazaxa.shipmentapi.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrder(Order order);
}
