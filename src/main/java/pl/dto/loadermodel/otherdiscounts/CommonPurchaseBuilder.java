package pl.dto.loadermodel.otherdiscounts;

import pl.dto.loadermodel.otherdiscounts.productsforcommondiscount.FourProducts;
import pl.dto.loadermodel.otherdiscounts.productsforcommondiscount.TwoProducts;

public class CommonPurchaseBuilder {

  int common_id;
  FourProducts fourProducts;
  TwoProducts twoProducts;

  public static CommonPurchaseBuilder builder() {
    return new CommonPurchaseBuilder();
  }

  public CommonPurchaseBuilder withId(int common_id) {
    this.common_id = common_id;
    return this;
  }

  public CommonPurchaseBuilder witchFourProducts(FourProducts fourProducts) {
    this.fourProducts = fourProducts;
    return this;
  }

  public CommonPurchaseBuilder withTwoProducts(TwoProducts twoProducts) {
    this.twoProducts = twoProducts;
    return this;
  }

  public CommonPurchase build() {
    return new CommonPurchase(fourProducts, twoProducts);
  }
}
