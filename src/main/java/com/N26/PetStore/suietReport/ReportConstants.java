package com.N26.PetStore.suietReport;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public abstract class ReportConstants {
	ExtentReports extent;
	ExtentTest logger;
	protected static ExtentReports report;
	protected static ExtentTest test;

	public static void Report(String TestSuietName) {
		report = ExtentFactory.Instance();
		test = report.startTest(TestSuietName);
	}

	public static void endReport() {
		report.endTest(test);
		report.flush();
	}
}
