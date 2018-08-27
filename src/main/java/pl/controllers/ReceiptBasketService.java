package pl.controllers;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBasket;

import java.util.List;

@Service
public class ReceiptBasketService {

  @Resource
  private final ReceiptBasket receiptToBasket;

  ReceiptBasketService(ReceiptBasket receiptToBasket) {
    this.receiptToBasket = receiptToBasket;
  }

  int postReceipt() {
    Receipt receipt = receiptToBasket
        .getCurrentReceipt();
    receiptToBasket.saveReceipt(receipt);
    return receipt.getId();
  }

  List<Receipt> getReceiptAll() {
    return receiptToBasket.getAllReceipt();
  }
}
