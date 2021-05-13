package com.sazaxa.shipmentapi.notice;

import com.sazaxa.shipmentapi.order.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "badara_notice")
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

    @Builder
    public Notice(Long id, String title, String content, OrderStatus orderStatus) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.orderStatus = orderStatus;
    }

}
