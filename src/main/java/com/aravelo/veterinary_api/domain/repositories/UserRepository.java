package com.aravelo.veterinary_api.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aravelo.veterinary_api.domain.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
