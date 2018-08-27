package pl.dto.loadermodel.storehouse;

import java.time.LocalDate;

public class StoreHouseBuilder {

  private int idStoreHouse;
  private LocalDate date = LocalDate.now();
  private Integer quantityToStore;

  public static StoreHouseBuilder builder() {
    return new StoreHouseBuilder();
  }

  public StoreHouseBuilder withStoreId(int idStoreHouse) {
    this.idStoreHouse = idStoreHouse;
    return this;
  }

  public StoreHouseBuilder withDate(LocalDate date) {
    this.date = date;
    return this;
  }

  public StoreHouseBuilder withQuantityToStore(Integer quantityToStore) {
    this.quantityToStore = quantityToStore;
    return this;
  }

  public StoreHouse build() {
    return new StoreHouse(date, quantityToStore);
  }
}
