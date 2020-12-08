package com.hixol.testcases;



import java.util.Hashtable;

import org.testng.annotations.Test;

import com.hixol.pages.HomePage;
import com.hixol.pages.LoginPage;


import com.hixol.utilites.Utilities;


public class LoginTest extends BaseTest {
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	
	public void loginTest(Hashtable<String, String> data) {
		
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		lp.doLogin(data.get("username"), data.get("password"));
	}

}
