package pl.controllers;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pl.dto.loadermodel.LoadBasket;
import pl.dto.loadermodel.ProductsStore;

import java.util.List;

@Service
class ProductLoadService {

  @Resource
  private final LoadBasket loadProduct;

  ProductLoadService(LoadBasket loadProduct) {
    this.loadProduct = loadProduct;
  }

  /**
   * @param productsStore - new product to the database
   * @return - id new product.
   */
  int postProductStore(ProductsStore productsStore) {
    loadProduct.addProductToWarehouse(productsStore);
    return productsStore.getId();
  }

  /**
   * @return - all products available in the database.
   */
  List<ProductsStore> getAllProducts() {
    return loadProduct.getProducts();
  }
}
