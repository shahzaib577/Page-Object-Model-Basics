package com.hixol.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.hixol.Base.Page;

import com.hixol.utilites.MonitoringMail;
import com.hixol.utilites.TestConfig;

import com.hixol.utilites.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	public String messagebody;

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// this will open the image in same tab of Reportng
		// Reporter.log("<a href=\"C:\\Users\\shahz\\Desktop\\me.jpg\">screenshot</a>");
		// and for opening the pic in new tab Reportng
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// for extent reports
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + "Failed");
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

		// for Reportng
		Reporter.log("Click here to see screenshot");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		// to add a small pic withthe link in reportng
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
				+ " height=200 width=200></img></a>");
		rep.endTest(test);
		rep.flush();

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase() + "Skipped the test case as the run mode is NO");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		test = rep.startTest(arg0.getName().toUpperCase());

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

		test.log(LogStatus.PASS, arg0.getName().toUpperCase() + "Pass");
		rep.endTest(test);
		rep.flush();

	}

	public void onStart(ISuite arg0) {

	}

	public void onFinish(ISuite arg0) {

		MonitoringMail mail = new MonitoringMail();

		try {
			messagebody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/PageObjectModelBasics/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messagebody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
