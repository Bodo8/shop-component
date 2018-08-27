package pl.dto.loadermodel;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pl.database.DataLoader;

import java.util.List;

@Service
public class LoadBasket {

  @Resource
  private final DataLoader dataLoader;

  public LoadBasket(DataLoader dataLoader) {
    this.dataLoader = dataLoader;
  }

  public void addProductToWarehouse(ProductsStore productsStore) {
    dataLoader.addProductToWarehouse(productsStore);
  }

  public List<ProductsStore> getProducts() {
    return dataLoader.getProductsStores();
  }
}
