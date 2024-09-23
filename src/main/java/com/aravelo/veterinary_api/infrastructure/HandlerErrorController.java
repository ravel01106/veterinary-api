package com.aravelo.veterinary_api.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.aravelo.veterinary_api.domain.enums.ErrorTypes;
import com.aravelo.veterinary_api.domain.error.ErrorMessage;
import com.aravelo.veterinary_api.domain.error.QuoteIsAlreadyExistException;
import com.aravelo.veterinary_api.domain.error.QuoteNotFoundException;
import com.aravelo.veterinary_api.domain.error.UserNotFoundException;

@ControllerAdvice
public class HandlerErrorController {


  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorMessage> notFoundUser(UserNotFoundException ex){
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ErrorTypes.USER_NOT_EXISTS);
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);

  }


  @ExceptionHandler(QuoteNotFoundException.class)
  public ResponseEntity<ErrorMessage> notFoundQuote(QuoteNotFoundException ex){
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ErrorTypes.QUOTE_NOT_FOUND);
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(QuoteIsAlreadyExistException.class)
  public ResponseEntity<ErrorMessage> thereIsQuoteWithSameDate(QuoteIsAlreadyExistException ex){
    ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ErrorTypes.QUOTE_IS_ALREADY_EXIST);
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);

  }


  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorMessage> isEmptyBody(HttpMessageNotReadableException ex){
    ErrorMessage errorMessage = new ErrorMessage("The body is empty!!", ErrorTypes.EMPTY_BODY);
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorMessage> getIncorrectPath(NoResourceFoundException ex){
    ErrorMessage errorMessage = new ErrorMessage("This path does not exist !!!", ErrorTypes.FAIL_PATH);
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
  }

}
