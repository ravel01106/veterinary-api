package com.aravelo.veterinary_api.domain.services;

import java.util.List;

import com.aravelo.veterinary_api.domain.models.Quote;

public interface QuoteService {
  List<Quote> getAllQuotes();
  Quote getQuoteById(Long id);
  Quote createQuote(Quote quote);
  Quote updatedQuote(Long id, Quote quote);
  Boolean deleteQuote(Long id);
  Boolean haveSameDate(Quote quote, Quote quoteToCompare);
}
