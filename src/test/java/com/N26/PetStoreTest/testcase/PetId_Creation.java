
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

public class PetId_Creation extends ReportConstants {

	public PetId_Creation() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	static Response Responsecode;
	RequestControler rc = new RequestControler();
	String EndUrl = "PetId_create";
	String EndUrl1 = "pet_find";
    String EndUrl2 = "pet_delete";
	String EndUrl3 = "PetFindStatus";
	String EndUrl4 = "PetId_Put";
	String EndUrl5= "Update_Pet_id";
	String EndUrl6 = "Upload_pet";
	private volatile AtomicInteger counter = new AtomicInteger(1);

	@BeforeClass
	public void startReport() {
		FileUtils.prepare_Setup_to_rerun();
		report = ExtentFactory.Instance();
		test = report.startTest("Petid_Creation_Api");
	}


	@Test(priority = 1)
	public void PetId_Creation_Sucess() throws IOException, Exception {
		String ProcessedJson = (System.getProperty("user.dir") + "\\Json\\RegisterationPet\\Success\\success.json");
		rc.PostRequest(ProcessedJson, EndUrl);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "Pet_Id creation TestCase Pass"); 
			System.out.println(rc.Request.path("id"));
		}else if(rc.Request.getStatusCode() == 405) {
			test.log(LogStatus.FAIL, "Invalid Input");
		}else  {
			test.log(LogStatus.FAIL,"pet_Id creation TestCase Fail");
		}
	}
	@Test(priority = 2)
	public void PetId_Fetch() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.getRequest(EndUrl1);
		System.out.println(rc.Request.getStatusCode() );
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS,"Pet_id ftech TestCase Pass");
		//	System.out.println(rc.Request.getStatusCode() );
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL,"Invalid ID supplied");
		}else if (rc.Request.getStatusCode()==404) {
			test.log(LogStatus.FAIL,"Pet not found");
		} else {
			test.log(LogStatus.FAIL, "Pet_id ftech TestCase fail");
		}
	}
	@Test(priority = 7)
	public void PetId_Delete() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.deleteRequest(EndUrl2);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "Pet_id delete TestCase Pass");
		} else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Invalid ID supplied");
		} else if (rc.Request.getStatusCode() == 404) {
			test.log(LogStatus.FAIL,"Pet not found");
		} else {
			test.log(LogStatus.FAIL, "Pet_id delete TestCase fail");
		}
	}
	@Test(priority = 3)
	public void petId_By_Status() throws IOException, Exception {
		System.err.println("Running iteration [" + counter.getAndIncrement() + "]");
		rc.getRequest(EndUrl3);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "pet_status TestCase Pass");
		} else if(rc.Request.getStatusCode() == 400){
			test.log(LogStatus.FAIL,"Invalid status value");
		}else {
			test.log(LogStatus.FAIL,"pet_status TestCase Fail");
		}
	}
	
	@Test(priority =4)
	public void Petid_Update() throws IOException, Exception {
		String ProcessedJson = (System.getProperty("user.dir") + "\\Json\\PetUpdate\\success\\success.json");
		rc.putRequest(ProcessedJson, EndUrl4);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "Pet Update by put TestCase Pass");
		}else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Invalid ID supplied");
		}else if (rc.Request.getStatusCode() == 404) {
			test.log(LogStatus.FAIL, "Pet not found");
		}else if (rc.Request.getStatusCode() == 405) {
			test.log(LogStatus.FAIL,"Validation exception");
		}else {
			test.log(LogStatus.FAIL, "Pet Update by put TestCase Fail");
		}
		
	}
	
	@Test(priority = 5)
	public void Update_Pet_by_post() throws IOException, Exception {
		rc.PostRequestWithParam(EndUrl5,false);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "Pet_Id updation by post TestCase Pass");
		}else if (rc.Request.getStatusCode() == 405) {
			test.log(LogStatus.FAIL,"Invalid input");
		} else {
			test.log(LogStatus.FAIL, "Pet_Id updation by post TestCase Fail");
		}
		
	}
	@Test(priority = 6)
	public void Upload_Pet_By_post() throws IOException, Exception {
		rc.PostRequestWithParam(EndUrl6,true);
		if (rc.Request.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "successful operation");
		}
		 else if (rc.Request.getStatusCode() == 400) {
			test.log(LogStatus.FAIL, "Error: response status is 400");
		}else if (rc.Request.getStatusCode() == 404) {
			test.log(LogStatus.FAIL, "Unknown");
		} 
		else {
			test.log(LogStatus.FAIL, "Pet_Id attachmnet url upload  TestCase Fail");
		}
		
	}
	
	@AfterClass
	public static void endReport() {
		ReportConstants.endReport();
	}
}

//Prangya Paramita Panda