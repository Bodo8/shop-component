package pl.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import pl.FileProcessorForTest.FileConfigurationToTest;
import pl.FileProcessorForTest.FileProviderToTest;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBuilder;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.sumsupmodel.SumsUp;
import pl.dto.salesmodel.sumsupmodel.SumsUpBuilder;
import pl.generatorfortests.ProductBodyGenerator;
import pl.generatorfortests.ProductGenerator;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public abstract class DatabaseTest {

  protected abstract Database provideDatabase();

  @Test
  public void shouldSaveOneReceiptWithId() throws Exception {
    //given
    Database database = provideDatabase();
    FileProviderToTest.purgeDirectory(new File(FileConfigurationToTest.DATABASE_TEST_PATH));
    FileProviderToTest.purgeDirectory(new File(FileConfigurationToTest.DATALOADER_TEST_PATH));
    List<ReceiptBody> productBodies = ProductBodyGenerator.generateBodyProductAB();
    BigDecimal number = new BigDecimal(30);
    SumsUp sumsUp = SumsUpBuilder.builder()
        .withCommonPurchases(number)
        .withBigPurchases(number)
        .withTotalPrice(number)
        .build();
    //when
    Receipt expectedReceipt = ReceiptBuilder.builder()
        .buildWithoutId(productBodies, sumsUp);
    database.saveReceipt(expectedReceipt);
    //then
    Receipt actualReceipt = database.getAllReceipt().get(0);
    assertNotNull(actualReceipt);
    assertEquals(expectedReceipt, actualReceipt);
  }

  @Test
  public void addProductToList() throws Exception {
    //given
    Database database = provideDatabase();
    Integer quantity1 = 3;
    Integer expectedQuantityPurchase = 8;
    int expectedSize = 2;
    Product productA = ProductGenerator.generateProduct("A", quantity1);
    Product productB = ProductGenerator.generateProduct("B", quantity1);
    Product productA1 = ProductGenerator.generateProduct("A", expectedQuantityPurchase);
    //when
    database.saveProduct(productA);
    database.saveProduct(productB);
    database.saveProduct(productA1);
    int actualSize = database.getProducts().size();
    Integer actualQuantityPurchase = database.getProduct("A").getQuantityPurchase();
    //then
    assertEquals(expectedSize, actualSize);
    assertEquals(expectedQuantityPurchase, actualQuantityPurchase);
  }
}