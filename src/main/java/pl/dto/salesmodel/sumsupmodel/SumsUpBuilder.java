package pl.dto.salesmodel.sumsupmodel;

import java.math.BigDecimal;

public class SumsUpBuilder {

  private int idSumsUp;
  private BigDecimal discountForCommonPurchases;
  private BigDecimal discountForBigPurchases;
  private BigDecimal totalPrice;

  public static SumsUpBuilder builder() {
    return new SumsUpBuilder();
  }

  public SumsUpBuilder withIdSumsUp(int idSumsUp) {
    this.idSumsUp = idSumsUp;
    return this;
  }

  public SumsUpBuilder withCommonPurchases(BigDecimal discountForCommonPurchases) {
    this.discountForCommonPurchases = discountForCommonPurchases;
    return this;
  }

  public SumsUpBuilder withBigPurchases(BigDecimal discountForBigPurchases) {
    this.discountForBigPurchases = discountForBigPurchases;
    return this;
  }

  public SumsUpBuilder withTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public SumsUp build() {
    return new SumsUp(discountForCommonPurchases, discountForBigPurchases, totalPrice);
  }

  public SumsUp buildWithoutId(BigDecimal discountForCommonPurchases
      , BigDecimal discountForBigPurchases
      , BigDecimal totalPrice) {
    return SumsUpBuilder.builder()
        .withCommonPurchases(discountForCommonPurchases)
        .withBigPurchases(discountForBigPurchases)
        .withTotalPrice(totalPrice)
        .build();
  }
}
