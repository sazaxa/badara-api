package com.sazaxa.shipmentapi.excel.shipping;

import com.sazaxa.shipmentapi.excel.shipping.exception.CountryNotFoundException;
import com.sazaxa.shipmentapi.excel.shipping.exception.ExcelExtensionException;
import com.sazaxa.shipmentapi.excel.shipping.dto.ShippingRequestDto;
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

    private final double MAX_WEIGHT = 30000;
    private final ShippingRepository shippingRepository;
    private final ShippingRepositorySupport shippingRepositorySupport;

    public ShippingService(ShippingRepository shippingRepository, ShippingRepositorySupport shippingRepositorySupport) {
        this.shippingRepository = shippingRepository;
        this.shippingRepositorySupport = shippingRepositorySupport;
    }

    public void saveDhlShipment(List<DhlShipping> dhlShipmentList){
        shippingRepository.deleteAll();
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
            listCountry.add(row.getCell(i).getStringCellValue().replace(" ", ""));
        }
        return listCountry;
    }

    //국가와 무게에 따른 배송비 측정
    public double getPrice(ShippingRequestDto requestDto){
        String country = requestDto.getCountry();
        double weight = 500 * Math.ceil(requestDto.getWeight() / 500);

        //최대값 확인
        if (weight > MAX_WEIGHT){ weight = MAX_WEIGHT; }

        List<DhlShipping> faqs = shippingRepositorySupport.getPrice(country, weight);
        return faqs.get(0).getPrice();
    }

    public List<DhlShipping> getCountriesInfo() {
        return shippingRepositorySupport.getCountriesInfo();
    }

    public List<DhlShipping> getPriceByCountryWeight(ShippingRequestDto requestDto) {
        if (shippingRepository.existsByCountry(requestDto.getCountry())){
            return shippingRepository.findAllByCountry(requestDto.getCountry());
        }
        throw new CountryNotFoundException("no country : " + requestDto.getCountry());
    }
}
