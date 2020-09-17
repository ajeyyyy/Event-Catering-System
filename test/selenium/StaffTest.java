package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cmn.EventCatering_Functions;
import event_management.util.CmnUtil;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;




@RunWith(JUnitParamsRunner.class)
public class StaffTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	EventCatering_Functions ecFunction;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ecFunction=new EventCatering_Functions();
	}

	@Test
	@FileParameters("resources/username_login_selenium.csv")
	public void testUsername(String testCaseNo, String username, String password, String unameError, String passError) throws Exception {
		ecFunction.login(driver, username, password);
		ecFunction.takeScreenshot(driver, "StaffLogin"+testCaseNo);
		if (!CmnUtil.isNullorEmpty(unameError) || !CmnUtil.isNullorEmpty(passError)) {
			assertEquals(unameError,
					driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[1]/td[3]")).getText());
			assertEquals(passError,
					driver.findElement(By.xpath("html/body/table/tbody/tr/td/form/table/tbody/tr[2]/td[3]")).getText());

		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
