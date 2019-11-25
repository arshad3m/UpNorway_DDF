package com.upnorway.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;

public class Video extends TestBase {

	@Test(enabled = true)
	@Parameters({ "videoPageTitle", "videoJourney", "fname", "lname", "email", "mobile", "from", "count", })
	public void video_goToJourney(String videoPageTitle, String videoJourney, String fname, String lname, String email,
			String mobile, String from, String count) throws InterruptedException, IOException {

		// navigate to video page
		click("video_button_XPATH");
		Thread.sleep(3000);

		// Verify opened video page
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("video_page_XPATH"))).getText();
		String expectedTitle = videoPageTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

		Thread.sleep(3000);

		// Click Go To Journey button
		click("video_GoToJourney_XPATH");

		// Open new page
		// swithToNewTab();

		// Verify opened Journey page
		String actualJourney = driver.findElement(By.xpath(OR.getProperty("video_Journey_XPATH"))).getText();
		String expectedJourney = videoJourney;
		verifyEqualsIgnoreCase(actualJourney, expectedJourney);

		// Click Enquire button
		click("video_Enquire_XPATH");

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
		String message = driver.findElement(By.xpath(OR.getProperty("video_Success_Message_XPATH"))).getText();

		// Verify the success message
		verifyEqualsIgnoreCase(OR.getProperty("successMessage"), message);

		// Close the success message
		click("video_Close_XPATH");

	}

	@Test(enabled = true)
	@Parameters({ "videoPageTitle", "videoExperience", "fname", "lname", "email", "mobile", "from", "count", })
	public void video_gotoExperience(String videoPageTitle, String videoExperience, String fname, String lname,
			String email, String mobile, String from, String count) throws InterruptedException, IOException {

		// navigate to video page
		click("video_button_XPATH");
		Thread.sleep(3000);

		// Verify opened video page
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("video_page_XPATH"))).getText();
		String expectedTitle = videoPageTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

		Thread.sleep(3000);

		// Click Go To Experience button
		click("video_GoToExperience_XPATH");

		// Open new page
		// swithToNewTab();

		// Verify opened Experience page
		String actualExperience = driver.findElement(By.xpath(OR.getProperty("video_Experience_XPATH"))).getText();
		String expectedExperience = videoExperience;
		verifyEqualsIgnoreCase(actualExperience, expectedExperience);

		// Click Enquire button
		click("video_Enquire_XPATH");

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
		String message = driver.findElement(By.xpath(OR.getProperty("video_Success_Message_XPATH"))).getText();

		// Verify the success message
		verifyEqualsIgnoreCase(OR.getProperty("successMessage"), message);

		// Close the success message
		click("video_Close_XPATH");

	}

	@Test(enabled = true)
	@Parameters({ "videoPageTitle", "fname", "lname", "email", "mobile", "from", "count" })
	public void video_Enquire(String videoPageTitle, String fname, String lname, String email, String mobile,
			String from, String count) throws InterruptedException, IOException {

		// navigate to video page
		click("video_button_XPATH");
		Thread.sleep(3000);

		// Verify opened video page
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("video_page_XPATH"))).getText();
		String expectedTitle = videoPageTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

		Thread.sleep(3000);

		// Check any video with Enquire button is available

		List<WebElement> enquiryVideos = driver.findElements(By.xpath(OR.getProperty("video_enquire_present_XPATH")));
		if (enquiryVideos.size() != 0) {
			// If list size is non-zero, element is present

			// Click Enquire button
			click("video_enquire_first_XPATH");

			// Fill Enquire form
			// Enter information
			type("JNfirstname_XPATH", fname);
			type("JNlastname_XPATH", lname);
			type("JNemail_XPATH", email);
			type("JNPhone_XPATH", mobile);
			type("JNtravellingFrom_XPATH", from);
			click("JNimnotsure_XPATH");
			select("JNnumberoftravellers_XPATH", count);
			click("JNimnotsure_XPATH");

			// Click send button
			click("JNsendbutton_XPATH");

			verifyElementExistNot("JNvalidationmessage_XPATH");

			// Send enquiry

			// Retrive notification message
			String message = driver.findElement(By.xpath(OR.getProperty("video_Success_Message_XPATH"))).getText();

			// Verify the success message
			verifyEqualsIgnoreCase(OR.getProperty("successMessage"), message);

			// Close the success message
			click("video_Close_XPATH");
		} else {
			// Else if size is 0, then element is not present
			test.log(LogStatus.INFO, "No video is available with enquirey option.");
		}
	}
}
