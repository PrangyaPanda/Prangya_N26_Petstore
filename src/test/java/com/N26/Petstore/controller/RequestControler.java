
package com.N26.Petstore.controller;
import java.io.File;
import java.io.IOException;
import com.N26.PetStore.baseclass.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.http.Header;
import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;


public class RequestControler extends BaseClass {
	public RequestControler() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response Request;

	public void PostRequest(String JsonPath, String EndUrl) throws Exception {
		try {
//Create via post with Json body
			String jsonBody = BaseClass.generateStringFromResource(JsonPath);
			RestAssured.baseURI = BaseClass.ConfigurationReader("server_host");
			System.out.println(RestAssured.baseURI);
			Request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody).when()
					.post(BaseClass.ConfigurationReader(EndUrl)).then().contentType(ContentType.JSON).assertThat().and().extract()
					.response();
			System.out.println(Request.asString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void PostRequestWithParam(String EndUrl,Boolean Status) throws Exception {
		try {
			File TestUploadfile = new File(System.getProperty("user.dir")+"\\Image\\ReadMeN26Smaple.PNG");
// Update via post api with parameter 
			RestAssured.baseURI = BaseClass.ConfigurationReader("server_host");
			System.out.println(RestAssured.baseURI);
			if(Status==true){
			Request = RestAssured.given().multiPart(TestUploadfile).body("").when()
					.post(BaseClass.ConfigurationReader(EndUrl)).then().contentType(ContentType.JSON).assertThat().and().extract()
					.response();
			}
		 if(Status==false) {
				Request = RestAssured.given().when()
						.post(BaseClass.ConfigurationReader(EndUrl)).then().contentType(ContentType.JSON).assertThat().and().extract()
						.response();
			}
			System.out.println(Request.asString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void getRequest(String EndUrl) throws Exception {
		try {
//To Fetch Details
			Request = RestAssured.given().contentType(ContentType.JSON).when()
					.get(BaseClass.ConfigurationReader("server_host") + "" + BaseClass.ConfigurationReader(EndUrl)).then()
					.contentType(ContentType.JSON).and().assertThat().extract().response();
			//System.out.println(Request);
			System.out.println(Request.asString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void putRequest(String JsonPath,String EndUrl) {
		try {
//To Update using put Method
		String jsonBody = BaseClass.generateStringFromResource(JsonPath);
		System.out.println(jsonBody);
        Request =RestAssured.given().header("Content-type", "application/json").and().body(jsonBody).when()
                .put(BaseClass.ConfigurationReader("server_host") + "" + BaseClass.ConfigurationReader(EndUrl))
                .then().contentType(ContentType.JSON).and().assertThat().extract().response();
        System.out.println(Request.asString());
	} catch (Exception e) {
		System.out.println(e);
	}
	}
	
	
	public void deleteRequest(String EndUrl) {
		try {
//To Delete
		  Request = RestAssured.given().header("Content-type", "application/json").when().delete(BaseClass.ConfigurationReader("server_host") + "" + BaseClass.ConfigurationReader(EndUrl))
                .then().contentType(ContentType.JSON).and().assertThat().extract().response();
		  System.out.println(BaseClass.ConfigurationReader("server_host") + "" + BaseClass.ConfigurationReader(EndUrl));
		  System.out.println(Request.asString());
    }
	 catch (Exception e) {
			System.out.println(e);
		}
	}
}

//Prangya Paramita Panda