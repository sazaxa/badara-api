package com.sazaxa.shipmentapi.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "zx_order")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String orderNumber;

    @Column
    private String expectedOrderPrice;

    @Column
    private String orderPrice;

    @Column
    private String invoice;

    @Column
    private String shippingCompany;

    @Column
    private String adminMemo;

    @Column
    private String userMemo;

    @Enumerated
    @Column
    private OrderStatus orderStatus;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Order(String orderNumber, String expectedOrderPrice, String orderPrice, String invoice, String shippingCompany, String adminMemo, String userMemo, OrderStatus orderStatus, Member member) {
        this.orderNumber = orderNumber;
        this.expectedOrderPrice = expectedOrderPrice;
        this.orderPrice = orderPrice;
        this.invoice = invoice;
        this.shippingCompany = shippingCompany;
        this.adminMemo = adminMemo;
        this.userMemo = userMemo;
        this.orderStatus = orderStatus;
        this.member = member;
    }
}
