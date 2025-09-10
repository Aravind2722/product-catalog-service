package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.dtos.GetSingleFakeStoreProductResponseDto;
import org.ecommerce.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductServiceFakeStoreImpl implements ProductService {
    private RestTemplate restTemplate;
    @Autowired
    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long id) {
        GetSingleFakeStoreProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                GetSingleFakeStoreProductResponseDto.class
        );
        return responseDto.toProduct();
    }
}
