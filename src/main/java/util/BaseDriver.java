package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BaseDriver {

	static {
		String driverpath = System.getProperty("user.dir");
		//System.setProperty("webdriver.chrome.driver", driverpath+"/src/main/resources/chromedriver.exe");
		//System.setProperty("webdriver.edge.driver", driverpath+"/src/main/resources/msedgedriver.exe");
	}

	public static WebDriver getWebDriver(String browser) {

		WebDriver driver = null;

		switch (browser) {
		case "edge":
			EdgeOptions options = new EdgeOptions();
			options.setAcceptInsecureCerts(true);
			//Specifying desired capabilities for Chrome browser
		
			driver = new EdgeDriver(options);
			break;
		case "chrome":
			ChromeOptions options1 = new ChromeOptions();
			options1.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options1);
			break;
        default:
        	ChromeOptions options11 = new ChromeOptions();
			options11.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options11);
        }
		return driver;
	}

}
