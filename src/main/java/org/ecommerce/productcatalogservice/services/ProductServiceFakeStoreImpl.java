package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.dtos.fakestore_dtos.CreateFakeStoreProductRequestDto;
import org.ecommerce.productcatalogservice.dtos.fakestore_dtos.FakeStoreProductDto;
import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.ecommerce.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Service
public class ProductServiceFakeStoreImpl implements ProductService {
    private RestTemplate restTemplate;
    @Autowired
    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        FakeStoreProductDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        if (responseDto == null) throw new ProductNotFoundException("Product " + id + " not found.");
        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        return Arrays.stream(fakeStoreProducts).map(FakeStoreProductDto::toProduct).toList();
    }

    @Override
    public Product createProduct(String title, String description, Double price, String categoryName, String imageUrl) {
        CreateFakeStoreProductRequestDto requestDto = new CreateFakeStoreProductRequestDto();
        requestDto.setId(new Random().nextLong());
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setPrice(price);
        requestDto.setImage(imageUrl);
        requestDto.setCategory(categoryName);

        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDto,
                FakeStoreProductDto.class
        );

        return fakeStoreProductDto.toProduct();
    }
}
