package com.appium.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Appium.pageObjects.CalculatorPageObjects;
import com.Appium.testBase.TestBase;

public class TC001_VerifyCalculatorPageTest extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_VerifyCalculatorPageTest.class.getName());

	@BeforeMethod
	public void testLaunch() {
		launchEmulator();
	}

	@Test
	public void verifyAdditionOperationTest() {
		log.info("******AdditionOperationTest Started *********");
		CalculatorPageObjects calculatorPageObjects = new CalculatorPageObjects(driver);
		calculatorPageObjects.aditionOfTwoNumbers();
		log.info("******AdditionOperationTest Finished *********");
	}

	@Test
	public void verifySubtractOperationTest() {
		log.info("******SubtractOperationTest Started *********");
		CalculatorPageObjects calculatorPageObjects = new CalculatorPageObjects(driver);
		calculatorPageObjects.subtractionOfTwoNumbers();
		log.info("******SubtractOperationTest Finished *********");
	}

	@Test
	public void verifyMultiplicationOperationTest() {
		log.info("******MultiplicationOperationTest Started *********");
		CalculatorPageObjects calculatorPageObjects = new CalculatorPageObjects(driver);
		calculatorPageObjects.multiplicationOfTwoNumbers();
		log.info("******MultiplicationOperationTest Finished *********");
	}

	@Test
	public void verifyDivisionOperationTest() {
		log.info("******DivisionOperationTest Started *********");
		CalculatorPageObjects calculatorPageObjects = new CalculatorPageObjects(driver);
		calculatorPageObjects.divisionOfTwoNumbers();
		log.info("******DivisionOperationTest Finished *********");
	}

}
