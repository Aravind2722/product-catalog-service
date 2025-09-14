package org.ecommerce.productcatalogservice.controllers;


import org.ecommerce.productcatalogservice.dtos.*;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;
import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.ecommerce.productcatalogservice.models.Product;
import org.ecommerce.productcatalogservice.services.ProductService;
import org.ecommerce.productcatalogservice.services.ProductServiceFakeStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductServiceFakeStoreImpl productServiceFakeStoreImpl) {
        this.productService = productServiceFakeStoreImpl;
    }
    @GetMapping("{id}")
    public ResponseEntity<GetSingleProductResponseDto> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(id);
        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        responseDto.setProductDto(ProductDto.fromProduct(product));
        responseDto.setMessage("Product retrieved successfully.");
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK
        );
    }

    @GetMapping("")
    public ResponseEntity<GetAllProductsResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto responseDto = new GetAllProductsResponseDto();
        responseDto.setProductCount((long)products.size());
        responseDto.setProducts(products.stream().map(ProductDto::fromProduct).toList());
        responseDto.setMessage("Products retrieved successfully.");
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<CreateProductResponseDto> createProduct(@RequestBody CreateProductRequestDto requestDto) {
        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        Product product = productService.createProduct(
                requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getCategoryName(), requestDto.getImageUrl()
        );
        responseDto.setProductDto(ProductDto.fromProduct(product));
        responseDto.setMessage("Product created successfully");
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateProductResponseDto> updateProduct(@RequestBody UpdateProductRequestDto requestDto) throws ProductNotFoundException {
        Product updatedProduct = productService.updateProduct(requestDto.getId(), requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getCategoryName(), requestDto.getImageUrl());
        UpdateProductResponseDto responseDto = new UpdateProductResponseDto();
        responseDto.setUpdatedProduct(ProductDto.fromProduct(updatedProduct));
        responseDto.setMessage("Product updated successfully.");
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
