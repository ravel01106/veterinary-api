package com.aravelo.veterinary_api.domain.error;

public class QuoteIsAlreadyExistException extends Exception{

  public QuoteIsAlreadyExistException(String message){
    super(message);
  }
}
