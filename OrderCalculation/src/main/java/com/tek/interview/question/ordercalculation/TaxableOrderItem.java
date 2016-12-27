package com.tek.interview.question.ordercalculation;

/**
 * An item that is part of a given order that is taxable.
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public interface TaxableOrderItem extends OrderItem
{
	/**
	 * Returns the tax decimal value associated with this item.
	 * @return Returns the tax as a double in decimal format. ie. A 60.2% tax rate would return .602
	 */
	public double getTaxDecimalValue();
	
	/**
	 * Returns the tax percent value associated with this item.
	 * @return Returns the tax as a double in percent format. ie. A 60.2% tax rate would return 60.2
	 */
	public double getTaxPercentValue();
}
