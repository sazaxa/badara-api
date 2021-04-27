package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqUpdateRequestDto;
import com.sazaxa.shipmentapi.faq.exception.FaqNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<Faq> getAllFaq() {
        return faqRepository.findAll();
    }

    public Faq getFaqById(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        return faq;
    }

    public Faq saveFaq(FaqSaveRequestDto request) {
        Faq faq = Faq.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        return faqRepository.save(faq);
    }

    public Faq updateFaq(Long id, FaqUpdateRequestDto faqUpdateRequestDto) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        faq.updateFaq(faqUpdateRequestDto.getTitle(), faqUpdateRequestDto.getContent());
        faqRepository.save(faq);
        return faq;
    }

    public Faq deleteFaq(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        faqRepository.delete(faq);
        return faq;
    }

    public void uploadImage(MultipartFile image) {

    }
}
