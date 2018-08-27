package pl.dto.loadermodel;

import pl.dto.loadermodel.basisproduct.BasisProduct;
import pl.dto.loadermodel.basisproduct.BasisProductBuilder;
import pl.dto.loadermodel.otherdiscounts.BigPurchase;
import pl.dto.loadermodel.otherdiscounts.BigPurchaseBuilder;
import pl.dto.loadermodel.otherdiscounts.CommonPurchase;
import pl.dto.loadermodel.otherdiscounts.CommonPurchaseBuilder;
import pl.dto.loadermodel.otherdiscounts.UnitDiscount;
import pl.dto.loadermodel.otherdiscounts.UnitDiscountBuilder;
import pl.dto.loadermodel.otherdiscounts.productsforcommondiscount.FourProductsBuilder;
import pl.dto.loadermodel.otherdiscounts.productsforcommondiscount.TwoProductsBuilder;
import pl.dto.loadermodel.storehouse.StoreHouse;
import pl.dto.loadermodel.storehouse.StoreHouseBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductStoreBuilder {

  private int id;
  private BasisProduct basisProduct;
  private UnitDiscount unitDiscount;
  private CommonPurchase commonPurchase;
  private BigPurchase bigPurchase;
  private StoreHouse storeHouse;

  public static ProductStoreBuilder builder() {
    return new ProductStoreBuilder();
  }

  public ProductStoreBuilder withId(int id) {
    this.id = id;
    return this;
  }

  public ProductStoreBuilder withBasisProduct(BasisProduct basisProduct) {
    this.basisProduct = basisProduct;
    return this;
  }

  public ProductStoreBuilder withUnitDiscount(UnitDiscount unitDiscount) {
    this.unitDiscount = unitDiscount;
    return this;
  }

  public ProductStoreBuilder withCommonPurchase(CommonPurchase commonPurchase) {
    this.commonPurchase = commonPurchase;
    return this;
  }

  public ProductStoreBuilder withBigPurchase(BigPurchase bigPurchase) {
    this.bigPurchase = bigPurchase;
    return this;
  }

  public ProductStoreBuilder withStoreHouse(StoreHouse storeHouse) {
    this.storeHouse = storeHouse;
    return this;
  }

  public ProductsStore build() {
    return new ProductsStore(id, basisProduct, unitDiscount,
        commonPurchase, bigPurchase, storeHouse);
  }

  public ProductsStore buildProductStoreWithGenerateId(int id, String nameProduct, BigDecimal price,
      Integer numberDiscountUnit, BigDecimal priceAfterDiscount, BigDecimal fourAmountDiscount,
      Integer isRebateFour, BigDecimal twoAmountDiscount, Integer isRebateTwo,
      Integer quantityToStore, Integer amountAbove, BigDecimal amountRebate) {

    return ProductStoreBuilder.builder()
        .withId(id)
        .withBasisProduct(BasisProductBuilder.builder()
            .withIdBasis(id)
            .withProductName(nameProduct)
            .withPrice(price)
            .build())
        .withUnitDiscount(UnitDiscountBuilder.builder()
            .withIdDiscountUnit(id)
            .withNumberDiscountUnit(numberDiscountUnit)
            .withPriceAfterDiscount(priceAfterDiscount)
            .build())
        .withCommonPurchase(CommonPurchaseBuilder.builder()
            .withId(id)
            .witchFourProducts(FourProductsBuilder.builder()
                .withId(id)
                .withAmountDiscount(fourAmountDiscount)
                .withIsRebateFour(isRebateFour)
                .build())
            .withTwoProducts(TwoProductsBuilder.builder()
                .withId(id)
                .withAmountDiscount(twoAmountDiscount)
                .withIsRebateTwo(isRebateTwo)
                .build())
            .build())
        .withStoreHouse(StoreHouseBuilder.builder()
            .withStoreId(id)
            .withDate(LocalDate.now())
            .withQuantityToStore(quantityToStore)
            .build())
        .withBigPurchase(BigPurchaseBuilder.builder()
            .withIdBig(id)
            .withAmountAboveRebateIsDue(amountAbove)
            .withAmountRebate(amountRebate)
            .build())
        .build();
  }

  public ProductsStore buildProductStoreWithoutId(String nameProduct, BigDecimal price,
      Integer numberDiscountUnit, BigDecimal priceAfterDiscount, BigDecimal fourAmountDiscount,
      Integer isRebateFour, BigDecimal twoAmountDiscount, Integer isRebateTwo,
      Integer quantityToStore, Integer amountAbove, BigDecimal amountRebate) {

    return ProductStoreBuilder.builder()
        .withBasisProduct(BasisProductBuilder.builder()
            .withProductName(nameProduct)
            .withPrice(price)
            .build())
        .withUnitDiscount(UnitDiscountBuilder.builder()
            .withNumberDiscountUnit(numberDiscountUnit)
            .withPriceAfterDiscount(priceAfterDiscount)
            .build())
        .withCommonPurchase(CommonPurchaseBuilder.builder()
            .witchFourProducts(FourProductsBuilder.builder()
                .withAmountDiscount(fourAmountDiscount)
                .withIsRebateFour(isRebateFour)
                .build())
            .withTwoProducts(TwoProductsBuilder.builder()
                .withAmountDiscount(twoAmountDiscount)
                .withIsRebateTwo(isRebateTwo)
                .build())
            .build())
        .withStoreHouse(StoreHouseBuilder.builder()
            .withDate(LocalDate.now())
            .withQuantityToStore(quantityToStore)
            .build())
        .withBigPurchase(BigPurchaseBuilder.builder()
            .withAmountAboveRebateIsDue(amountAbove)
            .withAmountRebate(amountRebate)
            .build())
        .build();
  }
}
