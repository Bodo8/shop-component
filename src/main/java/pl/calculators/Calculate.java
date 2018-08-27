package pl.calculators;

import pl.dto.loadermodel.ProductsStore;
import pl.dto.salesmodel.productmodel.Product;

import java.math.BigDecimal;
import java.util.List;

public interface Calculate {

  BigDecimal calculateSpecialPrice(ProductsStore productFromWarehouse, Integer quantity);

  BigDecimal calculateDiscountForBigPurchases(List<Product> listWithConsumerPurchases);

  BigDecimal calculateTotalPrice(List<Product> listWithConsumerPurchases,
      BigDecimal discountForCommonPurchases
      , BigDecimal discountForBigPurchases);

  BigDecimal calculateDiscountForCommonPurchase(List<Product> listWithConsumerPurchases);

}