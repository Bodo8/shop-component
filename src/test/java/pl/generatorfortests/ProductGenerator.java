package pl.generatorfortests;

import pl.calculators.impl.DiscountCalculator;
import pl.dto.loadermodel.ProductsStore;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.productmodel.ProductBuilder;
import pl.dto.salesmodel.productmodel.PurchaseProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductGenerator {

  public static List<Product> generateProductABCD() {
    DiscountCalculator calculator = new DiscountCalculator();
    Integer quantityPurchase = 13;
    Integer quantity = 22;
    List<Product> productListABCD = new ArrayList<>();
    List<ProductsStore> productsStores = StoreProductGenerator.generateListWarehouse();
    for (int i = 0; i < 4; i++) {
      ProductsStore productFromWarehouse = productsStores.get(i);
      BigDecimal specialPrice = calculator
          .calculateSpecialPrice(productFromWarehouse, quantityPurchase);
      Product product = ProductBuilder.builder()
          .buildWithGenerateId((i + 1), productFromWarehouse, quantity, specialPrice);
      productListABCD.add(product);
    }
    return productListABCD;
  }

  public static List<Product> generateProductAB() {
    DiscountCalculator calculator = new DiscountCalculator();
    Integer quantityPurchase = 9;
    Integer quantity = 21;
    List<Product> productListAB = new ArrayList<>();
    List<ProductsStore> productsStores = StoreProductGenerator.generateListWarehouse();
    for (int i = 0; i < 2; i++) {
      ProductsStore productFromWarehouse = productsStores.get(i);
      BigDecimal specialPrice = calculator
          .calculateSpecialPrice(productFromWarehouse, quantityPurchase);
      Product product = ProductBuilder.builder()
          .buildWithGenerateId((i + 1), productFromWarehouse, quantity, specialPrice);
      productListAB.add(product);
    }
    return productListAB;
  }

  public static Product generateProduct(String name, Integer quantity) {
    ProductsStore productsStore = StoreProductGenerator.getOneProductStore(name);
    Product product = ProductBuilder.builder()
        .buildWithOutId(productsStore, quantity, new BigDecimal(20));
    return product;
  }

  public static PurchaseProduct generateOnePurchaseProduct() {
    return new PurchaseProduct(1, "A", 8);
  }
}
