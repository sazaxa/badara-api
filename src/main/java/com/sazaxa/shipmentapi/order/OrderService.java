package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(OrderSaveRequestDto request, UserPrincipalCustom currentUser) {




        return null;
    }

//    public Order getProduct(Long id) {
//        return orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no product id : " + id));
//    }
//
//    public Order updateProductKoreanInvoice(Long id, OrderInvoiceRequestDto request) {
//        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no product id : " + id));
//        order.proceedInvoice(request.getInvoice(), request.getKoreanShippingCompany());
//        return order;
//    }
}
