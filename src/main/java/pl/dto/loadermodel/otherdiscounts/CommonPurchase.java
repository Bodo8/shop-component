package pl.dto.loadermodel.otherdiscounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import pl.dto.loadermodel.otherdiscounts.productsforcommondiscount.FourProducts;
import pl.dto.loadermodel.otherdiscounts.productsforcommondiscount.TwoProducts;

import java.io.Serializable;

@Entity
@Table(name = "common_purchase")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommonPurchase implements Serializable {

  @JsonIgnore
  private int common_id;
  private FourProducts fourProducts;
  private TwoProducts twoProducts;
  @JsonIgnore
  private ProductsStore productsStore;

  public CommonPurchase() {
  }

  public CommonPurchase(FourProducts fourProducts, TwoProducts twoProducts) {
    this.fourProducts = fourProducts;
    this.twoProducts = twoProducts;
  }

  @OneToOne(mappedBy = "commonPurchase", fetch = FetchType.LAZY)
  public ProductsStore getProductsStore() {
    return productsStore;
  }

  public void setProductsStore(ProductsStore productsStore) {
    this.productsStore = productsStore;
  }

  @Id
  @Column(name = "common_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getCommon_id() {
    return common_id;
  }

  public void setCommon_id(int common_id) {
    this.common_id = common_id;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "four_id")
  public FourProducts getFourProducts() {
    return fourProducts;
  }

  public void setFourProducts(FourProducts fourProducts) {
    this.fourProducts = fourProducts;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "two_id")
  public TwoProducts getTwoProducts() {
    return twoProducts;
  }

  public void setTwoProducts(TwoProducts twoProducts) {
    this.twoProducts = twoProducts;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    CommonPurchase that = (CommonPurchase) obj;

    if (common_id != that.common_id) {
      return false;
    }
    if (fourProducts != null ? !fourProducts.equals(that.fourProducts)
        : that.fourProducts != null) {
      return false;
    }
    return twoProducts != null ? twoProducts.equals(that.twoProducts) : that.twoProducts == null;
  }

  @Override
  public int hashCode() {
    int result = common_id;
    result = 31 * result + (fourProducts != null ? fourProducts.hashCode() : 0);
    result = 31 * result + (twoProducts != null ? twoProducts.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "CommonPurchase{" + "common_id=" + common_id
        + ", fourProducts=" + fourProducts
        + ", twoProducts=" + twoProducts + '}';
  }
}
