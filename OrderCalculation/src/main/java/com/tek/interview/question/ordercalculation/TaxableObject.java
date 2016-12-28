package com.tek.interview.question.ordercalculation;

/**
 * Interface for an object that is taxed.
 * @author Steve Whitmire (swhit114@gmail.com)
 *
 */
public interface TaxableObject
{
	/**
	 * Returns the amount (in USD) of calculated tax associated with this object.
	 * @return Returns the amount (in USD) of calculated tax associated with this object as a double.
	 */
	public double getCalculatedTaxAmountUSD();
}
