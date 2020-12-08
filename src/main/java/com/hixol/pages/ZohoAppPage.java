package com.hixol.pages;

import org.openqa.selenium.By;
import com.hixol.Base.Page;
import com.hixol.crm.pages.CRMHomePage;

public class ZohoAppPage extends Page {

	public void gotoBooks() {

		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[1]/div/a/span")).click();

	}

	public void gotoCalendar() {

		driver.findElement(By.xpath("//body/div[3]/div[1]/div[1]/div[2]/div[1]/a[1]/span[1]")).click();

	}

	public void gotoCampaign() {

		driver.findElement(By.xpath("//body/div[3]/div[1]/div[1]/div[3]/div[1]/a[1]/span[1]")).click();

	}

	public CRMHomePage gotoCRM() {

		click("crmlink_xpath");
		return new CRMHomePage();

	}
}
