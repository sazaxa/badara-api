package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.dto.FaqRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.faq.exception.FaqNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<Faq> getAllFaq() {
        return faqRepository.findAll();
    }

    public FaqResponseDto getFaqById(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        FaqResponseDto faqResponseDto = new FaqResponseDto(faq);
        return faqResponseDto;
    }

    public FaqResponseDto saveFaq(FaqSaveRequestDto faqSaveRequestDto) {
        //저장
        Faq faq = faqSaveRequestDto.toEntity();
        faqRepository.save(faq);

        //저장한 값 리턴
        FaqResponseDto faqResponseDto = new FaqResponseDto(faq);
        return faqResponseDto;
    }

    public FaqResponseDto updateFaq(Long id, FaqRequestDto faqRequestDto) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        faq.updateFaq(faqRequestDto.getTitle(), faqRequestDto.getContent());

        faqRepository.save(faq);

        FaqResponseDto faqResponseDto = new FaqResponseDto(faq);
        return faqResponseDto;
    }

    public void deleteFaqById(Long id) {
        faqRepository.deleteById(id);
    }

}
