package pl.dto.salesmodel.productmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.dto.loadermodel.ProductsStore;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {

  private int idProduct;
  private ProductsStore productStore;
  private Integer quantityPurchase;
  private BigDecimal specialPrice;

  public Product() {
  }

  @JsonCreator
  public Product(@JsonProperty("product_id") int idProduct,
      @JsonProperty("product_warehouse") ProductsStore productStore,
      @JsonProperty("quantity_Purchase") Integer quantityPurchase,
      @JsonProperty("special-price") BigDecimal specialPrice) {
    this.idProduct = idProduct;
    this.productStore = productStore;
    this.quantityPurchase = quantityPurchase;
    this.specialPrice = specialPrice;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id")
  public int getIdProduct() {
    return idProduct;
  }

  public void setIdProduct(int idProduct) {
    this.idProduct = idProduct;
  }

  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "products_id")
  public ProductsStore getProductStore() {
    return productStore;
  }

  public void setProductStore(ProductsStore productStore) {
    this.productStore = productStore;
  }

  public Integer getQuantityPurchase() {
    return quantityPurchase;
  }

  public void setQuantityPurchase(Integer quantityPurchase) {
    this.quantityPurchase = quantityPurchase;
  }

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

    Product product = (Product) obj;

    if (idProduct != product.idProduct) {
      return false;
    }
    if (productStore != null ? !productStore.equals(product.productStore)
        : product.productStore != null) {
      return false;
    }
    if (quantityPurchase != null ? !quantityPurchase.equals(product.quantityPurchase)
        : product.quantityPurchase != null) {
      return false;
    }
    return specialPrice != null ? specialPrice.equals(product.specialPrice)
        : product.specialPrice == null;
  }

  @Override
  public int hashCode() {
    int result = idProduct;
    result = 31 * result + (productStore != null ? productStore.hashCode() : 0);
    result = 31 * result + (quantityPurchase != null ? quantityPurchase.hashCode() : 0);
    result = 31 * result + (specialPrice != null ? specialPrice.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Product{" + "idProduct=" + idProduct
        + ", productStore=" + productStore
        + ", quantityPurchase=" + quantityPurchase
        + ", specialPrice=" + specialPrice + '}';
  }
}
