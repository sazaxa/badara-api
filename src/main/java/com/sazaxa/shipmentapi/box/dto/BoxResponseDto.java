package com.sazaxa.shipmentapi.box.dto;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.dto.ProductResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class BoxResponseDto {
    private Long id;
    private String type;
    private Double expectedWidth;
    private Double expectedDepth;
    private Double expectedHeight;
    private Double expectedVolumeWeight;
    private Double expectedNetWeight;
    private Double expectedPrice;
    private Double width;
    private Double depth;
    private Double height;
    private Double volumeWeight;
    private Double netWeight;
    private Double price;
    private String koreanInvoice;
    private String koreanShippingCompany;
    private String koreanShippingStatus;
    private String userMemo;
    private List<ProductResponseDto> products;

    public static BoxResponseDto of(Box box, List<Product> products) {
        return BoxResponseDto.builder()
                .id(box.getId())
                .type(box.getType())
                .expectedWidth(box.getExpectedWidth())
                .expectedDepth(box.getExpectedDepth())
                .expectedHeight(box.getExpectedHeight())
                .expectedVolumeWeight(box.getExpectedVolumeWeight())
                .expectedNetWeight(box.getExpectedNetWeight())
                .expectedPrice(box.getExpectedPrice())
                .width(box.getWidth())
                .depth(box.getDepth())
                .height(box.getHeight())
                .volumeWeight(box.getVolumeWeight())
                .netWeight(box.getNetWeight())
                .price(box.getPrice())
                .koreanInvoice(box.getKoreanInvoice())
                .koreanShippingCompany(box.getKoreanShippingCompany())
                .koreanShippingStatus(box.getKoreanShippingStatus().status)
                .userMemo(box.getUserMemo())
                .products(ProductResponseDto.ofList(products))
                .build();
    }

    public static BoxResponseDto of(Box box) {
        return BoxResponseDto.builder()
                .id(box.getId())
                .type(box.getType())
                .expectedWidth(box.getExpectedWidth())
                .expectedDepth(box.getExpectedDepth())
                .expectedHeight(box.getExpectedHeight())
                .expectedVolumeWeight(box.getExpectedVolumeWeight())
                .expectedNetWeight(box.getExpectedNetWeight())
                .expectedPrice(box.getExpectedPrice())
                .width(box.getWidth())
                .depth(box.getDepth())
                .height(box.getHeight())
                .volumeWeight(box.getVolumeWeight())
                .netWeight(box.getNetWeight())
                .price(box.getPrice())
                .koreanInvoice(box.getKoreanInvoice())
                .koreanShippingCompany(box.getKoreanShippingCompany())
                .koreanShippingStatus(box.getKoreanShippingStatus().status)
                .userMemo(box.getUserMemo())
                .build();
    }

    public static List<BoxResponseDto> ofList(List<Box> boxes) {
        List<BoxResponseDto> boxResponses = new ArrayList<>();
        for (Box box : boxes){
            boxResponses.add(BoxResponseDto.of(box));
        }
        return boxResponses;
    }

}
