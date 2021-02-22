package com.sazaxa.shipmentapi.product;

import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "zx_product")
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

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

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @Builder
    public Product(String productName, double width, double depth, double height, double volumeWeight, double netWeight, double expectedPrice, double shippingPrice, String koreanShippingCompany, String koreanInvoice, String abroadInvoice, String abroadShippingCompany, String recipientName, String recipientPhoneNumber, String recipientAddress, String status, String country, String userMemo, String adminMemo, Order order) {
        this.productName = productName;
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
        this.order = order;
    }

}
