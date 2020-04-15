package com.upnorway.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;

public class About extends TestBase {

	@Test(enabled = true)
	@Parameters({ "aboutTitle", "TMJvalidateMessage" })
	public void about_getStarted(String aboutTitle, String TMJvalidateMessage)
			throws InterruptedException, IOException {

		// Go to About page
		click("aboutButton_XPATH");

		// validate About page
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("aboutTitle_XPATH"))).getText();
		String expectedTitle = aboutTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

		// Select get started option
		click("aboutGetStartedButton_XPATH");

		// opens a new window
		swithToNewTab();

		// validate get started window
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty("TMJvalidateMessage_XPATH")))));

		String actualGetStartedTitle = driver.findElement(By.xpath(OR.getProperty("TMJvalidateMessage_XPATH")))
				.getText();
		String expectedGetStartedTitle = TMJvalidateMessage;
		verifyEqualsIgnoreCase(actualGetStartedTitle, expectedGetStartedTitle);

	}

	@Test(enabled = true)
	@Parameters({ "aboutTitle", "TestimonialTitle", "JourneyTitle" })
	public void about_JourneyTestimonials(String aboutTitle, String TestimonialTitle, String JourneyTitle)
			throws InterruptedException, IOException {

		// Go to About page
		click("aboutButton_XPATH");

		// validate About page
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("aboutTitle_XPATH"))).getText();
		String expectedTitle = aboutTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);

		// Verify testimonials are available or not in the about page
		Thread.sleep(3000);

		String actualTestimonialTitle = driver.findElement(By.xpath(OR.getProperty("aboutTestimonial_XPATH")))
				.getText();
		String expectedTestimonialTitle = TestimonialTitle;
		verifyEqualsIgnoreCase(actualTestimonialTitle, expectedTestimonialTitle);

		// click on right arrow key 5 times
		for (int i = 0; i < 5; i++) {
			// click the arrow button
			click("aboutArrowKey_XPATH");
			// wait 2 seconds
			Thread.sleep(2000);
			// check that data is being generated correctly
		}

		// Click on the Journey testimonial
		click("aboutJourneyTestimonial_XPATH");

		// Verify the loaded page
		Thread.sleep(3000);

		String actualJourneylTitle = driver.findElement(By.xpath(OR.getProperty("aboutTestimonialTitleJ_XPATH")))
				.getText();
		String expectedJourneyTitle = JourneyTitle;
		verifyEqualsIgnoreCase(actualJourneylTitle, expectedJourneyTitle);

	}

}