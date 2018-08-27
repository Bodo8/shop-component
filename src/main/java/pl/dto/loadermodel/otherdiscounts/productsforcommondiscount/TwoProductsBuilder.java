package pl.dto.loadermodel.otherdiscounts.productsforcommondiscount;

import java.math.BigDecimal;

public class TwoProductsBuilder {

  int two_id;
  BigDecimal amountDiscount;
  Integer isRebateTwo;

  public static TwoProductsBuilder builder() {
    return new TwoProductsBuilder();
  }

  public TwoProductsBuilder withId(int four_id) {
    this.two_id = four_id;
    return this;
  }

  public TwoProductsBuilder withAmountDiscount(BigDecimal amountDiscount) {
    this.amountDiscount = amountDiscount;
    return this;
  }

  public TwoProductsBuilder withIsRebateTwo(Integer isRebateTwo) {
    this.isRebateTwo = isRebateTwo;
    return this;
  }

  public TwoProducts build() {
    return new TwoProducts(amountDiscount, isRebateTwo);
  }
}
