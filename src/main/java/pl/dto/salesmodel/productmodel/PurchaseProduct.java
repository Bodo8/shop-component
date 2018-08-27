package pl.dto.salesmodel.productmodel;

import io.swagger.annotations.ApiModelProperty;

public class PurchaseProduct {

  @ApiModelProperty(required = true, example = "1")
  private int idPurchase;
  @ApiModelProperty(required = true, example = "A")
  private String nameProduct;
  @ApiModelProperty(required = true, example = "5")
  private Integer quantity;

  public PurchaseProduct() {
  }

  public PurchaseProduct(int idPurchase, String nameProduct, Integer quantity) {
    this.idPurchase = idPurchase;
    this.nameProduct = nameProduct;
    this.quantity = quantity;
  }

  public int getIdPurchase() {
    return idPurchase;
  }

  public void setIdPurchase(int idPurchase) {
    this.idPurchase = idPurchase;
  }

  public String getNameProduct() {
    return nameProduct;
  }

  public void setNameProduct(String nameProduct) {
    this.nameProduct = nameProduct;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PurchaseProduct that = (PurchaseProduct) o;

    if (idPurchase != that.idPurchase) {
      return false;
    }
    if (nameProduct != null ? !nameProduct.equals(that.nameProduct) : that.nameProduct != null) {
      return false;
    }
    return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;
  }

  @Override
  public int hashCode() {
    int result = idPurchase;
    result = 31 * result + (nameProduct != null ? nameProduct.hashCode() : 0);
    result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "PurchaseProduct{" +
        "idPurchase=" + idPurchase +
        ", nameProduct='" + nameProduct + '\'' +
        ", quantity=" + quantity +
        '}';
  }
}
