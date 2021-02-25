package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqUpdateRequestDto;
import com.sazaxa.shipmentapi.faq.exception.FaqNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
                .title("t1")
                .content("c1")
                .build();
        FaqResponseDto response = faqService.saveFaq(request);
        assertThat(response.getTitle()).isEqualTo("t1");
    }

    @Test
    void updateFaqWithExistedId() {
        FaqUpdateRequestDto request = FaqUpdateRequestDto.builder()
                .title("t2")
                .content("c2")
                .build();

        FaqResponseDto response = faqService.updateFaq(1L, request);
        assertThat(response.getTitle()).isEqualTo("t2");
    }

    @Test
    void updateFaqWithNotExistedId() {
        FaqUpdateRequestDto request = FaqUpdateRequestDto.builder()
                .title("t2")
                .content("c2")
                .build();

        assertThatThrownBy(() -> faqService.updateFaq(1000L, request))
                .isInstanceOf(FaqNotFoundException.class);

    }

    @Test
    void deleteFaqWithExistedId() {
        faqService.deleteFaq(1L);
        verify(faqRepository).delete(any(Faq.class));
    }

    @Test
    void deleteFaqWithNotExistedId() {
        assertThatThrownBy(()-> faqService.deleteFaq(1000L))
                .isInstanceOf(FaqNotFoundException.class);
    }

}