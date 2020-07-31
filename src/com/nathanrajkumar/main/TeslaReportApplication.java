package com.nathanrajkumar.main;

import com.nathanrajkumar.services.ReportGeneratorImpl;

public class TeslaReportApplication {
	public static void main(String[] args) {
		ReportGeneratorImpl reportGenerator = new ReportGeneratorImpl();
		reportGenerator.outputReport();
	}
}
