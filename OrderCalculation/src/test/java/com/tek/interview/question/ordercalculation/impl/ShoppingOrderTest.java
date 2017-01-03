package com.tek.interview.question.ordercalculation.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tek.interview.question.ordercalculation.OrderItem;

public class ShoppingOrderTest
{
	ShoppingOrder orderOneItem;
	ShoppingOrder orderTwoItems;
	
	@Before
	public void setUp() throws Exception
	{
		orderOneItem = new ShoppingOrder("OrderOneItem");
		orderOneItem.addOrderItem("First Item", 12.3321);
		
		orderTwoItems = new ShoppingOrder("OrderTwoItems");
		orderTwoItems.addOrderItem("Imported First Item", 14.239);
		orderTwoItems.addOrderItem("Second Item", 1324.322, 3);
	}
	
	@After
	public void tearDown() throws Exception
	{
		orderOneItem = null;
		orderTwoItems = null;
	}
	
	//========================================================
	
	@Test
	public void testShoppingOrder_ValidConstructor()
	{
		orderOneItem = new ShoppingOrder();
		
		assertTrue(orderOneItem.getOrderId() != null);
	}
	
	//========================================================
	
	@Test(expected=IllegalArgumentException.class)
	public void testShoppingOrderString_NullId()
	{
		orderOneItem = new ShoppingOrder(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShoppingOrderString_EmptyId()
	{
		orderOneItem = new ShoppingOrder("");
	}
	
	@Test
	public void testShoppingOrderString_Valid()
	{
		orderOneItem = new ShoppingOrder("Valid nameee");
		
		assertEquals(orderOneItem.getOrderId(), "Valid nameee");
		assertTrue(orderOneItem.getAllOrderItems() != null);
		assertEquals(orderOneItem.hasOrderItems(), false);
	}
	
	//========================================================
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddOrderItemStringDouble_InvalidItemName1()
	{
		orderOneItem.addOrderItem(null, 23.44);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddOrderItemStringDouble_InvalidItemName2()
	{
		orderOneItem.addOrderItem("", 23.44);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddOrderItemStringDouble_InvalidUnitPrice()
	{
		orderOneItem.addOrderItem("valid name", -23.44);
	}
	
	@Test
	public void testAddOrderItemStringDouble_Valid()
	{
		assertEquals(orderOneItem.isValidShoppingOrder(), true);
		assertEquals(orderOneItem.getAllOrderItems().size(), 1);
		assertEquals(orderOneItem.getAllOrderItems().get(0).getQuantity(), 1);
	}
	
	//========================================================

	@Test(expected=IllegalArgumentException.class)
	public void testAddOrderItemStringDoubleInt_NullItemName()
	{
		orderOneItem.addOrderItem(null, 23.325, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddOrderItemStringDoubleInt_InvalidArg()
	{
		orderOneItem.addOrderItem("valid", -23.325, 2);
	}
	
	@Test
	public void testAddOrderItemStringDoubleInt_Valid()
	{
		assertEquals(orderTwoItems.getAllOrderItems().size(), 2);
		
		assertTrue(orderTwoItems.getAllOrderItems().get(0) instanceof ImportedOrderItem);
		assertTrue(orderTwoItems.getAllOrderItems().get(1) instanceof LocalOrderItem);
	}
	
	//========================================================
	
	@Test
	public void testRemoveOrderItemIfExists()
	{
		assertFalse(orderOneItem.removeOrderItemIfExists(null));
		assertFalse(orderOneItem.removeOrderItemIfExists(""));
		assertFalse(orderOneItem.removeOrderItemIfExists("won't find this"));
		
		String idToRemove = orderOneItem.addOrderItem("remove me", 223.45).getItemId();
		int orderSize = orderOneItem.getAllOrderItems().size();
		
		assertTrue(orderOneItem.removeOrderItemIfExists(idToRemove));
		assertEquals(orderOneItem.getAllOrderItems().size(), orderSize - 1);
	}
	
	//========================================================
	
	@Test
	public void testRemoveAllOrderItems()
	{
		orderTwoItems.removeAllOrderItems();
		
		assertEquals(orderTwoItems.getAllOrderItems().size(), 0);
		assertFalse(orderTwoItems.hasOrderItems());
	}
	
	//========================================================
	
	@Test
	public void testGetAllOrderItems()
	{
		orderOneItem = new ShoppingOrder();
		
		OrderItem addedItem = orderOneItem.addOrderItem("valid", 23.43);
		
		assertTrue(orderOneItem.getAllOrderItems().size() == 1);
		assertTrue(orderOneItem.getAllOrderItems().get(0).equals(addedItem));
		
	}
	
	//========================================================
	
	@Test
	public void testHasOrderItems()
	{
		assertTrue(orderOneItem.hasOrderItems());
		assertTrue(orderTwoItems.hasOrderItems());
		
		orderOneItem.removeAllOrderItems();
		assertFalse(orderOneItem.hasOrderItems());
	}
	
	//========================================================
	
	@Test
	public void testGetOrderId()
	{
		assertEquals(orderOneItem.getOrderId(), "OrderOneItem");
	}
	
	//========================================================
	
	@Test
	public void testIsValidShoppingOrder()
	{
		// Can't create an invalid shopping order currently.
		// Restricted by the constructors
	}
	
	//========================================================
	
	@Test
	public void testToString1()
	{
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		
		order1.addOrderItem("book", 12.49, 1);
		order1.addOrderItem("music CD", 14.99, 1);
		order1.addOrderItem("chocolate bar", 0.85, 1);
		
		String expectedOutput = 
				"\n*******Order 1*******\n"
				+ "1 book: 13.74\n"
				+ "1 music CD: 16.49\n"
				+ "1 chocolate bar: 0.94\n"
				+ "Sales Tax: 2.84\n"
				+ "Total: 28.33";
		
		assertEquals(expectedOutput, order1.toString());
	}
	
	@Test
	public void testToString2()
	{
		ShoppingOrder order2 = new ShoppingOrder("Order 2");
		
		order2.addOrderItem("imported box of chocolate", 10, 1);
		order2.addOrderItem("imported bottle of perfume", 47.50, 1);
		
		String expectedOutput = 
				"\n*******Order 2*******\n"
				+ "1 imported box of chocolate: 11.5\n"
				+ "1 imported bottle of perfume: 54.62\n"
				+ "Sales Tax: 8.62\n"
				+ "Total: 57.5";
		
		assertEquals(expectedOutput, order2.toString());
	}
	
	//========================================================

	@Test 
	public void testEquals_NullObj()
	{
		assertFalse(orderOneItem.equals(null));
	}
	
	@Test 
	public void testEquals_DiffClass()
	{
		assertFalse(orderOneItem.equals(1));
		assertFalse(orderOneItem.equals(""));
	}
	
	@Test 
	public void testEquals_DiffOrderId()
	{
		ShoppingOrder orderToCompare = new ShoppingOrder("Diff OrderName");
		orderToCompare.addOrderItem("First Item", 12.3321);
		
		assertFalse(orderOneItem.equals(orderToCompare));
	}
	
	@Test 
	public void testEquals_diffItemSize()
	{
		ShoppingOrder orderToCompare = new ShoppingOrder(orderOneItem.getOrderId());
		orderToCompare.addOrderItem("First Item", 12.3321);
		orderToCompare.addOrderItem("Wrong size!", 123.342);
		
		assertFalse(orderOneItem.equals(orderToCompare));
	}
	
	@Test 
	public void testEquals_SameSizeDiffItems()
	{
		ShoppingOrder orderToCompare = new ShoppingOrder(orderOneItem.getOrderId());
		orderToCompare.addOrderItem("Wrong Id", 12.3321);

		assertFalse(orderOneItem.equals(orderToCompare));	
	}
	
	//========================================================
	
	@Test
	public void testGetCalculatedTaxAmountUSD1()
	{
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		
		order1.addOrderItem("book", 12.49, 1);
		order1.addOrderItem("music CD", 14.99, 1);
		order1.addOrderItem("chocolate bar", 0.85, 1);
		
		assertTrue(order1.getCalculatedTaxAmountUSD() == 2.84);
		
		ShoppingOrder order2 = new ShoppingOrder("Order 2");
		
		order2.addOrderItem("imported box of chocolate", 10, 1);
		order2.addOrderItem("imported bottle of perfume", 47.50, 1);
		
		assertTrue(order2.getCalculatedTaxAmountUSD() == 8.62);
	}
	
	@Test
	public void testGetCalculatedTaxAmountUSD_EmptyList()
	{
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		
		assertTrue(order1.getCalculatedTaxAmountUSD() == 0);
	}
	
	//========================================================

	@Test
	public void testGetTotalOrderCost_Empty()
	{
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		
		assertTrue(order1.getTotalOrderCost() == 0);
	}
	
	@Test
	public void testGetTotalOrderCost_WithItems()
	{
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		
		order1.addOrderItem("book", 12.49, 1);
		order1.addOrderItem("music CD", 14.99, 1);
		order1.addOrderItem("chocolate bar", 0.85, 1);
		
		assertTrue(order1.getTotalOrderCost() == 31.17);
	}
	
	//========================================================
	
	@Test
	public void testGetTotalCostMinusSalesTax()
	{
		ShoppingOrder order1 = new ShoppingOrder("Order 1");
		
		order1.addOrderItem("book", 12.49, 1);
		order1.addOrderItem("music CD", 14.99, 1);
		order1.addOrderItem("chocolate bar", 0.85, 1);
		
		assertTrue(order1.getTotalCostMinusSalesTax() == 28.33);
	}
	
	//========================================================
}

