package com.upnorway.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
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

		String actualGetStartedTitle = driver.findElement(By.xpath(OR.getProperty("TMJvalidateMessage_XPATH")))
				.getText();
		String expectedGetStartedTitle = TMJvalidateMessage;
		verifyEqualsIgnoreCase(actualGetStartedTitle, expectedGetStartedTitle);

	}
}
