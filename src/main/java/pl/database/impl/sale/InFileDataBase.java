package pl.database.impl.sale;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.database.Database;
import pl.database.impl.FileProcessor;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBuilder;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.sumsupmodel.SumsUp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = {"active.database"}, havingValue = "file")
public class InFileDataBase implements Database {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);
  private final String directoryName;
  ReceiptProcessor processor = new ReceiptProcessor();

  InFileDataBase(String directoryName) {
    this.directoryName = directoryName;
  }

  Integer getNextReceiptId() {
    return atomicInteger.getAndIncrement();
  }

  @Override
  public void saveReceipt(Receipt receipt) {
    try {
      File filePath = FileProcessor.getReceiptFilePath(directoryName, receipt);
      List<Receipt> listOfExistingReceipts = new ArrayList<>();
      if (filePath.isFile()) {
        listOfExistingReceipts = getReceiptFromGivenFile(filePath);
      }
      listOfExistingReceipts.add(receipt);
      Collections.sort(listOfExistingReceipts);
      processor.saveToFile(listOfExistingReceipts, filePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void removeReceipt(Receipt receipt) {
    try {
      File filePath = FileProcessor.getReceiptFilePath(directoryName, receipt);
      if (filePath.isFile()) {
        List<Receipt> receiptsFromFile = getReceiptFromGivenFile(filePath);

        Receipt foundInvoice = receiptsFromFile
            .stream()
            .filter(invoice1 -> invoice1.getId() == receipt.getId())
            .findFirst()
            .get();
        receiptsFromFile.remove(receiptsFromFile.indexOf(foundInvoice));
        if (receiptsFromFile.size() == 0) {
          filePath.delete();
        } else {
          processor.saveToFile(receiptsFromFile
              .stream()
              .sorted()
              .collect(Collectors.toList()), filePath);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Receipt> getAllReceipt() {
    List<Receipt> receiptList = new ArrayList<>();
    List<File> fileList = new ArrayList<>();
    List<String> listOfReceiptsPaths = new ArrayList<>();
    try {
      FileProcessor.getFilesIncludingSubdirectories(directoryName, fileList);
      for (File file : fileList) {
        listOfReceiptsPaths.add(file.getPath());
      }
      for (String listOfReceiptPath : listOfReceiptsPaths) {
        List<Receipt> helpList;
        helpList = processor.readFileAndConvertContentIntoListOfReceipts(listOfReceiptPath);
        receiptList.addAll(helpList);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return receiptList.stream().sorted().collect(Collectors.toList());
  }

  @Override
  public void saveProduct(Product product) {

  }

  @Override
  public List<Product> getProducts() {
    return null;
  }

  @Override
  public Product getProduct(String nameProduct) {
    return null;
  }

  @Override
  public void deleteProduct(Product product) {

  }

  @Override
  public void clearListWithProductsAfterSave() {

  }

  @Override
  public void updateProduct(String productName, Product product) {

  }

  @Override
  public Receipt generateReceipt(List<ReceiptBody> receiptBodyList,
      SumsUp sumsUp) {
    return ReceiptBuilder.builder()
        .buildWithGenerateId(getNextReceiptId(), receiptBodyList,
            sumsUp);
  }

  private List<Receipt> getReceiptFromGivenFile(File invoiceFile) {
    List<Receipt> list;
    try {
      String file = String.valueOf(invoiceFile);
      list = processor.readFileAndConvertContentIntoListOfReceipts(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return list.stream().sorted().collect(Collectors.toList());
  }
}
