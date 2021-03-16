package com.sazaxa.shipmentapi.product;

import com.sazaxa.shipmentapi.product.dto.ProductInvoiceRequestDto;
import com.sazaxa.shipmentapi.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("no product id : " + id));
    }

    public Product updateProductKoreanInvoice(Long id, ProductInvoiceRequestDto request) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("no product id : " + id));
        product.proceedInvoice(request.getInvoice());
        return product;
    }

}
