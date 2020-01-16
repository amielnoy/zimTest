/**
 * 
 */
package controllers;

import java.awt.Robot;
import java.io.File;
import java.net.URI;

import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class InitMethod
{
	public static ApplicationConfig appConfig = new ApplicationConfig();
	
	public static String WebsiteURL = appConfig.getWebsiteUrl();
	public static String Browser = appConfig.getBrowser();
	public static int MaxPageLoadTime = appConfig.getMaxPageLoadTime();
	public static int ImplicitlyWait = appConfig.getImplicitlyWait();
	
	public static String FS = File.separator;

	public static String OSName = System.getProperty("os.name");
	public static String OSArchitecture = System.getProperty("os.arch");
	public static String OSVersion = System.getProperty("os.version");
	public static String OSBit = System.getProperty("sun.arch.data.model");

	public static Robot re;
	public static Alert al;
	public static Select se;
	public static Actions ac;

	public static final String OUTPUT_FOLDER =  "./src/test/resources/Reports/";
	public static final String FILE_NAME = "Extent Report.html";
	public ExtentReports extent;
	public ExtentTest test;
	
}
