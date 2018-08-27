package pl.generatorfortests;

import pl.dto.loadermodel.ProductStoreBuilder;
import pl.dto.loadermodel.ProductsStore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StoreProductGenerator {

  public static List<ProductsStore> generateListWarehouse() {
    List<ProductsStore> productsWarehouse = new ArrayList<>();
    String[] nameProduct = getNameProduct();
    BigDecimal[] price = getPrice();
    Integer[] numberUnit = getNumberUnit();
    BigDecimal[] amountUnit = getAmountUnit();
    BigDecimal fourAmountDiscount = new BigDecimal(30);
    BigDecimal twoAmountDiscount = new BigDecimal(10);
    Integer quantityToStore = 22;
    Integer amountAbove = 600;
    BigDecimal amountRebate = new BigDecimal(10);
    Integer isRebateFour = 4;
    Integer isRebateTwo = 2;
    for (int i = 0; i < 4; i++) {
      ProductsStore productsStore = ProductStoreBuilder.builder()
          .buildProductStoreWithGenerateId((i + 1), nameProduct[i], price[i], numberUnit[i],
              amountUnit[i], fourAmountDiscount, isRebateFour, twoAmountDiscount,
              isRebateTwo, quantityToStore, amountAbove, amountRebate);
      productsWarehouse.add(productsStore);
    }
    return productsWarehouse;
  }

  public static ProductsStore getOneProductStore(String name) {
    ProductsStore productStore = ProductStoreBuilder.builder()
        .buildProductStoreWithoutId(name, new BigDecimal(40),
            3, new BigDecimal(90),
            new BigDecimal(20), 4,
            new BigDecimal(20), 2,
            8, 10, new BigDecimal(600));
    return productStore;
  }

  private static String[] getNameProduct() {
    String[] names = new String[4];
    names[0] = "A";
    names[1] = "B";
    names[2] = "C";
    names[3] = "D";
    return names;
  }

  private static BigDecimal[] getPrice() {
    BigDecimal[] price = new BigDecimal[4];
    price[0] = new BigDecimal(40);
    price[1] = new BigDecimal(10);
    price[2] = new BigDecimal(30);
    price[3] = new BigDecimal(25);
    return price;
  }

  private static Integer[] getNumberUnit() {
    Integer[] units = new Integer[4];
    units[0] = 3;
    units[1] = 2;
    units[2] = 4;
    units[3] = 2;
    return units;
  }

  private static BigDecimal[] getAmountUnit() {
    BigDecimal[] amounts = new BigDecimal[4];
    amounts[0] = new BigDecimal(70);
    amounts[1] = new BigDecimal(15);
    amounts[2] = new BigDecimal(60);
    amounts[3] = new BigDecimal(40);
    return amounts;
  }
}
