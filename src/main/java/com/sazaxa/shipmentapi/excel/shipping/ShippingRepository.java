package com.sazaxa.shipmentapi.excel.shipping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<DhlShipping, Long> {
}
