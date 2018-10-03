package pl.database.impl.sale;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.database.Database;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBuilder;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.sumsupmodel.SumsUp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * InMemoryDatabase - database int the memory.
 */
@Service
@ConditionalOnProperty(name = {"active.database"}, havingValue = "memory")
public class InMemoryDatabase implements Database {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);
  private static AtomicInteger productAtomicInteger = new AtomicInteger(1);
  private final Map<Integer, Receipt> receipts = new ConcurrentHashMap<>();
  private final Map<String, Product> products = new ConcurrentHashMap<>();


  private Integer getNextReceiptId() {
    return atomicInteger.getAndIncrement();
  }

  private Integer getNextProductId() {
    return productAtomicInteger.getAndIncrement();
  }

  /**
   * @param receipt - save given receipt into a pl.database.
   */
  @Override
  public void saveReceipt(Receipt receipt) {
    receipt.setId(getNextReceiptId());
    Integer receiptId = receipt.getId();
    receipts.put(receiptId, receipt);
  }

  /**
   * @return - all receipts from the database.
   */
  @Override
  public List<Receipt> getAllReceipt() {
    Collection<Receipt> receiptCollection = receipts.values();
    return receiptCollection.stream()
        .collect(Collectors.toCollection(() -> Collections.synchronizedList(new ArrayList<>())));
  }

  /**
   * @param receipt - deletes given receipt into a pl.database.
   */
  @Override
  public void removeReceipt(Receipt receipt) {
    receipts.remove(receipt.getId());
  }

  /**
   * @return - generates object Receipt from the ReceiptBody list.
   */
  @Override
  public Receipt generateReceipt(List<ReceiptBody> receiptBodyList,
      SumsUp sumsUp) {
    return ReceiptBuilder.builder()
        .buildWithGenerateId(getNextReceiptId(),
            receiptBodyList,
            sumsUp);
  }

  /**
   * @param product - saves given product into a current receipt and database.
   */
  @Override
  public synchronized void saveProduct(Product product) {
    product.setIdProduct(getNextProductId());
    String productName = product.getProductStore().getBasisProduct().getProductName();
    if (products.containsKey(productName)) {
      updateProduct(productName, product);
    }
    products.put(productName, product);
  }

  /**
   * @return - all products from the current receipt, from the database.
   */
  @Override
  public List<Product> getProducts() {
    Collection<Product> productCollection = products.values();
    return productCollection.stream()
        .collect(Collectors.toCollection(() -> Collections.synchronizedList(new ArrayList<>())));
  }

  /**
   * looking for a product in the currently purchase.
   */
  @Override
  public Product getProduct(String nameProduct) {
    return products.get(nameProduct);
  }

  /**
   * deletes product from the currently purchase.
   */
  @Override
  public void deleteProduct(Product product) {
    products.remove(product.getProductStore()
        .getBasisProduct().getProductName());
  }

  /**
   * clear currently list with Products after save.
   */
  @Override
  public void clearListWithProductsAfterSave() {
    products.clear();
  }

  /**
   * changes quantity the purchased of one product.
   */
  @Override
  public synchronized void updateProduct(String productName, Product product) {
    products.compute(productName, (key, product1) -> products.replace(productName, product));
  }
}
