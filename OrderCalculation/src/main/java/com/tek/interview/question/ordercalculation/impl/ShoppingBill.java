package com.tek.interview.question.ordercalculation.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public class ShoppingBill
{
	/**
	 * The list of shopping orders associated with this bill.
	 */
	private List<ShoppingOrder> shoppingOrderList;
	
	/**
	 * Default constructor.
	 */
	public ShoppingBill()
	{
		this( new ArrayList<ShoppingOrder>() );
	}
	
	/**
	 * Constructor that accepts a pre-defined list of orders to include on this bill.
	 * @param shoppingOrderList The list of orders to include on this bill
	 * @throws IllegalArgumentException Throws if the order list is null or if the orders contain invalid items in them.
	 */
	public ShoppingBill(List<ShoppingOrder> shoppingOrderList) throws IllegalArgumentException
	{
		if (shoppingOrderList == null)
		{
			throw new IllegalArgumentException("Cannot create new bill with a null shoppingOrderList");
		}
		
		this.shoppingOrderList = shoppingOrderList;
		
		if ( !isValidShoppingBill() )
		{
			shoppingOrderList = null;
			
			throw new IllegalArgumentException("Cannot create new bill because the orders have invalid items.");
		}
	}
	
	/**
	 * Returns the shopping orders on this bill.
	 * @return Returns the shopping order list for this bill
	 */
	public List<ShoppingOrder> getShoppingOrders()
	{
		return this.shoppingOrderList;
	}
	
	/**
	 * Adds a shopping order to the bill. 
	 * @param shoppingOrder The shopping order to add to the bill.
	 * @throws IllegalStateException Throws if the shopping bill hasn't been properly initialized.
	 * @throws IllegalArgumentException Throws if the shopping order that is passed, is null or invalid.
	 */
	public void addShoppingOrder(ShoppingOrder shoppingOrder) throws IllegalStateException, IllegalArgumentException
	{
		// Verify bill is properly initialized.
		if (shoppingOrderList == null)
		{
			throw new IllegalStateException("ShoppingBill must be initialized before adding orders.");
		}
		
		// Verify the shopping order
		if ( shoppingOrder == null || !shoppingOrder.isValidShoppingOrder())
		{
			throw new IllegalArgumentException("Cannot add order to bill because it isn't a valid shopping order.");
		}
		
		this.shoppingOrderList.add(shoppingOrder);
	}
	
	/**
	 * Attempts to remove the given shopping order from the bill.
	 * @param orderToRemove The order to remove from the bill.
	 * @return Returns true if the item was removed, otherwise false.
	 * @throws IllegalStateException Throws if the bill hasn't been properly initialized.
	 */
	public boolean removeShoppingOrderIfExists(ShoppingOrder orderToRemove) throws IllegalStateException
	{
		// Verify bill is properly initialized.
		if ( !isValidShoppingBill() )
		{
			throw new IllegalStateException("Cannot remove any orders until bill has been initialized.");
		}
		
		if (orderToRemove != null)
		{	
			// Iterate through each order in the bill and remove a matching order.
			for (Iterator<ShoppingOrder> orderIterator = shoppingOrderList.listIterator(); orderIterator.hasNext();)
			{
				ShoppingOrder order = orderIterator.next();
				
				// Remove if it's a matching order.
				if (order != null && order.isValidShoppingOrder() && order.equals(orderToRemove))
				{
					orderIterator.remove();
					
					return true;
				}
			}
		}
		
		// The default return is false since nothing was removed.
		return false;
	}
	
	/**
	 * Removes all shopping orders from the bill.
	 * @throws IllegalStateException Throws if bill has not been properly initialized.
	 */
	public void removeAllShoppingOrders() throws IllegalStateException
	{
		// Verify bill is properly initialized.
		if ( !isValidShoppingBill() )
		{
			throw new IllegalStateException("Cannot remove any orders until bill has been initialized.");
		}
		
		shoppingOrderList.clear();	
	}
	
	/**
	 * Verifies the bill has been properly initialized.
	 * @return Returns true if the shopping bill is valid, otherwise false.
	 */
	public boolean isValidShoppingBill()
	{
		if (shoppingOrderList == null)
		{
			return false;
		}
		
		// Check each order to make sure it's valid.
		for (ShoppingOrder order : shoppingOrderList)
		{
			if ( order == null || !order.isValidShoppingOrder() )
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Calculates the sum of all order in the bill.
	 * @return Returns the sum of all orders in the bill, as a double.
	 */
	public double calculateSumOfOrders()
	{
		// Verify bill has been properly initialized.
		if ( !isValidShoppingBill() )
		{
			throw new IllegalStateException("Cannot calculate sum of orders until bill has been initialized.");
		}
		
		double sumOfOrders = 0.0;
		
		// Add the totals of each of the orders.
		for(Iterator<ShoppingOrder> orderIterator = shoppingOrderList.listIterator(); orderIterator.hasNext();)
		{
			ShoppingOrder order = orderIterator.next();
			
			sumOfOrders += order.getTotalCostMinusSalesTax();
		}
		
		// Return the sum, rounded to two decimal places.
		return SalesCalculatorUtils.roundToTwoDecimalPlaces(sumOfOrders);
	}
	
	/**
	 * Returns the bill as a formatted string.
	 * <pre>
	 * Example output:
	 * 
	 * *******Order 1*******
	 * 1 book: 13.74
	 * 2 music players: 23.45
	 * Sales Tax: 2.33
	 * Total: 37.19
	 * *******Order 2*******
	 * 1 chocolate candy: 1.11
	 * 3 teddy bear: 23.33
	 * Sales Tax: 3.24
	 * Total: 22.45
	 * Sum of orders: 59.64
	 * </pre>
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		// Verify bill has been properly initialized.
		if ( isValidShoppingBill() )
		{
			if (hasShoppingOrders())
			{
				// Iterate each order and make sure it is valid before adding.
				for(Iterator<ShoppingOrder> orderIterator = shoppingOrderList.listIterator(); orderIterator.hasNext();)
				{
					ShoppingOrder order = orderIterator.next();
					
					// Make sure order is valid before adding to output.
					if ( order != null && order.isValidShoppingOrder() )
					{
						sb.append(String.format("%1$s", order.toString()));
					}
					else
					{
						sb.append("Invalid order.");
					}
				}
				
				DecimalFormat formatter = new DecimalFormat("0.####################################");
							
				// Append the sum of orders.
				sb.append(String.format("\nSum of orders: %1$s\n", formatter.format(calculateSumOfOrders())));
			}
			else
			{
				sb.append("No orders in bill.");
			}
		}
		else
		{
			sb.append("Invalid bill.");
		}
		
		return sb.toString();
	}

	/**
	 * Verifies that the bill has orders in it.
	 * @return Returns true if the bill has orders, otherwise false.
	 */
	private boolean hasShoppingOrders()
	{
		if (shoppingOrderList == null)
		{
			return false;
		}
		
		return !shoppingOrderList.isEmpty();
	}
}
