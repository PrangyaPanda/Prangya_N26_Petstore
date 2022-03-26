
package com.N26.PetStoreTest.testcase;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.N26.PetStore.suietReport.ExtentFactory;
import com.N26.PetStore.suietReport.ReportConstants;
import com.N26.PetStore.utilities.FileUtils;
import com.N26.Petstore.controller.RequestControler;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;

public class User_Creation extends ReportConstants {

	public User_Creation() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	static Response Responsecode;
	RequestControler rc = new RequestControler();
	String EndUrl = "User_create";
	String EndUrl1 = "User_create_withArray";
	String EndUrl2 = "User_create_withList";
	String EndUrl3 = "UserName_Fetch";
	String EndUrl4 = "User_Put";
	String EndUrl5 = "user_delete";
	private volatile AtomicInteger counter = new AtomicInteger(1);
	@BeforeClass
	public static void startReport(ITestContext TestSuietName) {
		if (report==null) {
			FileUtils.prepare_Setup_to_rerun();
			report = ExtentFactory.Instance();
		}
		String TestSuiet = "User_Create";
		ReportConstants.Report(TestSuiet);
	}
	
	@Test(priority = 0)
	public void User_Creation() throws IOException, Exception {
		String ProcessedJson = (System.getProperty("user.dir") + "\\Json\\CreateUser\\success\\success.json");
		rc.PostRequest(ProcessedJson, EndUrl);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		} else {
			test.log(LogStatus.FAIL, "Uesr creation TestCase Fail");
		}
	}
	@Test(priority = 1)
	public void UserCreationWithArray() throws IOException, Exception {
		String ProcessedJson = (System.getProperty("user.dir") + "\\Json\\CreateUserWithArray\\success\\success.json");
		rc.PostRequest(ProcessedJson, EndUrl1);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		} else {
			test.log(LogStatus.FAIL, "User Create With Array TestCase Fail");
		}
	}
	@Test(priority = 2)
	public void UserCreationWithList() throws IOException, Exception {
		String ProcessedJson = (System.getProperty("user.dir") + "\\Json\\CreateUserWithArray\\success\\success.json");
		rc.PostRequest(ProcessedJson, EndUrl2);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		} else {
			test.log(LogStatus.FAIL, "User Create With List TestCase Fail");
		}		
	}
	@Test(priority = 3)
	public void UserNameFetch() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.getRequest(EndUrl3);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Invalid username supplied");
		}else if (rc.Request.getStatusCode() == 404) {
			test.log(LogStatus.FAIL, "User not found");
		} else {
			test.log(LogStatus.FAIL, "UserName Fetch TestCase fail");
		}
	}
	@Test(priority = 4)
	public void UserPutRequest() throws IOException, Exception {
		String ProcessedJson = (System.getProperty("user.dir") + "\\Json\\CreateUser\\success\\success.json");
		rc.putRequest(ProcessedJson, EndUrl4);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Invalid user supplied");
		}else if (rc.Request.getStatusCode() == 404) {
			test.log(LogStatus.FAIL, "User not found");
		} else {
			test.log(LogStatus.FAIL, "User Update by put TestCase Fail");
		}
	}
	
	@Test(priority = 5)
	public void User_delete() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.deleteRequest(EndUrl5);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "User delete TestCase Pass");
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Invalid username supplied");
		}else if (rc.Request.getStatusCode() == 404) {
			test.log(LogStatus.FAIL, "User not found");
		} else {
			test.log(LogStatus.FAIL, "User delete TestCase fail");
		}
	}
	
	@AfterClass
	public static void endReport() {
		ReportConstants.endReport();
	}
}
//Prangya Paramita Panda