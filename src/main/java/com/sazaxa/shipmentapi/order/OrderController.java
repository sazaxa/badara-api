package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderInvoiceRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getProduct(@PathVariable Long id){
        return orderService.getProduct(id);
    }

    @PutMapping("/{id}")
    public Order updateProductInvoice(@PathVariable Long id, @RequestBody OrderInvoiceRequestDto request){
        return orderService.updateProductKoreanInvoice(id, request);
    }

}
