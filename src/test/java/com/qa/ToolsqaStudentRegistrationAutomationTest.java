package com.qa;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.BaseDriver;

public class ToolsqaStudentRegistrationAutomationTest extends BaseDriver {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = BaseDriver.getWebDriver("edge");
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/automation-practice-form");
	}

	@Test
	public void verifyPageTitle() {
		String expected = "ToolsQA";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		}
	
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
