package com.upnorway.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;

import net.sourceforge.htmlunit.corejs.javascript.ast.ThrowStatement;

public class TailorMyJourney extends TestBase {

	@Test(enabled = false)
	@Parameters({ "firstname", "lastname", "age", "email", "phone", "arrivaldate", "lengthofthetrip", "occupation",
			"adultscount", "travellingfrom" })

	public void tailorMyJourney_HappyPath(String firstname, String lastname, String age, String email, String phone,
			String arrivaldate, String lengthofthetrip, String occupation, String adultscount, String travellingfrom)
			throws InterruptedException {

		click("tailorMyJourney_XPATH");
		type("firstName_XPATH", firstname);
		click("getStartedBtn_XPATH");
		click("familyVacation_XPATH");
		click("activeAdventure_XPATH");
		click("nextBtn_XPATH");
		click("autumn_XPATH");
		click("nextBtn_XPATH");
		click("greatOutdoors_XPATH");
		click("nextBtn_XPATH");
		click("accommodation_XPATH");
		click("nextBtn_XPATH");
		click("transport_XPATH");
		click("nextBtn_XPATH");
		type("lastName_XPATH", lastname);
		select("ageSelect_XPATH", age);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		type("phone_XPATH", phone);
		click("arrivalNotSureBtn_XPATH");
		type("arrivalDate_XPATH", arrivaldate);
		type("lengthOfTrip_XPATH", lengthofthetrip);
		type("occupation_XPATH", occupation);
		select("adults_XPATH", adultscount);
		click("noChildrenBtn_XPATH");
		type("travellingFrom_XPATH", travellingfrom);
		click("contactNextBtn_XPATH");

	}

	@Test(enabled = true)
	@Parameters({ "firstname", "lastname", "age", "email", "phone", "arrivaldate", "lengthofthetrip", "occupation",
			"adultscount", "travellingfrom" })
	public void tailorMyJourney_ValidationCheck(String firstname, String lastname, String age, String email,
			String phone, String arrivaldate, String lengthofthetrip, String occupation, String adultscount,
			String travellingfrom) throws InterruptedException, IOException {

		click("tailorMyJourney_XPATH");
		type("firstName_XPATH", firstname);

		click("getStartedBtn_XPATH");

		// Click next button without selecting vacation type
		click("nextBtn_XPATH");

		// Verify message prompting to select vacation
		verifyEqualsIgnoreCase("Please select up to four.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select holiday type
		click("familyVacation_XPATH");
		click("activeAdventure_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Click next button without selecting season
		click("nextBtn_XPATH");

		// Verify message prompting to select season
		verifyEqualsIgnoreCase("I think you forgot to choose a season! Please select at least one.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select season
		click("autumn_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Click next button without selecting interests
		click("nextBtn_XPATH");

		// Verify message prompting to select interests
		verifyEqualsIgnoreCase("Please select up to four areas of interest.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select interests
		click("greatOutdoors_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Click next button without selecting accommodation
		click("nextBtn_XPATH");

		// Verify message prompting to select accommodation
		verifyEqualsIgnoreCase("Please choose one.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select accommodation
		click("accommodation_XPATH");

		// click next button
		click("nextBtn_XPATH");

		// Click next button without selecting transport
		click("nextBtn_XPATH");

		// Verify message prompting to select transport
		verifyEqualsIgnoreCase("Please select just one.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select transport
		click("transport_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Verify welcome name is equal the the first name entered at the beginning
		verifyEqualsIgnoreCase(firstname, driver.findElement(By.xpath(OR.getProperty("welcomeName_XPATH"))).getText());

		// Enter last name
		type("lastName_XPATH", lastname);

		// Enter age
		select("ageSelect_XPATH", age);

		// Enter email address
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);

		// Ente phone number
		type("phone_XPATH", phone);

		// Click on 'arrival date not sure' button
		click("arrivalNotSureBtn_XPATH");

		// Enter arrival date
		type("arrivalDate_XPATH", arrivaldate);

		// Enter length of trip
		type("lengthOfTrip_XPATH", lengthofthetrip);

		// Enter occupation
		type("occupation_XPATH", occupation);

		// Enter number of adults
		select("adults_XPATH", adultscount);

		// Click no children button
		click("noChildrenBtn_XPATH");

		// Enter location travelling from
		type("travellingFrom_XPATH", travellingfrom);

		// click next button
		click("contactNextBtn_XPATH");

		// Click finsihed button
		click("finishBtn_XPATH");

	}

	// Selecting "back to home" option from tailor my journey
	@Test(enabled = true)
	@Parameters({ "firstname", "verifyText" })
	public void testTailorMyJourenyBackToHome(String firstname, String verifyText) throws IOException {

		// Click Tailor My Journey option
		click("tailorMyJourney_XPATH");

		// Enter first name
		type("firstName_XPATH", firstname);

		// Click Next Button
		click("getStartedBtn_XPATH");

		// Select Family Vacation
		click("familyVacation_XPATH");

		// Select back to home link
		click("backToHome_XPATH");

		// Verify results_Home page

		String actualTitle = driver.findElement(By.xpath(OR.getProperty("homePageTitle_XPATH"))).getText();
		String expectedTitle = verifyText;
//		assertEquals(actualTitle, expectedTitle);
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

	}

	// Selecting "Contact Me" option from tailor my journey

	@Parameters({ "firstname", "lastname", "email", "message" })
	@Test(enabled = true)
	public void send_Enquiry(String firstname, String lastname, String email, String message)
			throws InterruptedException, IOException {

		// Click Tailor My Journey option
		click("tailorMyJourney_XPATH");

		// Enter first name
		type("firstName_XPATH", firstname);

		// Click Next Button
		click("getStartedBtn_XPATH");

		// Select Family Vacation
		click("familyVacation_XPATH");

		// Click on Send enquiry button
		click("contactMe_XPATH");

		// Enter first name
		type("firstName_XPATH", firstname);

		// Enter last name
		type("lastName_XPATH", lastname);

		// Enter email
		type("email_XPATH", email);

		// Enter message to Up Norway team
		type("message_XPATH", message);

		// Send the enquiry
		click("sendBtn_XPATH");

		// Retrieve notification message
		String message2 = driver.findElement(By.xpath(OR.getProperty("enquirySuccessMessage_XPATH"))).getText();

		// Verify the success message
		verifyEqualsIgnoreCase(OR.getProperty("successMessage"), message2);

		// Close the success message
		click("enquirySuccessMessageCloseBtn_XPATH");
	}

	// Selecting "UP Norway" logo option from tailor my journey and navigate back to
	// home page
	
	@Parameters({ "firstname", "verifyText" })
	@Test(enabled = true)
	public void testTailorMyJourenyUPNorwayLogo(String firstname, String verifyText) throws IOException {

		// Click Tailor My Journey option
		click("tailorMyJourney_XPATH");

		// Enter first name
		type("firstName_XPATH", firstname);

		// Click Next Button
		click("getStartedBtn_XPATH");

		// Select Family Vacation
		click("familyVacation_XPATH");

		// Click Up Norway Logo
		click("upNorwayLogo_XPATH");

		// Verify results_Home page

		String actualTitle = driver.findElement(By.xpath(OR.getProperty("homePageTitle_XPATH"))).getText();
		String expectedTitle = verifyText;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

	}

	// validating the contact details form - Generating an error message when not
	// filling all mandatory data
	
	@Parameters({ "firstname", "lastname", "age", "email", "phone", "arrivaldate", "lengthofthetrip", "occupation",
			"adultscount", "travellingfrom", "verifyTextContactInfo" })
	@Test(enabled = true)
	public void testTailorMyJourneyContactInfoValidating(String firstname, String lastname, String age, String email,
			String phone, String arrivaldate, String lengthofthetrip, String occupation, String adultscount,
			String travellingfrom, String verifyTextContactInfo) throws InterruptedException, IOException {

		click("tailorMyJourney_XPATH");
		type("firstName_XPATH", firstname);
		click("getStartedBtn_XPATH");
		click("familyVacation_XPATH");
		click("activeAdventure_XPATH");
		click("nextBtn_XPATH");
		click("autumn_XPATH");
		click("nextBtn_XPATH");
		click("greatOutdoors_XPATH");
		click("nextBtn_XPATH");
		click("accommodation_XPATH");
		click("nextBtn_XPATH");
		click("transport_XPATH");
		click("nextBtn_XPATH");
		// Keep last name field as blank
		// type("lastName_XPATH", lastname);
		select("ageSelect_XPATH", age);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		type("phone_XPATH", phone);
		// Picking a date from the calender by selecting the calender option
		click("calender_XPATH");
		click("calenderDate_XPATH");
		type("lengthOfTrip_XPATH", lengthofthetrip);
		type("occupation_XPATH", occupation);
		select("adults_XPATH", adultscount);
		click("noChildrenBtn_XPATH");
		type("travellingFrom_XPATH", travellingfrom);
		click("contactNextBtn_XPATH");

		// Validating the error message
		String actualError = driver.findElement(By.xpath(OR.getProperty("contactErrorMessage_XPATH"))).getText();
		String expectedError = verifyTextContactInfo;
		verifyEqualsIgnoreCase(actualError, expectedError);

	}

}
