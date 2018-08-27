package pl.dto.salesmodel;

import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.sumsupmodel.SumsUp;

import java.time.LocalDate;
import java.util.List;

public class ReceiptBuilder {

  private Integer id;
  private LocalDate date;
  private List<ReceiptBody> productBodies;
  private SumsUp sumsUp;

  public static ReceiptBuilder builder() {
    return new ReceiptBuilder();
  }

  public ReceiptBuilder withId(Integer id) {
    this.id = id;
    return this;
  }

  public ReceiptBuilder withDate(LocalDate date) {
    this.date = date;
    return this;
  }

  public ReceiptBuilder withProductBodies(List<ReceiptBody> productBodies) {
    this.productBodies = productBodies;
    return this;
  }

  public ReceiptBuilder withSumsUp(SumsUp sumsUp) {
    this.sumsUp = sumsUp;
    return this;
  }

  public Receipt build() {
    return new Receipt(id, date, productBodies, sumsUp);
  }

  public Receipt buildWithGenerateId(Integer id, List<ReceiptBody> productBodies,
      SumsUp sumsUp) {
    return ReceiptBuilder.builder()
        .withId(id)
        .withDate(LocalDate.now())
        .withProductBodies(productBodies)
        .withSumsUp(sumsUp)
        .build();
  }

  public Receipt buildWithoutId(List<ReceiptBody> productBodies, SumsUp sumsUp) {
    return ReceiptBuilder.builder()
        .withDate(LocalDate.now())
        .withProductBodies(productBodies)
        .withSumsUp(sumsUp)
        .build();
  }
}
