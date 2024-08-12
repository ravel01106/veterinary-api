package com.aravelo.veterinary_api.domain.services;

import com.aravelo.veterinary_api.domain.models.Quote;

public interface QuoteService {
  Quote[] getAllQuotes();
  Quote getQuoteByPetName(String petName);
  Quote getQuoteById(String id);
  Quote createQuote(Quote quote);
  Quote updatedQuote(String id, Quote quote);
  void deleteQuote(String id);
}
