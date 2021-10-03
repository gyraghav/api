package tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import client.RestClient;
import utility.TestUtil;

public class GetAPI extends TestBase {
	
	TestBase tb;
	String serviceurl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closableHttpResponse;
	
	@BeforeMethod
	public void setup() {
		tb=new TestBase();
		serviceurl=prop.getProperty("URL");
		apiUrl=prop.getProperty("serviceURL");
		url=serviceurl+apiUrl;
		System.out.println("URL: "+url);
		
		
	}
	
	@Test(priority=1)
	public void getAPITestwithoutHeader() throws ClientProtocolException, IOException {
		restClient=new RestClient();
		closableHttpResponse = restClient.get(url);
		
		// Status code
				int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Statuc Code :->"+statusCode);
			
			//JSON String
				String responseString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");
				
				JSONObject responseJson=new JSONObject(responseString);
				System.out.println("Response from JSON from API:->"+responseJson);
				
			// All headers	
				Header[] headerArray = closableHttpResponse.getAllHeaders();
				
				HashMap<String,String> allHeader=new HashMap<String, String>();
				for(Header header:headerArray) {
					allHeader.put(header.getName(), header.getValue());
				}
				System.out.println("Header array  :->"+allHeader);
			
		
	}
	
//	@Test(priority=2)
	public void getAPITestwithHeader() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-type", "application/json");
//		headerMap.put("user", "test@gmail.com");
		
		closableHttpResponse = restClient.get(url, headerMap);
		
//		Status code
		int statusCode=closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is :-> "+statusCode);
		Assert.assertEquals(statusCode, response_STATUS_CODE_200);
		
//		JSON body
		
		String JSonString = EntityUtils.toString(closableHttpResponse.getEntity(),"UTF-8");
		JSONObject Jsonresponse= new JSONObject(JSonString);
		System.out.println("JSON String:-> "+Jsonresponse);
		
		String totalvalue=TestUtil.getValueBypath(Jsonresponse, "/total");
		System.out.println("Total value :->"+totalvalue);
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		
//		get header list
		
		Header[] headerArray=closableHttpResponse.getAllHeaders();
		HashMap<String, String> headermap=new HashMap<String, String>();
		for(Header header:headerArray) {
			headermap.put(header.getName(), header.getValue());
			
		}
		System.out.println(headermap);
		
		
	}
	
	

}
