package pl.dto.salesmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.sumsupmodel.SumsUp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receipts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Receipt implements Serializable, Comparable<Receipt> {

  private Integer id;
  @ApiModelProperty(required = true, example = "2017-08-27")
  private LocalDate date = LocalDate.now();
  @ApiModelProperty(value = "ReceiptBody", name = "products", dataType = "Array",
      example = "[AddLine1,AddLine2,AddLine3,AddLine4]")
  private List<ReceiptBody> receiptBodies;
  private SumsUp sumsUp;

  public Receipt() {
  }

  @JsonCreator
  public Receipt(@JsonProperty("id") Integer id,
      @JsonProperty("date") LocalDate date,
      @JsonProperty("receiptBody") List<ReceiptBody> receiptBodies,
      @JsonProperty("sumsUp") SumsUp sumsUp) {
    this.id = id;
    this.date = date;
    this.receiptBodies = receiptBodies;
    this.sumsUp = sumsUp;
  }

  public Receipt(LocalDate date, List<ReceiptBody> receiptBodies, SumsUp sumsUp) {
    this.date = date;
    this.receiptBodies = receiptBodies;
    this.sumsUp = sumsUp;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "receipt_id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
  public List<ReceiptBody> getReceiptBodies() {
    return receiptBodies;
  }

  public void setReceiptBodies(List<ReceiptBody> receiptBodies) {
    this.receiptBodies = receiptBodies;
  }

  public void addReceiptBody(ReceiptBody receiptBody) {
    this.receiptBodies.add(receiptBody);
    receiptBody.setReceipt(this);
  }

  @Override
  public int compareTo(Receipt object) {
    if (object == null) {
      return -1;
    }
    if (this == null) {
      return -1;
    }
    int comparison = (this.date.getYear() - object.date.getYear());
    if (comparison == 0) {
      comparison = (this.date.getMonthValue() - object.date.getMonthValue());
      if (comparison == 0) {
        comparison = (this.date.getDayOfMonth() - object.date.getDayOfMonth());
      }
    }
    if (comparison < 0) {
      return -1;
    }
    if (comparison > 0) {
      return 1;
    }
    return 0;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "sumsUp_id")
  public SumsUp getSumsUp() {
    return sumsUp;
  }

  public void setSumsUp(SumsUp sumsUp) {
    this.sumsUp = sumsUp;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Receipt receipt = (Receipt) obj;

    if (id != null ? !id.equals(receipt.id) : receipt.id != null) {
      return false;
    }
    if (date != null ? !date.equals(receipt.date) : receipt.date != null) {
      return false;
    }
    if (receiptBodies != null ? !receiptBodies.equals(receipt.receiptBodies) : receipt.receiptBodies
        != null) {
      return false;
    }
    return sumsUp != null ? sumsUp.equals(receipt.sumsUp) : receipt.sumsUp == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + (receiptBodies != null ? receiptBodies.hashCode() : 0);
    result = 31 * result + (sumsUp != null ? sumsUp.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Receipt{" + "id=" + id
        + ", date=" + date
        + ", receiptBodies=" + receiptBodies
        + ", sumsUp=" + sumsUp
        + '}';
  }
}
