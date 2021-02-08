package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.dto.FaqRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqController.class)
class FaqControllerTest {

    @MockBean
    private FaqService faqService;

    @Autowired
    private MockMvc mockMvc;

    String BASE_URL = "/api/v1/faq";

    @Test
    void testGetAllFaq() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void testGetFaqById() throws Exception {
        Long id = 1L;
        mockMvc.perform(get(BASE_URL + "/" + id))
                .andExpect(status().isOk());
    }

    @Test
     void testSaveFaq() throws Exception {

        FaqResponseDto faqResponseDto = new FaqResponseDto(new Faq("t1", "c1"));

        mockMvc.perform(post(BASE_URL))
                .andExpect(status().isCreated());
    }


    void testUpdateFaq(){

    }


    void testDeleteFaq(){

    }

}

