package com.sazaxa.shipmentapi.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.recipient.Recipient;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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
    private Double orderPrice;

    @Column
    private String invoice;

    @Column
    private String shippingCompany;

    @Column
    private String adminMemo;

    @Column
    private String userMemo;

    @Column
    private String depositName;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

        @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(targetEntity = Recipient.class, fetch = FetchType.LAZY)
    @JoinColumn(name="recipient_id")
    private Recipient recipient;

    @Builder
    public Order(Long id, String orderNumber, String expectedOrderPrice, Double orderPrice,
                 String invoice, String shippingCompany, String adminMemo, String userMemo,
                 OrderStatus orderStatus, Member member, Recipient recipient, String depositName) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.expectedOrderPrice = expectedOrderPrice;
        this.orderPrice = orderPrice;
        this.invoice = invoice;
        this.shippingCompany = shippingCompany;
        this.adminMemo = adminMemo;
        this.userMemo = userMemo;
        this.orderStatus = orderStatus;
        this.member = member;
        this.recipient = recipient;
        this.depositName = depositName;
    }

    public void updateOrder(Double orderPrice, String invoice, String shippingCompany, String adminMemo, OrderStatus orderStatus) {
        this.orderPrice = orderPrice;
        this.invoice = invoice;
        this.shippingCompany = shippingCompany;
        this.adminMemo = adminMemo;
        this.orderStatus = orderStatus;
    }

    public void updateOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public void updateDepositName(String depositName) {
        this.depositName = depositName;
    }
}
