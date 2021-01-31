package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Driver {
	
	public Properties prop = null;;
	FileInputStream fis = null;
    ExtentHtmlReporter htmlReporter;    
    public ExtentReports extent;
    public ExtentTest test;
	
	public Driver() throws IOException {
		fis = new FileInputStream(new File("config/config.properties"));
		prop = new Properties();
		prop.load(fis);
		
	}

}
