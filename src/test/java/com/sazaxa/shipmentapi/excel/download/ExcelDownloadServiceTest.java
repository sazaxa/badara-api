package com.sazaxa.shipmentapi.excel.download;

import com.sazaxa.shipmentapi.order.OrderService;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ExcelDownloadServiceTest {

    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void downloadAllOrders() {
        List<OrderResponseDto> orderList = orderService.getAllOrder();
        for (OrderResponseDto order : orderList){

        }
    }
}
