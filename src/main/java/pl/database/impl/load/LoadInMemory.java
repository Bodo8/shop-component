package pl.database.impl.load;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.database.DataLoader;
import pl.dto.loadermodel.ProductsStore;
import pl.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = {"active.database"}, havingValue = "memory")
public class LoadInMemory implements DataLoader {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);
  private Map<String, ProductsStore> productsStores = new ConcurrentHashMap<>();

  Integer getNextId() {
    return atomicInteger.getAndIncrement();
  }

  @Override
  public void addProductToWarehouse(ProductsStore productsStore) {
    productsStore.setId(getNextId());
    productsStores.put(productsStore.getBasisProduct().getProductName(), productsStore);
  }

  @Override
  public ProductsStore getProductFromWarehouse(String nameChoiceProduct) {
    ProductsStore productsStore = productsStores.get(nameChoiceProduct);
    if (productsStore == null) {
      throw new ResourceNotFoundException(nameChoiceProduct, "ProductsStore", "value");
    }
    return productsStore;
  }

  @Override
  public List<ProductsStore> getProductsStores() {
    Collection<ProductsStore> productsStoreCollection = productsStores.values();
    return productsStoreCollection.stream()
        .collect(Collectors.toCollection(() -> Collections.synchronizedList(new ArrayList<>())));
  }
}
