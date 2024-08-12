package com.aravelo.veterinary_api.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aravelo.veterinary_api.domain.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
