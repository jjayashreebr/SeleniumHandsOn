package poi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import base.BaseDriver;
import util.ExcelHelper;

public class ToolSqaAutomation extends BaseDriver {

	public static void main(String[] args)  {
		
		String driverpath = System.getProperty("user.dir") + "/msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", driverpath);
		
		Map<Integer, HashMap<String, Object>> stuInfo = null;
		try {
			stuInfo = ExcelHelper.getExcelData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Integer> keyid = stuInfo.keySet();
     
		for (Integer key : keyid) {
			WebDriver driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get("https://demoqa.com/automation-practice-form");
			
			WebElement FirstName = driver.findElement(By.id("firstName"));
			WebElement LastName = driver.findElement(By.id("lastName"));
			WebElement Email = driver.findElement(By.id("userEmail"));
			//WebElement male = driver.findElement(By.id("gender-radio-1"));
			//WebElement female = driver.findElement(By.id("gender-radio-2"));
			WebElement Mobile = driver.findElement(By.id("userNumber"));
			WebElement CurrentAddress = driver.findElement(By.id("currentAddress"));
			String Excepted ="Thanks for submitting the form";
			Actions act =  new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, Object> objectArr = stuInfo.get(key);
			FirstName.sendKeys(objectArr.get("FirstName").toString());
			LastName.sendKeys(objectArr.get("LastName").toString());
			Email.sendKeys(objectArr.get("Email").toString());
			String gender = objectArr.get("Gender").toString();
			if(gender.equalsIgnoreCase("male")) {
				
				act.moveToElement( driver.findElement(By.id("gender-radio-1"))).click().perform();
			}else
			{
				act.moveToElement( driver.findElement(By.id("gender-radio-2"))).click().perform();
			}
			Mobile.sendKeys(objectArr.get("Mobile").toString());
			CurrentAddress.sendKeys(objectArr.get("CurrentAddress").toString());
			js.executeScript("window.scrollBy(0,1000)");
			act.moveToElement( driver.findElement(By.id("submit"))).click().perform();
			//System.out.println(driver.getWindowHandles().size());
			driver.switchTo().activeElement();
			String Actual=driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
			System.out.println(Actual);
			if(Actual.equals(Excepted)) {
				try {
					System.out.println(key+" pass");
					ExcelHelper.writeToExcel(key, "pass");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					System.out.println(key);
					ExcelHelper.writeToExcel(key, "fail");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			driver.quit();
		
			

	}

	}

}
