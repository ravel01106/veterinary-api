package com.aravelo.veterinary_api.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aravelo.veterinary_api.domain.models.Quote;
import com.aravelo.veterinary_api.domain.repositories.QuoteRepository;
import com.aravelo.veterinary_api.domain.services.QuoteService;

public class QuoteServiceImpl implements QuoteService{

  @Autowired
  QuoteRepository quoteRepository;


  @Override
  public List<Quote> getAllQuotes() {
    List<Quote> quotes = new ArrayList<>();
    quoteRepository.findAll().forEach(quotes::add);
    return quotes;
  }
  @Override
  public Quote getQuoteById(Long id) {
    return null;
  }

  @Override
  public Quote createQuote(Quote quote) {
    throw new UnsupportedOperationException("Unimplemented method 'createQuote'");
  }

  @Override
  public Quote updatedQuote(Long id, Quote quote) {
    throw new UnsupportedOperationException("Unimplemented method 'updatedQuote'");
  }

  @Override
  public void deleteQuote(Long id) {
    throw new UnsupportedOperationException("Unimplemented method 'deleteQuote'");
  }

}
