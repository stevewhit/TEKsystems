package com.tek.interview.question.ordercalculation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaxableObjectTest
{
	private class ConcreteTaxableObject implements TaxableObject
	{
		private double taxAmount;
		
		public ConcreteTaxableObject(double taxAmount)
		{
			this.taxAmount = taxAmount;
		}

		public double getCalculatedTaxAmountUSD()
		{
			return this.taxAmount;
		}
	}
	
	private ConcreteTaxableObject concreteTaxableObject;
	
	@Before
	public void setUp() throws Exception
	{
		concreteTaxableObject = new ConcreteTaxableObject(12.334);
	}
	
	@After
	public void tearDown() throws Exception
	{
		concreteTaxableObject = null;
	}
	
	@Test
	public void testGetCalculatedTaxAmountUSD()
	{
		assertTrue(concreteTaxableObject.getCalculatedTaxAmountUSD() == 12.334);
	}
	
}
