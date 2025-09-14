package org.ecommerce.productcatalogservice.dtos.responses;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.dtos.enums.ResponseStatus;

@Getter
@Setter
public class BaseResponse<T> {
    private String message;
    private ResponseStatus responseStatus;
    private T data;

    public static <T> BaseResponse<T> success(String message, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(message);
        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> failure(String message, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(message);
        response.setResponseStatus(ResponseStatus.FAILURE);
        response.setData(data);
        return response;
    }
}
