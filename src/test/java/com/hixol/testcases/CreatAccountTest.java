package com.hixol.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.hixol.Base.Page;
import com.hixol.crm.pages.Account.AddAccountPage;
import com.hixol.crm.pages.Account.CreatAccountPage;
import com.hixol.pages.ZohoAppPage;
import com.hixol.utilites.Utilities;



public class CreatAccountTest {

	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void creatAccountTest(Hashtable<String, String> data) {
		ZohoAppPage zap=new ZohoAppPage();
		zap.gotoCRM();
		CreatAccountPage cap = Page.menu.gotoAccounts();
		AddAccountPage aap=cap.gotoCreatAccount();
		aap.AddAccount(data.get("accountname"));
		
	}
}
