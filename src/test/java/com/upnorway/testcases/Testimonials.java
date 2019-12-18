package com.upnorway.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.upnorway.base.TestBase;

public class Testimonials extends TestBase {

	@Test(enabled = true)
	@Parameters({ "testimonialTitle" })
	public void navigateToTestimonials(String testimonialTitle) throws InterruptedException, IOException {
		// navigate to Testimonials section
		click("testimonial_button_XPATH");
		Thread.sleep(3000);

		// validate Testimonials section
		String actualTitle = driver.findElement(By.xpath(OR.getProperty("testimonialTitle_XPATH"))).getText();
		String expectedTitle = testimonialTitle;
		verifyEqualsIgnoreCase(actualTitle, expectedTitle);
	}
}
