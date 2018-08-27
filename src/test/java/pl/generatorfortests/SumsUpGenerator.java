package pl.generatorfortests;

import pl.dto.salesmodel.sumsupmodel.SumsUp;
import pl.dto.salesmodel.sumsupmodel.SumsUpBuilder;

import java.math.BigDecimal;

public class SumsUpGenerator {

  public static SumsUp getSumsUp(BigDecimal discountForCommonPurchase,
      BigDecimal discountForBigPurchases, BigDecimal totalPrice) {

    return SumsUpBuilder.builder()
        .withCommonPurchases(discountForCommonPurchase)
        .withBigPurchases(discountForBigPurchases)
        .withTotalPrice(totalPrice)
        .build();
  }
}
