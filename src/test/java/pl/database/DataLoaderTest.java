package pl.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import pl.FileProcessorForTest.FileConfigurationToTest;
import pl.FileProcessorForTest.FileProviderToTest;
import pl.dto.loadermodel.ProductsStore;
import pl.generatorfortests.StoreProductGenerator;

import java.io.File;

public abstract class DataLoaderTest {

  protected abstract DataLoader loader();

  @Test
  public void addProductToDatabase() throws Exception {
    //given
    DataLoader dataLoader = loader();
    String filePath = FileConfigurationToTest.DATALOADER_TEST_PATH;
    ProductsStore expectedProductsStore = StoreProductGenerator.getOneProductStore("A");
    //when
    dataLoader.addProductToWarehouse(expectedProductsStore);
    ProductsStore actualProductStore = dataLoader.getProductsStores().get(0);
    //then
    assertEquals(expectedProductsStore, actualProductStore);
    FileProviderToTest.purgeDirectory(new File(filePath));
  }
}