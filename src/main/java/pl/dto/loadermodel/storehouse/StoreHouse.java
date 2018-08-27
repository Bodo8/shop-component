package pl.dto.loadermodel.storehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.dto.loadermodel.ProductsStore;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "store_house")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StoreHouse implements Serializable {

  @JsonIgnore
  private int idStoreHouse;
  @ApiModelProperty(required = true, example = "2017-08-27")
  private LocalDate date = LocalDate.now();
  @ApiModelProperty(required = true, example = "15")
  private Integer quantityToStore;
  @JsonIgnore
  private ProductsStore productsStore;

  public StoreHouse() {
  }

  public StoreHouse(LocalDate date, Integer quantityToStore) {
    this.date = date;
    this.quantityToStore = quantityToStore;
  }

  @OneToOne(mappedBy = "storeHouse", fetch = FetchType.LAZY)
  public ProductsStore getProductsStore() {
    return productsStore;
  }

  public void setProductsStore(ProductsStore productsStore) {
    this.productsStore = productsStore;
  }

  @Id
  @Column(name = "storehouse_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdStoreHouse() {
    return idStoreHouse;
  }

  public void setIdStoreHouse(int idStoreHouse) {
    this.idStoreHouse = idStoreHouse;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Column(name = "quantity", nullable = false)
  public Integer getQuantityToStore() {
    return quantityToStore;
  }

  public void setQuantityToStore(Integer quantityToStore) {
    this.quantityToStore = quantityToStore;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    StoreHouse that = (StoreHouse) obj;

    if (idStoreHouse != that.idStoreHouse) {
      return false;
    }
    if (quantityToStore != that.quantityToStore) {
      return false;
    }
    return date != null ? date.equals(that.date) : that.date == null;
  }

  @Override
  public int hashCode() {
    int result = idStoreHouse;
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + quantityToStore;
    return result;
  }

  @Override
  public String toString() {
    return "StoreHouse{" + "idStoreHouse=" + idStoreHouse
        + ", date=" + date
        + ", quantityToStore=" + quantityToStore + '}';
  }
}
