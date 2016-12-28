package com.tek.interview.question.main;

import java.util.ArrayList;
import java.util.List;

import com.tek.interview.question.ordercalculation.OrderItem;
import com.tek.interview.question.ordercalculation.impl.ImportedOrderItem;
import com.tek.interview.question.ordercalculation.impl.LocalOrderItem;

public class Bar
{
	
	public static void main(String[] args)
	{
		System.out.println("Just getting started!!");

		List<OrderItem> shoppingOrderList = new ArrayList<OrderItem>();
		
		
		shoppingOrderList.add(new ImportedOrderItem("MyName", 203.3, 234));
		shoppingOrderList.add(new LocalOrderItem("local", 12.334, 1));
		
		for(OrderItem item : shoppingOrderList)
		{
			System.out.println(item.getCalculatedTaxAmountUSD());
		}
		
		
		System.out.println("Done.");
	}
	
}
