package com.hixol.pages;

import org.openqa.selenium.By;

import com.hixol.Base.Page;

public class HomePage extends Page {

	public void goToSupport(){
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/a[2]")).click();

	}

	public void goToSignUp(){
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/a[5]")).click();
	}

	public LoginPage goToLogin(){

		click("loginbtn_linktext");
		return new LoginPage();
	}

	public void goToCustomers(){
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/a[1]")).click();

	}
}
