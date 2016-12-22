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
public class TaxableOrderItemTest extends OrderItemTest
{
	protected class ConcreteTaxableOrderItem extends ConcreteOrderItem implements TaxableOrderItem
	{
		private double taxMultiplier;
		
		public ConcreteTaxableOrderItem(double taxMultiplier)
		{
			this.taxMultiplier = taxMultiplier;
		}

		public double getTaxMultiplier()
		{
			return this.taxMultiplier;
		}
	}
	
	public ConcreteTaxableOrderItem concreteTaxableOrderItem;
	
	/**
	 * Creates a new taxable order item with taxMultiplier = .60329
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		concreteTaxableOrderItem = new ConcreteTaxableOrderItem(.60329);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		super.tearDown();
		
		concreteTaxableOrderItem = null;
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.TaxableOrderItem#getTaxMultiplier()}.
	 */
	@Test
	public void testGetTaxMultiplier()
	{
		assertTrue(concreteTaxableOrderItem.getTaxMultiplier() == .60329);
	}
	
}
