package cmn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EventCatering_Functions {

	public static WebDriver driver;
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

	public void takeScreenshot(WebDriver driver, String screenshotname) {
		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/" + screenshotname + ".png"));
		} catch (IOException e) {
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	public void login(WebDriver driver, String username, String password) {
		driver.get(prop.getProperty("URL_BASE") + prop.getProperty("URL_LOGIN"));
		driver.findElement(By.name(prop.getProperty("Txt_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Username"))).sendKeys(username);
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys(password);
		driver.findElement(By.cssSelector(prop.getProperty("BTN_LOGIN_CSS"))).click();
	}

	public void register(WebDriver driver, String utaId, String username, String password, String firstName,
			String lastName, String email, String phone, String zipCode, String streetName, String streetNumber,
			String state, String role, String city, String error) {

		driver.get(prop.getProperty("URL_BASE") + prop.getProperty("URL_APP"));
		driver.findElement(By.xpath(prop.getProperty("Register_link"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_UtaId"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UtaId"))).sendKeys(utaId);
		driver.findElement(By.name(prop.getProperty("Txt_FirstName"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_FirstName"))).sendKeys(firstName);
		driver.findElement(By.name(prop.getProperty("Txt_LastName"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_LastName"))).sendKeys(lastName);
		driver.findElement(By.name(prop.getProperty("Txt_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Username"))).sendKeys(username);
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Password"))).sendKeys(password);
		driver.findElement(By.name(prop.getProperty("Txt_Phone"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Phone"))).sendKeys(phone);
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Email"))).sendKeys(email);
		driver.findElement(By.name(prop.getProperty("Txt_StreetNo"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_StreetNo"))).sendKeys(streetNumber);
		driver.findElement(By.name(prop.getProperty("Txt_StreetName"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_StreetName"))).sendKeys(streetName);
		driver.findElement(By.name(prop.getProperty("Txt_City"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_City"))).sendKeys(city);
		driver.findElement(By.name(prop.getProperty("Txt_State"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_State"))).sendKeys(state);
		driver.findElement(By.name(prop.getProperty("Txt_Zipcode"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Zipcode"))).sendKeys(zipCode);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Role")))).selectByVisibleText(role);
		driver.findElement(By.cssSelector(prop.getProperty("BTN_REGISTER_CSS"))).click();

	}
	
	public void logout(WebDriver driver) {
		driver.findElement(By.linkText(prop.getProperty("Txt_Logout_Link"))).click();
	}
	
	public void eventsummary(WebDriver driver,String id, String username, String date, String startTime, String duration,
			String hallName, String estAttendees, String name, String foodType, String meal, String mealFormality,
			String drinkType, String entertainmentItems, String eventStatus, String estCost, String error) throws Exception {
	  
	    driver.findElement(By.cssSelector("span")).click();
	    driver.findElement(By.id(prop.getProperty("Manager_Date_Picker"))).clear();
	    driver.findElement(By.id(prop.getProperty("Manager_Date_Picker"))).sendKeys("2020-04-28");
	    driver.findElement(By.name(prop.getProperty("Manager_Start_Time"))).clear();
	    driver.findElement(By.name(prop.getProperty("Manager_Start_Time"))).sendKeys("12:00");
	    driver.findElement(By.cssSelector(prop.getProperty("Manager_Submit"))).click();
	    driver.findElement(By.linkText(prop.getProperty("View_Event"))).click();
	    driver.findElement(By.id(prop.getProperty("Manager_Date_Picker"))).clear();
	    driver.findElement(By.id(prop.getProperty("Manager_Date_Picker"))).sendKeys(date);
	    driver.findElement(By.name(prop.getProperty("Manager_Start_Time"))).clear();
	    driver.findElement(By.name(prop.getProperty("Manager_Start_Time"))).sendKeys(startTime);
	    driver.findElement(By.name(prop.getProperty("Est_Attendees"))).clear();
	    driver.findElement(By.name(prop.getProperty("Est_Attendees"))).sendKeys(estAttendees);
	    driver.findElement(By.name(prop.getProperty("Event_Name"))).clear();
	    driver.findElement(By.name(prop.getProperty("Event_Name"))).sendKeys(name);
	    driver.findElement(By.cssSelector(prop.getProperty("Manager_Submit"))).click();
  
    
    
	}
	public void managerdatepicker(WebDriver driver,String date,String time ) throws Exception {
		  
		 
	    driver.findElement(By.id(prop.getProperty("Manager_Date_Picker"))).clear();
	    driver.findElement(By.id(prop.getProperty("Manager_Date_Picker"))).sendKeys(date);
	    driver.findElement(By.name(prop.getProperty("Manager_Start_Time"))).clear();
	    driver.findElement(By.name(prop.getProperty("Manager_Start_Time"))).sendKeys(time);
	    driver.findElement(By.cssSelector(prop.getProperty("Manager_Submit"))).click();
	    
  
    
    
	}

}
