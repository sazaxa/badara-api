package com.sazaxa.shipmentapi.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public OrderRepositorySupport(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Order> getProductsByOrderId(Long id){
        QOrder order = QOrder.order;
        return queryFactory.selectFrom(order)
                .where(order.order.id.eq(id))
                .fetch();
    }

}
