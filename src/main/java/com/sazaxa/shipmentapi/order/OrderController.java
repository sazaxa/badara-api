package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.security.CurrentUser;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public OrderResponseDto saveOrder(@Valid @RequestBody OrderSaveRequestDto request,
                                      @CurrentUser UserPrincipalCustom currentUser) {
        return orderService.saveOrder(request, currentUser);
    }

//    @GetMapping("/{id}")
//    public Order getProduct(@PathVariable Long id){
//        return orderService.getProduct(id);
//    }
//
//    @PutMapping("/{id}")
//    public Order updateProductInvoice(@PathVariable Long id, @RequestBody OrderInvoiceRequestDto request){
//        return orderService.updateProductKoreanInvoice(id, request);
//    }

}
