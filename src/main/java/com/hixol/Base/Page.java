package com.hixol.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.hixol.utilites.ExcelReader;
import com.hixol.utilites.ExtentManager;

import com.hixol.utilites.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page {

	public static WebDriver driver;

	/* properties */
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;

	/* Logs */

	public static Logger log = Logger.getLogger("devpinoyLogger");

	/* excel reader */
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\hixol\\excel\\TestData.xlsx");

	// explicit wait
	public static WebDriverWait wait;

	// extenet reports
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	// jenkins browser setup
	public static String browser;

	public static TopMenu menu;

	public Page() {

		if (driver == null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\hixol\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file is loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\hixol\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.debug("or file is loaded!!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// jenkins browser enciorment
			if (System.getenv("browser") != null && !System.getenv().isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);

			// browser configuration
			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\hixol\\executable\\chromedriver.exe");
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			// for removing yellow line
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver(options);

			driver.get(config.getProperty("websiteurl"));
			log.debug("navigating to " + config.getProperty("websiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait((Integer.parseInt(config.getProperty("implicit.wait"))),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 8);
			menu = new TopMenu();

		}
	}
	
	//for quiting the browser
	
	public static void quit() {
		
		driver.quit();
	}

	// Keywords fior click and send values
	public static void click(String locator) {

		if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		} else if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(or.getProperty(locator))).click();
		}else if (locator.endsWith("_linktext")) {
			driver.findElement(By.linkText(or.getProperty(locator))).click();
		}
		log.debug("Clicking on the element"+locator);
		test.log(LogStatus.INFO, "Clicking on the" + locator);

	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
		}
		log.debug("Clicking on the element"+locator+"and entering the values"+value);
		test.log(LogStatus.INFO, " Typing in" + locator, "and the value is " + value);

	}

	// dropdown keyword
	static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_xpath")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.endsWith("_css")) {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.endsWith("_id")) {
			dropdown = driver.findElement(By.id(or.getProperty(locator)));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.debug("Clicking on the element"+locator+"and selecting the value from list"+value);
		test.log(LogStatus.INFO, " Selecting from dropdown" + locator, "value is " + value);

	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			return false;

		}

	}

	public static void VerifyEquals(String expected, String actual) throws IOException {
		try {

			Assert.assertEquals(expected, actual);
		} catch (Throwable t) {
			// capturing a screenshot
			Utilities.captureScreenshot();
			// Attaching the screenshot with ReportNg
			Reporter.log("<br>" + "Verification failure:" + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src="
					+ Utilities.screenshotName + " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extenet Reeports
			test.log(LogStatus.FAIL, "Verification failure with expection:" + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
		}

	}
}
