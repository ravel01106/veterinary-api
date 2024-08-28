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
    return new ArrayList<Quote>();
  }
  @Override
  public Quote getQuoteById(String id) {
    throw new UnsupportedOperationException("Unimplemented method 'getQuoteById'");
  }

  @Override
  public Quote createQuote(Quote quote) {
    throw new UnsupportedOperationException("Unimplemented method 'createQuote'");
  }

  @Override
  public Quote updatedQuote(String id, Quote quote) {
    throw new UnsupportedOperationException("Unimplemented method 'updatedQuote'");
  }

  @Override
  public void deleteQuote(String id) {
    throw new UnsupportedOperationException("Unimplemented method 'deleteQuote'");
  }

}
