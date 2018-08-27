package pl.dto.loadermodel.otherdiscounts;

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
import java.math.BigDecimal;

@Entity
@Table(name = "discount_unit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UnitDiscount implements Serializable {

  @JsonIgnore
  private int idDiscountUnit;
  @ApiModelProperty(required = true, example = "3")
  private Integer numberDiscountUnit;
  @ApiModelProperty(required = true, example = "70")
  private BigDecimal priceAfterDiscount;
  @JsonIgnore
  private ProductsStore productsStore;

  public UnitDiscount() {
  }

  public UnitDiscount(Integer numberDiscountUnit, BigDecimal priceAfterDiscount) {
    this.numberDiscountUnit = numberDiscountUnit;
    this.priceAfterDiscount = priceAfterDiscount;
  }

  @OneToOne(mappedBy = "unitDiscount", fetch = FetchType.LAZY)
  public ProductsStore getProductsStore() {
    return productsStore;
  }

  public void setProductsStore(ProductsStore productsStore) {
    this.productsStore = productsStore;
  }

  @Id
  @Column(name = "unitdiscount_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdDiscountUnit() {
    return idDiscountUnit;
  }

  public void setIdDiscountUnit(int idDiscountUnit) {
    this.idDiscountUnit = idDiscountUnit;
  }

  @Column(name = "quantity_products", nullable = false)
  public Integer getNumberDiscountUnit() {
    return numberDiscountUnit;
  }

  public void setNumberDiscountUnit(Integer numberDiscountUnit) {
    this.numberDiscountUnit = numberDiscountUnit;
  }

  @Column(name = "lower_price", nullable = false)
  public BigDecimal getPriceAfterDiscount() {
    return priceAfterDiscount;
  }

  public void setPriceAfterDiscount(BigDecimal priceAfterDiscount) {
    this.priceAfterDiscount = priceAfterDiscount;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    UnitDiscount that = (UnitDiscount) obj;

    if (idDiscountUnit != that.idDiscountUnit) {
      return false;
    }

    if (numberDiscountUnit != null ? !numberDiscountUnit.equals(that.numberDiscountUnit)
        : that.numberDiscountUnit != null) {
      return false;
    }
    return priceAfterDiscount != null ? priceAfterDiscount.equals(that.priceAfterDiscount)
        : that.priceAfterDiscount == null;
  }

  @Override
  public int hashCode() {
    int result = idDiscountUnit;
    result = 31 * result + (numberDiscountUnit != null ? numberDiscountUnit.hashCode() : 0);
    result = 31 * result + (priceAfterDiscount != null ? priceAfterDiscount.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UnitDiscount{" + "idDiscountUnit=" + idDiscountUnit
        + ", numberDiscountUnit=" + numberDiscountUnit
        + ", priceAfterDiscount=" + priceAfterDiscount + '}';
  }
}
