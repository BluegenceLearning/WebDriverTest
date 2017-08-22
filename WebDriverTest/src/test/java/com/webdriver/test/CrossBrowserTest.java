package com.webdriver.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class CrossBrowserTest {
	
  public WebDriver driver;	
	
  @BeforeTest
  @Parameters("browser")
  public void SetUp(String browser) throws Exception {
	  
  if(browser.equalsIgnoreCase("Firefox")){
	  
	  DesiredCapabilities dc = DesiredCapabilities.firefox();
	  dc.setCapability("marionette", true);
	  System.setProperty("webdriver.gecko.driver",".\\Drivers\\geckodriver.exe");
	  System.out.println("Starting Firefox browser");
	  driver = new FirefoxDriver(dc);  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //driver.manage().window().maximize();
	  	  
   }else if(browser.equalsIgnoreCase("IE")){
	  
	  System.setProperty("webdriver.ie.driver",".\\Drivers\\IEDriverServer.exe");
	  DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
	  capabilitiesIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	  System.out.println("Starting IE browser");
	  driver = new InternetExplorerDriver(capabilitiesIE);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
   }else if(browser.equalsIgnoreCase("Chrome")){
		  
	  ChromeOptions opt = new ChromeOptions();
	  opt.addArguments("disable-extensions");
	  opt.addArguments("--start-maximized");
	  opt.setExperimentalOption("useAutomationExtension", false);
	  System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
	  System.out.println("Starting Chrome browser");
	  driver = new ChromeDriver(opt);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  
   }else {
	   
	  throw new Exception("Browser is not correct");
   }
  
     
  
  	  }
	
  @Test
  public void crossbrowsertest() {
	  
	 driver.get("https://www.google.com");
	 
	 String title = driver.getTitle();
	 
	 System.out.println("Page title is ... " + title);
	  
  }


  @AfterTest
  public void afterTest() {
	  
	 driver.quit(); 
	  
  }

}
