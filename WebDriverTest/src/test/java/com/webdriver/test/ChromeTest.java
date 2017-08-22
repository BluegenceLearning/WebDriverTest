package com.webdriver.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

public class ChromeTest {
	
public WebDriver driver;		
	
  
@BeforeTest
  public void SetUp() {
	
	ChromeOptions opt = new ChromeOptions();
	opt.addArguments("disable-extensions");
	opt.addArguments("--start-maximized");
	opt.setExperimentalOption("useAutomationExtension", false);
	System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver(opt);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  }
	
  @Test
  public void crossBrowserTest() {
	  
	driver.get("https://www.google.com/");
		 
	String title = driver.getTitle();
		 
	System.out.println("Page title is ... " + title);
  }


  @AfterTest
  public void TearDown() {
	  
	  driver.close(); 
	  driver.quit(); 
	  
  }

}
