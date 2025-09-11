package org.ecommerce.productcatalogservice.advices;

import org.ecommerce.productcatalogservice.dtos.ErrorResponse;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;
import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(exception.getMessage());
        response.setResponseStatus(ResponseStatus.FAILURE);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
