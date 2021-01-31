package core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class UIDriver extends Driver {


	public UIDriver() throws IOException {
		super();		
	}


	public static WebDriver driver = null;

	@BeforeSuite
	public void setUp() {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("ui.url.app"));
	}


	@AfterSuite	
	public void tearDown() {
		driver.quit();
		extent.flush();
		System.out.println("The detailed test report is available on "+System.getProperty("user.dir") +"/test-output/testReport.html");
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);	
		String destination = System.getProperty("user.dir") + "/screenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
