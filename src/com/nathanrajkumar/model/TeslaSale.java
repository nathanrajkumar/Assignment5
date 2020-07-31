package com.nathanrajkumar.model;

import java.time.YearMonth;

public class TeslaSale implements Comparable<TeslaSale> {

	
	private String teslaModel;
	private YearMonth saleDate;
	private Integer numberOfSales;
	
	public String getTeslaModel() {
		return teslaModel;
	}
	public void setTeslaModel(String teslaModel) {
		this.teslaModel = teslaModel;
	}
	public YearMonth getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(YearMonth saleDate) {
		this.saleDate = saleDate;
	}
	public Integer getNumberOfSales() {
		return numberOfSales;
	}
	public void setNumberOfSales(Integer numberOfSales) {
		this.numberOfSales = numberOfSales;
	}
	
	
	@Override
	public String toString() {
		return "TeslaSale [teslaModel=" + teslaModel + ", saleDate=" + saleDate + ", numberOfSales=" + numberOfSales
				+ "]";
	}
	@Override
	public int compareTo(TeslaSale that) {
		if (this.numberOfSales.compareTo(that.numberOfSales) == 0) {
			return this.numberOfSales.compareTo(that.numberOfSales);
		} else {
			return that.numberOfSales.compareTo(this.numberOfSales);
		}
	}


}
