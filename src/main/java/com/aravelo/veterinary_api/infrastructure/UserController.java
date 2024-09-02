package com.aravelo.veterinary_api.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aravelo.veterinary_api.domain.models.User;
import com.aravelo.veterinary_api.domain.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<User> loginUser(@RequestBody User user){

    User userdb = userService.getUserByName(user.getUsername());

    return new ResponseEntity<User>(userdb, HttpStatus.OK);

  }

}
