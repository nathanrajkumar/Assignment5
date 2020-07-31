package com.nathanrajkumar.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.nathanrajkumar.model.TeslaSale;


public class FileReaderImpl {
	
	public List<TeslaSale> readFile(String fileName) {
		
		Path path = Paths.get(fileName);
		List<TeslaSale> sales = new ArrayList<>();
		List<String> lines;
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
				lines = new ArrayList<>();
				lines = reader
					.lines()
					.skip(1)
					.collect(Collectors.toList());
				lines.stream()
					.forEach(line -> {
						String[] lineItems = line.split(",");
						TeslaSale sale = new TeslaSale();
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy", Locale.ENGLISH);
						YearMonth saleDate = YearMonth.parse(lineItems[0], formatter);
						
						String model = path.getFileName().toString().replaceFirst("[.][^.]+$", "");					
						sale.setTeslaModel(model.substring(0,1).toUpperCase() + model.substring(1, 5) + " " + model.substring(5));
						sale.setSaleDate(saleDate);
						sale.setNumberOfSales(Integer.parseInt(lineItems[1]));
						
						sales.add(sale);
					});
		} catch (IOException e) {
			System.out.println("There was an issue in reading the csv file");
			e.printStackTrace();
		}
		
		return sales;
	}

}
