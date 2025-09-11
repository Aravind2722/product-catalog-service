package org.ecommerce.productcatalogservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;
import org.ecommerce.productcatalogservice.models.Product;

import java.util.List;

@Getter
@Setter
public class GetAllProductsResponseDto {
    private List<ProductDto> products;
    private Long productCount;
    private String message;
    private ResponseStatus responseStatus;
}
