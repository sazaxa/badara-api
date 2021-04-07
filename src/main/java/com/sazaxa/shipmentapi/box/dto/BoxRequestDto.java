package com.sazaxa.shipmentapi.box.dto;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.order.OrderStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoxRequestDto {
    private Long id;
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

    public static Box toEntity(BoxRequestDto boxRequest){
        return Box.builder()
                .id(boxRequest.getId())
                .expectedWidth(boxRequest.getExpectedWidth())
                .expectedDepth(boxRequest.getExpectedDepth())
                .expectedHeight(boxRequest.getExpectedHeight())
                .expectedVolumeWeight(boxRequest.getExpectedVolumeWeight())
                .expectedNetWeight(boxRequest.getExpectedNetWeight())
                .expectedPrice(boxRequest.getExpectedPrice())
                .width(boxRequest.getWidth())
                .depth(boxRequest.getDepth())
                .height(boxRequest.getHeight())
                .volumeWeight(boxRequest.getVolumeWeight())
                .netWeight(boxRequest.getNetWeight())
                .price(boxRequest.getPrice())
                .koreanInvoice(boxRequest.getKoreanInvoice())
                .koreanShippingCompany(boxRequest.getKoreanShippingCompany())
                .koreanShippingStatus(OrderStatus.findByKorean(boxRequest.getKoreanShippingStatus()))
                .userMemo(boxRequest.getUserMemo())
                .build();
    }

    public static List<Box> toEntityList(List<BoxRequestDto> boxRequests){
        List<Box> list = new ArrayList<>();
        for (BoxRequestDto boxRequest : boxRequests){
            list.add(
                    Box.builder()
                    .id(boxRequest.getId())
                    .expectedWidth(boxRequest.getExpectedWidth())
                    .expectedDepth(boxRequest.getExpectedDepth())
                    .expectedHeight(boxRequest.getExpectedHeight())
                    .expectedVolumeWeight(boxRequest.getExpectedVolumeWeight())
                    .expectedNetWeight(boxRequest.getExpectedNetWeight())
                    .expectedPrice(boxRequest.getExpectedPrice())
                    .width(boxRequest.getWidth())
                    .depth(boxRequest.getDepth())
                    .height(boxRequest.getHeight())
                    .volumeWeight(boxRequest.getVolumeWeight())
                    .netWeight(boxRequest.getNetWeight())
                    .price(boxRequest.getPrice())
                    .koreanInvoice(boxRequest.getKoreanInvoice())
                    .koreanShippingCompany(boxRequest.getKoreanShippingCompany())
                    .koreanShippingStatus(OrderStatus.findByKorean(boxRequest.getKoreanShippingStatus()))
                    .userMemo(boxRequest.getUserMemo())
                    .build()
            );
        }
        return list;
    }

}
