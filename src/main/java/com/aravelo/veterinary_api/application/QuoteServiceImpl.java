package com.aravelo.veterinary_api.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    Quote quote = null;
    Optional<Quote> quoteOptional = quoteRepository.findById(id);
    if (quoteOptional.isPresent()){
      quote = quoteOptional.get();
    }
    return quote;
  }

  @Override
  public Quote createQuote(Quote quote) {
    return quoteRepository.save(quote);
  }

  @Override
  public Quote updatedQuote(Long id, Quote quote) {
    Quote quoteUpdated = null;

    if (quoteRepository.existsById(id)) {
      Quote newQuote = new Quote();
      newQuote.setId(id);
      newQuote.setPetName(quote.getPetName());
      newQuote.setOwnerName(quote.getOwnerName());
      newQuote.setDate(quote.getDate());
      newQuote.setTime(quote.getTime());
      newQuote.setSymptoms(quote.getSymptoms());
      quoteUpdated = quoteRepository.save(newQuote);
    }

    return quoteUpdated;
  }

  @Override
  public Boolean deleteQuote(Long id) {
    if (quoteRepository.existsById(id)) {
      quoteRepository.deleteById(id);
      return true;
    }
    return false;

  }

  @Override
  public Boolean haveSameDate(Quote quote, Quote quoteToCompare) {
    if (quote.getDate() == quoteToCompare.getDate() &&
    quote.getTime() == quoteToCompare.getTime()){
      return true;
    }
    return false;
  }

  @Override
  public Boolean existQuoteWithSameDateAndTime(String date, String time) {
    List<Quote> quotesWithSameDateAndTime = quoteRepository.findByDateAndTime(date, time);
    return !quotesWithSameDateAndTime.isEmpty();
  }

  @Override
  public Boolean existQuoteWithSameDateAndTime(String date, String time, Long updatedQuoteId) {
    List<Quote> quotes = quoteRepository.findByIdNot(updatedQuoteId);
    List<Quote> quoteWithSameDateAndTime = quotes.stream().filter( quote -> quote.getDate().equals(date) && quote.getTime().equals(time)).toList();
    return !quoteWithSameDateAndTime.isEmpty();
  }
}
