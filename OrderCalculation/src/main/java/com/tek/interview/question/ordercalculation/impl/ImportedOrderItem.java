package com.tek.interview.question.ordercalculation.impl;

/**
 * An item that is part of an order that is imported which contains a name, a unit price, and a quantity. The item has a standard tax rate of 15%.
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public final class ImportedOrderItem extends AbstractOrderItem
{
	/**
	 * Constant tax rate for imported order items in decimal form.
	 */
	public static final double TAX_RATE_DECIMAL_VALUE = .15d;
	
	/**
	 * Constant tax rate for imported order items in percent form.
	 */
	public static final double TAX_RATE_PERCENT_VALUE = 15.0d;
	
	/**
	 * Constructor that accepts a name and unit price. By default the quantity is set to 1.
	 * @param name The name or description of the order item. 
	 * @param unitPrice Price for one unit of the order item.
	 * @throws IllegalArgumentException Throws if name is null or empty, or if unitPrice is less than 0
	 */
	public ImportedOrderItem(String name, double unitPrice) throws IllegalArgumentException
	{
		super(name, unitPrice);
	}
	
	/**
	 * Constructor that accepts a name, unit price, and a quantity
	 * @param name The name or description of the order item. 
	 * @param unitPrice Price for one unit of the order item.
	 * @param quantity The amount of these items to be added to order.
	 * @throws IllegalArgumentException Throws if name is null or empty, or if unitPrice is less than 0
	 */
	public ImportedOrderItem(String name, double unitPrice, int quantity) throws IllegalArgumentException
	{
		super(name, unitPrice, quantity);
	}

	/**
	 * {@inheritDoc} <pre>Note: Tax rate for imported items is 15%.</pre>
	 */
	public double getCalculatedTaxAmountUSD()
	{
		return TaxCalculatorUtils.calculateTaxUSD(super.getUnitPrice(), super.getQuantity(), ImportedOrderItem.TAX_RATE_DECIMAL_VALUE);
	}
}
