package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.dto.FaqUpdateRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/faq")
@RestController
public class FaqController {

    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Faq> getAllFaq(){
        return faqService.getAllFaq();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public FaqResponseDto getFaqById(@PathVariable Long id){
        return faqService.getFaqById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FaqResponseDto saveFaq(@RequestBody FaqSaveRequestDto request){
        return faqService.saveFaq(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public FaqResponseDto updateFaq(@PathVariable Long id, @RequestBody FaqUpdateRequestDto request){
        return faqService.updateFaq(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFaq(@PathVariable Long id){
        faqService.deleteFaqById(id);
    }

}
