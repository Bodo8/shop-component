package pl.dto.loadermodel;

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
import pl.dto.loadermodel.basisproduct.BasisProduct;
import pl.dto.loadermodel.otherdiscounts.BigPurchase;
import pl.dto.loadermodel.otherdiscounts.CommonPurchase;
import pl.dto.loadermodel.otherdiscounts.UnitDiscount;
import pl.dto.loadermodel.storehouse.StoreHouse;
import pl.dto.salesmodel.productmodel.Product;

import java.io.Serializable;

@Entity
@Table(name = "products_store")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductsStore implements Comparable<ProductsStore>, Serializable {

  private int id;
  private BasisProduct basisProduct;
  private UnitDiscount unitDiscount;
  private CommonPurchase commonPurchase;
  private BigPurchase bigPurchase;
  private StoreHouse storeHouse;

  @JsonIgnore
  private Product product;

  public ProductsStore() {
  }

  public ProductsStore(int id, BasisProduct basisProduct,
      UnitDiscount unitDiscount, CommonPurchase commonPurchase,
      BigPurchase bigPurchase, StoreHouse storeHouse) {
    this.id = id;
    this.basisProduct = basisProduct;
    this.unitDiscount = unitDiscount;
    this.commonPurchase = commonPurchase;
    this.bigPurchase = bigPurchase;
    this.storeHouse = storeHouse;
  }

  public ProductsStore(BasisProduct basisProduct,
      UnitDiscount unitDiscount, CommonPurchase commonPurchase,
      BigPurchase bigPurchase, StoreHouse storeHouse) {
    this.basisProduct = basisProduct;
    this.unitDiscount = unitDiscount;
    this.commonPurchase = commonPurchase;
    this.bigPurchase = bigPurchase;
    this.storeHouse = storeHouse;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "products_id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "basis_product_id")
  public BasisProduct getBasisProduct() {
    return basisProduct;
  }

  public void setBasisProduct(BasisProduct basisProduct) {
    this.basisProduct = basisProduct;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "unitdiscount_id")
  public UnitDiscount getUnitDiscount() {
    return unitDiscount;
  }

  public void setUnitDiscount(UnitDiscount unitDiscount) {
    this.unitDiscount = unitDiscount;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "common_id")
  public CommonPurchase getCommonPurchase() {
    return commonPurchase;
  }

  public void setCommonPurchase(CommonPurchase commonPurchase) {
    this.commonPurchase = commonPurchase;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "idBig")
  public BigPurchase getBigPurchase() {
    return bigPurchase;
  }

  public void setBigPurchase(BigPurchase bigPurchase) {
    this.bigPurchase = bigPurchase;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "storehouse_id")
  public StoreHouse getStoreHouse() {
    return storeHouse;
  }

  public void setStoreHouse(StoreHouse storeHouse) {
    this.storeHouse = storeHouse;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    ProductsStore that = (ProductsStore) obj;

    if (id != that.id) {
      return false;
    }
    if (basisProduct != null ? !basisProduct.equals(that.basisProduct)
        : that.basisProduct != null) {
      return false;
    }
    if (unitDiscount != null ? !unitDiscount.equals(that.unitDiscount)
        : that.unitDiscount != null) {
      return false;
    }
    if (commonPurchase != null ? !commonPurchase.equals(that.commonPurchase)
        : that.commonPurchase != null) {
      return false;
    }
    if (bigPurchase != null ? !bigPurchase.equals(that.bigPurchase) : that.bigPurchase != null) {
      return false;
    }
    return storeHouse != null ? storeHouse.equals(that.storeHouse) : that.storeHouse == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (basisProduct != null ? basisProduct.hashCode() : 0);
    result = 31 * result + (unitDiscount != null ? unitDiscount.hashCode() : 0);
    result = 31 * result + (commonPurchase != null ? commonPurchase.hashCode() : 0);
    result = 31 * result + (storeHouse != null ? storeHouse.hashCode() : 0);
    return result;
  }

  @Override
  public int compareTo(ProductsStore object) {
    if (object == null) {
      return -1;
    }
    if (this == null) {
      return -1;
    }
    int comparison = (this.basisProduct.getProductName()
        .compareTo(object.basisProduct.getProductName()));
    if (comparison < 0) {
      return -1;
    }
    if (comparison > 0) {
      return 1;
    }
    return 0;
  }

  @Override
  public String toString() {
    return "ProductsStore{" + "id=" + id
        + ", basisProduct=" + basisProduct
        + ", unitDiscount=" + unitDiscount
        + ", commonPurchase=" + commonPurchase
        + ", bigPurchase=" + bigPurchase
        + ", storeHouse=" + storeHouse + '}';
  }
}
