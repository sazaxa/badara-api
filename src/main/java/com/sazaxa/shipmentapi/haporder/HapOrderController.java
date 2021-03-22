package com.sazaxa.shipmentapi.haporder;

import com.sazaxa.shipmentapi.haporder.dto.HapOrderResponseDto;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderSaveRequestDto;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderUpdateRequestDto;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderUpdateStatusRequestDto;
import com.sazaxa.shipmentapi.order.Order;
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

@RequestMapping("/api/v1/hapOrders")
@RestController
public class HapOrderController {

    private final HapOrderService hapOrderService;

    public HapOrderController(HapOrderService hapOrderService) {
        this.hapOrderService = hapOrderService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<HapOrderResponseDto> getAllOrders(){
        return hapOrderService.getAllOrders();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String saveOrders(
            @CurrentUser UserPrincipalCustom currentUser,
            @RequestBody List<HapOrderSaveRequestDto> request){
        hapOrderService.saveOrders(request, currentUser);
        return "success";
    }

    @GetMapping("/{id}")
    public HapOrderResponseDto getOrder(@PathVariable Long id){
        return hapOrderService.getOrder(id);
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @RequestBody HapOrderUpdateRequestDto request){
        hapOrderService.updateOrder(id, request);
        return "success";
    }

    /**
     * 결제 완료
     * @param id Order 구분하는 식별자
     * @param request 변경하기 원하는 상품의 Status
     * @return status를 변경한 상품들
     */
    @PutMapping("/payment/{id}")
    public List<Order> proceedPaymentComplete(@PathVariable Long id, @RequestBody HapOrderUpdateStatusRequestDto request){
        return hapOrderService.proceedPaymentComplete(id, request);
    }

}
