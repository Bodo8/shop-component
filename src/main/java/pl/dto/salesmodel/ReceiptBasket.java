package pl.dto.salesmodel;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pl.calculators.Calculate;
import pl.database.Database;
import pl.dto.loadermodel.basisproduct.BasisProduct;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.bodyReceipt.ReceiptBodyBuilder;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.productmodel.ProductBasket;
import pl.dto.salesmodel.sumsupmodel.SumsUp;
import pl.dto.salesmodel.sumsupmodel.SumsUpBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ReceiptBasket.
 */
@Service
public class ReceiptBasket {

  @Resource
  private final Database database;
  @Resource
  private final ProductBasket productBasket;
  @Resource
  private final Calculate calculate;
  private BlockingQueue<Receipt> queue = new ArrayBlockingQueue<Receipt>(50);

  public ReceiptBasket(Database database, ProductBasket productBasket,
      Calculate calculate) {
    this.database = database;
    this.productBasket = productBasket;
    this.calculate = calculate;
  }

  /**
   * @return - all receipt.
   */
  public List<Receipt> getAllReceipt() {
    return database.getAllReceipt();
  }

  /**
   * @return - currently receipt.
   */
  public Receipt getCurrentReceipt() {
    List<Product> products = getCurrentProducts();
    BigDecimal discountForCommonPurchase = calculate.calculateDiscountForCommonPurchase(products);
    BigDecimal discountForBigPurchases = calculate.calculateDiscountForBigPurchases(products);
    BigDecimal totalPrice = calculate.calculateTotalPrice(products,
        discountForCommonPurchase, discountForBigPurchases);
    SumsUp sumsUp = getSumsUp(discountForCommonPurchase, discountForBigPurchases, totalPrice);
    List<ReceiptBody> receiptBodyList = createReceiptBodyList(products);
    Receipt receipt = database.generateReceipt(receiptBodyList, sumsUp);
    return receipt;
  }

  /**
   * @param receipt - is saves to database.
   */
  public void saveReceipt(Receipt receipt) throws InterruptedException {
    ProducerReceiptQueue producerReceiptQueue =
        new ProducerReceiptQueue(queue, receipt);
    startThread(new Thread(producerReceiptQueue));
    ConsumerReceiptQueue consumer = new ConsumerReceiptQueue(queue, database);
    startThread(new Thread(consumer));
  }

  /**
   * @return - currently receipt, which is edited by the customer.
   */
  private List<Product> getCurrentProducts() {
    List<Product> products = productBasket.getProducts();
    return products;
  }

  private List<ReceiptBody> createReceiptBodyList(List<Product> products) {
    List<ReceiptBody> receiptBodyList = new ArrayList<>();
    for (Product product : products) {
      BasisProduct basisProduct = product.getProductStore().getBasisProduct();
      Integer quantityPurchase = product.getQuantityPurchase();
      String productName = basisProduct.getProductName();
      BigDecimal price = basisProduct.getPrice();
      BigDecimal specialPrice = product.getSpecialPrice();
      ReceiptBody createReceiptBody = ReceiptBodyBuilder.bodyBuilder()
          .withName(productName)
          .withPrice(price)
          .withQuantityPurchase(quantityPurchase)
          .withSpecialPrice(specialPrice)
          .build();
      receiptBodyList.add(createReceiptBody);
    }
    return receiptBodyList;
  }

  private SumsUp getSumsUp(BigDecimal discountForCommonPurchase,
      BigDecimal discountForBigPurchases,
      BigDecimal totalPrice) {
    return SumsUpBuilder.builder()
        .buildWithoutId(discountForCommonPurchase, discountForBigPurchases, totalPrice);
  }

  static void startThread(Thread thread) throws InterruptedException {
    thread.start();
  }
}
