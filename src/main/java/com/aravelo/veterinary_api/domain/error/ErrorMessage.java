package com.aravelo.veterinary_api.domain.error;


import com.aravelo.veterinary_api.domain.enums.ErrorTypes;

public class ErrorMessage {
  private String message;
  private ErrorTypes errorType;

  public ErrorMessage(String message, ErrorTypes errorType) {
    this.message = message;
    this.errorType = errorType;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorTypes getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorTypes errorType) {
    this.errorType = errorType;
  }



}
