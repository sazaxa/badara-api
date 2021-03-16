package com.sazaxa.shipmentapi.product;

import com.sazaxa.shipmentapi.product.dto.ProductInvoiceRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProductInvoice(@PathVariable Long id, @RequestBody ProductInvoiceRequestDto request){
        return productService.updateProductKoreanInvoice(id, request);
    }

}
