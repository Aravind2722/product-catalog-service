package org.ecommerce.productcatalogservice.controllers;


import org.ecommerce.productcatalogservice.dtos.models.ProductDto;
import org.ecommerce.productcatalogservice.dtos.requests.CreateProductRequestDto;
import org.ecommerce.productcatalogservice.dtos.requests.ReplaceProductRequestDto;
import org.ecommerce.productcatalogservice.dtos.requests.UpdateProductRequestDto;
import org.ecommerce.productcatalogservice.dtos.responses.*;
import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.ecommerce.productcatalogservice.models.Product;
import org.ecommerce.productcatalogservice.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<ProductDto>> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(id);
        return ResponseEntity.ok(BaseResponse.success("Product retrieved successfully.", ProductDto.fromProduct(product)));
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse<GetAllProductsResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto getAllProductsResponseDto = new GetAllProductsResponseDto();
        getAllProductsResponseDto.setProductCount((long)products.size());
        getAllProductsResponseDto.setProducts(products.stream().map(ProductDto::fromProduct).toList());

        return ResponseEntity.ok(BaseResponse.success("Products retrieved successfully.", getAllProductsResponseDto));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<ProductDto>> createProduct(@RequestBody CreateProductRequestDto requestDto) {
        Product createdProduct = productService.createProduct(
                requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getCategoryName(), requestDto.getImageUrl()
        );
        return new ResponseEntity<>(BaseResponse.success("Product created successfully", ProductDto.fromProduct(createdProduct)), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductDto>> updateProduct(@RequestBody UpdateProductRequestDto requestDto, @PathVariable("id") Long id) throws ProductNotFoundException {
        Product updatedProduct = productService.updateProduct(id, requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getCategoryName(), requestDto.getImageUrl());
        return ResponseEntity.ok(BaseResponse.success("Product updated successfully.", ProductDto.fromProduct(updatedProduct)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductDto>> replaceProduct(@RequestBody ReplaceProductRequestDto requestDto, @PathVariable("id") Long id) throws ProductNotFoundException {
        Product replacedProduct = productService.replaceProduct(id, requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getCategoryName(), requestDto.getImageUrl());
        return ResponseEntity.ok(BaseResponse.success("Product replaced successfully.", ProductDto.fromProduct(replacedProduct)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
