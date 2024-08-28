package com.aravelo.veterinary_api.domain.services;

import java.util.List;

import com.aravelo.veterinary_api.domain.models.Quote;

public interface QuoteService {
  List<Quote> getAllQuotes();
  Quote getQuoteById(String id);
  Quote createQuote(Quote quote);
  Quote updatedQuote(String id, Quote quote);
  void deleteQuote(String id);
}
