package com.sazaxa.shipmentapi.faq;

import com.github.dozermapper.core.Mapper;
import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqUpdateRequestDto;
import com.sazaxa.shipmentapi.faq.exception.FaqNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {


    private final Mapper mapper;
    private final FaqRepository faqRepository;

    public FaqService(Mapper mapper, FaqRepository faqRepository) {
        this.mapper = mapper;
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

        Faq faq = mapper.map(faqSaveRequestDto, Faq.class);
        faqRepository.save(faq);

        //저장한 값 리턴
        FaqResponseDto faqResponseDto = new FaqResponseDto(faq);

        return faqResponseDto;
    }

    public FaqResponseDto updateFaq(Long id, FaqUpdateRequestDto faqUpdateRequestDto) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        faq.updateFaq(faqUpdateRequestDto.getTitle(), faqUpdateRequestDto.getContent());

        faqRepository.save(faq);

        FaqResponseDto faqResponseDto = new FaqResponseDto(faq);
        return faqResponseDto;
    }

    public Faq deleteFaq(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new FaqNotFoundException("no id" + id) );
        faqRepository.delete(faq);
        return faq;
    }

}
