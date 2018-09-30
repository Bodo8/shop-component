package pl.dto.salesmodel.bodyReceipt;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class ReceiptBody implements Serializable {

  private String name;
  private BigDecimal price;
  private Integer quantityPurchase;
  private BigDecimal specialPrice;

  public ReceiptBody(String name, BigDecimal price, Integer quantityPurchase,
      BigDecimal specialPrice) {
    this.name = name;
    this.price = price;
    this.quantityPurchase = quantityPurchase;
    this.specialPrice = specialPrice;
  }

  public ReceiptBody() {
  }

  @Column(name = "name_product", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "price_product", nullable = false)
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    ReceiptBody that = (ReceiptBody) obj;

    if (name != null ? !name.equals(that.name) : that.name != null) {
      return false;
    }
    if (price != null ? !price.equals(that.price) : that.price != null) {
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
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (quantityPurchase != null ? quantityPurchase.hashCode() : 0);
    result = 31 * result + (specialPrice != null ? specialPrice.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ReceiptBody{" +
        "name='" + name + '\'' +
        ", price=" + price +
        ", quantityPurchase=" + quantityPurchase +
        ", specialPrice=" + specialPrice +
        '}';
  }
}
