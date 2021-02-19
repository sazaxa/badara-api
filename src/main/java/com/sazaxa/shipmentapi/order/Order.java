package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = {"products"})
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
    private double orderPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Product> products;

    @Builder
    public Order(String orderNumber, List<Product> products) {
        this.orderNumber = orderNumber;
        this.products = products;
    }

}
