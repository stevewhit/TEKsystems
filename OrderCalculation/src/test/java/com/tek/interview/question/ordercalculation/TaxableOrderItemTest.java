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
		private double taxDecimalValue;
		
		public ConcreteTaxableOrderItem(double taxDecimalValue)
		{
			this.taxDecimalValue = taxDecimalValue;
		}

		public double getTaxDecimalValue()
		{
			return this.taxDecimalValue;
		}
		
		public double getTaxPercentValue()
		{
			return this.taxDecimalValue * 100.0;
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
	 * Test method for {@link com.tek.interview.question.ordercalculation.TaxableOrderItem#getTaxDecimalValue()}.
	 */
	@Test
	public void testGetTaxDecimalValue()
	{
		assertTrue(concreteTaxableOrderItem.getTaxDecimalValue() == .60329);
	}
	
	/**
	 * Test method for {@link com.tek.interview.question.ordercalculation.TaxableOrderItem#getTaxPercentValue()}.
	 */
	@Test
	public void testGetTaxMultiplier()
	{
		assertTrue(concreteTaxableOrderItem.getTaxPercentValue() == 60.329);
	}
}
