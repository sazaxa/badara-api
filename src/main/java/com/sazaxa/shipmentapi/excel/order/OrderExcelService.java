package com.sazaxa.shipmentapi.excel.order;

import com.sazaxa.shipmentapi.excel.ExcelExtension;
import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import com.sazaxa.shipmentapi.excel.errors.ExcelExtensionException;
import com.sazaxa.shipmentapi.excel.errors.ExcelNotFoundException;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderExcelService {

    private final OrderRepository orderRepository;

    public OrderExcelService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ExcelSuccessResponseDto create(MultipartFile file) throws IOException {
        if (file == null){
            throw new ExcelNotFoundException("엑셀파일을 추가해주세요");
        }

        Workbook workbook = createWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<Order> orderList = new ArrayList<>();

        for (Row row : sheet){

            if (row.getRowNum() == 0){
                continue;
            }

        }
        orderRepository.saveAll(orderList);

        return new ExcelSuccessResponseDto();
    }

    private Workbook createWorkbook(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equals(ExcelExtension.XLSX.getExtension())) {
            return new XSSFWorkbook(file.getInputStream());
        }
        if (extension.equals(ExcelExtension.XLS.getExtension())) {
            return new HSSFWorkbook(file.getInputStream());
        }
        throw new ExcelExtensionException(extension);
    }
}
