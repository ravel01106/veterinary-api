package com.aravelo.veterinary_api.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.aravelo.veterinary_api.domain.models.User;
import com.aravelo.veterinary_api.domain.repositories.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceShould {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  // give us the user if it exists

  @Test
  public void giveUsTheUserIfItExists() {
    User user = new User("admin", "admin");
    Optional<User> optionalUser = Optional.of(user);
    when(userRepository.findByUsername("admin")).thenReturn(optionalUser);

    User userResult = userService.getUserByName("admin");

    assertEquals(userResult.getUsername(), user.getUsername());
    assertEquals(userResult.getPassword(), user.getPassword());
  }
}
