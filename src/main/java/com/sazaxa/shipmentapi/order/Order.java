package com.sazaxa.shipmentapi.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sazaxa.shipmentapi.haporder.HapOrder;
import com.sazaxa.shipmentapi.haporder.HapOrderStatus;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "zx_order")
@Entity
public class Order extends BaseEntity {

    /*
    - zipcode 입력
    - zip
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String orderName;

    @Column
    private double width;

    @Column
    private double depth;

    @Column
    private double height;

    @Column
    private double volumeWeight;

    @Column
    private double netWeight;

    @Column
    private double expectedWeight;

    @Column
    private double shippingWeight;

    @Column
    private double expectedPrice;

    @Column
    private double shippingPrice;

    @Column
    private String koreanShippingCompany;

    @Column
    private String koreanInvoice;

    @Column
    private String abroadInvoice;

    @Column
    private String abroadShippingCompany;

    @Column
    private String recipientName;

    @Column
    private String recipientPhoneNumber;

    @Column
    private String recipientAddress;

    @Column
    private String status;

    @Column
    private String country;

    @Column
    private String userMemo;

    @Column
    private String adminMemo;

    @Column
    private double adminVolumeWeight;

    @Column
    private double adminNetWeight;

    @JsonIgnore
    @ManyToOne(targetEntity = HapOrder.class, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private HapOrder hapOrder;

    @Builder
    public Order(String orderName, double width, double depth, double height, double volumeWeight, double netWeight, double expectedPrice, double shippingPrice, String koreanShippingCompany, String koreanInvoice, String abroadInvoice, String abroadShippingCompany, String recipientName, String recipientPhoneNumber, String recipientAddress, String status, String country, String userMemo, String adminMemo, double adminNetWeight, double adminVolumeWeight, HapOrder hapOrder) {
        this.orderName = orderName;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.volumeWeight = volumeWeight;
        this.netWeight = netWeight;
        this.expectedPrice = expectedPrice;
        this.shippingPrice = shippingPrice;
        this.koreanShippingCompany = koreanShippingCompany;
        this.koreanInvoice = koreanInvoice;
        this.abroadInvoice = abroadInvoice;
        this.abroadShippingCompany = abroadShippingCompany;
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientAddress = recipientAddress;
        this.status = status;
        this.country = country;
        this.userMemo = userMemo;
        this.adminMemo = adminMemo;
        this.adminNetWeight = adminNetWeight;
        this.adminVolumeWeight = adminVolumeWeight;
        this.hapOrder = hapOrder;
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public void proceedInvoice(String koreanInvoice, String koreanShippingCompany){
        this.koreanInvoice = koreanInvoice;
        this.koreanShippingCompany = koreanShippingCompany;
        this.status = HapOrderStatus.CENTER_INCOME.status;
    }

}
