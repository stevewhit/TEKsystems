package com.tek.interview.question.ordercalculation.impl;

import java.text.DecimalFormat;

/**
 * An item that is part of an order that is local which contains a name, a unit price, and a quantity. The item has a standard tax rate of 10%.
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public class LocalOrderItem extends AbstractOrderItem
{
	/**
	 * Constant tax rate for local order items in decimal form.
	 */
	public static final double TAX_RATE_DECIMAL_VALUE = .10;
	
	/**
	 * Constant tax rate for local order items in percent form.
	 */
	public static final double TAX_RATE_PERCENT_VALUE = 10.0;
	
	/**
	 * Constructor that accepts a name and unit price. By default the quantity is set to 1.
	 * @param name The name or description of the order item. 
	 * @param unitPrice Price for one unit of the order item.
	 * @throws IllegalArgumentException Throws if name is null or empty, or if unitPrice is less than 0
	 */
	protected LocalOrderItem(String name, double unitPrice) throws IllegalArgumentException
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
	protected LocalOrderItem(String name, double unitPrice, int quantity) throws IllegalArgumentException
	{
		super(name, unitPrice, quantity);
	}

	/**
	 * {@inheritDoc} <pre>Note: Tax rate for local items is 10%.</pre>
	 */
	public double getCalculatedTaxAmountUSD()
	{
		return SalesCalculatorUtils.calculateTaxUSD(super.getUnitPrice(), super.getQuantity(), LocalOrderItem.TAX_RATE_DECIMAL_VALUE);
	}
	
	/**
	 * {@inheritDoc} 
	 * <p>Note: Local sales tax is incorporated in final price.</p>
	 */
	@Override
	public String toString()
	{
		if ( isValidOrderItem() )
		{		
			DecimalFormat formatter = new DecimalFormat("0.####################################");
			
			double priceWithTax = (getUnitPrice() * getQuantity()) + getCalculatedTaxAmountUSD();	
			double roundedPriceWithTax = SalesCalculatorUtils.roundToTwoDecimalPlaces(priceWithTax);
			
			return String.format("%1$d %2$s: %3$s", getQuantity(), getName(), formatter.format(roundedPriceWithTax));
		}
		
		return "";
	}
}
