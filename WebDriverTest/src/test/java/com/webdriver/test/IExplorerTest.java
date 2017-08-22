package com.webdriver.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class IExplorerTest {
	
public WebDriver driver;		
	
  
@BeforeTest
  public void SetUp() {
	
	//ChromeOptions o = new ChromeOptions();
	//o.addArguments("disable-extensions");
	//o.addArguments("--start-maximized");
	//o.setExperimentalOption("useAutomationExtension", false);
	System.setProperty("webdriver.ie.driver",".\\Drivers\\IEDriverServer.exe");
	
	DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
    capabilitiesIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    driver = new InternetExplorerDriver(capabilitiesIE);
		
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	  
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
