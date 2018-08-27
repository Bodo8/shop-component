package pl.calculators;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.dto.loadermodel.ProductsStore;
import pl.dto.salesmodel.productmodel.Product;
import pl.generatorfortests.ProductGenerator;
import pl.generatorfortests.StoreProductGenerator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public abstract class CalculateTest {

  protected abstract Calculate provideCalculator();

  Calculate calculator = provideCalculator();

  @Test
  public void shouldSpecialPrice180when7ProductA() throws Exception {
    //given
    BigDecimal expectedSpecialPrice = new BigDecimal(180);
    ProductsStore productsStore = StoreProductGenerator.generateListWarehouse().get(0);
    Integer quantity = 7;
    //when
    BigDecimal actualSpecialPrice = calculator
        .calculateSpecialPrice(productsStore, quantity);
    //then
    assertEquals(expectedSpecialPrice, actualSpecialPrice);
  }

  @Test
  @Parameters({"1, 40",
      "3, 70",
      "10, 250",
      "32, 780",
      "78, 1820"})
  public void shouldReturnSpecialPrice(Integer quantityProducts, Integer result) throws Exception {
    //given
    ProductsStore productsStore = StoreProductGenerator.generateListWarehouse().get(0);
    BigDecimal expectedSpecialPrice = BigDecimal.valueOf(result);
    //when
    BigDecimal actualSpecialPrice = calculator.calculateSpecialPrice(productsStore,
        quantityProducts);
    //then
    assertEquals(expectedSpecialPrice, actualSpecialPrice);
  }

  @Test
  public void shouldReturnSpecialPrice290WhenQuantity33() throws Exception {
    //given
    ProductsStore productsStore = StoreProductGenerator.generateListWarehouse().get(0);
    Integer quantity = 11;
    //when
    BigDecimal expectedSpecialPrice = new BigDecimal(290);
    BigDecimal actualSpecialPrice = calculator.calculateSpecialPrice(productsStore, quantity);
    //then
    assertEquals(expectedSpecialPrice, actualSpecialPrice);
  }

  @Test
  public void shouldReturnDiscountForCommonPurchase30When4Products() throws Exception {
    //given
    List<Product> productABCD = ProductGenerator.generateProductABCD();
    BigDecimal expectedDiscountABCD = new BigDecimal(30);
    //when
    BigDecimal actualDiscountABCD = calculator.calculateDiscountForCommonPurchase(productABCD);
    ;
    //then
    assertEquals(expectedDiscountABCD, actualDiscountABCD);
  }

  @Test
  public void shouldReturnDiscountForCommonPurchase10When2Products() throws Exception {
    //given
    List<Product> productABCD = ProductGenerator.generateProductABCD();
    List<Product> productAB = ProductGenerator.generateProductAB();
    BigDecimal expectedDiscountAB = new BigDecimal(10);
    //when
    BigDecimal actualDiscountAB = calculator.calculateDiscountForCommonPurchase(productAB);
    //then
    assertEquals(expectedDiscountAB, actualDiscountAB);
  }

  @Test
  public void shouldReturnDiscountForBigPurchase89dot5WhenQuantity13() throws Exception {
    //given
    BigDecimal manualExpectedDiscountForBig = new BigDecimal(89.5, context());
    List<Product> productABCD = ProductGenerator.generateProductABCD();
    BigDecimal specialPriceA = new BigDecimal(320);
    BigDecimal specialPriceB = new BigDecimal(100);
    BigDecimal specialPriceC = new BigDecimal(210);
    BigDecimal specialPriceD = new BigDecimal(265);
    BigDecimal conversionFromPercent = new BigDecimal(100);
    BigDecimal discount = new BigDecimal(10).divide(conversionFromPercent, context());
    BigDecimal expectedDiscount10 = (specialPriceA.add(specialPriceB)
        .add(specialPriceC).add(specialPriceD)).multiply(discount, context());
    //when
    BigDecimal actualDiscount10 = calculator.calculateDiscountForBigPurchases(productABCD);
    //then
    assertEquals(expectedDiscount10, actualDiscount10);
    assertEquals(manualExpectedDiscountForBig, actualDiscount10);
  }

  @Test
  public void shouldReturnTotalPrice775dot5WhenQuantity13() throws Exception {
    //given
    List<Product> productABCD = ProductGenerator.generateProductABCD();
    BigDecimal manualExpectedTotalPrice = new BigDecimal(775.5000112, context());
    BigDecimal specialPriceA = new BigDecimal(320);
    BigDecimal specialPriceB = new BigDecimal(100);
    BigDecimal specialPriceC = new BigDecimal(210);
    BigDecimal specialPriceD = new BigDecimal(265);
    BigDecimal discount = new BigDecimal(0.1);
    BigDecimal sumDiscount = specialPriceA.add(specialPriceB)
        .add(specialPriceC).add(specialPriceD);
    BigDecimal discountForBigPurchase = sumDiscount.multiply(discount, context());
    BigDecimal discountForCommonPurchase = new BigDecimal(30);
    BigDecimal expectedTotalPrice = sumDiscount.subtract(discountForBigPurchase
        .add(discountForCommonPurchase), context());
    //when
    BigDecimal actualTotalPrice = calculator.calculateTotalPrice(productABCD,
        discountForCommonPurchase, discountForBigPurchase);
    //then
    assertEquals(expectedTotalPrice, actualTotalPrice);
    assertEquals(manualExpectedTotalPrice, actualTotalPrice);
  }

  private static MathContext context() {
    MathContext context = new MathContext(6, RoundingMode.HALF_UP);
    return context;
  }
}