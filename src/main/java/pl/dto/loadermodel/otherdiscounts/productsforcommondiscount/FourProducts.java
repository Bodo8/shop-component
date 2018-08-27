package pl.dto.loadermodel.otherdiscounts.productsforcommondiscount;

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
import pl.dto.loadermodel.otherdiscounts.CommonPurchase;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "rebate_four_product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FourProducts implements Serializable {

  @JsonIgnore
  private int four_id;
  @ApiModelProperty(required = true, example = "30")
  private BigDecimal amountDiscount;
  @ApiModelProperty(required = true, example = "only 4 or 0")
  private Integer isRebateFour;
  @JsonIgnore
  private CommonPurchase commonPurchase;

  public FourProducts() {
  }

  public FourProducts(BigDecimal amountDiscount, Integer isRebateFour) {
    this.amountDiscount = amountDiscount;
    this.isRebateFour = isRebateFour;
  }

  @Id
  @Column(name = "four_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getFour_id() {
    return four_id;
  }

  public void setFour_id(int four_id) {
    this.four_id = four_id;
  }

  @Column(name = "amount_rebate", nullable = false)
  public BigDecimal getAmountDiscount() {
    return amountDiscount;
  }

  public void setAmountDiscount(BigDecimal amountDiscount) {
    this.amountDiscount = amountDiscount;
  }

  @Column(name = "quantity_products", nullable = false)
  public Integer getIsRebateFour() {
    return isRebateFour;
  }

  public void setIsRebateFour(Integer isRebateFour) {
    this.isRebateFour = isRebateFour;
  }

  @OneToOne(mappedBy = "fourProducts", fetch = FetchType.LAZY)
  public CommonPurchase getCommonPurchase() {
    return commonPurchase;
  }

  public void setCommonPurchase(CommonPurchase commonPurchase) {
    this.commonPurchase = commonPurchase;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    FourProducts that = (FourProducts) obj;

    if (four_id != that.four_id) {
      return false;
    }
    if (amountDiscount != null ? !amountDiscount.equals(that.amountDiscount)
        : that.amountDiscount != null) {
      return false;
    }
    return isRebateFour != null ? isRebateFour.equals(that.isRebateFour)
        : that.isRebateFour == null;
  }

  @Override
  public int hashCode() {
    int result = four_id;
    result = 31 * result + (amountDiscount != null ? amountDiscount.hashCode() : 0);
    result = 31 * result + (isRebateFour != null ? isRebateFour.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "FourProducts{" + "four_id=" + four_id
        + ", amountDiscount=" + amountDiscount
        + ", isRebateFour=" + isRebateFour + '}';
  }
}
