package pl.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static pl.generatorfortests.ProductGenerator.generateProductAB;
import static pl.generatorfortests.ProductGenerator.generateProductABCD;
import static pl.generatorfortests.ReceiptGenerator.generateReceiptListForTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBasket;
import pl.dto.salesmodel.productmodel.ProductBasket;

import java.util.List;

public class ReceiptBasketServiceTest {

  @InjectMocks
  private ReceiptBasketService receiptBasketService;

  @Mock
  ReceiptBasket receiptToBasket;

  @Mock
  ProductBasket productToBasket;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void shouldReturnReceiptBook() throws Exception {
    //when
    new ReceiptBasketService(receiptToBasket);
    //then
    assertThat(receiptToBasket).isNotNull();
  }


  @Test
  public void shouldReturnReceipts() throws Exception {
    //given
    List<Receipt> receipts = generateReceiptListForTests(generateProductABCD(),
        generateProductAB());
    when(receiptToBasket.getAllReceipt()).thenReturn(receipts);
    //when
    List<Receipt> actualReceipts = receiptBasketService.getReceiptAll();
    //then
    verify(receiptToBasket).getAllReceipt();
    assertEquals(receipts, actualReceipts);
  }
}