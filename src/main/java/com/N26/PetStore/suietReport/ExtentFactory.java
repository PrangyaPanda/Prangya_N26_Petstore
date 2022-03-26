package com.N26.PetStore.suietReport;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {

	public static ExtentReports Instance() {
		ExtentReports extent;
		extent = new ExtentReports(System.getProperty("user.dir") + "/TestReport/API_TEST_RESULTS.html", false);
		extent.addSystemInfo("RestAssured", "3.0.1").addSystemInfo("platform", "Windows");
		return extent;

	}

//Prangya Panda
}
