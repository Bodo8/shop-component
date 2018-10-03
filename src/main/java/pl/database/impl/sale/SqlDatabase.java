package pl.database.impl.sale;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.database.Database;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBuilder;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.sumsupmodel.SumsUp;
import pl.repository.ProductRepository;
import pl.repository.ReceiptRepository;

import java.util.List;

/**
 * SqlDatabase - database int the MySQL database.
 */
@Repository
@ConditionalOnProperty(name = {"active.database"}, havingValue = "sql")
@Transactional
public class SqlDatabase implements Database {

  @Autowired
  ReceiptRepository receiptRepository;

  @Autowired
  ProductRepository productRepository;

  /**
   * @param receipt - saves given receipt into a pl.database.
   */
  @Override
  public void saveReceipt(Receipt receipt) {
    receiptRepository.save(receipt);
  }

  /**
   * @param receipt - deletes given receipt into a pl.database.
   */
  @Override
  public void removeReceipt(Receipt receipt) {
    receiptRepository.delete(receipt);
  }

  /**
   * @return - generates object Receipt from the ReceiptBody list.
   */
  @Override
  public Receipt generateReceipt(List<ReceiptBody> receiptBodyList,
      SumsUp sumsUp) {
    return ReceiptBuilder.builder()
        .buildWithoutId(receiptBodyList,
            sumsUp);
  }

  /**
   * @return - all receipt from pl.database.
   */
  @Override
  public List<Receipt> getAllReceipt() {
    return (List<Receipt>) receiptRepository.findAll();
  }

  /**
   * @param product - it is single consumer purchase.
   */
  @Override
  public synchronized void saveProduct(Product product) {
    String productName = product.getProductStore().getBasisProduct().getProductName();
    if (productRepository.existsProducts()) {
      List<Product> oldProducts = productRepository.findAll();
      Product oldProduct = oldProducts.stream()
          .filter(p -> p.getProductStore().getBasisProduct().getProductName().equals(productName))
          .findFirst()
          .orElse(null);
      if (oldProduct != null) {
        updateProduct(oldProduct, product);
      }
    }
    productRepository.save(product);
  }

  /**
   * @return - currently customer purchase.
   */
  @Override
  public List<Product> getProducts() {
    return (List<Product>) productRepository.findAll();
  }

  /**
   * looking for a product in the currently purchase.
   */
  @Override
  public Product getProduct(String nameProduct) {
    List<Product> productsList = (List<Product>) productRepository.findByNameProduct(nameProduct);
    return productsList.get(0);
  }

  /**
   * deletes product from the currently purchase.
   */
  @Override
  public void deleteProduct(Product product) {
    productRepository.delete(product);
  }

  /**
   * clear currently list with Products after save.
   */
  @Override
  public void clearListWithProductsAfterSave() {
    productRepository.deleteAll();
  }

  /**
   * changes quantity the purchased of one product.
   */
  @Override
  public void updateProduct(String productName, Product product) {
    Integer quantityPurchase = product.getQuantityPurchase();
    Product oldProduct = productRepository.findByNameProduct(productName).get(0);
    oldProduct.setQuantityPurchase(quantityPurchase);
    productRepository.save(oldProduct);
  }

  private void updateProduct(Product oldProduct, Product product) {
    int idProduct = oldProduct.getIdProduct();
    productRepository.deleteById(idProduct);
    productRepository.save(product);
  }

  private boolean existsProductsInTable() {
    return productRepository.existsProducts();
  }
}
