package com.sazaxa.shipmentapi.faq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FaqController.class)
class FaqControllerTest {

    @MockBean
    private FaqService faqService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllFaq(){

    }


}