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

@Service
@ConditionalOnProperty(name = {"active.database"}, havingValue = "memory")
public class InMemoryDatabase implements Database {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);
  private static AtomicInteger productAtomicInteger = new AtomicInteger(1);
  private final Map<Integer, Receipt> receipts = new ConcurrentHashMap<>();
  private final Map<String, Product> products = new ConcurrentHashMap<>();


  Integer getNextReceiptId() {
    return atomicInteger.getAndIncrement();
  }

  Integer getNextProductId() {
    return productAtomicInteger.getAndIncrement();
  }

  @Override
  public void saveReceipt(Receipt receipt) {
    receipt.setId(getNextReceiptId());
    Integer receiptId = receipt.getId();
    receipts.put(receiptId, receipt);
  }

  @Override
  public List<Receipt> getAllReceipt() {
    Collection<Receipt> receiptCollection = receipts.values();
    return receiptCollection.stream()
        .collect(Collectors.toCollection(() -> Collections.synchronizedList(new ArrayList<>())));
  }

  @Override
  public void removeReceipt(Receipt receipt) {
    receipts.remove(receipt.getId());
  }

  @Override
  public Receipt generateReceipt(List<ReceiptBody> receiptBodyList,
      SumsUp sumsUp) {
    return ReceiptBuilder.builder()
        .buildWithGenerateId(getNextReceiptId(),
            receiptBodyList,
            sumsUp);
  }

  @Override
  public synchronized void saveProduct(Product product) {
    product.setIdProduct(getNextProductId());
    String productName = product.getProductStore().getBasisProduct().getProductName();
    if (products.containsKey(productName)) {
      updateProduct(productName, product);
    }
    products.put(productName, product);
  }

  @Override
  public List<Product> getProducts() {
    Collection<Product> productCollection = products.values();
    return productCollection.stream()
        .collect(Collectors.toCollection(() -> Collections.synchronizedList(new ArrayList<>())));
  }

  @Override
  public Product getProduct(String nameProduct) {
    return products.get(nameProduct);
  }

  @Override
  public void deleteProduct(Product product) {
    products.remove(product.getProductStore()
        .getBasisProduct().getProductName());
  }

  @Override
  public void clearListWithProductsAfterSave() {
    products.clear();
  }

  @Override
  public synchronized void updateProduct(String productName, Product product) {
    products.compute(productName, (key, product1) -> products.replace(productName, product));
  }
}
