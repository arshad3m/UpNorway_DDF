package com.upnorway.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;
import com.upnorway.utilities.TestUtil;

public class SendEnquiry extends TestBase {
	
	
	@Parameters({"firstname","lastname","email","message"})
	@Test (enabled=true)
	public void send_Enquiry(String firstname, String lastname, String email, String message) throws InterruptedException, IOException {

		//Click on Send enquiry button
		click("sendEnquiry_XPATH");
		
		//Enter first name
		type("firstName_XPATH", firstname);
		
		//Enter last name
		type("lastName_XPATH", lastname);
		
		//Enter email
		type("email_XPATH", email);
		
		//Enter message to Up Norway team
		type("message_XPATH",message);

		//Send the enquiry
		click("sendBtn_XPATH");

		//Retrive notification message
		String message2 = driver.findElement(By.xpath(OR.getProperty("enquirySuccessMessage_XPATH"))).getText();

		//Verify the success message
		verifyEquals(OR.getProperty("successMessage"), message2);
		
		//Close the success message
		click("enquirySuccessMessageCloseBtn_XPATH");
	}
	
	


}
