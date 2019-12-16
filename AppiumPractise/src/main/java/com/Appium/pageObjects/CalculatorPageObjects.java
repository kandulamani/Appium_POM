package com.Appium.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Appium.testBase.TestBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CalculatorPageObjects extends TestBase {

	public static final Logger log = Logger.getLogger(CalculatorPageObjects.class.getName());

	@AndroidFindBy(id = "com.android.calculator2:id/digit_0")
	WebElement digit_0;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_1")
	WebElement digit_1;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_2")
	WebElement digit_2;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_3")
	WebElement digit_3;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_4")
	WebElement digit_4;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_5")
	WebElement digit_5;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_6")
	WebElement digit_6;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_7")
	WebElement digit_7;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_8")
	WebElement digit_8;

	@AndroidFindBy(id = "com.android.calculator2:id/digit_9")
	WebElement digit_9;

	@AndroidFindBy(id = "com.android.calculator2:id/dec_point")
	WebElement dec_Point;

	@AndroidFindBy(id = "com.android.calculator2:id/eq")
	WebElement equal;

	@AndroidFindBy(id = "com.android.calculator2:id/op_add")
	WebElement op_Add;

	@AndroidFindBy(id = "com.android.calculator2:id/op_sub")
	WebElement op_Sub;

	@AndroidFindBy(id = "com.android.calculator2:id/op_mul")
	WebElement op_mul;

	@AndroidFindBy(id = "com.android.calculator2:id/op_div")
	WebElement op_Div;

	@AndroidFindBy(id = "com.android.calculator2:id/del")
	WebElement op_Del;

	@AndroidFindBy(id = "com.android.calculator2:id/toolbar")
	WebElement toolBar;

	@AndroidFindBy(id = "com.android.calculator2:id/formula")
	WebElement formulaBar;

	@AndroidFindBy(id = "com.android.calculator2:id/result")
	WebElement resultBar;

	public CalculatorPageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	public void aditionOfTwoNumbers() {
		log.info("Addition of two numbers test Started");
		clickElement(digit_1);
		clickElement(op_Add);
		clickElement(digit_4);
		clickElement(equal);
		Assert.assertEquals(getElementText(resultBar), "5");
		log.info("Addition of two numbers test Finished");
	}

	public void subtractionOfTwoNumbers() {
		log.info("Subtraction of two numbers test Started");
		clickElement(digit_9);
		clickElement(op_Sub);
		clickElement(digit_5);
		clickElement(equal);
		Assert.assertEquals(getElementText(resultBar), "4");
		log.info("Subtraction of two numbers test Finished");
	}

	public void multiplicationOfTwoNumbers() {
		log.info("Multiplication of two numbers test Started");
		clickElement(digit_9);
		clickElement(op_mul);
		clickElement(digit_5);
		clickElement(equal);
		Assert.assertEquals(getElementText(resultBar), "45");
		log.info("Multiplication of two numbers test Finished");
	}

	public void divisionOfTwoNumbers() {
		log.info("Division of two numbers test Started");
		clickElement(digit_9);
		clickElement(digit_5);
		clickElement(op_Div);
		clickElement(digit_5);
		clickElement(equal);
		Assert.assertEquals(getElementText(resultBar), "19");
		log.info("Division of two numbers test Finished");
	}
}
