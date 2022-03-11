package com.qa;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.BaseDriver;

public class ToolsqaGroupTest extends BaseDriver {
	
    // Saving the expected title of the Webpage
    String title = "ToolsQA - Demo Website For Automation";
    
    WebDriver driver;
    @BeforeMethod
    public void starting_point(){
    	System.out.println("This is the starting point of the test");
    	//Initialize Chrome Driver
    	//driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
    	 driver = BaseDriver.getWebDriver("chrome");
    	 driver.get("https://demoqa.com/");
    }
    
    
    @Test(groups = { "demo" })	
    public void checkTitle() {	
        String testTitle = "ToolsQA";
        String originalTitle = driver.getTitle();
       Assert.assertEquals(originalTitle, testTitle);
     }	
     
     @Test(groups = { "function" })	
     public void click_element() {	
        driver.findElement(By.xpath("//div[@id='app']/header/a")).click();
         System.out.println("Home Page heading is displayed");	
     }
     
     @AfterMethod
     public void tearDown() {
    	 driver.quit();
     }
     
     
     
 }


