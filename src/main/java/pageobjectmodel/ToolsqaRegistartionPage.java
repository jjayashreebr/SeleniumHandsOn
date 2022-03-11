package pageobjectmodel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import locators.*;

public class ToolsqaRegistartionPage extends ToolsqaRegistartionPageLocators{

	public WebDriver driver=null;
	
	public ToolsqaRegistartionPage(WebDriver driver){
		this.driver=driver;
	}
	
	public WebElement getFirstName() {
		return driver.findElement(FIRST_NAME);
	}
	
	

}
