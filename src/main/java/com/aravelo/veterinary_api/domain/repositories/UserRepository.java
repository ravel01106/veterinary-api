package com.aravelo.veterinary_api.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aravelo.veterinary_api.domain.models.User;
import java.util.Optional;



@Repository
public interface UserRepository extends CrudRepository<User, Long>{
  Optional<User> findByUsername(String username);

}
