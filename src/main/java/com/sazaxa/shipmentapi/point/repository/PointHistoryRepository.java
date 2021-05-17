package com.sazaxa.shipmentapi.point.repository;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    List<PointHistory> findByOrder(Order order);
    List<PointHistory> findByMember(Member member);
}
