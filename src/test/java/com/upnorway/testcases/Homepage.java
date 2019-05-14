package com.upnorway.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.upnorway.base.TestBase;
import com.upnorway.utilities.TestUtil;


public class Homepage extends TestBase {

	

	/**
	 * Clicking on Take me there button in the home page should take the user to the
	 * tip of the season
	 * @throws IOException 
	 */

	@Test (enabled=false)
	public void take_Me_There() throws IOException {

		//retrieve tip of the season text to verify with the text after opening the next page 
		String topoftheseason = driver.findElement(By.xpath(OR.getProperty("tipOfTheSeason_XPATH"))).getText().toUpperCase();
		
		//Click on take me there button
		click("takeMeThere_XPATH");
		
		//retrive the text of the opened page title
		String first_desc=driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();
		
		//Verify that the user landed in the correct page
		verifyEquals(topoftheseason, first_desc);


	}

	/**
	 * Clicking on See more button in the home page should take the user to the
	 * Jourenys page
	 * @throws IOException 
	 */
	
	@Test (enabled=false)
	public void see_More() throws IOException {
		
		//Click on See more button
		click("seeMore_XPATH");
		
		//Verify that the user is navigated to the Journeys page
		verifyEquals("PERSONALIZED INSIDER JOURNEYS", driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText());
	

	}

}
