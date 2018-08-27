package pl.database.impl.load;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.database.DataLoader;
import pl.dto.loadermodel.ProductStoreBuilder;
import pl.dto.loadermodel.ProductsStore;
import pl.repository.StoreRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@ConditionalOnProperty(name = {"active.database"}, havingValue = "sql")
@Transactional
public class LoadInSql implements DataLoader {

  @Autowired
  StoreRepository storeRepository;

  @Override
  public void addProductToWarehouse(ProductsStore productsStore) {
    storeRepository.save(productsStore);
  }

  @Override

  public ProductsStore getProductFromWarehouse(String nameChoiceProduct) {
    List<ProductsStore> productsStoreList = (List<ProductsStore>) storeRepository
        .findByNameProduct(nameChoiceProduct);
    return productsStoreList.get(0);
  }

  @Override
  public List<ProductsStore> getProductsStores() {
    return (List<ProductsStore>) storeRepository.findAll();
  }

  private ProductsStore createProductsStore(String nameProduct, BigDecimal price,
      Integer numberDiscountUnit, BigDecimal priceAfterDiscount, BigDecimal fourAmountDiscount,
      Integer isRebateFour, BigDecimal twoAmountDiscount, Integer isRebateTwo,
      Integer quantityToStore, Integer amountAbove, BigDecimal amountRebate) {
    ProductsStore productsStore = ProductStoreBuilder.builder()
        .buildProductStoreWithoutId(nameProduct, price,
            numberDiscountUnit, priceAfterDiscount, fourAmountDiscount, isRebateFour,
            twoAmountDiscount, isRebateTwo, quantityToStore, amountAbove, amountRebate);
    return productsStore;
  }

}
