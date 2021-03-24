package com.sazaxa.shipmentapi.box;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderStatus;
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

@Getter
@NoArgsConstructor
@Table(name = "zx_box")
@Entity
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double expectedWidth;

    @Column
    private Double expectedDepth;

    @Column
    private Double expectedHeight;

    @Column
    private Double expectedVolumeWeight;

    @Column
    private Double expectedNetWeight;

    @Column
    private Double expectedPrice;

    @Column
    private Double width;

    @Column
    private Double depth;

    @Column
    private Double height;

    @Column
    private Double volumeWeight;

    @Column
    private Double netWeight;

    @Column
    private Double price;

    @Column
    private String koreanInvoice;

    @Column
    private String koreanShippingCompany;

    @Column
    private OrderStatus koreanShippingStatus;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="order_id")
    private Order order;

    @Builder
    public Box(Double expectedWidth, Double expectedDepth, Double expectedHeight, Double expectedVolumeWeight, Double expectedNetWeight, Double expectedPrice, Double width, Double depth, Double height, Double volumeWeight, Double netWeight, Double price, String koreanInvoice, String koreanShippingCompany, OrderStatus koreanShippingStatus, Order order) {
        this.expectedWidth = expectedWidth;
        this.expectedDepth = expectedDepth;
        this.expectedHeight = expectedHeight;
        this.expectedVolumeWeight = expectedVolumeWeight;
        this.expectedNetWeight = expectedNetWeight;
        this.expectedPrice = expectedPrice;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.volumeWeight = volumeWeight;
        this.netWeight = netWeight;
        this.price = price;
        this.koreanInvoice = koreanInvoice;
        this.koreanShippingCompany = koreanShippingCompany;
        this.koreanShippingStatus = koreanShippingStatus;
        this.order = order;
    }

    public void updateKoreanShippingStatus(OrderStatus koreanShippingStatus){
        this.koreanShippingStatus = koreanShippingStatus;
    }

    public void updateCenterIncome(String koreanInvoice, String koreanShippingCompany) {
        this.koreanInvoice = koreanInvoice;
        this.koreanShippingCompany = koreanShippingCompany;
        this.koreanShippingStatus = OrderStatus.CENTER_INCOME;
    }
}
