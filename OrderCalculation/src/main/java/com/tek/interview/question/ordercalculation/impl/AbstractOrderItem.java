
package com.tek.interview.question.ordercalculation.impl;

import java.text.DecimalFormat;
import com.tek.interview.question.ordercalculation.OrderItem;

/**
 * Creates an abstract order item containing a name, unit price, and quantity
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public abstract class AbstractOrderItem implements OrderItem
{
	/**
	 * The name/description of the order item.
	 */
	private String name;
	
	/**
	 * Price for one unit of the order item.
	 */
	private double unitPrice;
	
	/**
	 * The amount of these items to be added to the order
	 */
	private int quantity;
	
	/**
	 * Constructor that accepts a name and unit price. By default the quantity is set to 1.
	 * @param name The name or description of the order item. 
	 * @param unitPrice Price for one unit of the order item.
	 * @throws IllegalArgumentException Throws if name is null or empty, or if unitPrice is less than 0
	 */
	public AbstractOrderItem(String name, double unitPrice) throws IllegalArgumentException
	{
		this(name, unitPrice, 1);
	}
	
	/**
	 * Constructor that accepts a name, unit price, and a quantity
	 * @param name The name or description of the order item. 
	 * @param unitPrice Price for one unit of the order item.
	 * @param quantity The amount of these items to be added to order.
	 * @throws IllegalArgumentException Throws if name is null or empty, or if unitPrice is less than 0
	 */
	public AbstractOrderItem(String name, double unitPrice, int quantity) throws IllegalArgumentException
	{
		setName(name);
		setUnitPrice(unitPrice);
		setQuantity(quantity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getUnitPrice()
	{
		return this.unitPrice;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getQuantity()
	{
		return this.quantity;
	}

	/**
	 * Returns the item in the following format "'quantity' 'name': 'unitPrice'". 
	 * <pre>
	 * Example: 1 box of dark chocolates: 19.85
	 * </pre>
	 */
	@Override
	public String toString()
	{
		DecimalFormat formatter = new DecimalFormat("0.####################################");
		return String.format("%1$d %2$s: %3$s", this.quantity, this.name, formatter.format(this.unitPrice));
	}
	
	/**
	 * Checks for a valid name and updates the order item name if valid. 
	 * @param name Name or description of the order item.
	 * @throws IllegalArgumentException Throws if name is null, empty, or only spaces.
	 */
	private void setName(String name) throws IllegalArgumentException
	{
		if (name == null || name.trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Invalid name supplied for the item. The name must contain 1 or more characters");
		}
		
		this.name = name;
	}
	
	/**
	 * Checks for a valid unit price and updates the order item unit price if valid.
	 * @param unitPrice Price for one unit of the order item.
	 * @throws IllegalArgumentException Throws if the unitPrice is less than 0.
	 */
	private void setUnitPrice(double unitPrice) throws IllegalArgumentException
	{
		if (unitPrice < 0)
		{
			throw new IllegalArgumentException("Invalid unitPrice for the item. The unitPrice must be >= 0.");
		}
		
		this.unitPrice = unitPrice;
	}
	
	/**
	 * Checks for a valid quantity and updates the order item quantity if valid.
	 * @param quantity The amount of these items to be added to order.
	 * @throws IllegalArgumentException Throws if quantity <= 0
	 */
	private void setQuantity(int quantity) throws IllegalArgumentException
	{
		if (quantity <= 0)
		{
			throw new IllegalArgumentException("Invalid quantity for the item. The quantity must be > 0");
		}
		
		this.quantity = quantity;
	}
}
