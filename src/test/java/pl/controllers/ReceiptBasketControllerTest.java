package pl.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.generatorfortests.ProductGenerator.generateProductAB;
import static pl.generatorfortests.ReceiptGenerator.generateOneReceipt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.dto.salesmodel.Receipt;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ReceiptBasketController.class)
public class ReceiptBasketControllerTest {

  @Autowired
  ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ReceiptBasketService service;

  @Test
  public void shouldGetReceiptInService() throws Exception {
    //given
    Receipt receipt = generateOneReceipt(generateProductAB());
    final List<Receipt> receiptList = Arrays.asList(receipt);
    final String receiptsAsString = mapper.writeValueAsString(receiptList);
    when(service.getReceiptAll()).thenReturn(receiptList);
    //when
    mockMvc.perform(
        get("/receipts"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json; charset=UTF-8"));
    List<Receipt> actual = service.getReceiptAll();
    String actualReceiptsAsString = mapper.writeValueAsString(actual);
    //then
    assertEquals(receiptsAsString, actualReceiptsAsString);
  }

  @Test
  public void shouldReturnPostReceiptInService() throws Exception {
    //when
    mockMvc.perform(
        post("/receipts")
            .contentType("application/json; charset=UTF-8"))
        .andExpect(status().isCreated());
    //then
    verify(service, times(1)).postReceipt();
  }
}