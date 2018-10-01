package pl.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.dto.salesmodel.Receipt;

import java.io.IOException;
import java.util.List;

/**
 * ReceiptBasket controller.
 */
@RestController
@RequestMapping("/receipts")
public class ReceiptBasketController {

  private final ReceiptBasketService service;

  ReceiptBasketController(ReceiptBasketService service) {
    this.service = service;
  }

  /**
   * @return - all receipt from the database.
   * @throws IOException - when no success in reading receipt.
   */
  @ApiOperation(value = "Get all receipt",
      response = Receipt.class, responseContainer = "List")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<Receipt> getReceiptAll() throws IOException {
    return service.getReceiptAll();
  }

  /**
   * @return - all receipts from the database.
   * @throws IOException - when no success reading receipts.
   */
  @ApiOperation(value = "Post another receipt",
      response = Integer.class)
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  int postReceipt() throws IOException {
    return service.postReceipt();
  }
}
