package com.tek.interview.question.main;

import com.tek.interview.question.ordercalculation.impl.ShoppingBill;
import com.tek.interview.question.ordercalculation.impl.ShoppingOrder;

public class Bar
{
	/**
	 * Main entry point for the second version of the OrderCalculation interview question.
	 * @param args Arguments not used.
	 */
	public static void main(String[] args)
	{
		System.out.println("OrderCalculation v2.00 - Refactored Code");

		ShoppingBill shoppingBill = new ShoppingBill();
		
		ShoppingOrder orderOne = new ShoppingOrder("Order 1");
		orderOne.addOrderItem("book", 12.49d, 1);
		orderOne.addOrderItem("music CD", 14.99d, 1);
		orderOne.addOrderItem("chocolate bar", 0.85d, 1);
		shoppingBill.addShoppingOrder(orderOne);
		
		ShoppingOrder orderTwo = new ShoppingOrder("Order 2");
		orderTwo.addOrderItem("imported box of chocolate", 10, 1);
		orderTwo.addOrderItem("imported bottle of perfume", 47.50d, 1);
		shoppingBill.addShoppingOrder(orderTwo);
		
		ShoppingOrder orderThree = new ShoppingOrder("Order 3");
		orderThree.addOrderItem("Imported bottle of perfume", 27.99d, 1);
		orderThree.addOrderItem("bottle of perfume", 18.99d, 1);
		orderThree.addOrderItem("packet of headache pills", 9.75d, 1);
		orderThree.addOrderItem("box of imported chocolates", 11.25d, 1);
		shoppingBill.addShoppingOrder(orderThree);
		
		System.out.println(shoppingBill.toString());		
	}
	
}
