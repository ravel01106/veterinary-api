package com.aravelo.veterinary_api.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aravelo.veterinary_api.domain.models.Quote;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long>{

}
