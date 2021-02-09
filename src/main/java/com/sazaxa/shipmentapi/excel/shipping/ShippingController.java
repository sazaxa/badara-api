package com.sazaxa.shipmentapi.excel.shipping;

import com.sazaxa.shipmentapi.excel.shipping.dto.ShippingRequestDto;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/excel/shipping")
    public String readShip(@RequestParam("file") MultipartFile file) throws IOException {

        //확장자에 따른 인스턴스 생성
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        //엑셀 데이터 가져오기
        Workbook workbook = shippingService.getKindOfWorkbook(file, extension);

        //엑셀의 첫번째 시트 가져오기
        Sheet worksheet = workbook.getSheetAt(0);
        Row firstRow = worksheet.getRow(0);

        //엑셀 시트의 Row가져오기
        int sizeRow = firstRow.getPhysicalNumberOfCells();

        //국가명 저장
        List<String> listCountry = shippingService.getCountriesName(firstRow, sizeRow);

        //DHL 배송비 저장
        List<DhlShipping> dhlShipmentList = new ArrayList<>();
        int sizeColumn = worksheet.getPhysicalNumberOfRows();

        //국가에 따른 배송비 저장
        for (int i = 1; i < sizeColumn; i++) { // 4
            Row row = worksheet.getRow(i);
            int weight = (int) row.getCell(0).getNumericCellValue();

            for (int j = 1; j < sizeRow; j++){
                double price = (double) Math.round(row.getCell(j).getNumericCellValue() * 100) / 100;
                dhlShipmentList.add(new DhlShipping(listCountry.get(j-1), weight, price));
            }
        }

        shippingService.saveDhlShipment(dhlShipmentList);

        return "success-excel-upload";
    }

    @GetMapping("/api/v1/shipping/dhl")
    public double getPrice(@RequestBody ShippingRequestDto requestDto){
        return shippingService.getPrice(requestDto);
    }


}
