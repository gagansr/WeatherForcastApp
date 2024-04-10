package com.WeatherForcastApp.WeatherForcastApp.exceptionHandling;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UnauthorizedAccessException extends Exception {
    int httpStatusCode;
    String error;
    public UnauthorizedAccessException(String message, int httpCode,String error){

        super(message);
        this.httpStatusCode = httpCode;
        this.error = error;
    }
}
