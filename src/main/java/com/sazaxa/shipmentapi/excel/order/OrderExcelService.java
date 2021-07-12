package com.sazaxa.shipmentapi.excel.order;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.box.BoxRepository;
import com.sazaxa.shipmentapi.excel.ExcelExtension;
import com.sazaxa.shipmentapi.excel.country.Country;
import com.sazaxa.shipmentapi.excel.country.CountryRepository;
import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import com.sazaxa.shipmentapi.excel.errors.CountryNotFoundException;
import com.sazaxa.shipmentapi.excel.errors.ExcelBadRequestException;
import com.sazaxa.shipmentapi.excel.errors.ExcelExtensionException;
import com.sazaxa.shipmentapi.excel.errors.ExcelNotFoundException;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.errors.MemberNotFoundException;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderRepository;
import com.sazaxa.shipmentapi.order.OrderStatus;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.recipient.Recipient;
import com.sazaxa.shipmentapi.recipient.RecipientRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.ADDRESS;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.BOX_MEMO;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.CITY;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.COUNTRY;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.DEPTH;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.EMAIL;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.HEIGHT;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.NET_WEIGHT;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.PACKAGE_TYPE;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.PHONE_NUMBER;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.PRICE_1;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.PRICE_2;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.PRODUCT_NAME_1;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.PRODUCT_NAME_2;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.PRODUCT_NAME_3;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.QTY_1;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.QTY_2;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.RECIPIENT_MEMO;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.RECIPIENT_NAME;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.STATE;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.USER_ACCOUNT;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.WIDTH;
import static com.sazaxa.shipmentapi.excel.order.OrderExcelColumn.ZIPCODE;


@Transactional
@Service
public class OrderExcelService {

    private final OrderRepository orderRepository;
    private final CountryRepository countryRepository;
    private final MemberRepository memberRepository;
    private final RecipientRepository recipientRepository;
    private final BoxRepository boxRepository;
    private final ProductRepository productRepository;

    public OrderExcelService(OrderRepository orderRepository, CountryRepository countryRepository, MemberRepository memberRepository, RecipientRepository recipientRepository, BoxRepository boxRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.countryRepository = countryRepository;
        this.memberRepository = memberRepository;
        this.recipientRepository = recipientRepository;
        this.boxRepository = boxRepository;
        this.productRepository = productRepository;
    }

    public ExcelSuccessResponseDto create(MultipartFile file) throws IOException {

        if (file == null){
            throw new ExcelNotFoundException("엑셀파일을 추가해주세요");
        }

        Workbook workbook = createWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet){

            if (row.getRowNum() == 0){
                continue;
            }

            Member member = memberRepository.findByEmail(row.getCell(USER_ACCOUNT.ordinal()).getStringCellValue())
                    .orElseThrow(()-> new MemberNotFoundException(row.getRowNum()+1 + "번째 row : " + " 사용자 아이디를 확인하세요."));

            Country country = countryRepository.findByName(row.getCell(COUNTRY.ordinal()).getStringCellValue())
                    .orElseThrow(()-> new CountryNotFoundException(row.getRowNum()+1 + "번째 row : " + " 국가명을 확인하세요."));

            Recipient recipient = Recipient.builder()
                    .name(row.getCell(RECIPIENT_NAME.ordinal()).getStringCellValue())
                    .email(row.getCell(EMAIL.ordinal()).getStringCellValue())
                    .country(row.getCell(COUNTRY.ordinal()).getStringCellValue())
                    .address1((row.getCell(ADDRESS.ordinal()) != null) ? row.getCell(ADDRESS.ordinal()).getStringCellValue() : "")
                    .zipcode((row.getCell(ZIPCODE.ordinal()) != null) ? row.getCell(ZIPCODE.ordinal()).getStringCellValue() : "")
                    .city( (row.getCell(CITY.ordinal()) != null) ? row.getCell(CITY.ordinal()).getStringCellValue() : "")
                    .state( (row.getCell(STATE.ordinal()) != null) ? row.getCell(STATE.ordinal()).getStringCellValue() : "")
                    .memo( (row.getCell(RECIPIENT_MEMO.ordinal()) != null) ? row.getCell(RECIPIENT_MEMO.ordinal()).getStringCellValue() : "")
                    .phoneNumber(row.getCell(PHONE_NUMBER.ordinal()).getStringCellValue())
                    .countryCode(country.getCode())
                    .member(member)
                    .build();
            recipientRepository.save(recipient);

            String orderNumber = makeOrderNumber(recipient.getCountry(), recipient.getName());
            Order order = Order.builder()
                    .orderNumber(orderNumber)
                    .orderStatus(OrderStatus.PAYMENT_HOLDING)
                    .member(member)
                    .recipient(recipient)
                    .depositName("")
                    .build();
            orderRepository.save(order);

            Box box = Box.builder()
                    .type(row.getCell(PACKAGE_TYPE.ordinal()).getStringCellValue())
                    .koreanShippingStatus(OrderStatus.PAYMENT_HOLDING)
                    .expectedNetWeight(0d)
                    .expectedDepth(0d)
                    .expectedHeight(0d)
                    .expectedVolumeWeight(0d)
                    .expectedNetWeight(0d)
                    .width(row.getCell(WIDTH.ordinal()).getNumericCellValue())
                    .depth(row.getCell(DEPTH.ordinal()).getNumericCellValue())
                    .height(row.getCell(HEIGHT.ordinal()).getNumericCellValue())
                    .volumeWeight(makeVolumeWeight(row))
                    .netWeight(row.getCell(NET_WEIGHT.ordinal()).getNumericCellValue())
                    .resultWeight(makeResultWeight(makeVolumeWeight(row), row.getCell(NET_WEIGHT.ordinal()).getNumericCellValue()))
                    .userMemo( (row.getCell(BOX_MEMO.ordinal()) != null) ? row.getCell(BOX_MEMO.ordinal()).getStringCellValue() : "")
                    .order(order)
                    .build();
            boxRepository.save(box);

            List<Product> productList = makeProductList(row, box, order);
            productRepository.saveAll(productList);

        }

        return new ExcelSuccessResponseDto();
    }

    private Double makeResultWeight(Double volumeWeight, Double netWeight) {
        if (volumeWeight >= netWeight){
            return volumeWeight;
        }
        return netWeight;
    }

    private Double makeVolumeWeight(Row row) {
        Double width = row.getCell(WIDTH.ordinal()).getNumericCellValue();
        Double depth = row.getCell(DEPTH.ordinal()).getNumericCellValue();
        Double height = row.getCell(HEIGHT.ordinal()).getNumericCellValue();

        return (width * depth * height) / 5000;
    }

    private List<Product> makeProductList(Row row, Box box, Order order) {

        if (row.getCell(PRODUCT_NAME_1.ordinal()) == null ||
                row.getCell(PRODUCT_NAME_1.ordinal()).getStringCellValue().isBlank()){
            throw new ExcelBadRequestException(row.getRowNum() + " 번째  ");
        }

        List<Product> productList = new ArrayList<>();

        productList.add(Product.builder()
                .productDetail(row.getCell(PRODUCT_NAME_1.ordinal()).getStringCellValue())
                .quantity((int) row.getCell(QTY_1.ordinal()).getNumericCellValue())
                .price(row.getCell(PRICE_1.ordinal()).getNumericCellValue())
                .box(box)
                .order(order)
                .build());

        if (row.getCell(PRODUCT_NAME_2.ordinal()) == null ||
                row.getCell(PRODUCT_NAME_2.ordinal()).getStringCellValue().isBlank()
                ){
            return productList;
        }

        productList.add(Product.builder()
                .productDetail(row.getCell(PRODUCT_NAME_2.ordinal()).getStringCellValue())
                .quantity((int) row.getCell(QTY_2.ordinal()).getNumericCellValue())
                .price(row.getCell(PRICE_2.ordinal()).getNumericCellValue())
                .box(box)
                .order(order)
                .build());

        if (row.getCell(PRODUCT_NAME_3.ordinal()) == null ||
                row.getCell(PRODUCT_NAME_3.ordinal()).getStringCellValue().isBlank()){
            return productList;
        }

        productList.add(Product.builder()
                .productDetail(row.getCell(PRODUCT_NAME_2.ordinal()).getStringCellValue())
                .quantity((int) row.getCell(QTY_2.ordinal()).getNumericCellValue())
                .price(row.getCell(PRICE_2.ordinal()).getNumericCellValue())
                .box(box)
                .order(order)
                .build());

        return productList;
    }

    private Workbook createWorkbook(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equals(ExcelExtension.XLSX.getExtension())) {
            return new XSSFWorkbook(file.getInputStream());
        }
        if (extension.equals(ExcelExtension.XLS.getExtension())) {
            return new HSSFWorkbook(file.getInputStream());
        }
        throw new ExcelExtensionException(extension + "은 지원하는 확장자가 아닙니다.");
    }

    private String makeOrderNumber(String country, String name) {
        name = name.split(" ")[0];
        String orderNumber = country.toUpperCase() + "-" + name.toUpperCase() + "-" + new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(4).toUpperCase();
        return orderNumber;
    }
}
