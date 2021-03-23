package com.sazaxa.shipmentapi.box;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderStatus;
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
    
}
