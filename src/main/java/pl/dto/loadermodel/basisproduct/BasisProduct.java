package pl.dto.loadermodel.basisproduct;

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
@Table(name = "basis_product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BasisProduct implements Serializable {

  @JsonIgnore
  private int idBasis;
  @ApiModelProperty(required = true, example = " A or B or C...")
  private String productName;
  @ApiModelProperty(required = true, example = "40")
  private BigDecimal price;
  @JsonIgnore
  private ProductsStore productsStore;

  public BasisProduct() {
  }

  public BasisProduct(String productName, BigDecimal price) {
    this.productName = productName;
    this.price = price;
  }


  @OneToOne(mappedBy = "basisProduct", fetch = FetchType.LAZY)
  public ProductsStore getProductsStore() {
    return productsStore;
  }

  public void setProductsStore(ProductsStore productsStore) {
    this.productsStore = productsStore;
  }

  @Id
  @Column(name = "basis_product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdBasis() {
    return idBasis;
  }

  public void setIdBasis(int idBasis) {
    this.idBasis = idBasis;
  }

  @Column(unique = true)
  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  @Column(name = "price_product", nullable = false)
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

    BasisProduct that = (BasisProduct) o;

    if (idBasis != that.idBasis) {
      return false;
    }
    if (productName != null ? !productName.equals(that.productName) : that.productName != null) {
      return false;
    }
    return price != null ? price.equals(that.price) : that.price == null;
  }

  @Override
  public int hashCode() {
    int result = idBasis;
    result = 31 * result + (productName != null ? productName.hashCode() : 0);
    result = 31 * result + (price != null ? price.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BasisProduct{" + "idBasis=" + idBasis
        + ", productName='" + productName
        + '\'' + ", price=" + price + '}';
  }
}
