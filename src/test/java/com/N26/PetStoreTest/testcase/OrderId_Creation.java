
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

public class OrderId_Creation extends ReportConstants {

	public OrderId_Creation() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	static Response Responsecode;
	RequestControler rc = new RequestControler();
	String EndUrl = "OrderId_create";
	String EndUrl1 = "Order_find";
	String EndUrl2 = "Store_Inventory";
	String EndUrl3= "store_delete";
	private volatile AtomicInteger counter = new AtomicInteger(1);


	@BeforeClass
	public static void startReport(ITestContext TestSuietName) {
		if (report==null) {
			FileUtils.prepare_Setup_to_rerun();
			report = ExtentFactory.Instance();
		}
		String TestSuiet = "Order_Craete_Api";
		ReportConstants.Report(TestSuiet);
	}
	
	@Test(priority = 0)
	public void Order_create_Success() throws IOException, Exception {
		String ProcessedJson = (System.getProperty("user.dir") + "\\Json\\OrderIdCreation\\success\\success.json");
		rc.PostRequest(ProcessedJson, EndUrl);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL,"Invalid Order");
		} else {
			test.log(LogStatus.FAIL, "Pet_Id creation TestCase Fail");
		}
		
	}
	@Test(priority = 1)
	public void Order_Fetch() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.getRequest(EndUrl1);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Invalid ID supplied");
		} else {
			test.log(LogStatus.FAIL, "Pet_id ftech TestCase fail");
		}
	}
	
	@Test(priority = 2)
	public void Store_Inventory() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.getRequest(EndUrl2);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		} else {
			test.log(LogStatus.FAIL, "Store Inventory ftech TestCase fail");
		}
	}
	
	@Test(priority = 3)
	public void store_Delete() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.deleteRequest(EndUrl3);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "Store_id delete TestCase Pass");
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Invalid ID supplied");
		}else if (rc.Request.getStatusCode() == 404) {
			test.log(LogStatus.FAIL, "Order not found");
		} else {
			test.log(LogStatus.FAIL, "Store_id delete TestCase fail");
		}
	}

	@AfterClass
	public static void endReport() {
		ReportConstants.endReport();
	}
}

//Prangya Paramita Panda