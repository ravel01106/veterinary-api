package com.aravelo.veterinary_api.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aravelo.veterinary_api.domain.models.User;
import com.aravelo.veterinary_api.domain.repositories.UserRepository;
import com.aravelo.veterinary_api.domain.services.UserService;

public class UserServiceImpl implements UserService{

  @Autowired
  UserRepository userRepository;

  @Override
  public User getUserByName(String name) {
    User user = null;
    Optional<User> userOptional = userRepository.findByUsername(name);
    if (userOptional.isPresent()) {
      user = userOptional.get();
    }
    return user;
  }

}
