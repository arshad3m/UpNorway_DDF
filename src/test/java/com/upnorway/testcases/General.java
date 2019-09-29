package com.upnorway.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.upnorway.utilities.TestUtil;
import com.upnorway.base.TestBase;

public class General extends TestBase{
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 2)
	public void loginToForest(Hashtable<String, String> data) throws InterruptedException {
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		navigate(data.get("forestlink"));
		
		type("frst_username_XPATH", config.getProperty("forest_username"));
		type("frst_password_XPATH",config.getProperty("forest_password"));
		click("frst_login_button_XPATH");
		type("frst_renter_password_XPATH",config.getProperty("forest_password"));
		click("frst_enter_button_XPATH");
		
		Thread.sleep(3000);
		
		waitForElementVisibility("frst_booking_XPATH");
	}
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void routing(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		navigate(data.get("url"));
		
		verifyURL();
	}
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 3)
	public void email_solution(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		navigate(data.get("forestlink"));
		
/*		type("frst_username_XPATH", config.getProperty("forest_username"));
		type("frst_password_XPATH",config.getProperty("forest_password"));
		click("frst_login_button_XPATH");
		type("frst_renter_password_XPATH",config.getProperty("forest_password"));
		click("frst_enter_button_XPATH");
		
		Thread.sleep(3000);
		*/
		waitForElementVisibility("frst_booking_XPATH");
		
		click("frst_action_XPATH");
		click("frst_contact_partner_XPATH");
		type("frst_text_area_XPATH",data.get("message"));
		click("frst_send_button_XPATH");
		
		waitForElementVisibility("frst_booking_XPATH");
		
		String sent_message=getTextAttribute("frst_message_text_XPATH");
		String message_type=getTextAttribute("frst_message_type_XPATH");
		String booking_number_forest = getTextAttribute("frst_booking_number_XPATH");
		String total_cost_forst = getTextAttribute("frst_total_cost_XPATH");
		
		verifyEqualsIgnoreCase(data.get("message"), sent_message);
		verifyEqualsIgnoreCase("OUTGOING", message_type);
		
		openUrlOnNewTab(data.get("shortlink"));
		
		String latest_message=getTextAttribute("sltn_latest_message_XPATH");
		String booking_number_shortlink=getTextAttribute("slnt_booking_number_XPATH");
		String total_cost_shortlink=getTextAttribute("slnt_total_cost_XPATH").replace("NOK", "").trim();
		
		verifyEqualsIgnoreCase(data.get("message"), latest_message);
		verifyEqualsIgnoreCase(booking_number_forest, booking_number_shortlink);
		verifyEqualsIgnoreCase(total_cost_forst, total_cost_shortlink);

		click("sltn_messages_XPATH");
		
		type("sltn_message_box_XPATH",data.get("reply"));
		
		click("sltn_send_message_button_XPATH");
		
		waitForElementVisibility("sltn_success_message_XPATH");
		
		swithToNewTab();
		
		//refresh
		driver.navigate().to(driver.getCurrentUrl());
		
		waitForElementVisibility("frst_booking_XPATH");

		String sent_message2=getTextAttribute("frst_message_text_XPATH");
		
		verifyEqualsIgnoreCase(data.get("reply"), sent_message2);
		
		
		Thread.sleep(4000);


	}

}
