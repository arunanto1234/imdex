package web.amazon.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import core.UIDriver;

public class HomePage {

	private WebDriver driver;
	private ExtentTest test;

	public HomePage(WebDriver driver, ExtentTest test) {
		
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(this.driver, this);
		this.test.info("<h4>Entering Amazone Home Page</h4>");
	}
	
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Go']")
	private WebElement submitSearch;
	
	public void Search(String searchItem) throws IOException, Exception {
		
		searchBox.sendKeys(searchItem);
		test.addScreenCaptureFromPath(UIDriver.getScreenshot(driver, "search"));
		this.test.info("Entered the Search title as "+searchItem);
		submitSearch.click();
		this.test.info("Clicked on the Search button");
		test.addScreenCaptureFromPath(UIDriver.getScreenshot(driver, "clicked"));		
	}

}
