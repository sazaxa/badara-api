package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqUpdateRequestDto;
import com.sazaxa.shipmentapi.faq.dto.ImgResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/v1/faq")
@RestController
public class FaqController {

    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping
    public List<Faq> getAllFaq(){
        return faqService.getAllFaq();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Faq getFaqById(@PathVariable Long id){
        return faqService.getFaqById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Faq saveFaq(@RequestBody FaqSaveRequestDto request){
        return faqService.saveFaq(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Faq updateFaq(@PathVariable Long id, @RequestBody FaqUpdateRequestDto request){
        return faqService.updateFaq(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteFaq(@PathVariable Long id){
        faqService.deleteFaq(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    public ImgResponseDto uploadImage(@RequestParam MultipartFile image){
        faqService.uploadImage(image);
        return null;
    }

}
