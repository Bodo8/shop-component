package pl.generatorfortests;

import pl.dto.loadermodel.basisproduct.BasisProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasisProductGenerator {

  public static List<BasisProduct> productsABForTest() {
    List<BasisProduct> basisProductsAB = new ArrayList<>();
    BasisProduct basisProductA = new BasisProduct("A",
        new BigDecimal(40));
    BasisProduct basisProductB = new BasisProduct("B",
        new BigDecimal(10));
    basisProductsAB.add(basisProductA);
    basisProductsAB.add(basisProductB);
    return basisProductsAB;
  }

  public static List<BasisProduct> productsABCDForTest() {
    List<BasisProduct> basisProducts = new ArrayList<>();
    BasisProduct basisProductA = new BasisProduct("A",
        new BigDecimal(40));
    BasisProduct basisProductB = new BasisProduct("B",
        new BigDecimal(10));
    BasisProduct basisProductC = new BasisProduct("C",
        new BigDecimal(30));
    BasisProduct basisProductD = new BasisProduct("D",
        new BigDecimal(25));
    basisProducts.add(basisProductA);
    basisProducts.add(basisProductB);
    basisProducts.add(basisProductC);
    basisProducts.add(basisProductD);
    return basisProducts;
  }
}
