package pl.database.impl.load;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.database.impl.DateProcessorHelper;
import pl.dto.loadermodel.ProductsStore;

import java.io.File;
import java.io.IOException;
import java.util.List;

class ProductStoreProcessor {

  DateProcessorHelper processorHelper = new DateProcessorHelper();
  ObjectMapper mapper = processorHelper.objectMapper();

  List<ProductsStore> readFileAndConvertContentIntoListOfProductsStore(String productsPath)
      throws IOException {
    return mapper.readValue(new File(productsPath),
        mapper.getTypeFactory().constructCollectionType(List.class, ProductsStore.class));
  }

  void saveToFile(List<ProductsStore> productsStores, File filename) throws IOException {
    mapper.writeValue(filename, productsStores);
  }
}
