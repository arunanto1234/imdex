package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ServiceDriver extends Driver {

	public ServiceDriver() throws IOException {
		super();
	}

	public static HttpClient client = null;
	public static HttpResponse response = null;
	private static BufferedReader reader = null;

	@BeforeSuite
	public void setUp() throws ClientProtocolException, IOException {

		client = HttpClientBuilder.create().build();
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
		extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	}

	@AfterSuite
	public void tearDown() throws IOException {
		if (reader != null) {
			reader.close(); 
		}
		extent.flush();
		System.out.println("The detailed test report is available on "+System.getProperty("user.dir") +"/test-output/testReport.html");
	}

	public HttpResponse GetResponseCode(String url) throws ClientProtocolException, IOException {

		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request); 
		
		return response;
	}

	public String GetResponseBody(String urlString) throws Exception {

		URL url = new URL(urlString);
		reader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuffer buffer = new StringBuffer();
		int read;
		char[] chars = new char[1024];
		while ((read = reader.read(chars)) != -1)
			buffer.append(chars, 0, read);

		return buffer.toString();

	}



}
