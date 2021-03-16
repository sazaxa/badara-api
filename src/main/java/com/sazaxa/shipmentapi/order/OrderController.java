package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateStatusRequestDto;
import com.sazaxa.shipmentapi.product.Product;
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
    public List<OrderResponseDto> getAllOrders(){
        return orderService.getAllOrders();
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
    public OrderResponseDto getOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @RequestBody OrderUpdateRequestDto request){
        orderService.updateOrder(id, request);
        return "success";
    }

    /**
     * 결제 완료
     * @param id Order 구분하는 식별자
     * @param request 변경하기 원하는 상품의 Status
     * @return status를 변경한 상품들
     */
    @PutMapping("/{id}")
    public List<Product> proceedPaymentComplete(@PathVariable Long id, @RequestBody OrderUpdateStatusRequestDto request){
        return orderService.proceedPaymentComplete(id, request);
    }

}
