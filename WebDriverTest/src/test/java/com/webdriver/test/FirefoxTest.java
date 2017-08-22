package com.webdriver.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class FirefoxTest {
	
public WebDriver driver;		
	
  
@BeforeTest
  public void SetUp() {
	  
	DesiredCapabilities dc = DesiredCapabilities.firefox();
	dc.setCapability("marionette", true);
	System.setProperty("webdriver.gecko.driver",".\\Drivers\\geckodriver.exe");
	driver = new FirefoxDriver(dc);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  }
	
  @Test
  public void crossBrowserTest() {
	  
	driver.get("http://www.google.com");
		 
	String title = driver.getTitle();
		 
	System.out.println("Page title is ... " + title);
  }


  @AfterTest
  public void TearDown() {
	  
	  //driver.close(); 
	  driver.quit(); 
	  
  }

}
