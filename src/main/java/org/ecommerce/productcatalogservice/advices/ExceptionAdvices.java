package org.ecommerce.productcatalogservice.advices;

import org.ecommerce.productcatalogservice.dtos.responses.BaseResponse;
import org.ecommerce.productcatalogservice.dtos.responses.ErrorResponse;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;
import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleProductNotFoundException(ProductNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorMessage(exception.getLocalizedMessage());

        return new ResponseEntity<>(BaseResponse.failure("Request Failed!", errorResponse), HttpStatus.NOT_FOUND);
    }
}
