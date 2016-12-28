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
public class AbstractOrderItemTest
{
	private class ConcreteAbstractOrderItem extends AbstractOrderItem
	{
		public ConcreteAbstractOrderItem(String name, double unitPrice) throws IllegalArgumentException
		{
			super(name, unitPrice);
		}
		
		public ConcreteAbstractOrderItem(String name, double unitPrice, int quantity) throws IllegalArgumentException
		{
			super(name, unitPrice, quantity);
		}

		public double getCalculatedTaxAmountUSD()
		{
			// Inherited from TaxableObject. Don't need to test the method here because
			// it will be have different implementations in each of the children classes.
			return 0;
		}
	}

	private ConcreteAbstractOrderItem concreteAbstractOrderItem;
	
	/**
	 * Initializes concreteAbstractOrderItem with name="Chocolate cake", unitPrice=123.456, and quantity=3
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("Chocolate cake", 123.456, 3);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		concreteAbstractOrderItem = null;
	}
	
	// REGION: Constructor tests with Name, UnitPrice
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDouble_InvalidName1()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("", 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDouble_InvalidName2()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem(" ", 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDouble_InvalidName3()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem(null, 123.33);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDouble_InvalidUnitPrice()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("valid name", -0.00000000000000000001);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double)}.
	 */
	@Test
	public void testAbstractOrderItemStringDouble_ValidConstructors()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("valid name", 000000000000000000000000000000000.000000000000000000000000000000001);
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("        valiiiiid_nemm21234_..##$$1324", 00011234132421);
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("_", 11.132412353245);
	}
	
	// REGION: Constructor tests with Name, UnitPrice, and Quantity
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDoubleInt_InvalidName1()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("", 123, 1);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDoubleInt_InvalidName2()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem(" ", 123, 1);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDoubleInt_InvalidName3()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem(null, 1234.3, 12);
	}

	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDoubleInt_InvalidUnitPrice()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("valid name", -00000000000000000000000000.0000000000000000000000001, 23);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double, int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAbstractOrderItemStringDoubleInt_InvalidQuantity()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("valid name", 00000000000000000000000000.0000000000000000000000001, 0);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#AbstractOrderItem(java.lang.String, double, int)}.
	 */
	@Test
	public void testAbstractOrderItemStringDoubleInt_ValidConstructors()
	{
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("valid name", 00000000000000000000000000000000.000000000000000000000000000000001, 12335448);
	}
	
	// END CONSTRUCTORS REGION.
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#getName()}.
	 */
	@Test
	public void testGetName()
	{
		assertEquals("Chocolate cake", concreteAbstractOrderItem.getName());
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#getUnitPrice()}.
	 */
	@Test
	public void testGetUnitPrice()
	{
		assertTrue(concreteAbstractOrderItem.getUnitPrice() == 123.456);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#getQuantity()}.
	 */
	@Test
	public void testGetQuantity()
	{
		assertEquals(3, concreteAbstractOrderItem.getQuantity());
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.impl.AbstractOrderItem#toString()}.
	 */
	@Test
	public void testToString()
	{
		assertEquals(concreteAbstractOrderItem.toString(), "3 Chocolate cake: 123.456");
		
		concreteAbstractOrderItem = new ConcreteAbstractOrderItem("Chocolate cake", 00000000000000000000000.000000000000000000001, 3);
		assertEquals(concreteAbstractOrderItem.toString(), "3 Chocolate cake: 0.000000000000000000001");
	}
	
}
