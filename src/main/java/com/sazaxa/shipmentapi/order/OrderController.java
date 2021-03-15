package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import com.sazaxa.shipmentapi.security.CurrentUser;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public String saveOrders(
            @CurrentUser UserPrincipalCustom currentUser,
            @RequestBody List<OrderSaveRequestDto> request){
        orderService.saveOrders(request, currentUser);
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
