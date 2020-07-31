package com.nathanrajkumar.services;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nathanrajkumar.model.TeslaSale;

public class ReportGeneratorImpl implements ReportGeneratorService {
	
	FileReaderImpl reader = new FileReaderImpl();
	List<TeslaSale> model3Sales = new ArrayList<>(reader.readFile(ReportGeneratorService.MODEL3FILENAME));
	List<TeslaSale> modelSSales = new ArrayList<>(reader.readFile(ReportGeneratorService.MODELSFILENAME));
	List<TeslaSale> modelXSales = new ArrayList<>(reader.readFile(ReportGeneratorService.MODELXFILENAME));
	
	public void outputReport() {

		generateReport(model3Sales);
		generateReport(modelSSales);
		generateReport(modelXSales);
	}

	private void generateReport(List<TeslaSale> sales) {
		Set<Integer> uniqueYears = new HashSet<>();
		Map<Integer, Integer> report = new HashMap<>();
		
		sales.stream()
					.forEach(sale -> uniqueYears.add(sale.getSaleDate().getYear()));
		
		uniqueYears.stream()
					.forEach(year -> report.put(year, getTotalSalesForYear(sales, year)));
		
		System.out.println(sales.get(0).getTeslaModel() + " Yearly Sales Report");
		System.out.println("------------------------------");
		report.entrySet().stream()
							.forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

		Collections.sort(sales);
		Map<String, List<YearMonth>> highestLowestSaleDates = getHighestAndLowestSaleDates(sales);
		
		highestLowestSaleDates.entrySet().stream()
						  .filter(entry -> entry.getKey().equals("Highest"))
						  .forEach(entry -> System.out.println("\nThe best month(s) for " + sales.get(0).getTeslaModel() + " was: " + entry.getValue()));
		
		highestLowestSaleDates.entrySet().stream()
		  .filter(entry -> entry.getKey().equals("Lowest"))
		  .forEach(entry -> System.out.println("The worst month(s) for " + sales.get(0).getTeslaModel() + " was: " + entry.getValue() + "\n"));
	}

	private Map<String, List<YearMonth>> getHighestAndLowestSaleDates(List<TeslaSale> sales) {
		List<YearMonth> lowestSaleDates = new ArrayList<>();
		List<YearMonth> highestSaleDates = new ArrayList<>();
		Map<String, List<YearMonth>> maxMinSaleDates = new HashMap<>();
		
		// find the duplicated values but only get the lowest value as we only care about this for printing purposes
		sales.stream()
			.filter(sale -> sale.getNumberOfSales().equals(sales.get(sales.size() - 1).getNumberOfSales()))
			.forEach(sale -> lowestSaleDates.add(sale.getSaleDate()));
		// find the duplicated values but only get the highest value as only care about this for printing purposes
		sales.stream()
			.filter(sale -> sale.getNumberOfSales().equals(sales.get(0).getNumberOfSales()))
			.forEach(sale -> highestSaleDates.add(sale.getSaleDate()));

		maxMinSaleDates.put("Lowest", lowestSaleDates);
		maxMinSaleDates.put("Highest", highestSaleDates);

		return maxMinSaleDates;
	}
	
	private Integer getTotalSalesForYear(List<TeslaSale> sales, Integer year) {
		return sales.stream()
			.filter(sale -> Integer.valueOf(sale.getSaleDate().getYear()).equals(year))
			.mapToInt(sale -> sale.getNumberOfSales())
			.sum();
	}
}
