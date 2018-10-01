package pl.controllers;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.productmodel.ProductBasket;
import pl.dto.salesmodel.productmodel.PurchaseProduct;

import java.util.Iterator;
import java.util.List;

@Service
class ProductBasketService {

  @Resource
  private final ProductBasket productBasket;


  ProductBasketService(ProductBasket productBasket) {
    this.productBasket = productBasket;
  }

  /**
   * @param purchaseProduct - body of product.
   * @return - id of product.
   */
  int postProduct(PurchaseProduct purchaseProduct) {
    Product product = productBasket.createProduct(purchaseProduct);
    productBasket.saveProduct(product);
    return product.getIdProduct();
  }

  List<Product> getListProducts() {
    return productBasket.getProducts();
  }

  /**
   * @param id - Id of product to be changed.
   * @param purchaseProduct - new product.
   */
  void putProduct(Integer id, PurchaseProduct purchaseProduct) {
    Product product = productBasket.createProduct(purchaseProduct);
    String productName = product.getProductStore().getBasisProduct().getProductName();
    productBasket.updateProduct(productName, product);
  }

  /**
   * @param id - id of product.
   * @return - the number and description of exception.
   */
  boolean deleteProduct(Integer id) {
    Iterator<Product> iterator = productBasket.getProducts().iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getIdProduct() == id) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  Product getProduct(String nameProduct) {
    return productBasket.getProduct(nameProduct);
  }
}
