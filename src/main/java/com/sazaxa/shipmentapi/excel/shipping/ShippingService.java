package com.sazaxa.shipmentapi.excel.shipping;

import com.sazaxa.shipmentapi.excel.errors.CountryNotFoundException;
import com.sazaxa.shipmentapi.excel.errors.ExcelExtensionException;
import com.sazaxa.shipmentapi.excel.shipping.dto.ShippingRequestDto;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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

    private final double MAX_WEIGHT = 30;
    private final ShippingRepository shippingRepository;
    private final ShippingRepositorySupport shippingRepositorySupport;

    public ShippingService(ShippingRepository shippingRepository, ShippingRepositorySupport shippingRepositorySupport) {
        this.shippingRepository = shippingRepository;
        this.shippingRepositorySupport = shippingRepositorySupport;
    }

    public void uploadShippingFee(MultipartFile file) throws IOException {
        //확장자에 따른 인스턴스 생성
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        //엑셀 데이터 가져오기
        Workbook workbook = getKindOfWorkbook(file, extension);

        //엑셀의 첫번째 시트 가져오기
        Sheet worksheet = workbook.getSheetAt(0);
        Row firstRow = worksheet.getRow(0);

        //엑셀 시트의 Row가져오기
        int sizeRow = firstRow.getPhysicalNumberOfCells();

        //국가명 저장
        List<String> listCountry = getCountriesName(firstRow, sizeRow);

        //DHL 배송비 저장
        List<DhlShipping> dhlShipmentList = new ArrayList<>();
        int sizeColumn = worksheet.getPhysicalNumberOfRows();

        //국가에 따른 배송비 저장
        for (int i = 1; i < sizeColumn; i++) {
            Row row = worksheet.getRow(i);
            double weight = row.getCell(0).getNumericCellValue();

            for (int j = 1; j < sizeRow; j++){
                double price = (double) Math.round(row.getCell(j).getNumericCellValue() * 100) / 100;
                dhlShipmentList.add(new DhlShipping(listCountry.get(j-1), weight, price));
            }
        }
        saveDhlShipment(dhlShipmentList);
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
    public List<String> getCountriesName(Row row, int sizeRow){
        List<String> listCountry = new ArrayList<>();
        for (int i=1; i<sizeRow; i++){
            listCountry.add(row.getCell(i).getStringCellValue().replace(" ", ""));
        }
        return listCountry;
    }

    //국가와 무게에 따른 배송비 측정
    public double getPrice(ShippingRequestDto requestDto){

        String country = requestDto.getCountry();
        double weight = 0.5 * Math.ceil(requestDto.getWeight() / 0.5);

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
