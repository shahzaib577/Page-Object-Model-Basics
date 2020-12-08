package com.hixol.crm.pages.Account;

import com.hixol.Base.Page;

public class CreatAccountPage extends Page {

	public AddAccountPage gotoCreatAccount() {
		click("CreatAccountbtn_css");
		return  new AddAccountPage();

	}

	public void gotoImportAccount() {

	}

}
