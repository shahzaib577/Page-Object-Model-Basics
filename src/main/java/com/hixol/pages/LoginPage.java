package com.hixol.pages;

import com.hixol.Base.Page;

public class LoginPage extends Page {

	public ZohoAppPage doLogin(String username, String password) {

		type("email_css", username);
		click("nxtbtn1_css");
		type("password_css", password);
		click("nxtbtrn2_css");
		return  new ZohoAppPage();

	}

}
