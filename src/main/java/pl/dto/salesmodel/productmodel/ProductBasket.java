package pl.dto.salesmodel.productmodel;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pl.calculators.Calculate;
import pl.database.DataLoader;
import pl.database.Database;
import pl.dto.loadermodel.ProductsStore;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductBasket {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);

  @Resource
  private final Database database;
  @Resource
  private final DataLoader loader;
  @Resource
  private final Calculate calculate;

  public ProductBasket(Database database, DataLoader loader, Calculate calculate) {
    this.database = database;
    this.loader = loader;
    this.calculate = calculate;
  }

  public void saveProduct(Product product) {
    database.saveProduct(product);
  }

  public List<Product> getProducts() {
    return database.getProducts();
  }

  public void deleteProduct(Product product) {
    database.deleteProduct(product);
  }

  public void clearListWithProductsAfterSave() {
    database.clearListWithProductsAfterSave();
  }

  public Product createProduct(PurchaseProduct purchaseProduct) {
    String nameChoiceProduct = purchaseProduct.getNameProduct();
    Integer quantityPurchase = purchaseProduct.getQuantity();
    ProductsStore productFromWarehouse = loader.getProductFromWarehouse(nameChoiceProduct);
    BigDecimal specialPrice = getSpecialPrice(productFromWarehouse, quantityPurchase);
    return ProductBuilder.builder()
        .buildWithGenerateId(getNextId(), productFromWarehouse, quantityPurchase, specialPrice);
  }

  public void updateProduct(String productName, Product product) {
    database.updateProduct(productName, product);
  }

  public Product getProduct(String nameProduct) {
    return database.getProduct(nameProduct);
  }

  private BigDecimal getSpecialPrice(ProductsStore productFromWarehouse, Integer quantity) {
    BigDecimal specialPrice = calculate.calculateSpecialPrice(productFromWarehouse, quantity);
    return specialPrice;
  }

  private Integer getNextId() {
    return atomicInteger.getAndIncrement();
  }
}
