package com.sazaxa.shipmentapi.excel.shipping;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShippingRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ShippingRepositorySupport(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<DhlShipping> getPrice(String country, int weight){
        QDhlShipping dhlShipping = QDhlShipping.dhlShipping;

        List<DhlShipping> list = queryFactory.selectFrom(dhlShipping)
                .where(dhlShipping.country.eq(country))
                .fetch();

        for (int i=0; i < list.size(); i++){
            System.out.println("============");
            System.out.println(list.get(i).getCountry());
        }

        return queryFactory
                .selectFrom(dhlShipping)
                .where(dhlShipping.country.eq(country),dhlShipping.weight.eq(weight))
                .fetch();
    }

}
