package com.WeatherForcastApp.WeatherForcastApp.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(UnauthorizedAccessException exception){
        return new ResponseEntity<>(
                new ErrorResponse().builder()
                        .message(exception.getMessage())
                        .statusCode(exception.getHttpStatusCode())
                        .error(exception.getError())
                        .build(), HttpStatus.valueOf(exception.getHttpStatusCode()));
    }
}
