package com.upnorway.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;

public class Upguides extends TestBase {

	@Test(enabled = true)
	@Parameters({ "keyword3" })
	public void search_upguides(String keyword3) throws InterruptedException, IOException {

		// Go to journeys
		click("whyNorwayButton_XPATH");

		// type keyword in search box
		type("upgSearch_XPATH", keyword3);

		driver.findElement(By.xpath(OR.getProperty("upgSearch_XPATH"))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);

		// select region as South-eastern Norway and season as Sumemr
		click("upgDestinations_XPATH");

		Thread.sleep(4000);
		
		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("upgSearchResultsText_XPATH"))).getText();
		String resultCountText = driver.findElement(By.xpath(OR.getProperty("upgSearchResultsCount_XPATH")))
				.getAttribute("innerText").replaceAll("\\D+", "");

		// Verify results appearing are relevent for the search keyword
		verifyContains(searchresultText, keyword3);

		// Number of results showing in search results text
		int numberOfUpguides = Integer.parseInt(resultCountText);
		// open result

		// the xpath retrieve all the journeys - hidden and visible
		List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("upgCards_XPATH")));

		// add only the visible cards to the new array
		List<WebElement> cardNames = new ArrayList<WebElement>();

		for (int i = 0; i < cards.size(); i++) {
			int x = cards.get(i).getLocation().getX();
			if (x != 0) {
				int j = i + 1;
				/*
				 * cardNames.add(driver.findElement(
				 * By.xpath("(//div[@class='col-sm-6 col-md-6 col-lg-4 col-xl-3'])[" + j +
				 * "]/div/a/div/div/h3")));
				 */

				cardNames.add(driver.findElement(By.xpath(
						"(//div[@class='destination_main_wrap']//div[@class='destination_wrap '])[" + j + "]//h3")));

			}
		}

		// verify matching number of experience are shown as search result
		verifyEquals(numberOfUpguides, cardNames.size());

		// Title of the first card
		String title_of_first_card = cardNames.get(0).getAttribute("innerHTML");

		// Click on the first card
		click(cardNames.get(0));

		// Verify title of the newly openned article
		swithToNewTab();

		// Title of the opened page
		String journey_title = driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();

		// Verify title of the opened page is equal to what was retrieved before opening
		verifyEqualsIgnoreCase(title_of_first_card, journey_title);

	}

	@Test(enabled = true)
	@Parameters({ "fname", "lname", "email", "mobile", "from", "count" })
	public void upguide_go_to_journey(String fname, String lname, String email, String mobile, String from,
			String count) throws IOException, InterruptedException {

		// Go to up guides
		click("whyNorwayButton_XPATH");

		// the xpath retrieve all the journeys - hidden and visible
		List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("upgCards_XPATH")));

		// add only the visible cards to the new array
		List<WebElement> cardNames = new ArrayList<WebElement>();

		for (int i = 0; i < cards.size(); i++) {
			int x = cards.get(i).getLocation().getX();
			if (x != 0) {
				int j = i + 1;

				cardNames.add(driver.findElement(By.xpath(
						"(//div[@class='destination_main_wrap']//div[@class='destination_wrap '])[" + j + "]//h3")));

			}
		}

		// Title of the fourth card
		String title_of_sixth_card = cardNames.get(3).getAttribute("innerHTML");

		// Click on the fourth card
		click(cardNames.get(3));

		// Verify title of the newly openned article
		swithToNewTab();

		// Title of the opened page
		String journey_title = driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();

		// Verify title of the opened page is equal to what was retrieved before opening
		verifyEqualsIgnoreCase(title_of_sixth_card, journey_title);

		// read article title

		click("upgFirst_GoToButton_XPATH");

		// read opened page title

		// verify titles

		// Click on Enquiry button
		// click("enquireButton_XPATH");
		click(driver.findElement(By.xpath(OR.getProperty("enquireButton_XPATH"))));

		// Enter information
		type("JNfirstname_XPATH", fname);
		verifyElementExistNot("JNvalidationmessage_XPATH");
		type("JNlastname_XPATH", lname);
		click("JNsendbutton_XPATH");
		verifyElementExists("JNvalidationmessage_XPATH");
		type("JNemail_XPATH", email);
		type("JNPhone_XPATH", mobile);
		type("JNtravellingFrom_XPATH", from);
		click("JNimnotsure_XPATH");
		select("JNnumberoftravellers_XPATH", count);
		verifyElementExistNot("JNvalidationmessage_XPATH");
		click("JNsendbutton_XPATH");

		// Send enquiry

		// Verify success message

		// Retrive notification message
		String message2 = driver.findElement(By.xpath(OR.getProperty("enquirySuccessMessage_XPATH"))).getText();

		// Verify the success message
		verifyEqualsIgnoreCase(OR.getProperty("successMessage"), message2);

		// Close the success message
		click("upgClosebutton_XPATH");

	}

	// Validating the counts
	@Test(enabled = true)
	@Parameters({ "upguidekeyword" })
	public void upguide_validateCountsAfterFiltering(String upguidekeyword) throws InterruptedException, IOException {

		// Go to journeys
		click("whyNorwayButton_XPATH");
		Thread.sleep(3000);

		// select Destinations
		click("upgDestinations_XPATH");

		// Select Insiders
		click("upgInsiders_XPATH");

		Thread.sleep(3000);

		String resultCountText = driver.findElement(By.xpath(OR.getProperty("upgSearchResultsCount_XPATH")))
				.getAttribute("innerText");

		int numberOfUpGuides = Integer.parseInt(resultCountText.substring(5, 7));
		// open result

		// the xpath retrieve all the journeys - hidden and visible
		List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("upgCards_XPATH")));

		// add only the visible cards to the new array
		List<WebElement> cardNames = new ArrayList<WebElement>();

		for (int i = 0; i < cards.size(); i++) {
			int x = cards.get(i).getLocation().getX();
			if (x != 0) {
				int j = i + 1;

				cardNames.add(driver.findElement(By.xpath(
						"(//div[@class='destination_main_wrap']//div[@class='destination_wrap '])[" + j + "]//h3")));

			}
		}

		// verify matching number of upguides are shown as search result
		verifyEquals(numberOfUpGuides, cardNames.size());

		// type upguidekeyword in search box
		type("upgSearch_XPATH", upguidekeyword);
		Thread.sleep(3000);

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("upgSearchResultsText_XPATH"))).getText();
		String searchresultCountText = driver.findElement(By.xpath(OR.getProperty("upgSearchResultsCount_XPATH")))
				.getAttribute("innerText");

		verifyContains(searchresultText, upguidekeyword);

		// Number of results showing in search results text
		int numberOfJUpGuidesSearch = Integer.parseInt(searchresultCountText.substring(5, 7));

		// verifying the count of listed experiences are not more than the default count

		// assertTrue(numberOfExperiences >= numberOfJourneysSearch);
		verifyGreaterThanCondition(numberOfUpGuides, numberOfJUpGuidesSearch);

	}

//validate the menu title WHY NORWAY
	@Test(enabled = true)
	@Parameters({ "whyNorway"})
	public void Validate_WhyNorway(String whyNorway)
			throws IOException, InterruptedException {

		// Go to up guides
		click("whyNorwayButton_XPATH");

		// verify the menu name
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("whyNorwayButton_XPATH"))).getText();
		String expectedTitle = whyNorway;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

	}

}
