package com.sazaxa.shipmentapi.box;

import com.sazaxa.shipmentapi.box.dto.BoxUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class BoxServiceTest {

    private BoxService boxService;
    private BoxRepository boxRepository;
    private final Long EXISTED_ID = 1L;

    @BeforeEach
    void setUp() {
        boxRepository = mock(BoxRepository.class);
        boxService = new BoxService(boxRepository);
    }

//    @Test
//    void updateCenterIncome() {
//        Box box = Box.builder()
//                .id(EXISTED_ID)
//                .expectedPrice(20D)
//                .build();
//
//
//        BoxUpdateRequestDto boxUpdateRequest = BoxUpdateRequestDto.builder()
//                .koreanInvoice("test-invoice")
//                .koreanShippingCompany("test-company")
//                .build();
//
//        given(boxRepository.findById(EXISTED_ID)).willReturn(Optional.of(box));
//        Box newBox = boxService.updateCenterIncome(EXISTED_ID, boxUpdateRequest);
//
//        assertThat(newBox.getKoreanInvoice()).isEqualTo("test-invoice");
//    }

}
