package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.product.ProductRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ProductRepository productRepository;

}
