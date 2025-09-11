package org.ecommerce.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private ResponseStatus responseStatus;
}
