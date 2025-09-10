package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.models.Product;

public interface ProductService {
    Product getSingleProduct(Long id);
}
