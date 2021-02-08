package com.sazaxa.shipmentapi.excel.shipping;

import com.sazaxa.shipmentapi.excel.ExcelExtensionException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sazaxa.shipmentapi.excel.ExcelExtension.XLS;
import static com.sazaxa.shipmentapi.excel.ExcelExtension.XLSX;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public void saveDhlShipment(List<DhlShipping> dhlShipmentList){
        shippingRepository.saveAll(dhlShipmentList);
    }

    //확장자 별 인스턴스 생성
    public Workbook getKindOfWorkbook(MultipartFile file, String extension) throws IOException {
        if (extension.equals(XLSX.getExtension())){
            return new XSSFWorkbook(file.getInputStream());
        }
        if (extension.equals(XLS.getExtension())){
            return new HSSFWorkbook(file.getInputStream());
        }
        throw new ExcelExtensionException("xls, xlsx 확장자를 사용해주세요");
    }

    //국가명 얻기
    List<String> getCountriesName(Row row, int sizeRow){
        List<String> listCountry = new ArrayList<>();
        for (int i=1; i<sizeRow; i++){
            listCountry.add(row.getCell(i).getStringCellValue());
        }
        return listCountry;
    }

}
