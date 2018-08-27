package pl.database.impl.load;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.database.DataLoader;
import pl.database.impl.FileProcessor;
import pl.dto.loadermodel.ProductStoreBuilder;
import pl.dto.loadermodel.ProductsStore;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = {"active.database"}, havingValue = "file")
class LoadInFile implements DataLoader {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);
  private final String directoryName;
  ProductStoreProcessor processor = new ProductStoreProcessor();

  LoadInFile(String directoryName) {
    this.directoryName = directoryName;
  }

  Integer getNextId() {
    return atomicInteger.getAndIncrement();
  }

  @Override
  public void addProductToWarehouse(ProductsStore productsStore) {
    try {
      File filePath = FileProcessor.getProductsFilePath(directoryName);
      List<ProductsStore> listOfProductsStores = new ArrayList<>();
      if (filePath.isFile()) {
        listOfProductsStores = getProductsFromGivenFile(filePath);
      }
      listOfProductsStores.add(productsStore);
      Collections.sort(listOfProductsStores);
      processor.saveToFile(listOfProductsStores, filePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  private ProductsStore createProductsStore(String nameProduct, BigDecimal price,
      Integer numberDiscountUnit, BigDecimal priceAfterDiscount, BigDecimal fourAmountDiscount,
      Integer isRebateFour, BigDecimal twoAmountDiscount, Integer isRebateTwo,
      Integer quantityToStore, Integer amountAbove, BigDecimal amountRebate) {
    ProductsStore productsStore = ProductStoreBuilder.builder()
        .buildProductStoreWithGenerateId(getNextId(), nameProduct, price,
            numberDiscountUnit, priceAfterDiscount, fourAmountDiscount, isRebateFour,
            twoAmountDiscount, isRebateTwo, quantityToStore, amountAbove, amountRebate);
    return productsStore;
  }

  @Override
  public ProductsStore getProductFromWarehouse(String nameChoiceProduct) {

    List<ProductsStore> productsStoreList = getProductsStores();
    ProductsStore productFromWarehouse = null;
    for (ProductsStore store : productsStoreList) {
      String nameProduct = store.getBasisProduct().getProductName();
      if (nameChoiceProduct.equals(nameProduct)) {
        productFromWarehouse = store;
      }
    }
    return productFromWarehouse;
  }

  @Override
  public List<ProductsStore> getProductsStores() {
    List<ProductsStore> productsStores = new ArrayList<>();
    List<File> fileList = new ArrayList<>();
    List<String> listOfReceiptsPaths = new ArrayList<>();
    try {
      FileProcessor.getFilesIncludingSubdirectories(directoryName, fileList);
      for (File file : fileList) {
        listOfReceiptsPaths.add(file.getPath());
      }
      for (String listOfReceiptPath : listOfReceiptsPaths) {
        List<ProductsStore> helpList;
        helpList = processor.readFileAndConvertContentIntoListOfProductsStore(listOfReceiptPath);
        productsStores.addAll(helpList);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return productsStores.stream().sorted().collect(Collectors.toList());
  }

  private List<ProductsStore> getProductsFromGivenFile(File productsFile) {
    List<ProductsStore> list;
    try {
      String file = String.valueOf(productsFile);
      list = processor.readFileAndConvertContentIntoListOfProductsStore(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return list.stream().sorted().collect(Collectors.toList());
  }
}
