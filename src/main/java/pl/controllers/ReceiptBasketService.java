package pl.controllers;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBasket;

import java.util.List;

@Service
public class ReceiptBasketService {

  @Resource
  private final ReceiptBasket receiptBasket;

  ReceiptBasketService(ReceiptBasket receiptBasket) {
    this.receiptBasket = receiptBasket;
  }

  int postReceipt() {
    Receipt receipt = receiptBasket
        .getCurrentReceipt();
    receiptBasket.saveReceipt(receipt);
    return receipt.getId();
  }

  List<Receipt> getReceiptAll() {
    return receiptBasket.getAllReceipt();
  }
}
