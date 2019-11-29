package com.upnorway.testcases;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.functors.NotNullPredicate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;
import com.upnorway.utilities.TestUtil;

public class Journeys extends TestBase {

	/**
	 * Navigate to Journeys page Search using a keyword after selecting a filter
	 * verify that the search text and number of results are correct Open a journey
	 * from the result and verify that it is opened correctly
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority = 1, enabled = true)
	@Parameters({ "keyword1" })
	public void search_journeys(String keyword) throws InterruptedException, IOException {

		// Go to journeys
		click("journeysBtn_XPATH");

		// select interest as Scenic drives
		click(driver.findElement(By.xpath(OR.getProperty("interest_XPATH"))));

		// type keyword in search box
		type("searchBox_XPATH", keyword);

		Thread.sleep(3000);

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("searchResultText_XPATH"))).getText();
		String resultCountText = driver.findElement(By.xpath(OR.getProperty("resultsCount_XPATH")))
				.getAttribute("innerText");

		// assertTrue(searchresultText.contains(keyword));

		verifyContains(searchresultText, keyword);

		// Number of results showing in search results text
		int numberOfJourneys = Integer.parseInt(resultCountText.substring(20));
		// open result

		// the xpath retrieve all the journeys - hidden and visible
		List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("jourenyCards_XPATH")));

		// add only the visible cards to the new array
		List<WebElement> cardNames = new ArrayList<WebElement>();

		for (int i = 0; i < cards.size(); i++) {
			int x = cards.get(i).getLocation().getX();
			if (x != 0) {
				int j = i + 1;
				cardNames.add(driver.findElement(
						By.xpath("(//div[@class='col-sm-6 col-md-6 col-lg-4 col-xl-3'])[" + j + "]/div/a/div/div/h3")));

			}
		}

		// verify matching number of journeys are shown as search result
		verifyEquals(numberOfJourneys, cardNames.size());

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

	/**
	 * @param keyword 1. Go to journeys 2. Search using a keyword 3. Verify message
	 *                shows the number of results 4. Retrieve the title of the 6th
	 *                result 5. Open the 6th result 6. Verify correct result is
	 *                opened
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(priority = 2, enabled = true)
	@Parameters({ "keyword2", "fname", "lname", "email", "mobile", "from", "count" })
	public void journey_enquire(String keyword, String fname, String lname, String email, String mobile, String from,
			String count) throws IOException, InterruptedException {

		// Go to journeys
		click("journeysBtn_XPATH");

		Thread.sleep(3000);

		// type keyword in search box
		type("searchBox_XPATH", keyword);

		try {
			List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("jourenyCards_XPATH")));

		} catch (NoSuchElementException e) {
			type("searchBox_XPATH", keyword);

		}

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("searchResultText_XPATH"))).getText();
		String resultCountText = driver.findElement(By.xpath(OR.getProperty("resultsCount_XPATH")))
				.getAttribute("innerText");

		verifyContains(searchresultText, keyword);

		// Number of results showing in search results text
		int numberOfJourneys = Integer.parseInt(resultCountText.substring(20));
		// open result

		// the xpath retrieve all the journeys - hidden and visible
		List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("jourenyCards_XPATH")));

		// add only the visible cards to the new array
		List<WebElement> cardNames = new ArrayList<WebElement>();

		for (int i = 0; i < cards.size(); i++) {
			int x = cards.get(i).getLocation().getX();
			if (x != 0) {
				int j = i + 1;
				cardNames.add(driver.findElement(
						By.xpath("(//div[@class='col-sm-6 col-md-6 col-lg-4 col-xl-3'])[" + j + "]/div/a/div/div/h3")));

			}
		}

		// verify matching number of journeys are shown as search result
		verifyEquals(numberOfJourneys, cardNames.size());

		// Title of the sixth card
		String title_of_sixth_card = cardNames.get(5).getAttribute("innerHTML");

		// Click on the sixth card
		click(cardNames.get(5));

		// Verify title of the newly openned article
		swithToNewTab();

		// Title of the opened page
		String journey_title = driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();

		// Verify title of the opened page is equal to what was retrieved before opening
		verifyEqualsIgnoreCase(title_of_sixth_card, journey_title);

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
		click("JNclosebutton_XPATH");

	}
	/*
	 * <class name="com.upnorway.testcases.Homepage" /> <class
	 * name="com.upnorway.testcases.TailorMyJourney" /> <class
	 * name="com.upnorway.testcases.SendEnquiry" /> <class
	 * name="com.upnorway.testcases.SearchAnything" />
	 */

	/*
	 * <class name="com.upnorway.testcases.Journeys" /> <class
	 * name="com.upnorway.testcases.Experiences" />
	 */

	// Navigate to the Tailor My journey form by selecting "Get Started" Option in
	// Journeys page

	@Test(priority = 3, enabled = true)
	@Parameters({ "TMJvalidateMessage" })
	public void journey_getStarted(String TMJvalidateMessage) throws IOException, InterruptedException {

		// Go to journeys
		click("journeysBtn_XPATH");

		// Select Get Started option
		click("getStartedButton_XPATH");
		Thread.sleep(3000);

		// validate Tailor My Journey window
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("TMJvalidateMessage_XPATH"))).getText();
		String expectedTitle = TMJvalidateMessage;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

	}

	// Navigate to the Magic Map by selecting "Explore Norway using our magic app"
	// Option in Journeys page

	@Test(priority = 4, enabled = true)
	@Parameters({ "magicAppValidation" })
	public void journey_magicMap(String magicAppValidation) throws IOException, InterruptedException {

		// Go to journeys
		click("journeysBtn_XPATH");

		// Select Get Explore Norway using our magic app
		click("magicApp_XPATH");
		Thread.sleep(3000);

		swithToNewTab();


		// validate Magic Map window

		String actualTitle = driver.findElement(By.xpath(OR.getProperty("magicAppValidation_XPATH"))).getText();
		String expectedTitle = magicAppValidation;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

	}

	// Validate the result count which are generated after adding a search key with
	// an option, should be less than the result count generated after adding an
	// option only.
	@Test(priority = 5, enabled = true)
	@Parameters({ "keyword4" })
	public void validateCountsAfterFiltering(String keyword4) throws IOException, InterruptedException {

		// Go to journeys
		click("journeysBtn_XPATH");

		// select interest as Scenic drives
		click(driver.findElement(By.xpath(OR.getProperty("interest_XPATH"))));

		String resultCountText = driver.findElement(By.xpath(OR.getProperty("resultsCount_XPATH")))
				.getAttribute("innerText");

		int numberOfJourneys = Integer.parseInt(resultCountText.substring(20));

		// the xpath retrieve all the journeys - hidden and visible
		List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("jourenyCards_XPATH")));

		// add only the visible cards to the new array
		List<WebElement> cardNames = new ArrayList<WebElement>();

		for (int i = 0; i < cards.size(); i++) {
			int x = cards.get(i).getLocation().getX();
			if (x != 0) {
				int j = i + 1;
				cardNames.add(driver.findElement(
						By.xpath("(//div[@class='col-sm-6 col-md-6 col-lg-4 col-xl-3'])[" + j + "]/div/a/div/div/h3")));

			}
		}

		// verify matching number of journeys are shown as search result
		verifyEquals(numberOfJourneys, cardNames.size());

		// type keyword in search box
		type("searchBox_XPATH", keyword4);

		Thread.sleep(3000);

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("searchResultText_XPATH"))).getText();
		String searchresultCountText = driver.findElement(By.xpath(OR.getProperty("resultsCount_XPATH")))
				.getAttribute("innerText");

		verifyContains(searchresultText, keyword4);

		// Number of results showing in search results text
		int numberOfJourneysSearch = Integer.parseInt(searchresultCountText.substring(20));


		// verifying the count of listed journeys are not more than the default count

		//assertTrue(numberOfJourneys >= numberOfJourneysSearch);
		
		verifyGreaterThanCondition(numberOfJourneys,numberOfJourneysSearch);

	}

	// Price is available in for all currency types
	@Test(priority = 6, enabled = true)
	@Parameters({ "keyword2",  "currencytype" })
	public void currencyTypes_price(String keyword2, String currencytype) throws IOException, InterruptedException {

		// Go to journeys
		click("journeysBtn_XPATH");

		// select interest as Scenic drives
		click(driver.findElement(By.xpath(OR.getProperty("interest_XPATH"))));

		// type keyword in search box
		type("searchBox_XPATH", keyword2);

		Thread.sleep(3000);

		// open result

		// the xpath retrieve all the journeys - hidden and visible
		List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("jourenyCards_XPATH")));

		// add only the visible cards to the new array
		List<WebElement> cardNames = new ArrayList<WebElement>();

		for (int i = 0; i < cards.size(); i++) {
			int x = cards.get(i).getLocation().getX();
			if (x != 0) {
				int j = i + 1;
				cardNames.add(driver.findElement(
						By.xpath("(//div[@class='col-sm-6 col-md-6 col-lg-4 col-xl-3'])[" + j + "]/div/a/div/div/h3")));

			}
		}

		// Click on the first card
		click(cardNames.get(0));

		// Verify title of the newly openned article
		swithToNewTab();

		Thread.sleep(3000);

		// Select currency

		// Select currency type from drop down list

		select("selectedCurrencyName_XPATH", currencytype);
		isElementPresent(By.xpath("price_XPATH"));

	}

	}
