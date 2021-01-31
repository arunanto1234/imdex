package web.amazon.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import core.DataProvider;
import core.UIDriver;
import web.amazon.pages.HomePage;
import web.amazon.pages.SearchResultPage;

public class PriceCheck extends UIDriver {

	public PriceCheck() throws IOException {
		super();
	}

	@Test(priority = 1, enabled = true, dataProvider = "getBookPrice", dataProviderClass = DataProvider.class)
	public void verifyProductPrice(String bookT,String bookP) throws IOException, Exception {

		test = extent.createTest("UI Automation scenario: Test to verify the price of the book");		
		boolean isFailed = false;

		try {
			HomePage home = new HomePage(driver,test);
			home.Search(bookT);

			SearchResultPage searchResult = new SearchResultPage(driver,test);
			String bookPrice = searchResult.GetBookPrice(bookT);

			if(bookP.equals(bookPrice)) {
				System.out.println("Pass: The book price for the title "+bookT+"' is showing as "+bookPrice+" is matches with expected value is "+bookP+"'");
				test.log(Status.PASS,"The book price for the title "+bookT+"' is showing as "+bookPrice+" is matches with expected value is "+bookP+"'");
			}else {
				System.out.println("Fail: The book price for the title "+bookT+"' is showing as "+bookPrice+", where as the expected value is "+bookP+"'");
				test.log(Status.FAIL,"The book price for the title "+bookT+"' is showing as "+bookPrice+", where as the expected value is "+bookP+"'");
				isFailed = true;
			}
			Assert.assertFalse(isFailed, "UI Automation scenario failed.");
		}catch (Exception e) {
			test.log(Status.FAIL,"UI Automation scenario failed.");
			test.addScreenCaptureFromPath(UIDriver.getScreenshot(driver, "clicked"));
		}


	}

}
