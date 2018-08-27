package pl.generatorfortests;

import pl.calculators.impl.DiscountCalculator;
import pl.dto.salesmodel.Receipt;
import pl.dto.salesmodel.ReceiptBuilder;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.productmodel.Product;
import pl.dto.salesmodel.sumsupmodel.SumsUp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReceiptGenerator {

  public static Receipt generateOneReceipt(List<Product> products) {
    List<ReceiptBody> productBodies = ProductBodyGenerator.generateBodyProductAB();
    DiscountCalculator calculator = new DiscountCalculator();
    BigDecimal discountForCommonPurchase = calculator.calculateDiscountForCommonPurchase(products);
    BigDecimal discountForBigPurchases = calculator.calculateDiscountForBigPurchases(products);
    BigDecimal totalPrice = calculator
        .calculateTotalPrice(products, discountForCommonPurchase, discountForBigPurchases);
    SumsUp sumsUp = SumsUpGenerator.getSumsUp(discountForCommonPurchase, discountForBigPurchases,
        totalPrice);
    return ReceiptBuilder.builder()
        .buildWithGenerateId(1,
            productBodies,
            sumsUp);
  }

  public static List<Receipt> generateReceiptListForTests(List<Product> productsABCD,
      List<Product> productAB) {
    List<Receipt> receiptList = new ArrayList<>();
    receiptList.add(generateOneReceipt(productsABCD));
    receiptList.add(generateOneReceipt(productAB));
    return receiptList;
  }
}
