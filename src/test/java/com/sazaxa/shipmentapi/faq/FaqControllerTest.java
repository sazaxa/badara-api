package com.sazaxa.shipmentapi.faq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqController.class)
class FaqControllerTest {

    @MockBean
    private FaqService faqService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

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

//    @Test
//     void testSaveFaq() throws Exception {
//
//        //given
//        FaqSaveRequestDto faqSaveResponseDto = new FaqSaveRequestDto(new Faq("t1", "c1"));
//
//        //when
//        final ResultActions actions = mockMvc.perform(post(BASE_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(faqSaveResponseDto)));
//
//        //then
//        actions
//                .andExpect(status().isCreated());
//
//    }

    void testUpdateFaq(){

    }


    void testDeleteFaq(){

    }

}

