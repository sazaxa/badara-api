package com.sazaxa.shipmentapi.excel.shipping;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRepository extends JpaRepository<DhlShipping, Long> {
    List<DhlShipping> findAllByCountry(String country);
    boolean existsByCountry(String country);
}
