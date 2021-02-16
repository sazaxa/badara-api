package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public void getOrders(){
        orderService.getOrders();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void saveOrders(@RequestBody List<OrderSaveRequestDto> orderSaveRequestDto){
        orderService.saveOrders(orderSaveRequestDto);
    }


}
