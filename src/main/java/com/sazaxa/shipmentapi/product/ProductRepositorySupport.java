package com.sazaxa.shipmentapi.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ProductRepositorySupport(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

}
