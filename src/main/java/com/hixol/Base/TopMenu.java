package com.hixol.Base;

import org.openqa.selenium.By;

import com.hixol.crm.pages.Account.CreatAccountPage;

public class TopMenu {

	public void gotoHome() {

	}

	public void gotoLeads() {

	}

	public void gotoContacts() {

	}

	public CreatAccountPage gotoAccounts() {
		Page.click("accounts_xpath");
		return new CreatAccountPage();
	}

	public void gotoDeals() {

	}

	public void gotoActivities() {

	}

	public void gotoReports() {

	}

	public void gotoSignout() {

	}

}
