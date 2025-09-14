package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.ecommerce.productcatalogservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(String title, String description, Double price, String categoryName, String imageUrl);

    Product updateProduct(Long id, String title, String description, Double price, String categoryName, String imageUrl) throws ProductNotFoundException;
    Product replaceProduct(Long id, String title, String description, Double price, String categoryName, String imageUrl) throws ProductNotFoundException;
    void deleteProduct(Long id) throws ProductNotFoundException;
}
