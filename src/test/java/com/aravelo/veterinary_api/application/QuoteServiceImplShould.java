package com.aravelo.veterinary_api.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aravelo.veterinary_api.domain.models.Quote;
import com.aravelo.veterinary_api.domain.repositories.QuoteRepository;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceImplShould {

  @Mock
  private QuoteRepository quoteRepository;

  @InjectMocks
  private QuoteServiceImpl quoteServiceImpl;

  // provide all quotes
  // give a quote by id
  // create a new quote
  // update a quote by id
  // delete a quote by id

  @Test
  public void provideAllQuotes(){
    List<Quote> quotesObtain = Arrays.asList(
        new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain"),
        new Quote("Harry", "Adrian Gonzalez","15/05/2024", "10:05", "diarrhoea"),
        new Quote("Bolt", "Miriam Cabrera","22/06/2024", "17:30", "annual review")
      );
    when(quoteRepository.findAll()).thenReturn(quotesObtain);

    List<Quote> quotes = quoteServiceImpl.getAllQuotes();

    verify(quoteRepository, times(1)).findAll();

    assertEquals(quotes.size(), 3);

  }

  @Test
  public void giveQuoteById(){
    Long quoteId = 1L;
    Quote quoteInRepository = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");
    quoteInRepository.setId(quoteId);

    when(quoteRepository.findById(quoteId)).thenReturn(Optional.of(quoteInRepository));

    Quote quote = quoteServiceImpl.getQuoteById(quoteId);

    verify(quoteRepository, times(1)).findById(quoteId);

    assertEquals(quoteInRepository, quote);

  }
}
