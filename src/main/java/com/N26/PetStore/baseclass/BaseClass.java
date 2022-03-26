package com.N26.PetStore.baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//super class
public class BaseClass {

	public ExtentReports extent;
	public ExtentTest logger;
	public ExtentReports report;
	public static ExtentTest test;
	static Properties configuration_ref = new Properties();
	static Properties TestData_ref = new Properties();

	public static String ConfigurationReader(String getData) throws IOException {
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\N26\\PetStore\\Configuration\\config.properties");
		configuration_ref.load(file);
		return configuration_ref.getProperty(getData);
	}
	
	public static String generateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
