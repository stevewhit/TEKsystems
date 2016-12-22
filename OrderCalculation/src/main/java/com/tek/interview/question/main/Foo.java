package com.tek.interview.question.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* ****************************************************************************************
 
Please remove all bugs from the code below to get the following output:

<pre>

*******Order 1*******
1 book: 13.74
1 music CD: 16.49
1 chocolate bar: 0.94
Sales Tax: 2.84
Total: 28.33
*******Order 2*******
1 imported box of chocolate: 11.5
1 imported bottle of perfume: 54.62
Sales Tax: 8.62
Total: 57.5
*******Order 3*******
1 Imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 10.73
1 box of imported chocolates: 12.94
Sales Tax: 8.77
Total: 67.98
Sum of orders: 153.81
 
</pre>
 
******************************************************************************************** */

/*
 * represents an item, contains a price and a description.
 *
 */ 
class Item {

	private String description;
	private float price;

	public Item(String description, float price) {
		super();
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}
}

/*
 * represents an order line which contains the @link Item and the quantity.
 *
 */
class OrderLine {

	private int quantity;
	private Item item;

	/*
	 * @param item Item of the order
	 * 
	 * @param quantity Quantity of the item
	 */
	public OrderLine(Item item, int quantity) throws Exception {
		if (item == null) {
			System.err.println("ERROR - Item is NULL");
			throw new Exception("Item is NULL");
		}
		assert quantity > 0;
		
		// Bug Found: Not updating class variable 'item'
		//item = item;
		this.item = item;
		
		// Bug Found: Not updating class variable 'quantity'
		//quantity = quantity;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
}

class Order {

	// Bug Found: NullPointerException thrown when adding an OrderLine because it wasn't instantiated first.
	//private List<OrderLine> orderLines;
	private List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
	
	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}

	public int size() {
		return orderLines.size();
	}

	public OrderLine get(int i) {
		return orderLines.get(i);
	}

	public void clear() {
		this.orderLines.clear();
	}
}

class calculator {

	public static double rounding(double value) {
		// Bug Found: Dividing an integer by an integer results in the return value being an integer cast to a double.
		// Also, we should be utilizing BigDecimal to avoid any internal rounding that may occur.
		//return ( (int) (value * 100)) / 100;
		
		return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	/**
	 * receives a collection of orders. For each order, iterates on the order lines and calculate the total price which
	 * is the item's price * quantity * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without taxes for this order
	 */
	public void calculate(Map<String, Order> o) {

		// Bug Found: Should be verifying orders is not null before iterating through the entrySet.
		if (o == null){
			throw new IllegalArgumentException("Order map is null.");
		}
		
		double grandtotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");
			
			// Bug Found: grandtotal should not be reset to 0 every iteration.
			//grandtotal = 0;

			Order r = entry.getValue();

			double totalTax = 0;
			double total = 0;

			// Iterate through the items in the order
			// Bug Found: NullPointerException thrown because for-loop is trying to access r.size()+1 elements
			//for (int i = 0; i <= r.size(); i++) {
			for (int i = 0; i < r.size(); i++) {
			
				// Calculate the taxes
				double tax = 0;

				// Bug Found: Searching through item descriptions containing "imported" is case-sensitive.
				//if (r.get(i).getItem().getDescription().contains("imported")) {
				if (r.get(i).getItem().getDescription().toLowerCase().contains("imported")) {
					tax = rounding(r.get(i).getItem().getPrice() * 0.15); // Extra 5% tax on
					// imported items
				} else {
					tax = rounding(r.get(i).getItem().getPrice() * 0.10);
				}

				// Calculate the total price
				// Bug Found: Math.Floor removes any precision beyond the decimal.
				//double totalprice = r.get(i).getItem().getPrice() + Math.floor(tax);
				double totalprice = r.get(i).getItem().getPrice() + rounding(tax);

				// Print out the item's total price
				// Bug Found: Math.Floor removes any precision beyond the decimal.
				// Bug Found: Item quantity not added to output.
				//System.out.println(r.get(i).getItem().getDescription() + ": " + Math.floor(totalprice));
				System.out.println(
						r.get(i).getQuantity() + 
						" " + 
						r.get(i).getItem().getDescription() + 
						": " + 
						rounding(totalprice));
				
				// Keep a running total
				totalTax += tax;
				total += r.get(i).getItem().getPrice();
			}

			// Print out the total taxes
			// Bug Found: Math.Floor removes any precision beyond the decimal.
			//System.out.println("Sales Tax: " + Math.floor(totalTax));
			System.out.println("Sales Tax: " + rounding(totalTax));

			// Bug Found: Total should represent the price of all the items minus their sales tax.
			//total = total + totalTax;

			// Print out the total amount
			// Bug Found: Math.Floor removes any precision beyond the decimal.
			//System.out.println("Total: " + Math.floor(total * 100) / 100);
			System.out.println("Total: " + rounding(total));
			grandtotal += total;
		}

		// Bug Found: Math.Floor removes any precision beyond the decimal.
		//System.out.println("Sum of orders: " + Math.floor(grandtotal * 100) / 100);
		System.out.println("Sum of orders: " + rounding(grandtotal));
	}
}

public class Foo {

	public static void main(String[] args) throws Exception {

		// Bug Found: HashMaps offer a performance enhancement when the order of the entrysets don't matter because entrysets
		// are added to the map according to their calculated hash values rather than the order with which they are added.
		// In this case, we care about the order of the entries added.
		//Map<String, Order> o = new HashMap<String, Order>();
		Map<String, Order> o = new LinkedHashMap<String, Order>();

		Order c = new Order();

		// In this implementation, all calculations and outputs are accomplished in the calculator.calculate()
		// so this is unnecessary.
		//double grandTotal = 0;

		c.add(new OrderLine(new Item("book", (float) 12.49), 1));
		c.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		c.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		o.put("Order 1", c);

		// Reuse cart for an other order
		// Bug Found: Java is pass-by-reference therefore any changes to the order at this point result in changes to the entire order at the end
		//c.clear();
		c = new Order();
			
		c.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		c.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		o.put("Order 2", c);

		// Reuse cart for an other order
		// Bug Found: Java is pass-by-reference therefore any changes to the order at this point result in changes to the entire order at the end
		//c.clear();
		c = new Order();

		c.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		c.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		c.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		
		// Bug Found: Imported misspelled.
		c.add(new OrderLine(new Item("box of imported chocolates", (float) 11.25), 1));

		o.put("Order 3", c);

		new calculator().calculate(o);

	}
}
