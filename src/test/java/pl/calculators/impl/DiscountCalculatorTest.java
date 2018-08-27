package pl.calculators.impl;

import pl.calculators.Calculate;
import pl.calculators.CalculateTest;

public class DiscountCalculatorTest extends CalculateTest {

  @Override
  protected Calculate provideCalculator() {
    return new DiscountCalculator();
  }

}