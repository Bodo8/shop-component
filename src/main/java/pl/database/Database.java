package pl.database;

import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.sumsupmodel.SumsUp;

import java.util.List;

public interface Database {

  void saveReceipt(Receipt receipt);

  void removeReceipt(Receipt receipt);

  Receipt generateReceipt(List<ReceiptBody> receiptBodyList, SumsUp sumsUp);

  List<Receipt> getAllReceipt();

  void saveProduct(Product product);

  List<Product> getProducts();

  Product getProduct(String nameProduct);

  void deleteProduct(Product product);

  void clearListWithProductsAfterSave();

  void updateProduct(String productName, Product product);
}
