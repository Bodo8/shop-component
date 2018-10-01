package pl.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.dto.loadermodel.ProductsStore;

import java.io.IOException;
import java.util.List;

/**
 * ProductLoad controller - new product to the database.
 */
@RestController
@RequestMapping("/load")
public class ProductLoadController {

  private final ProductLoadService productLoadService;

  ProductLoadController(ProductLoadService productLoadService) {
    this.productLoadService = productLoadService;
  }

  /**
   * @param productsStore - admin add new product to the database.
   * @return - id new purchased product.
   */
  @ApiOperation(value = "Post another product in database",
      response = Integer.class)
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  int postProduct(
      @ApiParam(value = "product data")
      @RequestBody ProductsStore productsStore) {
    return productLoadService.postProductStore(productsStore);
  }

  /**
   * @return - all products available in the database.
   * @throws IOException - when no success in reading product.
   */
  @ApiOperation(value = "Get all product",
      response = ProductsStore.class, responseContainer = "List")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<ProductsStore> getProducts() throws IOException {
    return productLoadService.getAllProducts();
  }
}
