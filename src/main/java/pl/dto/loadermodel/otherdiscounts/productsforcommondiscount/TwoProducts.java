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
@Table(name = "rebate_two_product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TwoProducts implements Serializable {

  @JsonIgnore
  private int two_id;
  @ApiModelProperty(required = true, example = "10")
  private BigDecimal amountDiscount;
  @ApiModelProperty(required = true, example = "only 2  or 0")
  private Integer isRebateTwo;
  @JsonIgnore
  private CommonPurchase commonPurchase;

  public TwoProducts() {
  }

  public TwoProducts(BigDecimal amountDiscount, Integer isRebateTwo) {
    this.amountDiscount = amountDiscount;
    this.isRebateTwo = isRebateTwo;
  }

  @OneToOne(mappedBy = "twoProducts", fetch = FetchType.LAZY)
  public CommonPurchase getCommonPurchase() {
    return commonPurchase;
  }

  public void setCommonPurchase(CommonPurchase commonPurchase) {
    this.commonPurchase = commonPurchase;
  }

  @Id
  @Column(name = "two_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getTwo_id() {
    return two_id;
  }

  public void setTwo_id(int two_id) {
    this.two_id = two_id;
  }

  @Column(name = "amount_rebate", nullable = false)
  public BigDecimal getAmountDiscount() {
    return amountDiscount;
  }

  public void setAmountDiscount(BigDecimal amountDiscount) {
    this.amountDiscount = amountDiscount;
  }

  @Column(name = "quantity_products", nullable = false)
  public Integer getIsRebateTwo() {
    return isRebateTwo;
  }

  public void setIsRebateTwo(Integer isRebateTwo) {
    this.isRebateTwo = isRebateTwo;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    TwoProducts that = (TwoProducts) obj;

    if (two_id != that.two_id) {
      return false;
    }
    if (amountDiscount != null ? !amountDiscount.equals(that.amountDiscount)
        : that.amountDiscount != null) {
      return false;
    }
    return isRebateTwo != null ? isRebateTwo.equals(that.isRebateTwo) : that.isRebateTwo == null;
  }

  @Override
  public int hashCode() {
    int result = two_id;
    result = 31 * result + (amountDiscount != null ? amountDiscount.hashCode() : 0);
    result = 31 * result + (isRebateTwo != null ? isRebateTwo.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "TwoProducts{" + "two_id=" + two_id
        + ", amountDiscount=" + amountDiscount
        + ", isRebateTwo=" + isRebateTwo + '}';
  }
}
