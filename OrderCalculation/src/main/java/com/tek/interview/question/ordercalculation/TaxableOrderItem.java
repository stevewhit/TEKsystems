package com.tek.interview.question.ordercalculation;

/**
 * An item that is part of a given order that is taxable.
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public interface TaxableOrderItem extends OrderItem
{
	/**
	 * Returns the tax multiplier associated with this item.
	 * @return Returns the tax multiplier as a double in decimal format. ie. 60.2% tax returns '.602'
	 */
	public double getTaxMultiplier();
}
