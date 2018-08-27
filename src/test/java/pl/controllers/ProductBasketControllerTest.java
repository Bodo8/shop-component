package pl.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.productmodel.PurchaseProduct;
import pl.generatorfortests.ProductGenerator;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductBasketController.class)
public class ProductBasketControllerTest {

  @Autowired
  ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductBasketService service;

  @Test
  public void shouldReturnPostProductInService() throws Exception {
    //given
    ArgumentCaptor<PurchaseProduct> argumentCaptor = ArgumentCaptor.forClass(PurchaseProduct.class);
    PurchaseProduct purchaseProduct = ProductGenerator.generateOnePurchaseProduct();
    final String purchaseProductAsString = mapper.writeValueAsString(purchaseProduct);
    //when
    mockMvc.perform(
        post("/product")
            .content(purchaseProductAsString)
            .contentType("application/json; charset=UTF-8"))
        .andExpect(status().isCreated());

    verify(service)
        .postProduct(argumentCaptor.capture());
    PurchaseProduct result = argumentCaptor.getValue();
    //then
    assertEquals(purchaseProduct.getNameProduct(), result.getNameProduct());
    assertEquals(purchaseProduct.getQuantity(), result.getQuantity());
  }

  @Test
  public void shouldGetProductsInService() throws Exception {
    //given
    Product product = ProductGenerator.generateProduct("A", 6);
    final List<Product> products = Arrays.asList(product);
    final String productsAsString = mapper.writeValueAsString(products);
    when(service.getListProducts()).thenReturn(products);
    //when
    mockMvc.perform(
        get("/product"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json; charset=UTF-8"));
    List<Product> actual = service.getListProducts();
    String actualProductsAsString = mapper.writeValueAsString(actual);
    //then
    assertEquals(productsAsString, actualProductsAsString);
  }
}