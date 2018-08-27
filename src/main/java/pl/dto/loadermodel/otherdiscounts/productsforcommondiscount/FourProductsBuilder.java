package pl.dto.loadermodel.otherdiscounts.productsforcommondiscount;

import java.math.BigDecimal;

public class FourProductsBuilder {

  int four_id;
  BigDecimal amountDiscount;
  Integer isRebateFour;

  public static FourProductsBuilder builder() {
    return new FourProductsBuilder();
  }

  public FourProductsBuilder withId(int two_id) {
    this.four_id = two_id;
    return this;
  }

  public FourProductsBuilder withAmountDiscount(BigDecimal amountDiscount) {
    this.amountDiscount = amountDiscount;
    return this;
  }

  public FourProductsBuilder withIsRebateFour(Integer isRebateFour) {
    this.isRebateFour = isRebateFour;
    return this;
  }

  public FourProducts build() {
    return new FourProducts(amountDiscount, isRebateFour);
  }
}