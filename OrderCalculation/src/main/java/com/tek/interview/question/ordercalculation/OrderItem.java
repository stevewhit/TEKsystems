package com.tek.interview.question.ordercalculation;

/**
 * An item that is part of a given order. 
 * 
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public interface OrderItem
{
	/**
	 * Returns the name of the item. 
	 * @return Returns the name of the item as a String.
	 */
	public String getName();
	
	/**
	 * Returns the unit price of the item.
	 * @return Returns the unit price of the item as a double. 
	 */
	public double getUnitPrice();
	
	/**
	 * Returns the quantity of this item in a given order.
	 * @return Returns the quantity of this item in a given order as an int.
	 */
	public int getQuantity();
}
