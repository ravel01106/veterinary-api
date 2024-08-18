package com.aravelo.veterinary_api.application;

import com.aravelo.veterinary_api.domain.models.User;
import com.aravelo.veterinary_api.domain.services.UserService;

public class UserServiceImpl implements UserService{

  @Override
  public User getUserByName(String name) {
    return new User("","");
  }

}
