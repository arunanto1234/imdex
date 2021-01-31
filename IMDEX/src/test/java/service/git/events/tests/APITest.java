package service.git.events.tests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import core.ServiceDriver;

public class APITest extends ServiceDriver {
	
	public APITest() throws IOException {
		super();
	}

	@Test
	public void verifyEventAPI() throws Exception {
		
		boolean isFailed = false;
		test = extent.createTest("API test scenario:: Test to verify the response code,response is array,count of PushEvent & CreateEvent");	
		
		try {
			String api = prop.getProperty("api.url.event");		
			HttpResponse response = GetResponseCode(api);
			int resonseCode =  response.getStatusLine().getStatusCode();
			String body =  GetResponseBody(api);
			
			int totalPushEvent = GetEventCount(body,"PushEvent");
			int totalCreateEvent = GetEventCount(body,"CreateEvent");
			
			System.out.println("Total number of 'PushEvent':"+totalPushEvent+" and 'CreateEvent':"+totalCreateEvent);
			test.info("<h5>Total number of 'PushEvent':"+totalPushEvent+" and 'CreateEvent':"+totalCreateEvent+"</h5>");
			
			if(resonseCode == 200) {
				System.out.println("Pass: Verified that the response code is 200.");
				test.log(Status.PASS,"Verified that the response code is 200.");

			}else {
				System.out.println("Fail: Verified that the response code is not 200");
				test.log(Status.FAIL,"Verified that the response code is not 200");
				isFailed = true;
			}
			
			if(body.trim().startsWith("[") && body.trim().endsWith("]")) {
				System.out.println("Pass: Verified that the response content is array");
				test.log(Status.PASS,"Verified that the response content is array");
			}else {
				System.out.println("Fail: Verified that the response content is NOT an array");
				test.log(Status.FAIL,"Verified that the response content is NOT an array");
				isFailed = true;
			}
			test.log(Status.INFO,"Response from the API is show below:"+body);
			Assert.assertFalse(isFailed, "API test scenario failed.");
		}catch (Exception e) {
			test.log(Status.FAIL,"API test scenario failed.");
		}
		

	}
	
	public int GetEventCount(String body,String eventName) {
		
			int count = 0;
		    JSONArray jsonArr = new JSONArray(body);

		    for (int i = 0; i < jsonArr.length(); i++) {
		        JSONObject jsonObj = jsonArr.getJSONObject(i);
		        if(jsonObj.get("type").equals(eventName)) {
		        	count++;
		        }
		    }
		    
		    return count;		
	}
}
