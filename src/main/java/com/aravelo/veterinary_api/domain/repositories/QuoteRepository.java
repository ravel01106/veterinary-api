package com.aravelo.veterinary_api.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aravelo.veterinary_api.domain.models.Quote;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long>{

  List<Quote> findByDateAndTime(String date, String time);
  List<Quote> findByIdNot(Long id);

}
