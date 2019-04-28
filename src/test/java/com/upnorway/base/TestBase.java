package com.upnorway.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.upnorway.utilities.ExcelReader;
import com.upnorway.utilities.ExtentManager;
import com.upnorway.utilities.TestUtil;

/**
 * @author ArshadM
 *
 */
public class TestBase {

	/*
	 * WebDriver - done Properties - done Logs - log4j jar, .log,
	 * log4j.properties, Logger ExtentReports DB Excel Mail ReportNG,
	 * ExtentReports Jenkins
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static Actions action;
	

	/**
	 * @author ArshadM
	 * initiating file input streams
	 * initiating browser driver
	 * inititating configurations
	 * initiating reports
	 * intitiating logs
	 */
	
	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = config.getProperty("browser");
				
			}
			
			config.setProperty("browser", browser);
			
			
			

			if (config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 50);
			action = new Actions(driver);
		}

	}

	/**
	 * @author ArshadM
	 * Wrapper mehtod to click on an element
	 */
	
	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(OR.getProperty(locator)))).perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator)))).click();
			//driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	
	/**
	 * @author ArshadM
	 * Wrapper method to enter a value in textbox field
	 */
	public void type(String locator, String value) {

				
		if (locator.endsWith("_CSS")) {
			
			//wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			//action.moveToElement(driver.findElement(By.xpath(OR.getProperty(locator)))).sendKeys(value).build().perform();;;
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator)))).clear();
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(locator))))).clear();
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

	}
	
	
	
	/**
	 * @author ArshadM
	 * Method to enter a key
	 */
	
	public void type(String locator, Keys key) {

		
		if (locator.endsWith("_CSS")) {
			
			//wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(key);
		} else if (locator.endsWith("_XPATH")) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(locator)))));
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(key);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(key);
		}

		test.log(LogStatus.INFO, "Pressed the key: "+key+ " in : " + locator);

	}
	
	static WebElement dropdown;

	/**
	 * @author ArshadM
	 * Wrapper method for selecting a value from a drop down
	 */
	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	
	/**
	 * @author ArshadM
	 * Check if an element is present
	 */
	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	/**
	 * @author ArshadM
	 * Verify two strings
	 */
	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);
			test.log(LogStatus.INFO, "Verifying the expected text: " +  expected);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			
			

		}
		
		

	}
	
	
	/**
	 * @author ArshadM
	 * Close newly opened tab
	 */
	
	public void closeNewTab() {
		 String originalHandle = driver.getWindowHandle();

		    //Do something to open new tabs

		    for(String handle : driver.getWindowHandles()) {
		        if (!handle.equals(originalHandle)) {
		            driver.switchTo().window(handle);
		            driver.close();
		        }
		    }

		    driver.switchTo().window(originalHandle);
	}
	
	
	/**
	 * @author ArshadM
	 * Navigate to newly openened tab
	 */
	public void swithToNewTab() {
		 String originalHandle = driver.getWindowHandle();

		    //Do something to open new tabs

		    for(String handle : driver.getWindowHandles()) {
		        if (!handle.equals(originalHandle)) {
		            driver.switchTo().window(handle);
		            //driver.close();
		        }
		    }
		    
		    test.log(LogStatus.INFO, "Switching to new tab ");

		   // driver.switchTo().window(originalHandle);
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

		log.debug("test execution completed !!!");
	}
	
	
	@BeforeMethod
	public void beforeTest() throws InterruptedException {
		closeNewTab();

		driver.get("https://upnorway:upnorway123@qa.upnorway.net");
	}
	
	

	
	@AfterTest
	public void afterTest() {
		wait=new WebDriverWait(driver, 30);
	}
}
