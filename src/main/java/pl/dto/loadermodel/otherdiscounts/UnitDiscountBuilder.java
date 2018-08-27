package pl.dto.loadermodel.otherdiscounts;

import java.math.BigDecimal;

public class UnitDiscountBuilder {

  private int idDiscountUnit;
  private Integer numberDiscountUnit;
  private BigDecimal priceAfterDiscount;

  public static UnitDiscountBuilder builder() {
    return new UnitDiscountBuilder();
  }

  public UnitDiscountBuilder withIdDiscountUnit(int idDiscountUnit) {
    this.idDiscountUnit = idDiscountUnit;
    return this;
  }

  public UnitDiscountBuilder withNumberDiscountUnit(Integer numberDiscountUnit) {
    this.numberDiscountUnit = numberDiscountUnit;
    return this;
  }

  public UnitDiscountBuilder withPriceAfterDiscount(BigDecimal priceAfterDiscount) {
    this.priceAfterDiscount = priceAfterDiscount;
    return this;
  }

  public UnitDiscount build() {
    return new UnitDiscount(numberDiscountUnit, priceAfterDiscount);
  }
}
