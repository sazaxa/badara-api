package com.sazaxa.shipmentapi.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ProductRepositorySupport(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Product> getProductsByOrderId(Long id){
        QProduct product = QProduct.product;
        return queryFactory.selectFrom(product)
                .where(product.order.id.eq(id))
                .fetch();
    }

}
