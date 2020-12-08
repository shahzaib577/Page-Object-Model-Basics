package com.hixol.testcases;

import org.testng.annotations.AfterSuite;

import com.hixol.Base.Page;

public class BaseTest {
	
	@AfterSuite
	public void tearDown() {
		
		Page.quit();
	}

}
