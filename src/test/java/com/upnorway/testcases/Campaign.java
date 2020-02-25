package com.upnorway.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;

public class Campaign extends TestBase {

	@Test(enabled = true)
	@Parameters({ "campaignTitle", "fname", "lname", "email", "mobile", "from", "count", "campaignSuccessMessage" })
	public void campaign_enquireExperience(String campaignTitle, String fname, String lname, String email, String mobile,
			String from, String count, String campaignSuccessMessage) throws InterruptedException, IOException {
		// navigate to campaign page
		driver.get(config.getProperty("testsiteurl")+"/campaign/a-taste-of-local-secrets");

		// Verify opened campaign
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("campaign_Title_XPATH"))).getText();
		String expectedTitle = campaignTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

		// Click Enquire button
		click("campaign_Enquire_XPATH");

		// Fill Enquire form
		// Enter information
		type("JNfirstname_XPATH", fname);
		type("JNlastname_XPATH", lname);
		type("JNemail_XPATH", email);
		type("JNPhone_XPATH", mobile);
		type("JNtravellingFrom_XPATH", from);
		click("JNimnotsure_XPATH");
		select("JNnumberoftravellers_XPATH", count);
		verifyElementExistNot("JNvalidationmessage_XPATH");
		click("JNsendbutton_XPATH");

		// Send enquiry


		// Retrive notification message
		String message = driver.findElement(By.xpath(OR.getProperty("campaign_Success_Message_XPATH"))).getText();
		String expectedmessage = campaignSuccessMessage;
		
		// Verify the success message
		verifyEqualsIgnoreCase(expectedmessage, message);

		// Close the success message
		click("campaign_Close_XPATH");

	}

	@Test(enabled = true)
	@Parameters({ "campaignTitleJourney", "campaignJourney", "fname", "lname", "email", "mobile", "from", "count"})
	public void campaign_goToJourney(String campaignTitleJourney, String campaignJourney, String fname, String lname, String email, String mobile, String from, String count) throws InterruptedException, IOException {
		// navigate to campaign page
		driver.get(config.getProperty("testsiteurl")+"/campaign/the-discovery-route-adventures");

		// Verify opened campaign
		String actualTitleJourney = driver.findElement(By.xpath(OR.getProperty("campaign_Title_XPATH"))).getText();
		String expectedTitleJourney = campaignTitleJourney;
		verifyEqualsIgnoreCase(actualTitleJourney, expectedTitleJourney);

		// Click Go To Journey button
		click("campaign_GoToJourney_XPATH");
		
		// Verify opened Journey page
				String actualJourney = driver.findElement(By.xpath(OR.getProperty("campaign_Journey_XPATH"))).getText();
				String expectedJourney = campaignJourney;
				verifyEqualsIgnoreCase( expectedJourney,actualJourney);
				
				// Click Enquire button
				click("campaign_Enquire_XPATH");

				// Fill Enquire form
				// Enter information
				type("JNfirstname_XPATH", fname);
				type("JNlastname_XPATH", lname);
				type("JNemail_XPATH", email);
				type("JNPhone_XPATH", mobile);
				type("JNtravellingFrom_XPATH", from);
				click("JNimnotsure_XPATH");
				select("JNnumberoftravellers_XPATH", count);
				verifyElementExistNot("JNvalidationmessage_XPATH");
				click("JNsendbutton_XPATH");

				// Send enquiry

				// Retrive notification message
				String message = driver.findElement(By.xpath(OR.getProperty("campaign_Success_Message_XPATH"))).getText();
				
				// Verify the success message
				verifyEqualsIgnoreCase(OR.getProperty("successMessage"), message);

				// Close the success message
				click("campaign_Close_XPATH");
				

	}
}
