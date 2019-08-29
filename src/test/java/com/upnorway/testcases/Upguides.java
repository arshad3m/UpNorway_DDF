package com.upnorway.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;

public class Upguides extends TestBase {
	
	
	@Test(enabled = true)
	@Parameters({ "keyword3" })
	public void search_upguides(String keyword) throws InterruptedException, IOException {

		// Go to journeys
				click("upguidesButton_XPATH");


				// type keyword in search box
				type("upgSearch_XPATH", keyword);
				
				driver.findElement(By.xpath(OR.getProperty("upgSearch_XPATH"))).sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);

				
				// select region as South-eastern Norway and season as Sumemr
				click("upgDestinations_XPATH");
				

				// verify results
				String searchresultText = driver.findElement(By.xpath(OR.getProperty("upgSearchResultsText_XPATH"))).getText();
				String resultCountText = driver.findElement(By.xpath(OR.getProperty("upgSearchResultsCount_XPATH")))
						.getAttribute("innerText").replaceAll("\\D+","");

				//Verify results appearing are relevent for the search keyword
				verifyContains(searchresultText, keyword);
				
				

				// Number of results showing in search results text
				int numberOfUpguides = Integer.parseInt(resultCountText);
				// open result

				// the xpath retrieve all the journeys - hidden and visible
				List<WebElement> cards = driver.findElements(By.xpath(OR.getProperty("upgCards_XPATH")));

				// add only the visible cards to the new array
				List<WebElement> cardNames = new ArrayList<WebElement>();

				for (int i = 0; i < cards.size() ; i++) {
					int x = cards.get(i).getLocation().getX();
					if (x != 0) {
						int j = i + 1;
						/*cardNames.add(driver.findElement(
								By.xpath("(//div[@class='col-sm-6 col-md-6 col-lg-4 col-xl-3'])[" + j + "]/div/a/div/div/h3")));*/
						
						cardNames.add(driver.findElement(By.xpath("(//div[@class='destination_main_wrap']//div[@class='destination_wrap '])["+j+"]//h3")));

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

}
