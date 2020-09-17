package selenium;


import java.util.regex.Pattern;

import javax.imageio.spi.RegisterableService;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import cmn.EventCatering_Functions;
//import functions.EventCateringFunctions;
import event_management.util.CmnUtil;


@RunWith(JUnitParamsRunner.class)
public class ManagerTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private EventCatering_Functions function;
  public static Properties prop;
  
  static {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("./login/Login.properties"));
			prop.load(new FileInputStream("./sharedUIMap/SharedUIMap.properties"));
			prop.load(new FileInputStream("./configuration/ECS_Configuration.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    function = new EventCatering_Functions();
  }
  
  @Test
  public void testvalidateMainAppPage()  {
	  driver.get(prop.getProperty("URL_BASE") + prop.getProperty("URL_APP"));
	  function.takeScreenshot(driver, "MainAppPageValidate"+1);
	  
	  assertTrue(isElementPresent(By.linkText(prop.getProperty("Txt_EventCateringSys_Link"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Register_link"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Login_Link"))));
	  
  }
  
  @Test
  public void testvalidateRegisterPage()  {
	  
	  driver.get(prop.getProperty("URL_BASE") + prop.getProperty("URL_APP"));
	  driver.findElement(By.xpath(prop.getProperty("Register_link"))).click();
	  function.takeScreenshot(driver, "ManagerRegisterPageValidate"+1);
	  
	  assertTrue(isElementPresent(By.linkText(prop.getProperty("Txt_EventCateringSys_Link"))));
	  
	  //Labels
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_UtaId_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_FirstName_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_LastName_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_UserName_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_Password_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_Phone_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_Email_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_StreetNo_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_StreetName_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_City_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_State_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_Zipcode_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_Role_Xpath"))));
	  assertTrue(isElementPresent(By.xpath(prop.getProperty("Lbl_Mandatory_Xpath"))));
	  
	  //Textboxes
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_UtaId"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_FirstName"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_LastName"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_Username"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_Password"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_Phone"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_Email"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_StreetNo"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_StreetName"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_City"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_State"))));
	  assertTrue(isElementPresent(By.name(prop.getProperty("Txt_Zipcode"))));
	  
	  //Dropdown
	  assertTrue(isElementPresent(By.id(prop.getProperty("Txt_Role"))));
	  
	  //Button
	  assertTrue(isElementPresent(By.cssSelector(prop.getProperty("BTN_REGISTER_CSS"))));
  	  
  }
  
  @Test
  @FileParameters("resources/selenium/uta_id_selenium.csv")
  public void testUtaId(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
	  
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterUtaId"+testCaseNo);
	  
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_UtaId_Xpath"))).getText());
  }

  @Test
  @FileParameters("resources/selenium/first_name_selenium.csv")
  public void testFirstName(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	 
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterFirstName"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_FirstName_Xpath"))).getText());
    
  }
  

  @Test
  @FileParameters("resources/selenium/last_name_selenium.csv")
  public void testLastName(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterLastName"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_LastName_Xpath"))).getText());
    
  }
  
 
  @Test
  @FileParameters("resources/selenium/uname_selenium.csv")
  public void testUsername(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterUsername"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_Username_Xpath"))).getText());
    
  }
  
  
  @Test
  @FileParameters("resources/selenium/password_selenium.csv")
  public void testPassword(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterPassword"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_Password_Xpath"))).getText());
  }
  
  
  @Test
  @FileParameters("resources/selenium/phone_number_selenium.csv")
  public void testPhone(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterPhone"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_Phone_Xpath"))).getText());
  }
  
  
 
  @Test
  @FileParameters("resources/selenium/email_selenium.csv")
  public void testEmail(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterEmail"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_Email_Xpath"))).getText());
  }
  
  
  
  @Test
  @FileParameters("resources/selenium/street_selenium.csv")
  public void testStreetNo(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterStreetNo"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_StreetNo_XPath"))).getText());
  }
 
  @Test
  @FileParameters("resources/selenium/st_name_selenium.csv")
  public void testStreetName(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterStreetName"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_StreetName_Xpath"))).getText());
  }
  
  
  @Test
  @FileParameters("resources/selenium/city_selenium.csv")
  public void testCity(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterCity"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_City_Xpath"))).getText());
  }
  
  @Test
  @FileParameters("resources/selenium/state_selenium.csv")
  public void testState(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterState"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_State_Xpath"))).getText());
  }
  
  @Test
  @FileParameters("resources/selenium/zip_code_selenium.csv")
  public void testZipCode(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterZipcode"+testCaseNo);
	  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_Zipcode_Xpath"))).getText());
  }
  
  @Test
  @FileParameters("resources/selenium/role_selenium.csv")
  public void testRole(String testCaseNo, String utaId, String username, String password, String firstName, String lastName,
			String email, String phone, String zipCode, String streetName, String streetNumber, String state,
			String role, String city, String error) throws Exception {
    
	  function.register(driver, utaId, username, password, firstName, lastName, email, phone, zipCode, streetName, streetNumber, 
			  state, role, city, error);
	  function.takeScreenshot(driver, "ManagerRegisterRole"+testCaseNo);
	  if (!CmnUtil.isNullorEmpty(error))
		  assertEquals(error, driver.findElement(By.xpath(prop.getProperty("Msg_Role_Xpath"))).getText());
	  else
	  {
		  assertTrue(isElementPresent(By.linkText(prop.getProperty("Txt_EventCateringSys_Link"))));
		  assertTrue(isElementPresent(By.xpath(prop.getProperty("Register_link"))));
		  assertTrue(isElementPresent(By.xpath(prop.getProperty("Login_Link"))));
	  }
  }
 
 
  @Test
  @FileParameters("resources/selenium/manager_login_selenium.csv")
  public void testManagerLogin(String testCaseNo, String username, String password, String UnError, String PassError) throws Exception {
    
    function.login(driver, username, password);
    function.takeScreenshot(driver, "ManagerLogin"+testCaseNo);
    if (!CmnUtil.isNullorEmpty(UnError) || !CmnUtil.isNullorEmpty(PassError)) {
    assertEquals(UnError, driver.findElement(By.xpath(prop.getProperty("Msg_Uname_Xpath"))).getText());
    assertEquals(PassError, driver.findElement(By.xpath(prop.getProperty("Msg_Pass_Xpath"))).getText());
    }
    else {
    	
    	assertTrue(isElementPresent(By.xpath(prop.getProperty("Txt_ReservedEvents_Link"))));
    	assertTrue(isElementPresent(By.linkText(prop.getProperty("Txt_Logout_Link"))));
    	assertTrue(isElementPresent(By.linkText(prop.getProperty("Txt_EventCateringSys_Link"))));
    	function.logout(driver);
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

