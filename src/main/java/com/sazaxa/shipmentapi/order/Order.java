package com.sazaxa.shipmentapi.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.recipient.Recipient;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "badara_order")
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
    private Double extraPrice;

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

    @Column
    private String cardType;

    @Column
    private String cardCompany;

    @Column
    private String cardOwnerType;

    @Column
    private String paymentKey;

    @Column
    private LocalDateTime cardRequestedDate;

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
    public Order(Long id, String orderNumber, String expectedOrderPrice, Double orderPrice, Double extraPrice,
                 String invoice, String shippingCompany, String adminMemo, String userMemo,
                 OrderStatus orderStatus, Member member, Recipient recipient, String depositName, String cardType, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.expectedOrderPrice = expectedOrderPrice;
        this.orderPrice = orderPrice;
        this.extraPrice = extraPrice;
        this.invoice = invoice;
        this.shippingCompany = shippingCompany;
        this.adminMemo = adminMemo;
        this.userMemo = userMemo;
        this.orderStatus = orderStatus;
        this.member = member;
        this.recipient = recipient;
        this.depositName = depositName;
        this.cardType = cardType;
        super.setCreatedDate(createdDate);
        super.setModifiedDate(modifiedDate);
    }

    public void updateOrder(Double extraPrice, Double orderPrice, String invoice, String shippingCompany, String adminMemo, OrderStatus orderStatus) {
        this.extraPrice = extraPrice;
        this.orderPrice = orderPrice;
        this.invoice = invoice;
        this.shippingCompany = shippingCompany;
        this.adminMemo = adminMemo;
        this.orderStatus = orderStatus;
    }

    public void updateOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public void updateOrderCardType(String cardType) {
        this.cardType = cardType;
    }


    public void updateDepositName(String depositName) {
        this.depositName = depositName;
    }

    public void updateOrderPayment(String cardType, String cardCompany, String cardOwnerType, String paymentKey, LocalDateTime cardRequestedDate) {
        this.cardType = cardType;
        this.cardCompany = cardCompany;
        this.cardOwnerType = cardOwnerType;
        this.paymentKey = paymentKey;
        this.cardRequestedDate = cardRequestedDate;
    }


}
