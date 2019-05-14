package com.upnorway.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.upnorway.base.TestBase;
import com.upnorway.utilities.TestUtil;


public class SearchAnything extends TestBase {

	/**
	 * @author ArshadM
	 *Search using a keyword so that yield more than 4 results appear
	 */
	@Test(priority=1, enabled=true)
	@Parameters({"keyword_for_many"})
	public void search_for_many(String keyword_for_many) throws InterruptedException, IOException {
		
		Thread.sleep(2000);
		type("search_XPATH", keyword_for_many);
		type("search_XPATH", Keys.ENTER);

		
		// verify more than 4 results show
		List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("card_bucket_XPATH")));
		int count = list.size();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(count >= 3);

		// click on the first result and verify user is navigated to the correct page
		String joureny_title_detailed = driver.findElement(By.xpath(OR.getProperty("first_result_desc_XPATH"))).getText();
		click("first_result1_XPATH");
		
		swithToNewTab();
		
		String first_desc=driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();
		
		verifyEquals(joureny_title_detailed, first_desc);

		sa.assertAll();

	}
	
	/**
	 * @author ArshadM
	 *Search using a keyword so that yield less than 4 results appear
	 */
	
	@Test(priority=2, enabled=true)
	@Parameters({"keyword_for_less"})
	public void search_for_less(String keyword_for_less) throws InterruptedException, IOException {

		Thread.sleep(2000);


		type("search_XPATH", keyword_for_less);
		type("search_XPATH", Keys.ENTER);

	//	Thread.sleep(2000);

		
		// verify less than 4 results are shown
		List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("card_bucket_XPATH")));
		int count = list.size();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(count <= 3);

		// click on the first result and verify user is navigated to the correct page
		String joureny_title_detailed = driver.findElement(By.xpath(OR.getProperty("first_result_desc_XPATH"))).getText();

		//click on the first card
		click("first_result1_XPATH");
		
		swithToNewTab();

		//retrive the title
		String first_desc=driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();
		
		//verify both titles match 
		verifyEquals(joureny_title_detailed, first_desc);
		
		sa.assertAll();

	}
	
	
	/**
	 * @author ArshadM
	 *Search using a keyword so that yield more than 4 results appear.
	 *Navigate to a 4th page
	 *Click on the first result of the 4th page
	 * @throws InterruptedException 
	 */
	
	@Test(enabled=true)
	@Parameters({"keyword_for_many"})
	public void search_Pagination(String keyword_for_many) throws IOException, InterruptedException {
		
		Thread.sleep(2000);

		type("search_XPATH", keyword_for_many);
		type("search_XPATH", Keys.ENTER);

	//	Thread.sleep(2000);

		//Click on the fourth page
		click("fourth_page_XPATH");
		
		//retrive the title before clicking on the first result
		String joureny_title_detailed = driver.findElement(By.xpath(OR.getProperty("first_result_desc_XPATH"))).getText();

		//click on the first card
		click("first_result1_XPATH");
		
		swithToNewTab();

		//retrive the title
		String first_desc=driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();
		
		//verify both titles match 
		verifyEquals(joureny_title_detailed, first_desc);
		
	}
	
	/**
	 * @author ArshadM
	 * Verify no results show up when an invalid keyword is used for searching
	 * @throws InterruptedException 
	 */
	
	@Test(enabled=false)
	@Parameters({"keyword_invalid"})
	public void search_With_Invalid_Keyword(String keyword_invalid) throws IOException, InterruptedException {
		
		Thread.sleep(2000);

		
		//Search using the keyword
		type("search_XPATH", keyword_invalid);
		type("search_XPATH", Keys.ENTER);

		
		//Verify no cards are shown as results
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(false, driver.findElements(By.xpath(OR.getProperty("card_bucket_XPATH"))).size()>0);
		
		sa.assertAll();
		
		
	}
	
	/**
	 * @author ArshadM
	 * Navigate through pagination
	 * Come back and verify content has not changed
	 * @throws InterruptedException 
	 */
	
	@Test (enabled=true)
	@Parameters({"keyword_for_many"})
	public void navigate_Pagination(String keyword_for_many) throws IOException, InterruptedException {
		
		Thread.sleep(2000);
		//Type keyword in search textbox
		type("search_XPATH", keyword_for_many);
		type("search_XPATH", Keys.ENTER);

	//	Thread.sleep(2000);
		
		//retrive the title before clicking on the first result
		String joureny_title_detailed_before = driver.findElement(By.xpath(OR.getProperty("first_result_desc_XPATH"))).getText();

		//Click on last page
		click("last_page_XPATH");
		
		//Click on first pate
		click("first_page_XPATH");
		
		
		
		//retrive the title before clicking on the first result
		String joureny_title_detailed_after = driver.findElement(By.xpath(OR.getProperty("first_result_desc_XPATH"))).getText();
		

		//Verify same results remains after navigating through pages
		verifyEquals(joureny_title_detailed_before, joureny_title_detailed_after);
		
		//click on the first card
		click("first_result1_XPATH");
		
		swithToNewTab();


		//retrive the title
		String first_desc=driver.findElement(By.xpath(OR.getProperty("opened_page_title_XPATH"))).getText();
		
		
		
		//verify both titles match 
		verifyEquals(joureny_title_detailed_before, first_desc);
		
	}
	
}
