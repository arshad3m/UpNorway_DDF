package com.upnorway.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.upnorway.utilities.TestUtil;
import com.upnorway.base.TestBase;

public class General extends TestBase{
	
	
	@Test(enabled = true, dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void routing(Hashtable<String, String> data) throws InterruptedException, IOException {
		
		if (!data.get("runmode").equals("Y")) {

			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		navigate(data.get("url"));
		
		verifyURL();
	}
	
	
	public void email_solution() {
		
		
	}

}
