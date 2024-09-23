package com.aravelo.veterinary_api.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravelo.veterinary_api.domain.error.QuoteIsAlreadyExistException;
import com.aravelo.veterinary_api.domain.error.QuoteNotFoundException;
import com.aravelo.veterinary_api.domain.models.Quote;
import com.aravelo.veterinary_api.domain.models.ResultMessage;
import com.aravelo.veterinary_api.domain.services.QuoteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1")
public class QuoteController {

  @Autowired
  private QuoteService quoteService;

  @GetMapping("/quotes")
  public ResponseEntity<List<Quote>> showAllQuotes(){
    List<Quote> quotes =  quoteService.getAllQuotes();
    return new ResponseEntity<List<Quote>>(quotes, HttpStatus.OK);
  }

  @GetMapping("/quote/{quoteId}")
  public ResponseEntity<Quote> showQuoteById(@PathVariable("quoteId") Long quoteId) throws QuoteNotFoundException{
    Quote quoteById = quoteService.getQuoteById(quoteId);
    if (quoteById == null) {
      String errorMessage = "The quote with id " + quoteId + " is not found.";
      throw new QuoteNotFoundException(errorMessage);
    }
    return new ResponseEntity<Quote>(quoteById, HttpStatus.OK);
  }

  @PostMapping("/quote")
  public ResponseEntity<Quote> createNewQuote(@RequestBody Quote quote) throws QuoteIsAlreadyExistException{

    if (quoteService.existQuoteWithSameDateAndTime(quote.getDate(), quote.getTime())) {
      throw new QuoteIsAlreadyExistException("There is quote with the same date and time.");
    }

    Quote _quote = quoteService.createQuote(quote);
    return new ResponseEntity<Quote>(_quote, HttpStatus.CREATED);

  }

  @PutMapping("/quote/{quoteId}")
  public ResponseEntity<ResultMessage> updateQuoteById(@PathVariable("quoteId") Long quoteId, @RequestBody Quote quote) throws QuoteIsAlreadyExistException{

    if (quoteService.existQuoteWithSameDateAndTime(quote.getDate(), quote.getTime(), quoteId)) {
      throw new QuoteIsAlreadyExistException("There is quote with the same date and time.");
    }
    quoteService.updatedQuote(quoteId, quote);
    return new ResponseEntity<ResultMessage>(new ResultMessage("The quote is updated", 1), HttpStatus.OK);

  }

  @DeleteMapping("/quote/{quoteId}")
  public ResponseEntity<ResultMessage> deleteQuoteById(@PathVariable("quoteId") Long quoteId) throws QuoteNotFoundException {

    if (!quoteService.deleteQuote(quoteId)) {
      String errorMessage = "The quote with id " + quoteId + " is not found.";
      throw new QuoteNotFoundException(errorMessage);
    }
    return new ResponseEntity<ResultMessage>(new ResultMessage("The quote is deleted", 1), HttpStatus.OK);


  }

}
