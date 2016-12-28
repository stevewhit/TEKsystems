package com.tek.interview.question.ordercalculation;

/**
 * An item that is part of a given order. 
 * 
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public interface OrderItem extends TaxableObject
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
	
	/**
	 * Returns the unique id of this item.
	 * @return Returns the unique id of this item as a String.
	 */
	public String getItemId();
	
	/**
	 * Verifies that this orderItem is valid by checking the necessary fields.
	 * @return Returns true if the item is valid and false if the item is not valid.
	 */
	public boolean isValidOrderItem();
	
	/**
	 * Returns the item in the following format "'quantity' 'name': 'unitPrice'". 
	 * <pre>Example: 1 box of dark chocolates: 19.85</pre>
	 */
	@Override
	public String toString();
}
