package com.upnorway.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;

public class TailorMyJourney extends TestBase {

	// @Test
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

	@Test (enabled=false)
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
		verifyEquals("Please select up to four.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select holiday type
		click("familyVacation_XPATH");
		click("activeAdventure_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Click next button without selecting season
		click("nextBtn_XPATH");

		// Verify message prompting to select season
		verifyEquals("I think you forgot to choose a season! Please select at least one.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select season
		click("autumn_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Click next button without selecting interests
		click("nextBtn_XPATH");

		// Verify message prompting to select interests
		verifyEquals("Please select up to four areas of interest.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select interests
		click("greatOutdoors_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Click next button without selecting accommodation
		click("nextBtn_XPATH");

		// Verify message prompting to select accommodation
		verifyEquals("Please limit your choice to one.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select accommodation
		click("accommodation_XPATH");

		// click next button
		click("nextBtn_XPATH");

		// Click next button without selecting transport
		click("nextBtn_XPATH");

		// Verify message prompting to select transport
		verifyEquals("Please select just one.",
				driver.findElement(By.xpath(OR.getProperty("validation_XPATH"))).getText());

		// Select transport
		click("transport_XPATH");

		// Click next button
		click("nextBtn_XPATH");

		// Verify welcome name is equal the the first name entered at the beginning
		verifyEquals(firstname, driver.findElement(By.xpath(OR.getProperty("welcomeName_XPATH"))).getText());

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

}
