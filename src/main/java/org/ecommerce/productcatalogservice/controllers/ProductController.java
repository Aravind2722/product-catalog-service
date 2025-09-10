package org.ecommerce.productcatalogservice.controllers;


import org.ecommerce.productcatalogservice.dtos.GetSingleFakeStoreProductResponseDto;
import org.ecommerce.productcatalogservice.dtos.GetSingleProductResponseDto;
import org.ecommerce.productcatalogservice.models.Product;
import org.ecommerce.productcatalogservice.services.ProductService;
import org.ecommerce.productcatalogservice.services.ProductServiceFakeStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products/")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductServiceFakeStoreImpl productServiceFakeStoreImpl) {
        this.productService = productServiceFakeStoreImpl;
    }
    @GetMapping("{id}")
    public ResponseEntity<GetSingleProductResponseDto> getSingleProduct(@PathVariable("id") Long id) {
        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<>(
                GetSingleProductResponseDto.fromProduct(product),
                HttpStatus.OK
        );
    }
}
