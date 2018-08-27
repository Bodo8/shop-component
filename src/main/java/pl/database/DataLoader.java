package pl.database;

import pl.dto.loadermodel.ProductsStore;

import java.util.List;

public interface DataLoader {

  void addProductToWarehouse(ProductsStore productsStore);

  ProductsStore getProductFromWarehouse(String nameChoiceProduct);

  List<ProductsStore> getProductsStores();
}
