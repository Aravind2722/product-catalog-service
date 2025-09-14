package org.ecommerce.productcatalogservice.dtos.models;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.models.Product;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String categoryName;
    private String imageUrl;

    public static ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setImageUrl(product.getImage());

        return productDto;
    }

}
