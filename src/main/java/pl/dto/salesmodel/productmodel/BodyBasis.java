package pl.dto.salesmodel.productmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "body_basis_product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BodyBasis implements Serializable {

  @JsonIgnore
  private int idBodyBasis;
  private String bodyBasisName;
  private BigDecimal price;
  @JsonIgnore
  private ReceiptBody receiptBody;

  public BodyBasis() {
  }

  @JsonCreator
  public BodyBasis(@JsonProperty("id") String bodyBasisName,
      @JsonProperty("price") BigDecimal price) {
    this.bodyBasisName = bodyBasisName;
    this.price = price;
  }

  @OneToOne(mappedBy = "bodyBasis", fetch = FetchType.LAZY)
  public ReceiptBody getReceiptBody() {
    return receiptBody;
  }

  public void setReceiptBody(ReceiptBody receiptBody) {
    this.receiptBody = receiptBody;
  }

  @Id
  @Column(name = "body_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdBodyBasis() {
    return idBodyBasis;
  }

  public void setIdBodyBasis(int idBodyBasis) {
    this.idBodyBasis = idBodyBasis;
  }

  public String getBodyBasisName() {
    return bodyBasisName;
  }

  public void setBodyBasisName(String bodyBasisName) {
    this.bodyBasisName = bodyBasisName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BodyBasis bodyBasis = (BodyBasis) o;

    if (idBodyBasis != bodyBasis.idBodyBasis) {
      return false;
    }
    if (bodyBasisName != null ? !bodyBasisName.equals(bodyBasis.bodyBasisName)
        : bodyBasis.bodyBasisName != null) {
      return false;
    }
    return price != null ? price.equals(bodyBasis.price) : bodyBasis.price == null;
  }

  @Override
  public int hashCode() {
    int result = idBodyBasis;
    result = 31 * result + (bodyBasisName != null ? bodyBasisName.hashCode() : 0);
    result = 31 * result + (price != null ? price.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BodyBasis{" + "idBodyBasis=" + idBodyBasis
        + ", bodyBasisName='" + bodyBasisName
        + '\'' + ", price=" + price + '}';
  }
}
