package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderStatusRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import com.sazaxa.shipmentapi.order.dto.VirtualAccountRequestDto;
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

    @GetMapping
    public List<OrderResponseDto> list(){
        return orderService.list();
    }

    @GetMapping("/{id}")
    public OrderResponseDto detail(@PathVariable Long id){
        return orderService.detail(id);
    }

    @PostMapping("/{orderNumber}")
    public OrderResponseDto detailWithOrderNumber(@PathVariable String orderNumber, @CurrentUser UserPrincipalCustom currentUser) {
        return orderService.detailWithOrderNumber(orderNumber, currentUser);
    }

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderSaveRequestDto request,
                                      @CurrentUser UserPrincipalCustom currentUser) {
        return orderService.create(request, currentUser);
    }

    @PutMapping("/{id}")
    public OrderResponseDto update(@PathVariable Long id, @RequestBody OrderUpdateRequestDto request, @CurrentUser UserPrincipalCustom currentUser){
        return orderService.update(id, request, currentUser);
    }

    @PutMapping("/status/{orderNumber}")
    public OrderResponseDto updateStatus(@PathVariable String orderNumber, @RequestBody OrderStatusRequestDto request){
        return orderService.updateStatus(orderNumber, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/virtual/done")
    public void perceiveVirtualAccount(@RequestBody VirtualAccountRequestDto request){
        orderService.perceiveVirtualAccount(request);
    }

}
