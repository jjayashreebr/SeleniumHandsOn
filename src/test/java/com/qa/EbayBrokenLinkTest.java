package com.qa;

import java.io.IOException;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class EbayBrokenLinkTest {

	WebDriver driver;
	String homePage = "https://www.ebay.com";
	String driverpath = System.getProperty("user.dir");
	@BeforeMethod
	public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverpath+"/src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
        driver.get(homePage);
	}

	@Test
	public void verifyFooterLinks() throws IOException {
		List<WebElement> elemList = driver.findElements(By.cssSelector("div#gf-BIG td:nth-child(1) li a"));
		List<String> linkList = elemList.stream().map(m -> m.getAttribute("href")).collect(Collectors.toList());
        SoftAssert s = new SoftAssert();
		Iterator<String> it = linkList.iterator();
		HttpsURLConnection huc = null;
		//boolean expected = true;
		int response = 0;
		while (it.hasNext()) {
			String link = it.next();
			System.out.println(link);
			if (link == null || link.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				
				//s.assertEquals(false, true);
				continue;
			}
            //to prevent ssl  validation exception
			if (!link.startsWith(homePage)) {
				System.out.println("URL belongs to another domain, skipping it.");
				//s.assertEquals(false, true);
				continue;
			}
			huc = (HttpsURLConnection) new URL(link).openConnection();
			huc.setRequestMethod("GET");
			huc.connect();
			response = huc.getResponseCode();

			if (response >= 400) {
				System.out.println(link + " is a broken link");
				s.assertEquals(false, true);
			} else {
				System.out.println(link + " is a valid link");
				s.assertEquals(true, true);
			}
			huc.disconnect();
			s.assertAll();
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
