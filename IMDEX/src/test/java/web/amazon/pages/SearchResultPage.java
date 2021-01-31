package web.amazon.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class SearchResultPage {

	private WebDriver driver;
	private ExtentTest test;

	public SearchResultPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		this.test.info("<h4>Entering Search result page</h4>");
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//*[@class='a-section a-spacing-none a-spacing-top-small']//span[@class='a-size-base-plus a-color-base a-text-normal']")
	private java.util.List<WebElement> allbooks;

	public ArrayList<String> GetAllBooksName() {

		ArrayList<String> books = new ArrayList<>();	
		
		for (WebElement ele : allbooks) {
			books.add(ele.getText().trim());
		}		
		return books;
	}

	public String GetBookPrice(String bookName) {

		String finalPrice = "";
		WebDriverWait wait = new WebDriverWait(driver, 5);

		try 
		{
			String wholePrice = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='"+bookName+"']/../../../../../../..//span[@class='a-price-whole']")))).getText().trim();
			String wholeDecimel = driver.findElement(By.xpath("//span[text()='"+bookName+"']/../../../../../../..//span[@class=\"a-price-fraction\"]")).getText().trim();
			finalPrice = wholePrice+"."+wholeDecimel;
		}catch (Exception e) {
			System.out.println("Error getting the price for the product-"+e.getMessage());
		}
		return finalPrice;		
	}


}
