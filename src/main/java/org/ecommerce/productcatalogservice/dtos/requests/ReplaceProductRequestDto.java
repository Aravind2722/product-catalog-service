package org.ecommerce.productcatalogservice.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String categoryName;
    private String imageUrl;
}
