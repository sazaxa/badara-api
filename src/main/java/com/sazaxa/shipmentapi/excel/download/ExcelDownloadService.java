package com.sazaxa.shipmentapi.excel.download;

import com.sazaxa.shipmentapi.excel.dto.ExcelOrderListRequestDto;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderRepository;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.recipient.Recipient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ExcelDownloadService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ExcelDownloadService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public ByteArrayInputStream downloadAllOrders() throws IOException {
        List<Order> orderList = orderRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("sienna");

        // Row for Header
        Row headerRow = sheet.createRow(0);

        // rowCount for Body
        Integer columnCount = 1;

        for (Order order : orderList) {
            // 필요한 정보 불러오기
            List<Product> productList = productRepository.findAllByOrder(order);
            Recipient recipient = order.getRecipient();

            List<String> headerName = makeHeaderName(productList);
            for (int i = 0; i < headerName.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headerName.get(i));
            }

            List<String> excelDataList = makeExcelDataList(order, productList, recipient);

            Row bodyRow = sheet.createRow(columnCount);
            //body 만들기
            for (int i = 0; i < headerName.size(); i++) {
                bodyRow.createCell(i).setCellValue(excelDataList.get(i));
            }
            columnCount++;
        }
        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }


    public ByteArrayInputStream downloadSelectedOrders(ExcelOrderListRequestDto excelOrderListRequestDto) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("sienna의 바다라 주문정보");

        // Row for Header
        Row headerRow = sheet.createRow(0);

        // rowCount for Body
        Integer columnCount = 1;

        for (String orderNumber : excelOrderListRequestDto.getOrderNumbers()){

            Order order = orderRepository.findByOrderNumber(orderNumber).orElseThrow(()->new OrderNotFoundException("no orderNumber : " + orderNumber));

            // 필요한 정보 불러오기
            List<Product> productList = productRepository.findAllByOrder(order);
            Recipient recipient = order.getRecipient();

            //header 만들기
            List<String> headerName = makeHeaderName(productList);
            for (int i=0; i < headerName.size(); i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headerName.get(i));
            }

            // body에 필요한 값들 String으로 만들기
            List<String> excelDataList = makeExcelDataList(order, productList, recipient);

            Row bodyRow = sheet.createRow(columnCount);
            //body 만들기
            for (int i = 0; i < headerName.size(); i++) {
                bodyRow.createCell(i).setCellValue(excelDataList.get(i));
            }
            columnCount++;
        }
        return new ByteArrayInputStream(out.toByteArray());
    }


    private List<String> makeExcelDataList(Order order, List<Product> productList, Recipient recipient) {
        List<String> excelDataList = new ArrayList<>();

        excelDataList.add(makeDateFormat(order.getCreatedDate()));
        excelDataList.add(recipient.getName());
        excelDataList.add(order.getOrderNumber());
        excelDataList.add(recipient.getAddress1());
        excelDataList.add(recipient.getCity());
        excelDataList.add(recipient.getState());
        excelDataList.add(recipient.getCountry());
        excelDataList.add(recipient.getZipcode());
        excelDataList.add(order.getCardType());
        excelDataList.add(String.valueOf(order.getOrderPrice()));
        for (Product product : productList){
            excelDataList.add(product.getProductDetail());
            excelDataList.add(String.valueOf(product.getQuantity()));
            excelDataList.add(String.valueOf(product.getPrice()));
        }

        return excelDataList;
    }

    private List<String> makeHeaderName(List<Product> product) {
        List<String> excelHeader = new ArrayList<>();
        excelHeader.add("날짜");
        excelHeader.add("주문자명");
        excelHeader.add("주문번호");
        excelHeader.add("상세주소");
        excelHeader.add("도시");
        excelHeader.add("주/도");
        excelHeader.add("국가");
        excelHeader.add("우편번호");
        excelHeader.add("결제방법");
        excelHeader.add("결제금액");

        for (int i=1; i <= product.size(); i++){
            excelHeader.add("상품" + i + "-이름");
            excelHeader.add("상품" + i + "-개수");
            excelHeader.add("상품" + i + "-금액");
        }

        return excelHeader;
    }

    public String makeDateFormat(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
