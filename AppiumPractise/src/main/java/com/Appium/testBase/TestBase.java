package com.Appium.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {

	public AndroidDriver<AndroidElement> driver = null;
	Properties OR = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	public DesiredCapabilities capabilities;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "\\src\\main\\java\\com\\Appium\\reports\\"
				+ formater.format(calendar.getTime()) + "_Appium_Report.html", false);
	}

	public void loadData() {
		try {
			File file = new File(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\Appium\\config\\config.properties");
			FileInputStream fis = new FileInputStream(file);
			OR.load(fis);
		} catch (Exception e) {
			System.out.println("Cause :" + e.getCause());
			System.out.println("Message :" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void launchEmulator() {
		loadData();
		PropertyConfigurator.configure("log4j.properties");
		try {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, OR.getProperty("AUTOMATION_NAME"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, OR.getProperty("PLATFORM_NAME"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, OR.getProperty("PLATFORM_VERSION"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, OR.getProperty("DEVICE_NAME"));
			capabilities.setCapability("appPackage", OR.getProperty("appPackage"));
			capabilities.setCapability("appActivity", OR.getProperty("appActivity"));

			URL url = new URL(OR.getProperty("URL"));
			driver = new AndroidDriver<AndroidElement>(url, capabilities);
			log.info("App Launched in Mobile Emulator");
		} catch (Exception e) {
			System.out.println("Cause : " + e.getCause());
			System.out.println("Message :" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void onTestFinish() {
		driver.quit();
		log.info("Mobile App closed");
		extent.endTest(test);
		extent.flush();
	}

	/*
	 * This method can be used for to get the status of test case
	 * 
	 * @param result
	 * 
	 */

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP,
					result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	/*
	 * To capture ScreenShot operation
	 * 
	 * @param fileName
	 */
	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "\\src\\main\\java\\com\\Appium\\screenshots\\";
			destFile = new File(
					(String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void clickElement(WebElement element) {
		try {
			element.click();
			log.info("To Perform Click Operation on : " + element);
		} catch (Exception e) {
			log.info("Not able to click on element : " + element);
		}
	}

	public void enterTextInElement(WebElement element, String text) {
		try {
			element.clear();
			element.sendKeys(text);
			log.info("Entered text into Element and text is : " + text);
		} catch (Exception e) {
			log.info("Not able to enter text into element : " + element);
		}
	}

	public String getElementText(WebElement element) {
		String text = "";
		try {
			text = element.getText();
			log.info("The Text of the Element is : " + text);
		} catch (Exception e) {
			log.info("Unable to get the text of the Element : " + element);
		}
		return text;
	}

	public void elementIsDisplayed(WebElement element) {
		boolean elementStatus = element.isDisplayed();
		log.info("element is Displayed and Status is : " + elementStatus);
		Assert.assertTrue(elementStatus);
	}

	public void elementIsEnabled(WebElement element) {
		boolean elementStatus = element.isEnabled();
		log.info("element is Enabled and Status is : " + elementStatus);
		Assert.assertTrue(elementStatus);
	}

	public void elementIsSelected(WebElement element) {
		element.click();
		boolean elementStatus = element.isSelected();
		log.info("element is Selected and Status is : " + elementStatus);
		Assert.assertTrue(elementStatus);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		onTestFinish();
	}
}
