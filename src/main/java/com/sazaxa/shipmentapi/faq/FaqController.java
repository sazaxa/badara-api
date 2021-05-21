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
    public List<Faq> list(){
        return faqService.list();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Faq detail(@PathVariable Long id){
        return faqService.detail(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Faq create(@RequestBody FaqSaveRequestDto request){
        return faqService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Faq update(@PathVariable Long id, @RequestBody FaqUpdateRequestDto request){
        return faqService.update(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        faqService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    public ImgResponseDto uploadImage(@RequestParam("image") MultipartFile image){
        return faqService.uploadImage(image);
    }

}
