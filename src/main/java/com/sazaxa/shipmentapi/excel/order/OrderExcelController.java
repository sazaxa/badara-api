package com.sazaxa.shipmentapi.excel.order;

import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class OrderExcelController {

    private final OrderExcelService orderExcelService;

    public OrderExcelController(OrderExcelService orderExcelService) {
        this.orderExcelService = orderExcelService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/excel/order/upload")
    public ExcelSuccessResponseDto create(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("check excel order upload");
        return orderExcelService.create(file);
    }

}
