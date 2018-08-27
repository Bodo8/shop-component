package pl.dto.salesmodel.productmodel;

import pl.dto.loadermodel.ProductsStore;

import java.math.BigDecimal;

public class ProductBuilder {

  private int idProduct;
  private ProductsStore productStore;
  private Integer quantityPurchase;
  private BigDecimal specialPrice;

  public static ProductBuilder builder() {
    return new ProductBuilder();
  }

  public ProductBuilder withIdProduct(int idProduct) {
    this.idProduct = idProduct;
    return this;
  }

  public ProductBuilder withProductsStore(ProductsStore productStore) {
    this.productStore = productStore;
    return this;
  }

  public ProductBuilder withQuantityPurchase(Integer quantityPurchase) {
    this.quantityPurchase = quantityPurchase;
    return this;
  }

  public ProductBuilder withSpecialPrice(BigDecimal specialPrice) {
    this.specialPrice = specialPrice;
    return this;
  }

  public Product build() {
    return new Product(idProduct, productStore, quantityPurchase, specialPrice);
  }

  public Product buildWithGenerateId(int idProduct, ProductsStore productFromWarehouse,
      Integer quantityPurchase,
      BigDecimal specialPrice) {
    return ProductBuilder.builder()
        .withIdProduct(idProduct)
        .withProductsStore(productFromWarehouse)
        .withQuantityPurchase(quantityPurchase)
        .withSpecialPrice(specialPrice)
        .build();
  }

  public Product buildWithOutId(ProductsStore productFromWarehouse,
      Integer quantityPurchase,
      BigDecimal specialPrice) {
    return ProductBuilder.builder()
        .withProductsStore(productFromWarehouse)
        .withQuantityPurchase(quantityPurchase)
        .withSpecialPrice(specialPrice)
        .build();
  }
}
