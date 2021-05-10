package com.sazaxa.shipmentapi.excel.download;

import com.sazaxa.shipmentapi.order.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;

@Transactional
@Service
public class ExcelDownloadService {

    private final OrderService orderService;

    public ExcelDownloadService(OrderService orderService) {
        this.orderService = orderService;
    }

    public ByteArrayInputStream downloadAllOrders() {
        orderService.getAllOrder();
        return null;
    }
}
