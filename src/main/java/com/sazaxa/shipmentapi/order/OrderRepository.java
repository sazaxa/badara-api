package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMember(Member member);
    Optional<Order> findByOrderNumber(String orderNumber);
}
