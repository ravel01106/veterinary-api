package com.aravelo.veterinary_api.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aravelo.veterinary_api.domain.enums.ErrorTypes;
import com.aravelo.veterinary_api.domain.error.ErrorMessage;
import com.aravelo.veterinary_api.domain.error.UserNotFoundException;

@ControllerAdvice
public class HandlerErrorController {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorMessage> notFoundUser(UserNotFoundException ex){
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ErrorTypes.USER_NOT_EXISTS);
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);

  }

}
