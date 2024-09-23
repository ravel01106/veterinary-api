package com.aravelo.veterinary_api.domain.services;

import com.aravelo.veterinary_api.domain.models.User;

public interface UserService {
  User getUserByName(String name);
}
