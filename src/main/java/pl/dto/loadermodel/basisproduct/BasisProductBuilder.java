package pl.dto.loadermodel.basisproduct;

import java.math.BigDecimal;

public class BasisProductBuilder {

  private int idBasis;
  private String productName;
  private BigDecimal price;

  public static BasisProductBuilder builder() {
    return new BasisProductBuilder();
  }

  public BasisProductBuilder withIdBasis(int id) {
    this.idBasis = id;
    return this;
  }

  public BasisProductBuilder withProductName(String productName) {
    this.productName = productName;
    return this;
  }

  public BasisProductBuilder withPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public BasisProduct build() {
    return new BasisProduct(productName, price);
  }
}
