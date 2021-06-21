package com.sazaxa.shipmentapi.excel.download;

import com.sazaxa.shipmentapi.excel.dto.ExcelOrderListRequestDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class ExcelDownloadController {

    private final ExcelDownloadService excelDownloadService;

    public ExcelDownloadController(ExcelDownloadService excelDownloadService) {
        this.excelDownloadService = excelDownloadService;
    }

    @GetMapping("/excel/download/orders")
    public ResponseEntity<InputStreamResource> downloadAllOrders() throws IOException {
        ByteArrayInputStream inputStream = excelDownloadService.downloadAllOrders();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=orders.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

    @PostMapping("/excel/download/orders")
    public ResponseEntity<InputStreamResource> downloadSelectedOrders(@RequestBody ExcelOrderListRequestDto excelOrderListRequestDto) throws IOException {
        ByteArrayInputStream inputStream = excelDownloadService.downloadSelectedOrders(excelOrderListRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=orders.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

}
