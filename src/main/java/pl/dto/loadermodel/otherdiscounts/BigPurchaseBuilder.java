package pl.dto.loadermodel.otherdiscounts;

import java.math.BigDecimal;

public class BigPurchaseBuilder {

  int idBig;
  Integer amountAboveRebateIsDue;
  BigDecimal amountRebate;

  public static BigPurchaseBuilder builder() {
    return new BigPurchaseBuilder();
  }

  public BigPurchaseBuilder withIdBig(int idBig) {
    this.idBig = idBig;
    return this;
  }

  public BigPurchaseBuilder withAmountAboveRebateIsDue(Integer amountAboveRebateIsDue) {
    this.amountAboveRebateIsDue = amountAboveRebateIsDue;
    return this;
  }

  public BigPurchaseBuilder withAmountRebate(BigDecimal amountRebate) {
    this.amountRebate = amountRebate;
    return this;
  }

  public BigPurchase build() {
    return new BigPurchase(amountAboveRebateIsDue, amountRebate);
  }

}
