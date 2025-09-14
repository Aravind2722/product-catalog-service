package org.ecommerce.productcatalogservice.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;

@Getter
@Setter
public class ErrorResponse {
    private String errorMessage;
    private String details;
}
