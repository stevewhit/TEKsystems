package com.tek.interview.question.ordercalculation.impl;

import java.math.BigDecimal;

/**
 * Tax utility class used for calculating tax of item/s.
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public final class SalesCalculatorUtils
{
	/**
	 * To make it impossible to instantiate this class. 
	 */
	private SalesCalculatorUtils(){}
	
	/**
	 * Calculates the tax amount of one item in USD. 
	 * @param unitPrice The price of one unit in USD.
	 * @param taxRateDecimal The tax rate in decimal format as a double (between 0.0 and 1.0 inclusive).
	 * @return Returns the tax amount of one item in USD as a double.
	 * @throws IllegalArgumentException Throws if unitPrice or quantity are less than 0 or if taxRateDecimal is not between 0 and 1(inclusive). 
	 */
	public static double calculateTaxUSD(double unitPrice, double taxRateDecimal) throws IllegalArgumentException
	{
		return calculateTaxUSD(unitPrice, 1, taxRateDecimal);
	}
	
	/**
	 * Calculates the tax amount of one item in USD. 
	 * @param unitPrice The price of one unit in USD.
	 * @param quantity The number of units that are to be included in the tax calculation.
	 * @param taxRateDecimal The tax rate in decimal format as a double (between 0.0 and 1.0 inclusive).
	 * @return Returns the tax amount of one item in USD as a double.
	 * @throws IllegalArgumentException Throws if unitPrice or quantity are less than 0 or if taxRateDecimal is not between 0 and 1(inclusive). 
	 */
	public static double calculateTaxUSD(double unitPrice, int quantity, double taxRateDecimal) throws IllegalArgumentException
	{
		if (unitPrice < 0)
		{
			throw new IllegalArgumentException("Illegal unitPrice supplied. unitPrice should be >= 0.");
		}
		
		if (taxRateDecimal < 0 || taxRateDecimal > 1)
		{
			throw new IllegalArgumentException("Illegal taxRateDecimal supplied. Tax rate should be between 0 and 1 (inclusive).");
		}
		
		if (quantity < 0)
		{
			throw new IllegalArgumentException("Illegal quantity supplied. Quantity should be >= 0.");
		}
		
		return roundToTwoDecimalPlaces(unitPrice * quantity * taxRateDecimal);
	}
	
	/**
	 * Rounds the supplied value to two decimal places.
	 * @param valueToRound The value that needs to be rounded.
	 * @return Returns the valueToRound rounded to two decimal places, as a double.
	 */
	public static double roundToTwoDecimalPlaces(double valueToRound)
	{
		return BigDecimal.valueOf(valueToRound).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue(); 
	}
}
