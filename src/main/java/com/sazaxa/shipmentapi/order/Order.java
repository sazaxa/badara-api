package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder // DTO -> ENTITY 변환시 필요
@AllArgsConstructor // DTO -> ENTITY 변환시 필요
@NoArgsConstructor
@Table(name = "zx_order")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String recipientName;

    @Column
    private String recipientPhoneNumber;

    @Column
    private String recipientAddress;

    @Column
    private String koreanInvoice;

    @Column
    private String abroadInvoice;

    @Column
    private String status;

    @Column
    private String orderNumber;

    @Column
    private String country;

    @Column
    private double orderWeight;

    @Column
    private String memo;

    @Column
    private double orderPrice;

    @Column
    private double koreanShippingCompany;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    /**
     * 최초 Order생성
     * */
    @Builder
    public Order(String recipientName, String recipientPhoneNumber, String recipientAddress, String koreanInvoice, String status, String orderNumber, String country, Product product) {
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientAddress = recipientAddress;
        this.koreanInvoice = koreanInvoice;
        this.status = status;
        this.orderNumber = orderNumber;
        this.country = country;
        this.product = product;
    }

    /**
     * 주문서 상태 변경
     * @param status 주문상태
     * @Author wenodev
     */
    public void updateOrderStatus(String status){
        this.status = status;
    }

}
