package org.ecommerce.productcatalogservice.dtos.responses;


import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.dtos.models.ProductDto;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;

import java.util.List;

@Getter
@Setter
public class GetAllProductsResponseDto {
    private List<ProductDto> products;
    private Long productCount;
}
