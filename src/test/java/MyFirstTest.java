import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import base.BaseDriver;

public class MyFirstTest extends BaseDriver {
	WebDriver driver;
@Test
public void firstTest() {
	driver= BaseDriver.getWebDriver();
	driver.get("Https:\\www.ebay.com");
	
}

}
