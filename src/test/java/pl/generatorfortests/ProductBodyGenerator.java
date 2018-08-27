package pl.generatorfortests;

import pl.dto.loadermodel.basisproduct.BasisProduct;
import pl.dto.salesmodel.bodyReceipt.ReceiptBody;
import pl.dto.salesmodel.bodyReceipt.ReceiptBodyBuilder;
import pl.dto.salesmodel.productmodel.BodyBasis;
import pl.dto.salesmodel.productmodel.BodyBasisBuilder;
import pl.dto.salesmodel.productmodel.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductBodyGenerator {

  public static List<ReceiptBody> generateBodyProductABCD() {
    List<ReceiptBody> receiptBodyList = new ArrayList<>();
    List<Product> products = ProductGenerator.generateProductABCD();
    for (Product product : products) {
      BasisProduct basisProduct = product.getProductStore().getBasisProduct();
      BodyBasis bodyBasis = BodyBasisBuilder.builder()
          .withBodyBasisName(basisProduct.getProductName())
          .withPrice(basisProduct.getPrice())
          .build();
      Integer quantityPurchase = product.getQuantityPurchase();
      BigDecimal specialPrice = product.getSpecialPrice();
      ReceiptBody createReceiptBody = ReceiptBodyBuilder.bodyBuilder()
          .withBasisProduct(bodyBasis)
          .withQuantityPurchase(quantityPurchase)
          .withSpecialPrice(specialPrice)
          .build();
      receiptBodyList.add(createReceiptBody);
    }
    return receiptBodyList;
  }

  public static List<ReceiptBody> generateBodyProductAB() {
    List<ReceiptBody> receiptBodyList = new ArrayList<>();
    List<Product> products = ProductGenerator.generateProductAB();
    for (Product product : products) {
      BasisProduct basisProduct = product.getProductStore().getBasisProduct();
      BodyBasis bodyBasis = BodyBasisBuilder.builder()
          .withBodyBasisName(basisProduct.getProductName())
          .withPrice(basisProduct.getPrice())
          .build();
      Integer quantityPurchase = product.getQuantityPurchase();
      BigDecimal specialPrice = product.getSpecialPrice();
      ReceiptBody createReceiptBody = ReceiptBodyBuilder.bodyBuilder()
          .withBasisProduct(bodyBasis)
          .withQuantityPurchase(quantityPurchase)
          .withSpecialPrice(specialPrice)
          .build();
      receiptBodyList.add(createReceiptBody);
    }
    return receiptBodyList;
  }
}
