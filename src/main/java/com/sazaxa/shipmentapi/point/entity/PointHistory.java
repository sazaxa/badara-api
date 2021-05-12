package com.sazaxa.shipmentapi.point.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double balance;
    
    @Column
    private Double deposit;

    @Column
    private Double withdraw;

    @Column
    private String detail;

    @Builder
    public PointHistory(Long id, Double balance, Double deposit, Double withdraw, String detail) {
        this.id = id;
        this.balance = balance;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.detail = detail;
    }
}
