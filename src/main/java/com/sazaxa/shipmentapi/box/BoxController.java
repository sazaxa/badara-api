package com.sazaxa.shipmentapi.box;

import com.sazaxa.shipmentapi.box.dto.BoxUpdateRequestDto;
import com.sazaxa.shipmentapi.box.dto.BoxWeightRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/boxes")
@RestController
public class BoxController {

    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping
    public Double weightVolumeWeight(@RequestBody BoxWeightRequestDto boxWeightRequest){
        return boxService.weightVolumeWeight(boxWeightRequest);
    }

    @PutMapping("/{id}")
    public Box updateCenterIncome(@PathVariable Long id, @RequestBody BoxUpdateRequestDto request){
        return boxService.updateCenterIncome(id, request);
    }

}
