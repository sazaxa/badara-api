package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
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
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String saveOrders(@RequestBody List<OrderSaveRequestDto> request){
        orderService.saveOrders(request);
        return "success";
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrdersById(@PathVariable Long id){
        return orderService.getOrdersById(id);
    }

    @PutMapping("/{id}")
    public String updateOrderById(@PathVariable Long id, @RequestBody OrderUpdateRequestDto request){
        orderService.updateOrderById(id, request);
        return "success";
    }

}
