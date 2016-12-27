package com.tek.interview.question.ordercalculation.impl;

import com.tek.interview.question.ordercalculation.TaxableOrderItem;

public class LocalOrderItem extends AbstractOrderItem implements TaxableOrderItem
{
	/**
	 * Constant tax rate for local order items in decimal form.
	 */
	public static final double TAX_DECIMAL_VALUE = .10d;
	
	/**
	 * Constant tax rate for local order items in percent form.
	 */
	public static final double TAX_PERCENT_VALUE = 10.0d;
	
	/**
	 * Constructor that accepts a name and unit price. By default the quantity is set to 1.
	 * @param name - The name or description of the order item. 
	 * @param unitPrice - Price for one unit of the order item.
	 * @throws IllegalArgumentException - Throws if name is null or empty, or if unitPrice is < 0
	 */
	public LocalOrderItem(String name, double unitPrice) throws IllegalArgumentException
	{
		super(name, unitPrice);
	}
	
	/**
	 * Constructor that accepts a name, unit price, and a quantity
	 * @param name - The name or description of the order item. 
	 * @param unitPrice - Price for one unit of the order item.
	 * @param quantity - The amount of these items to be added to order.
	 * @throws IllegalArgumentException - Throws if name is null or empty, or if unitPrice is < 0
	 */
	public LocalOrderItem(String name, double unitPrice, int quantity) throws IllegalArgumentException
	{
		super(name, unitPrice, quantity);
	}
	
	public double getTaxDecimalValue()
	{
		return LocalOrderItem.TAX_DECIMAL_VALUE;
	}
	
	public double getTaxPercentValue()
	{
		return LocalOrderItem.TAX_PERCENT_VALUE;
	}
}
