package com.upnorway.rough;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;
import com.upnorway.utilities.TestUtil;

public class AddCustomerTest extends TestBase {
	
	
//	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException, IOException{
		
		if(!data.get("runmode").equals("Y")){
			
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
/*		click("addCustBtn_CSS");
		type("firstname_CSS",data.get("firstname"));
		type("lastname_XPATH",data.get("lastname"));
		type("postcode_CSS",data.get("postcode"));
		click("addbtn_CSS");
		Thread.sleep(2000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();
		
		Thread.sleep(2000);*/
		
		
		click("sendEnquiry_XPATH");
		type("firstName_XPATH",data.get("firstname"));
		type("lastName_XPATH",data.get("lastname"));
		type("email_XPATH",data.get("postcode")+"@gmail.com");
		type("message_XPATH",data.get("lastname")+" this is message body");

		click("sendBtn_XPATH");
		
	//	String message = driver.findElement(By.xpath("enquirySuccessMessage_XPATH")).getText();
		//verifyEquals("Thank you, we will get back to you shortly. A copy of your request has been sent to your email.", message);
		click("enquirySuccessMessageCloseBtn_XPATH");
		Thread.sleep(2000);
	}
	
	

}
