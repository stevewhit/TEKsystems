/**
 * 
 */
package com.tek.interview.question.ordercalculation.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public class ImportedOrderItemTest extends AbstractOrderItemTest
{

	private ImportedOrderItem importedOrderItem;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		importedOrderItem = new ImportedOrderItem("ValidName", 12.98765d, 123);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		super.tearDown();
		
		this.importedOrderItem = null;
	}
	
	//REGION: Constructor tests with name and unitprice
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDouble_InvalidName1()
	{
		importedOrderItem = new ImportedOrderItem("", 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDouble_InvalidName2()
	{
		importedOrderItem = new ImportedOrderItem(" ", 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDouble_InvalidName3()
	{
		importedOrderItem = new ImportedOrderItem(null, 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDouble_InvalidUnitPrice()
	{
		importedOrderItem = new ImportedOrderItem("Valid nammmme", -0000000000000000000000.000000000000000001);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double)}.
	 */
	@Test
	public void testImportedOrderItemStringDouble_ValidConstructors()
	{
		importedOrderItem = new ImportedOrderItem("Valid name", 0.00000000000000000000000001);
		importedOrderItem = new ImportedOrderItem("       validN     ame.valid.name__", 123413242.222d);
		importedOrderItem = new ImportedOrderItem("_", 1.392435729);
	}
	
	// REGION: Constructor tests with name, unit price, and quantity
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDoubleInt_InvalidName1()
	{
		importedOrderItem = new ImportedOrderItem("", 1233.3, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDoubleInt_InvalidName2()
	{
		importedOrderItem = new ImportedOrderItem(" ", 1233.3, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDoubleInt_InvalidName3()
	{
		importedOrderItem = new ImportedOrderItem(null, 1233.3, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDoubleInt_InvalidUnitPrice()
	{
		importedOrderItem = new ImportedOrderItem("validname", -0000000000000.0000000000000001, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testImportedOrderItemStringDoubleInt_InvalidQuantity()
	{
		importedOrderItem = new ImportedOrderItem("validd", 1233.3, 0);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#ImportedOrderItem(java.lang.String, double, int)}.
	 */
	@Test
	public void testImportedOrderItemStringDoubleInt_ValidConstructors()
	{
		importedOrderItem = new ImportedOrderItem("validd", 0, 1);
		importedOrderItem = new ImportedOrderItem("________________213412dd__validd", 0, 1);
		importedOrderItem = new ImportedOrderItem("           d", 123.12348979132413, 100213);
		importedOrderItem = new ImportedOrderItem("_ _____ -_____- - - - ___	", 0000000001, 1234);
	}
	
	// END CONSTRUCTORS REGION	
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#getTaxDecimalValue()}.
	 */
	@Test
	public void testGetTaxDecimalValue()
	{
		assertTrue(ImportedOrderItem.TAX_DECIMAL_VALUE == importedOrderItem.getTaxDecimalValue());
		assertTrue(importedOrderItem.getTaxDecimalValue() == 0.15d);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.ImportedOrderItem#getTaxPercentValue()}.
	 */
	@Test
	public void testGetTaxPercentValue()
	{
		assertTrue(ImportedOrderItem.TAX_PERCENT_VALUE == importedOrderItem.getTaxPercentValue());
		assertTrue(importedOrderItem.getTaxPercentValue() == 15.0d);
	}
	
}
