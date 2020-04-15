package com.upnorway.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.base.TestBase;
import com.upnorway.utilities.TestUtil;

public class Homepage extends TestBase {

	/**
	 * Clicking on Take me there button in the home page should take the user to the
	 * tip of the season
	 * 
	 * @throws IOException
	 */

	@Test(enabled = false)
	public void take_Me_There() throws IOException {

		// retrieve tip of the season text to verify with the text after opening the
		// next page
		String topoftheseason = driver.findElement(By.xpath(OR.getProperty("tipOfTheSeason_XPATH"))).getText()
				.toUpperCase();

		// Click on take me there button
		click("takeMeThere_XPATH");

		// retrive the text of the opened page title
		String first_desc = driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();

		// Verify that the user landed in the correct page
		verifyEqualsIgnoreCase(topoftheseason, first_desc);

	}

	/**
	 * Clicking on See more button in the home page should take the user to the
	 * Jourenys page
	 * 
	 * @throws IOException
	 */

	@Test(enabled = false)
	public void see_More() throws IOException {

		// Click on See more button
		click("seeMore_XPATH");

		// Verify that the user is navigated to the Journeys page
		verifyEqualsIgnoreCase("PERSONALIZED INSIDER JOURNEYS",
				driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText());

	}

	@Test(enabled = false)
	public void slideTestimonials() {

		// Move to testimonials section
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(OR.getProperty("testimonials_XPATH"))));

		// Read first, second and third testimonials

		List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("testimonial_cards_XPATH")));

		String first_title = list.get(0).getAttribute("");
	}

	@Test(enabled = true)
	@Parameters({ "PressItemTitle" })
	public void slidePressItems(String PressItemTitle) throws InterruptedException, IOException {

		// Verify Press Items section is available
		String actualPressItemTitle = driver.findElement(By.xpath(OR.getProperty("homePressItem_XPATH"))).getText();
		String expectedPressItemTitle = PressItemTitle;
		verifyEqualsIgnoreCase(actualPressItemTitle, expectedPressItemTitle);

		// click on right arrow key 5 times
		for (int i = 0; i < 2; i++) {
			// click the arrow button
			click("homeArrowKey_XPATH");
			// wait 2 seconds
			Thread.sleep(2000);
		}
		// Getting the text of the press item which appears after clicking right arrow
		// key two times
		String TheTelegraPhparagraph = driver.findElement(By.xpath(OR.getProperty("homeTelegraph_XPATH"))).getText();
		test.log(LogStatus.INFO, "Getting the text: " + TheTelegraPhparagraph);

	}

	@Test(enabled = true)
	@Parameters({ "GoogleReviewTitle", "GoogleReviewPage" })
	public void googleReviews(String GoogleReviewTitle, String GoogleReviewPage)
			throws InterruptedException, IOException {

		// Verify Google reviews section is available
		String actualGoogleReviewTitle = driver.findElement(By.xpath(OR.getProperty("homeGoogleReviews_XPATH")))
				.getText();
		String expectedGoogleReviewTitle = GoogleReviewTitle;
		verifyEqualsIgnoreCase(actualGoogleReviewTitle, expectedGoogleReviewTitle);

		// click Google Reviews link
		click("homeGoogleReviewLink_XPATH");
		Thread.sleep(2000);

		// Opening a new page
		swithToNewTab();

		// Validate the opened page

		String actualGoogleReviewPage = driver.findElement(By.xpath(OR.getProperty("googleReviews_XPATH"))).getText();
		String expectedGoogleReviewPage = GoogleReviewPage;
		verifyEqualsIgnoreCase(actualGoogleReviewPage, expectedGoogleReviewPage);

	}

	@Test(enabled = false)
	@Parameters({ "GooglereviewCount" })
	public void googleReviewCount(int GooglereviewCount) throws InterruptedException, IOException {

		// Get the count of displayed
		List<WebElement> reviews = driver.findElements(By.xpath(OR.getProperty("homeGoogleReviewsCount")));

		test.log(LogStatus.INFO, "Number of displayed Google reviews: " + reviews.size());

		// verify the Google Review count is same as expected
		int actualGoogleReviewCount = reviews.size();
		int expectedGoogleReviewCount = GooglereviewCount;
		verifyEquals(expectedGoogleReviewCount,actualGoogleReviewCount);

	}

	@Test(enabled = false) //disabling because test case takes 15 mins to finish execution
	public void googleReviewfiveStars() throws InterruptedException, IOException {

		// Get the count of displayed
		List<WebElement> reviews = driver.findElements(By.xpath(OR.getProperty("homeGoogleReviewsCount")));

		test.log(LogStatus.INFO, "Number of displayed Google reviews: " + reviews.size());

		// Navigate google reviews using arrow keys
		for (int i = 0; i < reviews.size(); i++) {
			// click the arrow button
			click("homeGoogleReviewsArrow_XPATH");
			// wait 1 second
			// Thread.sleep(1000);

			// Check whether the google review has 5 stars or not
			isElementPresent(By.xpath("homeFiveStar_XPATH"));

		}
	}

	@Test(enabled = false)
	public void googleReviewDescription() throws InterruptedException, IOException {

		// Get the count of displayed
		List<WebElement> reviews = driver.findElements(By.xpath(OR.getProperty("homeGoogleReviewsCount")));

		test.log(LogStatus.INFO, "Number of displayed Google reviews: " + reviews.size());

		// Navigate google reviews using arrow keys
		for (int i = 0; i < reviews.size(); i++) {
			// click the arrow button
			click("homeGoogleReviewsArrow_XPATH");
			// wait 1 second
			// Thread.sleep(1000);

			// Verify the google reviews which do not contain description does not display
			// under the Google review Section
			isElementPresent(By.xpath("homeGoogleReviewContent_XPATH"));

		}
	}

}