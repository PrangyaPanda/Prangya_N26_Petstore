package com.N26.PetStoreTest.testcase;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.N26.PetStore.suietReport.ExtentFactory;
import com.N26.PetStore.suietReport.ReportConstants;
import com.N26.PetStore.utilities.CanRunMultipleTimes;
import com.N26.PetStore.utilities.FileUtils;
import com.N26.Petstore.controller.RequestControler;
import com.relevantcodes.extentreports.LogStatus;


import io.restassured.response.Response;

public class logIn extends ReportConstants {

	public logIn() throws IOException {
	}

	static Response Responsecode;
	RequestControler rc = new RequestControler();
	String EndUrl = "LogIn";
	String EndUrl1 = "LogOut";
	private volatile AtomicInteger counter = new AtomicInteger(1);

	@BeforeClass
	public static void startReport(ITestContext TestSuietName) {
		if (report==null) {
			FileUtils.prepare_Setup_to_rerun();
			report = ExtentFactory.Instance();
		}
		String TestSuiet = "LogIn";
		ReportConstants.Report(TestSuiet);
	}
	
	@Test(priority = 1)
	public void LogIn() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.getRequest(EndUrl);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		} else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL,"Invalid username/password supplied");
		} else {
			test.log(LogStatus.FAIL, "LogIn TestCase fail");
		}
	}
	@Test(priority = 2)
	public void Log_Out() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.getRequest(EndUrl1);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		} else {
			test.log(LogStatus.FAIL, "Log Out TestCase fail");
		}
	}

	@AfterClass
	public static void endReport() {
		ReportConstants.endReport();
	}
}

//Prangya Paramita Panda