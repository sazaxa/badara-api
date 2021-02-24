package com.sazaxa.shipmentapi.faq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

class FaqServiceTest {

    private FaqRepository faqRepository = mock(FaqRepository.class);
    private FaqService faqService;

    @BeforeEach
    void setUp(){
        faqService = new FaqService(faqRepository);
    }

    @Test
    void getAllFaq() {
        List<Faq> faqs = faqService.getAllFaq();
    }

    void getFaqById() {

    }

    void saveFaq() {

    }

    void updateFaq() {

    }

    void deleteFaqById() {

    }

}