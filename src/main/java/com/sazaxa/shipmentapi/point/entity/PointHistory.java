package com.sazaxa.shipmentapi.point.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Table(name = "badara_point_history")
@Entity
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double balance;
    
    @Column
    private Double deposit;

    @Column
    private Double withdraw;

    @Column
    private String section;

    @Column
    private String detail;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="order_id")
    private Order order;

    @Builder
    public PointHistory(Long id, Double balance, Double deposit, Double withdraw, String section, String detail, Member member, Order order) {
        this.id = id;
        this.balance = balance;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.section = section;
        this.detail = detail;
        this.member = member;
        this.order = order;
    }
}
