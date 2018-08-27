package pl.dto.salesmodel.bodyReceipt;

import pl.dto.salesmodel.productmodel.BodyBasis;

import java.math.BigDecimal;

public class ReceiptBodyBuilder {

  private int idReceiptBody;
  private BodyBasis bodyBasis;
  private Integer quantityPurchase;
  private BigDecimal specialPrice;

  public static ReceiptBodyBuilder bodyBuilder() {
    return new ReceiptBodyBuilder();
  }

  public ReceiptBodyBuilder withIdProductBody(int idReceiptBody) {
    this.idReceiptBody = idReceiptBody;
    return this;
  }

  public ReceiptBodyBuilder withBasisProduct(BodyBasis bodyBasis) {
    this.bodyBasis = bodyBasis;
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
    return new ReceiptBody(idReceiptBody, bodyBasis, quantityPurchase, specialPrice);
  }

  public ReceiptBody buildWithOutId(BodyBasis bodyBasis, Integer quantityPurchase,
      BigDecimal specialPrice) {
    return ReceiptBodyBuilder.bodyBuilder()
        .withBasisProduct(bodyBasis)
        .withQuantityPurchase(quantityPurchase)
        .withSpecialPrice(specialPrice)
        .build();

  }
}
