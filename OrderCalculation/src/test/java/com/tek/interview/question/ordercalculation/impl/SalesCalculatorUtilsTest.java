/**
 * 
 */
package com.tek.interview.question.ordercalculation.impl;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public class TaxCalculatorUtilsTest
{
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateTaxUSDDoubleDouble_InvalidUnitPrice()
	{
		TaxCalculatorUtils.calculateTaxUSD(-0000000000000000000000000000000.0000000000000000000000000001, .12349);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateTaxUSDDoubleDouble_InvalidTaxRateDecimal1()
	{
		TaxCalculatorUtils.calculateTaxUSD(12.443, -00000000000000000000000000000.00000000000000000001);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateTaxUSDDoubleDouble_InvalidTaxRateDecimal2()
	{
		TaxCalculatorUtils.calculateTaxUSD(12.443, 1.00000000001);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, double)}.
	 */
	@Test
	public void testCalculateTaxUSDDoubleDouble_ValidCalculations()
	{
		TaxCalculatorUtils.calculateTaxUSD(0000.0001, 0000.000001);
		TaxCalculatorUtils.calculateTaxUSD(00000.00000001, .99999999);
		TaxCalculatorUtils.calculateTaxUSD(0, .99999999);
		TaxCalculatorUtils.calculateTaxUSD(123, .45);
		TaxCalculatorUtils.calculateTaxUSD(123.001, .4500);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, double)}.
	 */
	@Test
	public void testCalculateTaxUSDDoubleDouble_ValidReturns()
	{
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(12.49, .1) == 1.25);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(14.99, .1) == 1.5);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(47.50, .15) == 7.12);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(27.99, .15) == 4.2);
		
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(42.52, .1) == 4.25);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(48.57, .15) == 7.29);
	}

	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, int, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateTaxUSDDoubleIntDouble_InvalidUnitPrice()
	{
		TaxCalculatorUtils.calculateTaxUSD(-00000000.00001, 2, .1234);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, int, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateTaxUSDDoubleIntDouble_InvalidQuantity()
	{
		TaxCalculatorUtils.calculateTaxUSD(132.34, -1, .34325);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, int, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateTaxUSDDoubleIntDouble_InvalidTaxRateDecimal1()
	{
		TaxCalculatorUtils.calculateTaxUSD(144, 12, -00.000000001);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, int, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCalculateTaxUSDDoubleIntDouble_InvalidTaxRateDecimal2()
	{
		TaxCalculatorUtils.calculateTaxUSD(144, 12, 1.00123);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, int, double)}.
	 */
	@Test
	public void testCalculateTaxUSDDoubleIntDouble_ValidCalculations()
	{
		TaxCalculatorUtils.calculateTaxUSD(0, 12, 0);
		TaxCalculatorUtils.calculateTaxUSD(0.1, 12, 0);
		TaxCalculatorUtils.calculateTaxUSD(1, 12, 0);
		TaxCalculatorUtils.calculateTaxUSD(.99, 12, 0);
		TaxCalculatorUtils.calculateTaxUSD(12.34, 1, .54);
		TaxCalculatorUtils.calculateTaxUSD(12.34, 99, .523);
		TaxCalculatorUtils.calculateTaxUSD(4435.5d, 2, 1);
	}	
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.TaxCalculatorUtils#calculateTaxUSD(double, int, double)}.
	 */
	@Test
	public void testCalculateTaxUSDDoubleIntDouble_ValidReturns()
	{
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(12.49,  1, .1) == 1.25);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(14.99,  1, .1) == 1.5);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(47.50,  1, .15) == 7.12);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(27.99,  1, .15) == 4.2);
		
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(42.52, 2, .1) == 8.5);
		assertTrue(TaxCalculatorUtils.calculateTaxUSD(48.57, 2, .15) == 14.57);
	}
}
