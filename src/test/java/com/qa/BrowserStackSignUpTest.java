package com.qa;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserStackSignUpTest {
	WebDriver driver;

	@BeforeClass
	public void testSetup() {
		String path = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);

		  ChromeOptions options = new ChromeOptions();
		  options.setAcceptInsecureCerts(true);

		  //options.addArguments("--disable-blink-features");
		  options.addArguments("--disable-blink-features=AutomationControlled");
		  //options.addArguments("–disable-blink-features=AutomationControlled");
		  options.addArguments("--headless");
		  options.addArguments("window-size=1280,800");
		  options.addArguments("user-agent=Mozilla/5.0" +
		  " (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
		  "(KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");


		  driver = new ChromeDriver(options);


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	@BeforeMethod
	public void openBrowser() {
		driver.get("https://www.browserstack.com/");
		driver.findElement(By.id("signupModalButton")).click();
		System.out.println("We are currently on the following URL" + driver.getCurrentUrl());
	}

	@Test(description = "This method validates the sign up functionality")
	public void signUp() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_full_name")));

		driver.findElement(By.id("user_full_name")).sendKeys("Sadhvi Singh");
		driver.findElement(By.id("user_email_login")).sendKeys("sadhvisingh9049+1@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("BrowserStack123*");
		driver.findElement(By.xpath("//input[@name='terms_and_conditions']")).click();
		driver.findElement(By.id("user_submit")).click();

	}

	@AfterMethod
	public void postSignUp() {
		System.out.println(driver.getCurrentUrl());

	}

	@AfterClass
		public void afterClass()
		{
		//driver.quit();
		}
}


