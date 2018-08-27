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
@Table(name = "big_purchase")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BigPurchase implements Serializable {

  @JsonIgnore
  private int idBig;
  @ApiModelProperty(required = true, example = "600")
  private Integer amountAboveRebateIsDue;
  @ApiModelProperty(required = true, example = "10")
  private BigDecimal amountRebate;
  @JsonIgnore
  private ProductsStore productsStore;

  public BigPurchase() {
  }

  public BigPurchase(Integer amountAboveRebateIsDue, BigDecimal amountRebate) {
    this.amountAboveRebateIsDue = amountAboveRebateIsDue;
    this.amountRebate = amountRebate;
  }

  @OneToOne(mappedBy = "bigPurchase", fetch = FetchType.LAZY)
  public ProductsStore getProductsStore() {
    return productsStore;
  }

  public void setProductsStore(ProductsStore productsStore) {
    this.productsStore = productsStore;
  }

  @Id
  @Column(name = "idBig")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdBig() {
    return idBig;
  }

  public void setIdBig(int idBig) {
    this.idBig = idBig;
  }

  @Column(name = "amount_min_purchase", nullable = false)
  public Integer getAmountAboveRebateIsDue() {
    return amountAboveRebateIsDue;
  }

  public void setAmountAboveRebateIsDue(Integer amountAboveRebateIsDue) {
    this.amountAboveRebateIsDue = amountAboveRebateIsDue;
  }

  @Column(name = "amount_rebate", nullable = false)
  public BigDecimal getAmountRebate() {
    return amountRebate;
  }

  public void setAmountRebate(BigDecimal amountRebate) {
    this.amountRebate = amountRebate;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    BigPurchase that = (BigPurchase) obj;

    if (idBig != that.idBig) {
      return false;
    }
    if (amountAboveRebateIsDue != null ? !amountAboveRebateIsDue.equals(that.amountAboveRebateIsDue)
        : that.amountAboveRebateIsDue != null) {
      return false;
    }
    return amountRebate != null ? amountRebate.equals(that.amountRebate)
        : that.amountRebate == null;
  }

  @Override
  public int hashCode() {
    int result = idBig;
    result = 31 * result + (amountAboveRebateIsDue != null ? amountAboveRebateIsDue.hashCode() : 0);
    result = 31 * result + (amountRebate != null ? amountRebate.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BigPurchase{" + "idBig=" + idBig
        + ", amountAboveRebateIsDue=" + amountAboveRebateIsDue
        + ", amountRebate=" + amountRebate + '}';
  }
}
