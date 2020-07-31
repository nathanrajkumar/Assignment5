package com.nathanrajkumar.services;

public interface ReportGeneratorService {

	public final static String MODEL3FILENAME = "resources\\model3.csv";
	public final static String MODELSFILENAME = "resources\\modelS.csv";
	public final static String MODELXFILENAME = "resources\\modelX.csv";
	
	void outputReport();
	
}
