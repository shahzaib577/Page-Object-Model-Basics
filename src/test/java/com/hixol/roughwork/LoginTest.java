package com.hixol.roughwork;

import com.hixol.Base.Page;
import com.hixol.crm.pages.Account.AddAccountPage;
import com.hixol.crm.pages.Account.CreatAccountPage;
import com.hixol.pages.HomePage;
import com.hixol.pages.LoginPage;
import com.hixol.pages.ZohoAppPage;

public class LoginTest {

	public static void main(String[] args) {

		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		ZohoAppPage zap = lp.doLogin("shahzaibveriks@yahoo.com", "Lucifer139");
		zap.gotoCRM();
		CreatAccountPage cap = Page.menu.gotoAccounts();
		AddAccountPage aap=cap.gotoCreatAccount();
		aap.AddAccount("Shahzaib virk");

	}

}
