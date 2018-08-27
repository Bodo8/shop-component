package pl.dto.salesmodel.bodyReceipt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.productmodel.BodyBasis;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "body_receipt")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReceiptBody implements Serializable {

  @JsonIgnore
  private int idReceiptBody;
  private BodyBasis bodyBasis;
  private Integer quantityPurchase;
  private BigDecimal specialPrice;

  private Receipt receipt;

  public ReceiptBody() {
  }

  @JsonCreator
  public ReceiptBody(@JsonProperty("id") int idReceiptBody,
      @JsonProperty("basisBody") BodyBasis bodyBasis,
      @JsonProperty("quantity") Integer quantityPurchase,
      @JsonProperty("specialPrice") BigDecimal specialPrice) {
    this.idReceiptBody = idReceiptBody;
    this.bodyBasis = bodyBasis;
    this.quantityPurchase = quantityPurchase;
    this.specialPrice = specialPrice;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "body_receipt_id")
  public int getIdReceiptBody() {
    return idReceiptBody;
  }

  public void setIdReceiptBody(int idReceiptBody) {
    this.idReceiptBody = idReceiptBody;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "body_id")
  public BodyBasis getBodyBasis() {
    return bodyBasis;
  }

  public void setBodyBasis(BodyBasis bodyBasis) {
    this.bodyBasis = bodyBasis;
  }

  @Column(name = "quantity_purchase", nullable = false)
  public Integer getQuantityPurchase() {
    return quantityPurchase;
  }

  public void setQuantityPurchase(Integer quantityPurchase) {
    this.quantityPurchase = quantityPurchase;
  }

  @Column(name = "special_price", nullable = false)
  public BigDecimal getSpecialPrice() {
    return specialPrice;
  }

  public void setSpecialPrice(BigDecimal specialPrice) {
    this.specialPrice = specialPrice;
  }

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  public Receipt getReceipt() {
    return receipt;
  }

  public void setReceipt(Receipt receipt) {
    this.receipt = receipt;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    ReceiptBody that = (ReceiptBody) obj;

    if (idReceiptBody != that.idReceiptBody) {
      return false;
    }
    if (bodyBasis != null ? !bodyBasis.equals(that.bodyBasis)
        : that.bodyBasis != null) {
      return false;
    }
    if (quantityPurchase != null ? !quantityPurchase.equals(that.quantityPurchase)
        : that.quantityPurchase != null) {
      return false;
    }
    return specialPrice != null ? specialPrice.equals(that.specialPrice)
        : that.specialPrice == null;
  }

  @Override
  public int hashCode() {
    int result = idReceiptBody;
    result = 31 * result + (bodyBasis != null ? bodyBasis.hashCode() : 0);
    result = 31 * result + (quantityPurchase != null ? quantityPurchase.hashCode() : 0);
    result = 31 * result + (specialPrice != null ? specialPrice.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ReceiptBody{" + "idReceiptBody=" + idReceiptBody
        + ", bodyBasis=" + bodyBasis
        + ", quantityPurchase=" + quantityPurchase
        + ", specialPrice=" + specialPrice + '}';
  }
}
