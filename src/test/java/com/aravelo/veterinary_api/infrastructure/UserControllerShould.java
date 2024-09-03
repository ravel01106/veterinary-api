package com.aravelo.veterinary_api.infrastructure;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aravelo.veterinary_api.application.UserServiceImpl;
import com.aravelo.veterinary_api.domain.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)

public class UserControllerShould {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userServiceImpl;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void logInAnExistingUser() throws Exception {
    User userAdmin = new User("admin", "admin");
    when(userServiceImpl.getUserByName("admin")).thenReturn(userAdmin);

    String userAdminJson = objectMapper.writeValueAsString(userAdmin);

    this.mockMvc.perform(
      post("/login")
      .contentType(MediaType.APPLICATION_JSON)
      .content(userAdminJson))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.username").value("admin"))
      .andExpect(jsonPath("$.password").value("admin"));

  }

  @Test
  public void throwErrorMessageWhenDoesNotExistTheUser() throws Exception {
    User user = new User("pepe", "1234");
    when(userServiceImpl.getUserByName("pepe")).thenReturn(null);

    String userAdminJson = objectMapper.writeValueAsString(user);

    this.mockMvc.perform(
      post("/login")
      .contentType(MediaType.APPLICATION_JSON)
      .content(userAdminJson))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value("The User does not exist in the database"))
      .andExpect(jsonPath("$.errorType").value("USER_NOT_EXISTS"));

  }

}
