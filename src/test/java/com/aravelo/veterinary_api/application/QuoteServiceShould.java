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
public class QuoteServiceShould {

  @Mock
  private QuoteRepository quoteRepository;

  @InjectMocks
  private QuoteServiceImpl quoteServiceImpl;

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

  @Test
  public void createNewQuote(){
    Quote newQuote = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");
    Quote newQuoteRepository = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");
    newQuoteRepository.setId(1L);

    when(quoteRepository.save(newQuote)).thenReturn(newQuoteRepository);

    Quote quoteCreated = quoteServiceImpl.createQuote(newQuote);

    verify(quoteRepository, times(1)).save(newQuote);

    assertEquals(newQuoteRepository, quoteCreated);

  }

  @Test
  public void updateQuoteById(){
    Long quoteId = 1L;
    Quote updatedQuote = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    Quote updatedQuoteRepository = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    updatedQuoteRepository.setId(quoteId);

    when(quoteRepository.existsById(quoteId)).thenReturn(true);
    when(quoteRepository.save(updatedQuoteRepository)).thenReturn(updatedQuoteRepository);

    Quote quoteResult = quoteServiceImpl.updatedQuote(quoteId, updatedQuote);

    verify(quoteRepository, times(1)).existsById(quoteId);
    verify(quoteRepository, times(1)).save(updatedQuoteRepository);

    assertEquals(updatedQuoteRepository, quoteResult);

  }

  @Test
  public void deleteQuoteById(){
    Long quoteId = 1L;

    when(quoteRepository.existsById(quoteId)).thenReturn(true);

    quoteServiceImpl.deleteQuote(quoteId);

    verify(quoteRepository, times(1)).existsById(quoteId);
    verify(quoteRepository, times(1)).deleteById(quoteId);
  }

  @Test
  public void returnFalseWhenTwoQuotesDoNotHaveTheSameDate(){
    Quote quoteToCompare = new Quote("Marco", "David Perez","16/04/2024", "12:05", "stomach pain");
    Quote quoteDB = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    quoteDB.setId(1L);

    Boolean result = quoteServiceImpl.haveSameDate(quoteDB, quoteToCompare);

    assertEquals(false, result);
  }

  @Test
  public void returnTrueWhenTwoQuotesHaveTheSameDate(){
    Quote quoteToCompare = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    Quote quoteDB = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    quoteDB.setId(1L);

    Boolean result = quoteServiceImpl.haveSameDate(quoteDB, quoteToCompare);

    assertEquals(true, result);
  }

  @Test
  public void returnTrueIfExistQuoteWithSameDateAndTime(){
    Quote quote = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    Quote quoteDB = new Quote("Pepe", "Gonzalez Perez","15/04/2024", "12:05", "anual revision");
    quoteDB.setId(1L);

    when(quoteRepository.findByDateAndTime(quote.getDate(), quote.getTime())).thenReturn(Arrays.asList(quoteDB));
    Boolean result = quoteServiceImpl.existQuoteWithSameDateAndTime(quote.getDate(), quote.getTime());

    verify(quoteRepository, times(1)).findByDateAndTime(quote.getDate(), quote.getTime());
    assertEquals(true, result);

  }

}
