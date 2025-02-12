package com.sazaxa.shipmentapi.box;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderStatus;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "badara_box")
@Entity
public class Box extends BaseEntity {

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
    private Boolean isVolumeWeight;

    @Column
    private Double resultWeight;

    @Column
    private String userMemo;

    @Column
    private String type;

    @Column
    private OrderStatus koreanShippingStatus;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="order_id")
    private Order order;

    @Builder
    public Box(Long id,Double expectedWidth, Double expectedDepth, Double expectedHeight, Double expectedVolumeWeight,
               Double expectedNetWeight, Double expectedPrice, Double width, Double depth, Double height, Double volumeWeight,
               Double netWeight, Double price, String koreanInvoice, Double resultWeight, String koreanShippingCompany,
               OrderStatus koreanShippingStatus, String userMemo, String type,  Order order) {
        this.id = id;
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
        this.resultWeight = resultWeight;
        this.koreanShippingCompany = koreanShippingCompany;
        this.koreanShippingStatus = koreanShippingStatus;
        this.userMemo = userMemo;
        this.type = type;
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

    public void updateBox(Double expectedWidth, Double expectedDepth, Double expectedHeight, Double expectedVolumeWeight, Double expectedNetWeight,
                          Double expectedPrice, Double width, Double depth, Double height, Double volumeWeight, Double netWeight, Double price,
                          String koreanInvoice, String koreanShippingCompany, Boolean isVolumeWeight, Double resultWeight, String userMemo, String type,
                          OrderStatus koreanShippingStatus) {
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
        this.isVolumeWeight = isVolumeWeight;
        this.resultWeight = resultWeight;
        this.userMemo = userMemo;
        this.type = type;
        this.koreanShippingStatus = koreanShippingStatus;
    }

}
