package pl.dto.salesmodel.productmodel;

import java.math.BigDecimal;

public class BodyBasisBuilder {

  private int idBodyBasis;
  private String bodyBasisName;
  private BigDecimal price;

  public static BodyBasisBuilder builder() {
    return new BodyBasisBuilder();
  }

  public BodyBasisBuilder withIdBodyBasis(int idBodyBasis) {
    this.idBodyBasis = idBodyBasis;
    return this;
  }

  public BodyBasisBuilder withBodyBasisName(String bodyBasisName) {
    this.bodyBasisName = bodyBasisName;
    return this;
  }

  public BodyBasisBuilder withPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public BodyBasis build() {
    return new BodyBasis(bodyBasisName, price);
  }

  public BodyBasis bodyWithOutId(String bodyBasisName, BigDecimal price) {
    return BodyBasisBuilder.builder()
        .withBodyBasisName(bodyBasisName)
        .withPrice(price)
        .build();
  }
}
