package com.sazaxa.shipmentapi.faq;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class FaqRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public FaqRepositorySupport(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

}
