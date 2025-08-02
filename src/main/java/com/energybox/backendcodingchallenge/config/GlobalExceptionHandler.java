package com.energybox.backendcodingchallenge.config;

import exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<Object> handleUnhandledExceptions(Exception ex){
//        logger.error(Arrays.toString(ex.getStackTrace()));
//        return new ResponseEntity<>("Internal server error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
