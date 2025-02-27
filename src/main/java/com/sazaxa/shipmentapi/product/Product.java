package com.sazaxa.shipmentapi.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.util.BaseEntity;
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
@Table(name = "badara_product")
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productDetail;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @Column
    private Double weight;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(targetEntity = Box.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="box_id")
    private Box box;

    @Builder
    public Product(String productDetail, Integer quantity, Double price, Double weight, Order order, Box box) {
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
        this.order = order;
        this.box = box;
    }

    public void updateProduct(String productDetail, Integer quantity, Double price, Double weight) {
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
    }
}
