package pl.dto.salesmodel.bodyReceipt;

import java.math.BigDecimal;

public class ReceiptBodyBuilder {

  private String name;
  private BigDecimal price;
  private Integer quantityPurchase;
  private BigDecimal specialPrice;

  public static ReceiptBodyBuilder bodyBuilder() {
    return new ReceiptBodyBuilder();
  }

  public ReceiptBodyBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public ReceiptBodyBuilder withPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public ReceiptBodyBuilder withQuantityPurchase(Integer quantityPurchase) {
    this.quantityPurchase = quantityPurchase;
    return this;
  }

  public ReceiptBodyBuilder withSpecialPrice(BigDecimal specialPrice) {
    this.specialPrice = specialPrice;
    return this;
  }

  public ReceiptBody build() {
    return new ReceiptBody(name, price, quantityPurchase, specialPrice);
  }
}
