package com.upnorway.testcases;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;
import com.upnorway.utilities.TestUtil;

public class Experiences extends TestBase {

	/**
	 * Navigate to Experience page Search using a keyword after selecting a filter
	 * verify that the search text and number of results are correct Open a journey
	 * from the result and verify that it is opened correctly
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(enabled = true)
	@Parameters({ "expkeyword1" })
	public void search_experiences(String keyword) throws InterruptedException, IOException {

		// Go to journeys
		click("experienceButton_XPATH");

		// select region as South-eastern Norway and season as Sumemr
		click("expRegion_XPATH");
		click("expSeason_XPATH");

		// type keyword in search box
		type("expSearch_XPATH", keyword);
		Thread.sleep(3000);

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("expSearchResultText_XPATH"))).getText();
		String resultCountText = driver.findElement(By.xpath(OR.getProperty("expResultsCount_XPATH")))
				.getAttribute("innerText");

		// assertTrue(searchresultText.contains(keyword));

		verifyContains(searchresultText, keyword);

		// Number of results showing in search results text
		int numberOfJourneys = Integer.parseInt(resultCountText.substring(23));
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

		// verify matching number of experience are shown as search result
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
	 * @param keyword 1. Go to experiences 2. Search using a keyword 3. Verify
	 *                message shows the number of results 4. Retrieve the title of
	 *                the 6th result 5. Open the 6th result 6. Verify correct result
	 *                is opened
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(enabled = true)
	@Parameters({ "keyword2", "fname", "lname", "email", "mobile", "from", "count" })
	public void experience_enquire(String keyword, String fname, String lname, String email, String mobile, String from,
			String count) throws IOException, InterruptedException {

		// Go to journeys
		click("experienceButton_XPATH");

		// type keyword in search box
		type("expSearch_XPATH", keyword);

		Thread.sleep(3000);

		try {
			List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("jourenyCards_XPATH")));

		} catch (NoSuchElementException e) {
			type("expSearch_XPATH", keyword);

		}

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("expSearchResultText_XPATH"))).getText();
		String resultCountText = driver.findElement(By.xpath(OR.getProperty("expResultsCount_XPATH")))
				.getAttribute("innerText");

		verifyContains(searchresultText, keyword);

		// Number of results showing in search results text
		int numberOfExperiences = Integer.parseInt(resultCountText.substring(23));
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
		verifyEquals(numberOfExperiences, cardNames.size());

		// Title of the sixth card
		String title_of_sixth_card = cardNames.get(4).getAttribute("innerHTML");

		// Click on the sixth card
		click(cardNames.get(4));

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
		click("expClosebutton_XPATH");

	}

	/*
	 * <class name="com.upnorway.testcases.Homepage" /> <class
	 * name="com.upnorway.testcases.TailorMyJourney" /> <class
	 * name="com.upnorway.testcases.SendEnquiry" /> <class
	 * name="com.upnorway.testcases.SearchAnything" />
	 */

	// Test Expand option and collapse option
	@Test(enabled = true)
	@Parameters({ "expkeyword", "count", "verifyTopic", "verifyExperienceTitle" })
	public void experience_expand(String expkeyword, String count, String verifyTopic, String verifyExperienceTitle)
			throws IOException, InterruptedException {

		// Go to experiences
		click("experienceButton_XPATH");

		// type keyword in search box
		type("expSearch_XPATH", expkeyword);

		Thread.sleep(3000);

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("expSearchResultText_XPATH"))).getText();
		String resultCountText = driver.findElement(By.xpath(OR.getProperty("expResultsCount_XPATH")))
				.getAttribute("innerText");

		verifyContains(searchresultText, expkeyword);

		
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

		String actualTitle = driver.findElement(By.xpath(OR.getProperty("expPageLocal_XPATH"))).getText();
		String expectedTitle = verifyExperienceTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

		// Click expand button
		click("expExpandButton_XPATH");

		// Validate the expansion
		String actualTopic = driver.findElement(By.xpath(OR.getProperty("expExpandTopic_XPATH"))).getText();
		String expectedTopic = verifyTopic;
		verifyEqualsIgnoreCase(actualTopic, expectedTopic);

		click("expExpandButton_XPATH");
	}

	// Validating the counts
	@Test(enabled = true)
	@Parameters({ "expkeyword1" })
	public void exp_validateCountsAfterFiltering(String expkeyword1) throws InterruptedException, IOException {

		// Go to Experiences
		click("experienceButton_XPATH");

		String resultCountText = driver.findElement(By.xpath(OR.getProperty("expResultsCount_XPATH")))
				.getAttribute("innerText");

		int numberOfExperiences = Integer.parseInt(resultCountText.substring(23));
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
		verifyEquals(numberOfExperiences, cardNames.size());

		// type keyword1 in search box
		type("expSearch_XPATH", expkeyword1);
		Thread.sleep(3000);

		// verify results
		String searchresultText = driver.findElement(By.xpath(OR.getProperty("expSearchResultText_XPATH"))).getText();
		String searchresultCountText = driver.findElement(By.xpath(OR.getProperty("expResultsCount_XPATH")))
				.getAttribute("innerText");

		verifyContains(searchresultText, expkeyword1);

		// Number of results showing in search results text
		int numberOfJourneysSearch = Integer.parseInt(searchresultCountText.substring(23));

		// verifying the count of listed experiences are not more than the default count

		// assertTrue(numberOfExperiences >= numberOfJourneysSearch);
		verifyGreaterThanCondition(numberOfExperiences, numberOfJourneysSearch);

	}

	@Test(enabled = true)
	@Parameters({ "magicAppValidation" })
	public void experiences_magicMap(String magicAppValidation) throws IOException, InterruptedException {

		// Go to experiences
		click("experienceButton_XPATH");

		// Select Get Explore Norway using our magic app
		click("exp_magicApp_XPATH");
		Thread.sleep(3000);

		swithToNewTab();

		// validate Tailor My Journey window
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("magicAppValidation_XPATH"))).getText();
		String expectedTitle = magicAppValidation;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

	}

	@Test(enabled = true)
	@Parameters({ "TMJvalidateMessage" })
	public void experience_getStarted(String TMJvalidateMessage) throws IOException, InterruptedException {

		// Go to experiences
		click("experienceButton_XPATH");

		// Select Get Started option
		click("expGetStartedButton_XPATH");
		Thread.sleep(3000);

		// validate Tailor My Journey window
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("TMJvalidateMessage_XPATH"))).getText();
		String expectedTitle = TMJvalidateMessage;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

	}
	 
}