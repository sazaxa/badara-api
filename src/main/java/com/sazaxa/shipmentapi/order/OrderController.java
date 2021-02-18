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
    public String saveOrders(@RequestBody List<OrderSaveRequestDto> orderSaveRequestDto){
        orderService.saveOrders(orderSaveRequestDto);
        return "success";
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrdersById(@PathVariable Long id){
        return orderService.getOrdersById(id);
    }

    @PutMapping("/{id}")
    public String updateOrderById(@PathVariable Long id, @RequestBody OrderUpdateRequestDto orderUpdateRequestDto){
        orderService.updateOrderById(id, orderUpdateRequestDto);
        return "success";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public String deleteOrdersById(@PathVariable Long id){
        orderService.deleteOrdersById(id);
        return "success";
    }

}
