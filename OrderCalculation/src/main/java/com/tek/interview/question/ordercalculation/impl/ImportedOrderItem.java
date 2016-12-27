package com.tek.interview.question.ordercalculation.impl;

import com.tek.interview.question.ordercalculation.TaxableOrderItem;

public final class ImportedOrderItem extends AbstractOrderItem implements TaxableOrderItem
{
	/**
	 * Constant tax rate for imported order items in decimal form.
	 */
	public static final double TAX_DECIMAL_VALUE = .15d;
	
	/**
	 * Constant tax rate for imported order items in percent form.
	 */
	public static final double TAX_PERCENT_VALUE = 15.0d;
	
	/**
	 * Constructor that accepts a name and unit price. By default the quantity is set to 1.
	 * @param name - The name or description of the order item. 
	 * @param unitPrice - Price for one unit of the order item.
	 * @throws IllegalArgumentException - Throws if name is null or empty, or if unitPrice is < 0
	 */
	public ImportedOrderItem(String name, double unitPrice) throws IllegalArgumentException
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
	public ImportedOrderItem(String name, double unitPrice, int quantity) throws IllegalArgumentException
	{
		super(name, unitPrice, quantity);
	}
	
	public double getTaxDecimalValue()
	{
		return ImportedOrderItem.TAX_DECIMAL_VALUE;
	}
	
	public double getTaxPercentValue()
	{
		return ImportedOrderItem.TAX_PERCENT_VALUE;
	}
}
