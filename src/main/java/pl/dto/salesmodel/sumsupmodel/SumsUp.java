package pl.dto.salesmodel.sumsupmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.dto.salesmodel.Receipt;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "sumsUp")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SumsUp implements Serializable {

  @JsonIgnore
  private int idSumsUp;
  private BigDecimal discountForCommonPurchases;
  private BigDecimal discountForBigPurchases;
  private BigDecimal totalPrice;
  @JsonIgnore
  private Receipt receipt;

  public SumsUp() {
  }

  public SumsUp(BigDecimal discountForCommonPurchases,
      BigDecimal discountForBigPurchases,
      BigDecimal totalPrice) {
    this.discountForCommonPurchases = discountForCommonPurchases;
    this.discountForBigPurchases = discountForBigPurchases;
    this.totalPrice = totalPrice;
  }

  @OneToOne(mappedBy = "sumsUp", cascade = CascadeType.ALL)
  public Receipt getReceipt() {
    return receipt;
  }

  public void setReceipt(Receipt receipt) {
    this.receipt = receipt;
  }

  @Id
  @Column(name = "sumsUp_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdSumsUp() {
    return idSumsUp;
  }

  public void setIdSumsUp(int idSumsUp) {
    this.idSumsUp = idSumsUp;
  }

  public BigDecimal getDiscountForCommonPurchases() {
    return discountForCommonPurchases;
  }

  public void setDiscountForCommonPurchases(BigDecimal discountForCommonPurchases) {
    this.discountForCommonPurchases = discountForCommonPurchases;
  }

  public BigDecimal getDiscountForBigPurchases() {
    return discountForBigPurchases;
  }

  public void setDiscountForBigPurchases(BigDecimal discountForBigPurchases) {
    this.discountForBigPurchases = discountForBigPurchases;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    SumsUp sumsUp = (SumsUp) obj;

    if (idSumsUp != sumsUp.idSumsUp) {
      return false;
    }
    if (discountForCommonPurchases != null ? !discountForCommonPurchases
        .equals(sumsUp.discountForCommonPurchases) : sumsUp.discountForCommonPurchases != null) {
      return false;
    }
    if (discountForBigPurchases != null ? !discountForBigPurchases
        .equals(sumsUp.discountForBigPurchases) : sumsUp.discountForBigPurchases != null) {
      return false;
    }
    return totalPrice != null ? totalPrice.equals(sumsUp.totalPrice) : sumsUp.totalPrice == null;
  }

  @Override
  public int hashCode() {
    int result = idSumsUp;
    result =
        31 * result + (discountForCommonPurchases != null ? discountForCommonPurchases.hashCode()
            : 0);
    result =
        31 * result + (discountForBigPurchases != null ? discountForBigPurchases.hashCode() : 0);
    result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SumsUp{" + "idSumsUp=" + idSumsUp
        + ", discountForCommonPurchases=" + discountForCommonPurchases
        + ", discountForBigPurchases=" + discountForBigPurchases
        + ", totalPrice=" + totalPrice
        + '}';
  }
}
