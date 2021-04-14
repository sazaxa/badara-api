package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderPaymentRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import com.sazaxa.shipmentapi.security.CurrentUser;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @PostMapping
    public OrderResponseDto saveOrder(@RequestBody OrderSaveRequestDto request,
                                      @CurrentUser UserPrincipalCustom currentUser) {
        return orderService.saveOrder(request, currentUser);
    }

    @PutMapping("/{id}")
    public OrderResponseDto updateOrder(@PathVariable Long id, @RequestBody OrderUpdateRequestDto request){
        return orderService.updateOrder(id, request);
    }

    @PutMapping("/status/{orderNumber}")
    public OrderResponseDto updateStatus(@PathVariable String orderNumber, @RequestBody OrderPaymentRequestDto request){
        return orderService.updateStatus(orderNumber, request);
    }

//    @PutMapping("/cancel/{id}")
//    public OrderResponseDto cancelOrder(@PathVariable Long id){
//        return orderService.cancelOrder(id);
//    }

}
