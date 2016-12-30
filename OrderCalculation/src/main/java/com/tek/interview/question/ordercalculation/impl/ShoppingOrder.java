package com.tek.interview.question.ordercalculation.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import com.tek.interview.question.ordercalculation.OrderItem;
import com.tek.interview.question.ordercalculation.TaxableObject;

/**
 * Represents a shopping cart which contains zero or more items.
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public class ShoppingOrder implements TaxableObject
{
	/**
	 * A list of all items corresponding to this order.
	 */
	private List<OrderItem> orderItemList;
	
	/**
	 * The ID/name of this order.
	 */
	private String orderId;

	/**
	 * Default constructor that initializes the orders and creates a psuedo-random ID for this object.
	 */
	public ShoppingOrder()
	{
		this( generateUniqueId() );
	}
	
	/**
	 * Constructor that intializes a new ShoppingOrder and associates a supplied id with this object.
	 * @param orderId The ID or name of the order.
	 * @throws IllegalArgumentException Throws if the supplied id is null.
	 */
	public ShoppingOrder(String orderId) throws IllegalArgumentException
	{
		if (orderId == null || orderId.trim().isEmpty())
		{
			throw new IllegalArgumentException("Order id cannot be null or empty.");
		}
		
		orderItemList = new ArrayList<OrderItem>();
		this.orderId = orderId;
		
		if (!isValidShoppingOrder())
		{
			orderId = null;
			orderItemList = null;
			
			throw new IllegalArgumentException("ShoppingOrder could not be created because an invalid orderId was passed.");
		}
	}
	
	/**
	 * Creates and adds a new item to the order with the given name, and price. 
	 * @param itemName Name of the item that is being added to the order.
	 * @param unitPrice Price of the item in USD
	 * @return Returns the itemId associated with the order item that is created.
	 * @throws IllegalArgumentException Throws if the name or price are invalid.
	 * @throws IllegalStateException Throws if class hasn't been properly initialized.
	 */
	public String addOrderItem(String itemName, double unitPrice) throws IllegalArgumentException, IllegalStateException
	{
		return addOrderItem(itemName, unitPrice, 1);
	}
	
	/**
	 * Creates and adds a new item to the order with the given name, price, and quantity.
	 * @param itemName Name of the item that is being added to the order.
	 * @param unitPrice Price of the item in USD
	 * @param quantity The number of this item that is added to the order.
	 * @return Returns the itemId associated with the order item that is created.
	 * @throws IllegalArgumentException Throws if the name or price are invalid.
	 * @throws IllegalStateException Throws if class hasn't been properly initialized.
	 */
	public String addOrderItem(String itemName, double unitPrice, int quantity) throws IllegalArgumentException, IllegalStateException
	{
		if ( !isValidShoppingOrder() )
		{
			throw new IllegalStateException("Cannot remove item because shopping order needs to be initialized first.");
		}
		
		try
		{
			if (itemName == null)
			{
				throw new IllegalArgumentException("ItemName cannot be null.");
			}
			// If the name contains imported, then create an ImportedOrderItem
			if (itemName.toLowerCase().contains("imported"))
			{
				return addOrderItem(new ImportedOrderItem(itemName, unitPrice, quantity));
			}
			
			// If the name doesn't contain imported, then create a LocalOrderItem
			else 
			{
				return addOrderItem(new LocalOrderItem(itemName, unitPrice, quantity));
			}
		}
		catch(IllegalArgumentException ex)
		{
			// Invalid item details
			throw new IllegalArgumentException("Invalid arguments supplied when adding a new order item: " + ex.getMessage());
		}
		catch(IllegalStateException ex)
		{
			// Class not initialized properly.
			throw ex;
		}
	}
	
	/**
	 * Removes an OrderItem with the given id from the ShoppingOrder. Returns a value depending on what the status of the removal was.
	 * @param itemId The ID of the item that is to be removed.
	 * @return Returns true if the item was sucessfully removed, otherwise it returns false.
	 * @throws IllegalStateException Throws if class hasn't been properly initialized.
	 */
	public boolean removeOrderItemIfExists(String itemId) throws IllegalStateException
	{
		if ( !isValidShoppingOrder() )
		{
			throw new IllegalStateException("Cannot remove item because shopping order needs to be initialized first.");
		}
		
		// Valididate the itemId
		if ( itemId != null && !itemId.trim().isEmpty() )
		{			
			// Iterate through the list looking for a matching itemId.
			// If found, remove it and return true.
			for(Iterator<OrderItem> itemIterator = orderItemList.listIterator(); itemIterator.hasNext();)
			{
				OrderItem item = itemIterator.next();
				
				if (item != null && item.isValidOrderItem() && item.getItemId().equals(itemId))
				{
					itemIterator.remove();
					
					return true;
				}
			}
		}
		
		// The default return is false since nothing was removed.
		return false;
	}
	
	/**
	 * Removes all items from the order.
	 * @throws IllegalStateException Throws if class hasn't been properly initialized.
	 */
	public void removeAllOrderItems() throws IllegalStateException
	{
		if ( !isValidShoppingOrder() )
		{
			throw new IllegalStateException("Cannot remove item because shopping order needs to be initialized first.");
		}
		
		orderItemList.clear();
	}
	
	/**
	 * Returns a list of the orderitems associated with this shopping order.
	 * @return Returns a list of the item associated with this order.
	 */
	public List<OrderItem> getAllOrderItems()
	{
		return orderItemList;
	}
	
	/**
	 * Returns boolean indicating whether the ShoppingOrder has OrderItems in it.
	 */
	public boolean hasOrderItems() throws IllegalStateException
	{
		if (orderItemList == null)
		{
			return false;
		}
		
		return !orderItemList.isEmpty();
	}
	
	/**
	 * Returns the id/name of this ShoppingOrder.
	 * @return
	 */
	public String getOrderId()
	{
		return this.orderId;
	}
	
	/**
	 * Returns a boolean indicating whether this order is valid by checking for the appropriate fields.
	 * @return Returns true if the order has been properly initialized.
	 */
	public boolean isValidShoppingOrder()
	{
		if (orderItemList == null)
		{
			return false;
		}
		
		// Check each item to make sure they're valid.
		for(OrderItem item : orderItemList)
		{
			if (item == null || !item.isValidOrderItem() )
			{
				return false;
			}
		}
		
		return (orderId != null) && (!orderId.trim().isEmpty());
	}
	
	/**
	 * Returns the order as a formatted string.
	 * <pre>
	 * Example output: 
	 * 
	 * *******Order 1*******
	 * 1 book: 13.74
	 * 2 music players: 23.45
	 * Sales Tax: 2.33
	 * Total: 37.19
	 * </pre>
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		if ( isValidShoppingOrder() )
		{
			// Append 'header' containing orderID
			sb.append(String.format("\n*******%1$s*******\n", orderId));
			
			if (hasOrderItems())
			{
				for (Iterator<OrderItem> itemIterator = orderItemList.listIterator(); itemIterator.hasNext();)
				{			
					OrderItem item = itemIterator.next();
					
					if ( item.isValidOrderItem() )
					{
						sb.append(item.toString() + "\n");
					}
				}
			}
			else
			{
				sb.append("No items in order.\n");
			}
			
			DecimalFormat formatter = new DecimalFormat("0.####################################");
			
			// Append the sales tax
			sb.append(String.format("Sales Tax: %1$s\n", formatter.format(getCalculatedTaxAmountUSD())));
			
			// Append the total item cost (without tax).
			sb.append(String.format("Total: %1$s", formatter.format(getTotalCostMinusSalesTax())));
		}
		
		return sb.toString();
	}
	
	/**
	 * Checks for equality between two orders by comparing the orderId and items stored inside them.
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object == null)
		{
			return false;
		}
		
		if (!ShoppingOrder.class.isAssignableFrom(object.getClass()))
		{
			return false;
		}
		
		final ShoppingOrder castObj = (ShoppingOrder) object;
		
		// Compare orderIds
		if (castObj.getOrderId() != this.getOrderId())
		{
			return false;
		}

		if (castObj.getAllOrderItems().size() != getAllOrderItems().size())
		{
			return false;
		}
		
		Iterator<OrderItem> castObjIter = castObj.getAllOrderItems().listIterator();
		Iterator<OrderItem> thisObjIter = getAllOrderItems().listIterator(); 
		
		// Check each item in the cast object's list
		while(thisObjIter.hasNext() && castObjIter.hasNext())
		{
			OrderItem thisItem = thisObjIter.next();
			OrderItem objItem = castObjIter.next();
			
			if (!thisItem.equals(objItem))
			{
				return false;
			}
		}
				
		return true;
	}
	
	/**
	 * {@inheritDoc} <pre>Note: This amount is calculated by taking the sum of each item's tax in the overall order.</pre>
	 */
	public double getCalculatedTaxAmountUSD() 
	{
		double salesTax = 0.0d;
		 
		if ( isValidShoppingOrder() )
		{
			// Iterate each order item and add the sales tax.
			for (Iterator<OrderItem> itemIterator = orderItemList.listIterator(); itemIterator.hasNext();)
			{
				OrderItem item = itemIterator.next();
				
				if ( item.isValidOrderItem() )
				{
					salesTax += item.getCalculatedTaxAmountUSD();
				}
			}
		}
		
		return SalesCalculatorUtils.roundToTwoDecimalPlaces(salesTax);
	}
	
	/**
	 * Returns the total cost of the order by summing the total of each item. This figure includes tax.
	 * @return Returns the total cost of the order (with tax) as a double.
	 */
	public double getTotalOrderCost()
	{
		double totalCost = 0.0;
		
		if ( isValidShoppingOrder() )
		{
			totalCost = SalesCalculatorUtils.roundToTwoDecimalPlaces(getTotalCostMinusSalesTax() + getCalculatedTaxAmountUSD());
		}
		
		return totalCost;
	}
	
	/**
	 * Returns the total cost of the entire order (minus sales tax) by summing the unit price * quantity from each of the items.
	 * @return Returns the total cost of the order (minus sales tax) as a double.
	 */
	public double getTotalCostMinusSalesTax()
	{
		double totalPrice = 0.0d;
		
		if ( isValidShoppingOrder() )
		{
			// Iterate each order item and add each unit price.
			for (Iterator <OrderItem> itemIterator = orderItemList.listIterator(); itemIterator.hasNext(); )
			{
				OrderItem item = itemIterator.next();
				
				if ( item.isValidOrderItem() )
				{
					totalPrice += (item.getUnitPrice() * item.getQuantity());
				}
			}
		}
		
		return SalesCalculatorUtils.roundToTwoDecimalPlaces(totalPrice);
	}
	
	/**
	 * Adds the given OrderItem to the ShoppingOrder. 
	 * @param itemToAdd The item to add to the order.
	 * @return Returns the id of the item that is being added. (For tracking reasons)
	 * @throws IllegalArgumentException Throws if trying to add a null item to the order.
	 * @throws IllegalStateException Throws if class hasn't been properly initialized.
	 */
	private String addOrderItem(OrderItem itemToAdd) throws IllegalArgumentException, IllegalStateException
	{
		if ( !isValidShoppingOrder() )
		{
			throw new IllegalStateException("Cannot add new items because shopping order needs to be initialized first.");
		}
		
		// Validate the item that is being added.
		if (itemToAdd == null || !itemToAdd.isValidOrderItem())
		{
			throw new IllegalArgumentException("Cannot add supplied orderitem to shoppingorder because it is null or invalid.");
		}
		
		orderItemList.add(itemToAdd);
		
		return itemToAdd.getItemId();
	}
	
	/**
	 * Generates a unique id.
	 * @return Returns a unique id as a string.
	 */
	private static String generateUniqueId()
	{
		return UUID.randomUUID().toString();
	}
}

