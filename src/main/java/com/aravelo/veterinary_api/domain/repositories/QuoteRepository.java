package com.aravelo.veterinary_api.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aravelo.veterinary_api.domain.models.Quotes;

public interface QuoteRepository extends CrudRepository<Quotes, Long>{

}
