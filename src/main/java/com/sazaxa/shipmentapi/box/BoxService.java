package com.sazaxa.shipmentapi.box;

import com.sazaxa.shipmentapi.box.dto.BoxUpdateRequestDto;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BoxService {

    private final BoxRepository boxRepository;

    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public Box updateCenterIncome(Long id, BoxUpdateRequestDto request) {
        Box box = boxRepository.findById(id).orElseThrow(()->new OrderNotFoundException("no order id : " + id));
        box.updateCenterIncome(request.getKoreanInvoice(), request.getKoreanShippingCompany());
        return box;
    }

}
