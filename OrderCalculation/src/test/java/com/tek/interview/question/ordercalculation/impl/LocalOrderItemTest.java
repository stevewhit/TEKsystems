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
public class LocalOrderItemTest extends AbstractOrderItemTest
{

	private LocalOrderItem localOrderItem;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		localOrderItem = new LocalOrderItem("ValidName", 12.98765d, 123);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		super.tearDown();
		
		this.localOrderItem = null;
	}
	
	//REGION: Constructor tests with name and unitprice
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDouble_InvalidName1()
	{
		localOrderItem = new LocalOrderItem("", 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDouble_InvalidName2()
	{
		localOrderItem = new LocalOrderItem(" ", 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDouble_InvalidName3()
	{
		localOrderItem = new LocalOrderItem(null, 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDouble_InvalidUnitPrice()
	{
		localOrderItem = new LocalOrderItem("Valid nammmme", -0000000000000000000000.000000000000000001);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double)}.
	 */
	@Test
	public void testLocalOrderItemStringDouble_ValidConstructors()
	{
		localOrderItem = new LocalOrderItem("Valid name", 0.00000000000000000000000001);
		localOrderItem = new LocalOrderItem("       validN     ame.valid.name__", 123413242.222d);
		localOrderItem = new LocalOrderItem("_", 1.392435729);
	}
	
	// REGION: Constructor tests with name, unit price, and quantity
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDoubleInt_InvalidName1()
	{
		localOrderItem = new LocalOrderItem("", 1233.3, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDoubleInt_InvalidName2()
	{
		localOrderItem = new LocalOrderItem(" ", 1233.3, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDoubleInt_InvalidName3()
	{
		localOrderItem = new LocalOrderItem(null, 1233.3, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDoubleInt_InvalidUnitPrice()
	{
		localOrderItem = new LocalOrderItem("validname", -0000000000000.0000000000000001, 2);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLocalOrderItemStringDoubleInt_InvalidQuantity()
	{
		localOrderItem = new LocalOrderItem("validd", 1233.3, 0);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#LocalOrderItem(java.lang.String, double, int)}.
	 */
	@Test
	public void testLocalOrderItemStringDoubleInt_ValidConstructors()
	{
		localOrderItem = new LocalOrderItem("validd", 0, 1);
		localOrderItem = new LocalOrderItem("________________213412dd__validd", 0, 1);
		localOrderItem = new LocalOrderItem("           d", 123.12348979132413, 100213);
		localOrderItem = new LocalOrderItem("_ _____ -_____- - - - ___	", 0000000001, 1234);
	}
	
	// END CONSTRUCTORS REGION	
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#getCalculatedTaxAmountUSD()}.
	 */
	@Test
	public void testGetCalculatedTaxAmountUSD()
	{
		assertTrue(localOrderItem.getCalculatedTaxAmountUSD() == 159.75);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.LocalOrderItem#toString()}.
	 */
	@Test
	public void testToString()
	{	
		assertEquals(localOrderItem.toString(), "123 ValidName: 1757.23");
		
		localOrderItem = new LocalOrderItem("ValidName", 0.85, 1);
		assertEquals(localOrderItem.toString(), "1 ValidName: 0.94");
	}
}
