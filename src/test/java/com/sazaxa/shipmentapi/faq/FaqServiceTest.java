package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class FaqServiceTest {

    private FaqRepository faqRepository = mock(FaqRepository.class);
    private FaqService faqService;

    @BeforeEach
    void setUp(){
        faqService = new FaqService(faqRepository);

        Faq faq = Faq.builder()
                .title("t1")
                .content("c1")
                .build();

        given(faqRepository.findAll()).willReturn(List.of(faq));
        given(faqRepository.findById(1L)).willReturn(Optional.of(faq));
        given(faqRepository.save(any(Faq.class))).willReturn(faq);
    }

    @Test
    void getAllFaq() {
        List<Faq> faqs = faqService.getAllFaq();
        assertThat(faqs.get(0).getTitle()).isEqualTo("t1");
    }

    @Test
    void getFaqById() {
        Long id = 1L;
        FaqResponseDto faqResponseDto = faqService.getFaqById(id);
        assertThat(faqResponseDto).isNotNull();
    }

    @Test
    void saveFaq() {

        FaqSaveRequestDto request = FaqSaveRequestDto.builder()
                .title("t2")
                .content("c1")
                .build();

        FaqResponseDto response = faqService.saveFaq(request);

        assertThat(response.getTitle()).isEqualTo("t2");

    }

    void updateFaq() {

    }

    void deleteFaqById() {

    }

}