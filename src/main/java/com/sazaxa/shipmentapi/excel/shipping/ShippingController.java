package com.sazaxa.shipmentapi.excel.shipping;

import com.sazaxa.shipmentapi.excel.shipping.dto.ShippingRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/excel/shipping")
    public String uploadShippingFee(@RequestParam("file") MultipartFile file) throws IOException {
        shippingService.uploadShippingFee(file);
        return "success-excel-upload";
    }

    @PostMapping("/api/v1/shipping/dhl")
    public double getPrice(@RequestBody ShippingRequestDto requestDto){
        return shippingService.getPrice(requestDto);
    }

    @PostMapping("/api/v1/shipping/dhl/country/price")
    public List<DhlShipping> getPriceByCountryWeight(@RequestBody ShippingRequestDto requestDto){
        return shippingService.getPriceByCountryWeight(requestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/shipping/dhl/countries")
    public List<DhlShipping> getCountriesInfo(){
        return shippingService.getCountriesInfo();
    }

}
