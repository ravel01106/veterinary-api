package com.aravelo.veterinary_api.infrastructure;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aravelo.veterinary_api.domain.models.Quote;
import com.aravelo.veterinary_api.domain.services.QuoteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(QuoteController.class)
public class QuoteControllerShould {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private QuoteService quoteService;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  public void showAllQuotes() throws Exception{
    List<Quote> quotesObtain = Arrays.asList(
        new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain"),
        new Quote("Harry", "Adrian Gonzalez","15/05/2024", "10:05", "diarrhoea"),
        new Quote("Bolt", "Miriam Cabrera","22/06/2024", "17:30", "annual review")
      );
    when(quoteService.getAllQuotes()).thenReturn(quotesObtain);

    mockMvc.perform(
      get("/api/v1/quotes")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.size()").value(3));
  }


  @Test
  public void showQuoteById() throws Exception{
    Long quoteId = 1L;
    Quote quoteInService = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");
    quoteInService.setId(quoteId);

    when(quoteService.getQuoteById(quoteId)).thenReturn(quoteInService);

    mockMvc.perform(
      get("/api/v1/quote/{quoteId}", quoteId)
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.id").value("1"))
    .andExpect(jsonPath("$.petName").value("John"))
    .andExpect(jsonPath("$.ownerName").value("Marco Perez"))
    .andExpect(jsonPath("$.date").value("12/04/2024"))
    .andExpect(jsonPath("$.time").value("12:45"))
    .andExpect(jsonPath("$.symptoms").value("stomach pain"));
  }


  @Test
  public void throwErrorWhenQuoteDoesNotFound() throws Exception{
    Long quoteId = 1L;

    when(quoteService.getQuoteById(quoteId)).thenReturn(null);

    mockMvc.perform(
      get("/api/v1/quote/{quoteId}", quoteId)
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isBadRequest())
    .andExpect(jsonPath("$.message").value("The quote with id 1 is not found."))
    .andExpect(jsonPath("$.errorType").value("QUOTE_NOT_FOUND"));

  }


  @Test
  public void createNewQuote() throws Exception{
    Long quoteId = 3L;
    Quote newQuote = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");
    Quote newQuoteInService = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");
    newQuoteInService.setId(quoteId);

    when(quoteService.createQuote(newQuote)).thenReturn(newQuoteInService);

    String quoteJson = objectMapper.writeValueAsString(newQuote);

    mockMvc.perform(post("/api/v1/quote")
    .contentType(MediaType.APPLICATION_JSON)
    .content(quoteJson))
    .andExpect(status().isCreated())
    .andExpect(jsonPath("$.id").value("3"))
    .andExpect(jsonPath("$.petName").value("John"))
    .andExpect(jsonPath("$.ownerName").value("Marco Perez"))
    .andExpect(jsonPath("$.date").value("12/04/2024"))
    .andExpect(jsonPath("$.time").value("12:45"))
    .andExpect(jsonPath("$.symptoms").value("stomach pain"));
  }


  @Test
  public void throwErrorWhenSendAnEmptyBodyCreateQuote() throws Exception{

    this.mockMvc.perform(post("/api/v1/quote")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isBadRequest())
    .andExpect(jsonPath("$.message").value("The body is empty!!"))
    .andExpect(jsonPath("$.errorType").value("EMPTY_BODY"));

  }


  @Test
  public void throwErrorIfExistQuoteWithSameDateAndTimeWhenCreateQuote() throws Exception{
    Quote newQuote = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");

    when(quoteService.existQuoteWithSameDateAndTime(newQuote.getDate(), newQuote.getTime())).thenReturn(true);

    String quoteJson = objectMapper.writeValueAsString(newQuote);

    mockMvc.perform(post("/api/v1/quote")
    .contentType(MediaType.APPLICATION_JSON)
    .content(quoteJson))
    .andExpect(status().isBadRequest())
    .andExpect(jsonPath("$.message").value("There is quote with the same date and time."))
    .andExpect(jsonPath("$.errorType").value("QUOTE_IS_ALREADY_EXIST"));

  }


  @Test
  public void updateQuoteById() throws Exception{
    Long quoteId = 1L;
    Quote updatedQuote = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    Quote updatedQuoteInService = new Quote("Marco", "David Perez","15/04/2024", "12:05", "stomach pain");
    updatedQuoteInService.setId(quoteId);

    when(quoteService.updatedQuote(quoteId, updatedQuote)).thenReturn(updatedQuoteInService);
    String quoteJson = objectMapper.writeValueAsString(updatedQuote);


    mockMvc.perform(put("/api/v1/quote/{quoteId}", quoteId)
    .contentType(MediaType.APPLICATION_JSON)
    .content(quoteJson))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.infoMessage").value("The quote is updated"))
    .andExpect(jsonPath("$.rowChanged").value("1"));
  }


  @Test
  public void throwErrorWhenSendAnEmptyBodyWhenUpdateQuote() throws Exception{
    Long quoteId = 1L;
    this.mockMvc.perform(put("/api/v1/quote/{quoteId}", quoteId)
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isBadRequest())
    .andExpect(jsonPath("$.message").value("The body is empty!!"))
    .andExpect(jsonPath("$.errorType").value("EMPTY_BODY"));

  }


  @Test
  public void throwErrorIfExistQuoteWithSameDateAndTimeWhenUpdateQuote() throws Exception{
    Long quoteId = 1L;
    Quote updatedQuote = new Quote("John", "Marco Perez","12/04/2024", "12:45", "stomach pain");

    when(quoteService.existQuoteWithSameDateAndTime(updatedQuote.getDate(), updatedQuote.getTime(), quoteId)).thenReturn(true);

    String quoteJson = objectMapper.writeValueAsString(updatedQuote);

    mockMvc.perform(put("/api/v1/quote/{quoteId}", quoteId)
    .contentType(MediaType.APPLICATION_JSON)
    .content(quoteJson))
    .andExpect(status().isBadRequest())
    .andExpect(jsonPath("$.message").value("There is quote with the same date and time."))
    .andExpect(jsonPath("$.errorType").value("QUOTE_IS_ALREADY_EXIST"));

  }


  @Test
  public void deleteQuoteById() throws Exception{
    Long quoteId = 1L;
    when(quoteService.deleteQuote(quoteId)).thenReturn(true);

    mockMvc.perform(delete("/api/v1/quote/{quoteId}", quoteId)
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.infoMessage").value("The quote is deleted"))
    .andExpect(jsonPath("$.rowChanged").value("1"));

  }


  @Test
  public void throwErrorIfDoesNotExistQuoteWhenDeleteQuote() throws Exception{
    Long quoteId = 1L;
    when(quoteService.deleteQuote(quoteId)).thenReturn(false);

    mockMvc.perform(delete("/api/v1/quote/{quoteId}", quoteId)
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isBadRequest())
    .andExpect(jsonPath("$.message").value("The quote with id 1 is not found."))
    .andExpect(jsonPath("$.errorType").value("QUOTE_NOT_FOUND"));

  }


  @Test
  public void throwErrorWhenAccessTheWrongPath() throws Exception {
    this.mockMvc.perform(get("/api/v1")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isNotFound())
    .andExpect(jsonPath("$.message").value("This path does not exist !!!"))
    .andExpect(jsonPath("$.errorType").value("FAIL_PATH"));
  }

}
