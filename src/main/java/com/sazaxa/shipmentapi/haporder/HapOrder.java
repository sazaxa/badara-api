//package com.sazaxa.shipmentapi.haporder;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.sazaxa.shipmentapi.member.Member;
//import com.sazaxa.shipmentapi.util.BaseEntity;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
////@ToString(exclude = {"products"})
//@Getter
//@NoArgsConstructor
//@Table(name = "zx_haporder")
//@Entity
//public class HapOrder extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String hapOrderNumber;
//
//    @Column
//    private double hapOrderPrice;
//
//    @Column
//    private double hapOrderWeight;
//
//    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer"})
//    @JoinColumn(name="member_id")
//    private Member member;
//
//    @Builder
//    public HapOrder(String hapOrderNumber, Member member) {
//        this.hapOrderNumber = hapOrderNumber;
//        this.member = member;
//    }
//
//    public void updateHapOrderPrice(double hapOrderPrice){
//        this.hapOrderPrice = hapOrderPrice;
//    }
//
//}
