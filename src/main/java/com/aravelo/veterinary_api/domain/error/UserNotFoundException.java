package com.aravelo.veterinary_api.domain.error;

public class UserNotFoundException extends Exception{

  public UserNotFoundException(String message){
    super(message);
  }

}
