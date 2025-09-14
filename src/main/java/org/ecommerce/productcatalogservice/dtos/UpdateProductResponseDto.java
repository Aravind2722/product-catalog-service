package org.ecommerce.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;

@Getter
@Setter
public class UpdateProductResponseDto {
    private ProductDto updatedProduct;
    private String message;
    private ResponseStatus responseStatus;
}
