/**
 * 
 */
package com.tek.interview.question.ordercalculation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public class OrderItemTest
{
	private class ConcreteOrderItem implements OrderItem
	{
		private String name;
		private double unitPrice;
		private int quantity;
		
		public ConcreteOrderItem(String name, double unitPrice, int quantity)
		{
			this.name = name;
			this.unitPrice = unitPrice;
			this.quantity = quantity;
		}

		public String getName()
		{
			return name;
		}

		public double getUnitPrice()
		{
			return unitPrice;
		}

		public int getQuantity()
		{
			return quantity;
		}
	}
	
	public ConcreteOrderItem concreteOrderItem;
	
	/**
	 * Creates a new ConcreteOrderItem with name("mock Order Item"), unitPrice(3902.54), and quantity(2)
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		concreteOrderItem = new ConcreteOrderItem("mock Order Item", 3902.54, 2);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		concreteOrderItem = null;
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.OrderItem#getName()}.
	 */
	@Test
	public void testGetName()
	{
		assertEquals("mock Order Item", this.concreteOrderItem.getName());
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.OrderItem#getUnitPrice()}.
	 */
	@Test
	public void testGetUnitPrice()
	{
		assertTrue(this.concreteOrderItem.getUnitPrice() == 3902.54);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.OrderItem#getQuantity()}.
	 */
	@Test
	public void testGetQuantity()
	{
		assertEquals(2, concreteOrderItem.getQuantity());
	}
	
}
