package pl.database;

import pl.dto.loadermodel.ProductsStore;

import java.util.List;

/**
 * provides methods save and get product from pl.database.
 */
public interface DataLoader {

  void addProductToWarehouse(ProductsStore productsStore);

  ProductsStore getProductFromWarehouse(String nameChoiceProduct);

  List<ProductsStore> getProductsStores();
}
