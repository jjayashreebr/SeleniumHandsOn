package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseDriver {
	
	public static void main(String[] arg) {
		BaseDriver.getWebDriver();
	}
	
	public static WebDriver getWebDriver(){
		String path = System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		WebDriver driver= new ChromeDriver(options);
		driver.get("https:\\www.google.com");
		//return driver;
		return driver;
	}

}
