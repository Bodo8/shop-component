package pl.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.productmodel.PurchaseProduct;

import java.io.IOException;
import java.util.List;

/**
 * ProductBasket controller - new purchase customers.
 */
@RestController
@RequestMapping("/product")
public class ProductBasketController {

  private final ProductBasketService productBasketService;

  ProductBasketController(ProductBasketService productBasketService) {
    this.productBasketService = productBasketService;
  }

  /**
   * @param purchaseProduct - product witch will ad to the current receipt.
   * @return - id product.
   */
  @ApiOperation(value = "Post another product in receipt",
      response = Integer.class)
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  int postProduct(
      @ApiParam(value = "product data")
      @RequestBody PurchaseProduct purchaseProduct) {
    return productBasketService.postProduct(purchaseProduct);
  }

  /**
   * @return - all purchased products.
   * @throws IOException - when no success in reading product.
   */
  @ApiOperation(value = "Get current products",
      response = Product.class, responseContainer = "List")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<Product> getListProduct() throws IOException {
    return productBasketService.getListProducts();
  }

  /**
   * @param id - Id of product to be changed.
   * @param purchaseProduct - new product.
   * @throws IOException - when no success in reading and saving product.
   */
  @ApiOperation(value = "Change an product of provided id")
  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void putProduct(@ApiParam(value = "Id of product to be changed")
  @PathVariable Integer id,
      @ApiParam(value = "Updated invoice data")
      @RequestBody PurchaseProduct purchaseProduct) throws IOException {
    productBasketService.putProduct(id, purchaseProduct);
  }

  /**
   * @param id - id product.
   * @return - the number and description of exception.
   * @throws IOException - when problems deleting product.
   */
  @ApiOperation(value = "Delete an product of provided id",
      response = ResponseEntity.class, responseContainer = "List")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteProduct(
      @ApiParam(value = "Id of product to be removed")
      @PathVariable Integer id)
      throws IOException {
    return (productBasketService.deleteProduct(id) ? ResponseEntity.ok()
        : ResponseEntity.notFound()).build();
  }

}
