package com.sazaxa.shipmentapi.faq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FaqServiceTest {

    private FaqService faqService;
    private FaqRepository faqRepository;

    @BeforeEach
    void setUp() {
        faqRepository = mock(FaqRepository.class);
        faqService = new FaqService(faqRepository);
    }

    @Test
    void testMockito(){
//        Faq faq = Faq.builder()
//                .title("dummy-test-title-1")
//                .build();
       Faq mockedFaq = mock(Faq.class);

       when(mockedFaq.getTitle()).thenReturn("dummy-test-title-1");
       assertThat(mockedFaq.getTitle()).isEqualTo("dummy-test-title-1");

       verify(mockedFaq).getTitle();
    }

    @Test
    void getAllFaq() {
        Faq faq = Faq.builder()
                .title("dummy-test-title-1")
                .build();

        given(faqRepository.findAll()).willReturn(Arrays.asList(faq));
        List<Faq> list = faqService.getAllFaq();

        assertThat(list.get(0).getTitle()).isEqualTo("dummy-test-title-1");
        verify(faqRepository).findAll();
    }

    @Test
    void getFaqById() {
        Faq faq = Faq.builder()
                .id(1L)
                .title("dummy-test-title-1")
                .build();

        given(faqRepository.findById(1L)).willReturn(Optional.of(faq));
        Faq mockFaq = faqService.getFaqById(1L);

        assertThat(mockFaq.getTitle()).isEqualTo("dummy-test-title-1");
        verify(faqRepository).findById(1L);
    }

    @Test
    void saveFaq() {
        Faq faq = Faq.builder()
                .id(1L)
                .title("dummy-test-title-1")
                .build();

    }

    @Test
    void updateFaq() {
    }

    @Test
    void deleteFaq() {
    }
}
