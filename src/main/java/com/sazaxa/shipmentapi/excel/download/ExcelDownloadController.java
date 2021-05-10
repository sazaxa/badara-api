package com.sazaxa.shipmentapi.excel.download;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelDownloadController {

    private final ExcelDownloadService excelDownloadService;

    public ExcelDownloadController(ExcelDownloadService excelDownloadService) {
        this.excelDownloadService = excelDownloadService;
    }

    @GetMapping("/excel/download/orders")
    public void downloadAllOrders(){
        excelDownloadService.downloadAllOrders();
    }

}
