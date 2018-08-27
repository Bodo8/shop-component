package pl.calculators.impl;

import pl.calculators.Calculate;
import pl.dto.loadermodel.ProductsStore;
import pl.dto.loadermodel.basisproduct.BasisProduct;
import pl.dto.loadermodel.otherdiscounts.BigPurchase;
import pl.dto.loadermodel.otherdiscounts.CommonPurchase;
import pl.dto.loadermodel.otherdiscounts.UnitDiscount;
import pl.dto.salesmodel.productmodel.Product;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator implements Calculate {

  @Override
  public BigDecimal calculateSpecialPrice(ProductsStore productFromWarehouse, Integer quantity) {
    BasisProduct basisProduct = productFromWarehouse.getBasisProduct();
    UnitDiscount unitDiscount = productFromWarehouse.getUnitDiscount();
    BigDecimal price = basisProduct.getPrice();
    BigDecimal priceAfterUnitDiscount = unitDiscount.getPriceAfterDiscount();
    Integer unitDiscounts = unitDiscount.getNumberDiscountUnit();
    BigDecimal divisionOfProductByUnit = new BigDecimal(quantity / unitDiscounts);
    BigDecimal moduloOfProduct = new BigDecimal(quantity % unitDiscounts);
    BigDecimal specialPrice = (priceAfterUnitDiscount.multiply(divisionOfProductByUnit))
        .add(price.multiply(moduloOfProduct));
    return specialPrice;
  }

  @Override
  public BigDecimal calculateDiscountForBigPurchases(List<Product> listWithConsumerPurchases) {
    BigDecimal discountForBigPurchases = BigDecimal.ZERO;
    BigDecimal conversionFromPercent = new BigDecimal(100);
    BigPurchase bigPurchase = listWithConsumerPurchases.get(0).getProductStore().getBigPurchase();
    Integer amountAboveRebateIsDue = bigPurchase.getAmountAboveRebateIsDue();
    BigDecimal amountRebate = (bigPurchase
        .getAmountRebate()).divide(conversionFromPercent, context());
    BigDecimal sumSpecialPrice = calculateSumSpecialPrice(listWithConsumerPurchases);
    if (sumSpecialPrice.intValue() > amountAboveRebateIsDue) {
      discountForBigPurchases = discountForBigPurchases
          .add(sumSpecialPrice.multiply(amountRebate),
              context());
    }
    return discountForBigPurchases;
  }

  @Override
  public BigDecimal calculateTotalPrice(List<Product> listWithConsumerPurchases,
      BigDecimal discountForCommonPurchases
      , BigDecimal discountForBigPurchases) {

    BigDecimal totalPrice = calculateSumSpecialPrice(listWithConsumerPurchases)
        .subtract(discountForCommonPurchases.add(discountForBigPurchases), context());
    return totalPrice;
  }

  @Override
  public BigDecimal calculateDiscountForCommonPurchase(List<Product> listWithConsumerPurchases) {
    BigDecimal noDiscount = BigDecimal.ZERO;
    if (listWithConsumerPurchases.size() < 2) {
      return noDiscount;
    } else {
      BigDecimal whichRebateIsDue = getWhichRebateIsDue(listWithConsumerPurchases);
      return whichRebateIsDue;
    }
  }

  private BigDecimal getWhichRebateIsDue(List<Product> listWithConsumerPurchases) {
    BigDecimal noDiscount = BigDecimal.ZERO;
    List<BigDecimal> amountFourDiscountList = new ArrayList<>();
    List<BigDecimal> amountTwoDiscountList = new ArrayList<>();
    Integer rebateFour = 0;
    Integer rebateTwo = 0;
    for (Product product : listWithConsumerPurchases) {
      CommonPurchase commonPurchase = product.getProductStore().getCommonPurchase();
      Integer isRebateFour = commonPurchase.getFourProducts().getIsRebateFour();
      Integer isRebateTwo = commonPurchase.getTwoProducts().getIsRebateTwo();
      BigDecimal amountFourDiscount = commonPurchase.getFourProducts().getAmountDiscount();
      BigDecimal amountTwoDiscount = commonPurchase.getTwoProducts().getAmountDiscount();
      if (isRebateFour > 0) {
        amountFourDiscountList.add(amountFourDiscount);
        rebateFour = isRebateFour;
        if (isRebateTwo > 0) {
          amountTwoDiscountList.add(amountTwoDiscount);
          rebateTwo = isRebateTwo;
        }
      }
    }
    if (amountFourDiscountList.size() >= rebateFour && rebateFour != 0) {
      BigDecimal amountFourCommonDiscount = amountFourDiscountList.get(0);
      return amountFourCommonDiscount;
    } else {
      if (amountTwoDiscountList.size() >= rebateTwo && rebateTwo != 0) {
        BigDecimal amountTwoCommonDiscount = amountTwoDiscountList.get(0);
        return amountTwoCommonDiscount;
      }
    }
    return noDiscount;
  }

  private BigDecimal calculateSumSpecialPrice(List<Product> listWithConsumerPurchases) {
    BigDecimal sumSpecialPrice = listWithConsumerPurchases.stream()
        .map(Product::getSpecialPrice)
        .reduce(BigDecimal::add)
        .get();
    return sumSpecialPrice;
  }

  private static MathContext context() {
    MathContext context = new MathContext(6, RoundingMode.HALF_UP);
    return context;
  }
}
