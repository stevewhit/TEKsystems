package com.tek.interview.question.ordercalculation.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.print.MultiDocPrintService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShoppingBillTest
{
	ShoppingBill bill;
	ShoppingOrder order;
	
	@Before
	public void setUp() throws Exception
	{
		bill = new ShoppingBill();
		
		order = new ShoppingOrder("Order 1");
		order.addOrderItem("Chocolate candy", 12.334);
	}
	
	@After
	public void tearDown() throws Exception
	{
		bill = null;
	}
	
	//============================================
	
	@Test
	public void testShoppingBill()
	{
		bill = new ShoppingBill();
		
		assertTrue(bill.getShoppingOrders() != null);
		assertTrue(bill.getShoppingOrders().size() == 0);
		assertTrue(bill.isValidShoppingBill() == true);
	}
	
	//============================================
	
	@Test(expected=IllegalArgumentException.class)
	public void testShoppingBillListOfShoppingOrder_NullOrderList()
	{
		bill = new ShoppingBill(null);
	}
	
	@Test
	public void testShoppingBillListOfShoppingOrder_EmptyOrderList()
	{
		bill = new ShoppingBill();
		
		assertTrue(bill.getShoppingOrders() != null);
		assertTrue(bill.getShoppingOrders().size() == 0);
		assertTrue(bill.isValidShoppingBill() == true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShoppingBillListOfShoppingOrder_InvalidOrderList()
	{
		List<ShoppingOrder> orderList = new ArrayList<ShoppingOrder>();
		
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		order1.addOrderItem("Chocolate candy", 12.334);
		
		orderList.add(order1);
		orderList.add(null);
		
		bill = new ShoppingBill(orderList);
	}
	
	//============================================
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddShoppingOrder_NullOrder()
	{
		bill.addShoppingOrder(null);	
	}
	
	@Test
	public void testAddShoppingOrder_ValidOrder()
	{
		bill.addShoppingOrder(order);
		assertTrue(bill.getShoppingOrders().size() == 1);
		
		bill.addShoppingOrder(order);
		assertTrue(bill.getShoppingOrders().size() == 2);
	}
	
	//============================================
	
	@Test
	public void testRemoveShoppingOrderIfExists_NullOrder()
	{
		assertFalse(bill.removeShoppingOrderIfExists(null));
	}
	
	@Test
	public void testRemoveShoppingOrderIfExists_EmptyOrderList()
	{
		assertFalse(bill.removeShoppingOrderIfExists(new ShoppingOrder("Not in list.")));
	}

	@Test
	public void testRemoveShoppingOrderIfExists_ValidRemoval()
	{
		bill.addShoppingOrder(order);
		
		assertTrue(bill.removeShoppingOrderIfExists(order));
	}
		
	//============================================
	
	@Test
	public void testRemoveAllShoppingOrders_Invalidbill()
	{
		// No way to test for an invalidshopping bill because of restrictions 
		// I have in place.
	}
	
	@Test
	public void testRemoveAllShoppingOrders_ValidRemoval()
	{
		bill.addShoppingOrder(order);
		
		assertTrue(bill.getShoppingOrders().size() == 1);
		
		bill.removeAllShoppingOrders();
		
		assertTrue(bill.getShoppingOrders().size() == 0);		
	}
	
	//============================================
	
	@Test
	public void testIsValidShoppingBill_Invalid()
	{
		// Can't currently make an invalid shoppingorder therefore
		// there is no way to test for an invalid bill.
	}
	
	@Test
	public void testIsValidShoppingBill_Valid()
	{
		assertTrue(bill.isValidShoppingBill());
		
		bill.addShoppingOrder(order);
		
		assertTrue(bill.isValidShoppingBill());		
	}
	
	//============================================
	
	@Test
	public void testCalculateSumOfOrders_NoOrders()
	{
		assertTrue(bill.calculateSumOfOrders() == 0.0);
	}
	
	@Test
	public void testCalculateSumOfOrders_Orders()
	{
		bill.addShoppingOrder(order);
		assertTrue(bill.calculateSumOfOrders() == order.getTotalCostMinusSalesTax());
		
		bill.addShoppingOrder(order);
		assertTrue(bill.calculateSumOfOrders() == 2.0 * order.getTotalCostMinusSalesTax());
	}
	
	//============================================
	
	@Test
	public void testToString()
	{
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		order1.addOrderItem("book", 12.49);
		order1.addOrderItem("music CD" , 14.99);
		order1.addOrderItem("chocolate bar", 0.85);
		
		ShoppingOrder order2 = new ShoppingOrder("Order 2");
		order2.addOrderItem("imported box of chocolate", 10);
		order2.addOrderItem("imported bottle of perfume" , 47.50);
		
		ShoppingOrder order3 = new ShoppingOrder("Order 3");
		order3.addOrderItem("Imported bottle of perfume", 27.99);
		order3.addOrderItem("bottle of perfume" , 18.99);
		order3.addOrderItem("packet of headache pills", 9.75);
		order3.addOrderItem("box of imported chocolates", 11.25);
		
		bill.addShoppingOrder(order1);
		bill.addShoppingOrder(order2);
		bill.addShoppingOrder(order3);
		
		String expectedOutput = 
				"\n*******Order 1*******\n"
				+ "1 book: 13.74\n"
				+ "1 music CD: 16.49\n"
				+ "1 chocolate bar: 0.94\n"
				+ "Sales Tax: 2.84\n"
				+ "Total: 28.33\n"
				+ "*******Order 2*******\n"
				+ "1 imported box of chocolate: 11.5\n"
				+ "1 imported bottle of perfume: 54.62\n"
				+ "Sales Tax: 8.62\n"
				+ "Total: 57.5\n"
				+ "*******Order 3*******\n"
				+ "1 Imported bottle of perfume: 32.19\n"
				+ "1 bottle of perfume: 20.89\n"
				+ "1 packet of headache pills: 10.73\n"
				+ "1 box of imported chocolates: 12.94\n"
				+ "Sales Tax: 8.77\n"
				+ "Total: 67.98\n"
				+ "Sum of orders: 153.81\n";		
		
		assertEquals(expectedOutput, bill.toString());
	}
	
	//============================================
}