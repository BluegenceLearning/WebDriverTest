package com.webdriver.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.Platform;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;



/**
 * @author AXV680
 * 
 * ******* Selenium Grid Setup ***********
 * 
 * Set the Driver path in Environment Variable e.g 'C:\Users\axv680\Desktop\My Docs\Selenium Automation\Selenium Web Drivers'
 * 
 * To start Hub by default at 4444
 * 
 * java -jar selenium-server-standalone-3.4.0.jar -role hub 
 * 
 * Check : http://localhost:4444/grid/console
 * 
 * To start Node with IE11 at 5555
 * 
 * java -jar selenium-server-standalone-3.4.0.jar -port 5555 -role node -hub http://localhost:4444/grid/register -browser "browserName=internet explorer,version=11,platform=WINDOWS,maxInstances=1"
 *
 * To start Node with Chrome at 5556
 *
 * java -jar selenium-server-standalone-3.4.0.jar -port 5556 -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=1"
 * 
 * To start Node with FF at 5557
 *
 * java -jar selenium-server-standalone-3.4.0.jar -port 5557 -role node -hub http://localhost:4444/grid/register -browser "browserName=firefox,version=ANY,platform=WINDOWS,maxInstances=1"
 *
 */

public class SeleniumGridTest {
	
  public RemoteWebDriver driver;	
  public DesiredCapabilities dc;
	
  @BeforeTest
  @Parameters("browser")
  public void SetUp(String browser) throws Exception {
	  
  if(browser.equalsIgnoreCase("Firefox")){
	  
	  dc = DesiredCapabilities.firefox();
	  dc.setCapability("marionette", true);
	  //dc.setBrowserName("firefox");
	  //dc.setPlatform(Platform.WINDOWS);
	  //System.setProperty("webdriver.gecko.driver",".\\Drivers\\geckodriver.exe");
	  System.out.println("Starting Firefox browser");
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //driver.manage().window().maximize();
	  	  
   }else if(browser.equalsIgnoreCase("IE")){
	   
	  dc = DesiredCapabilities.internetExplorer();
	  //dc.setCapability(CapabilityType.BROWSER_NAME, "IE");
	  dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
	  //dc.setBrowserName("internet explorer");
	  //dc.setPlatform(Platform.ANY);
	  //System.setProperty("webdriver.ie.driver",".\\Drivers\\IEDriverServer.exe");
	  System.out.println("Starting IE browser");
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
   }else if(browser.equalsIgnoreCase("Chrome")){
		  
	  ChromeOptions opt = new ChromeOptions();
	  opt.addArguments("disable-extensions");
	  opt.addArguments("--start-maximized");
	  opt.setExperimentalOption("useAutomationExtension", false);
	  dc = DesiredCapabilities.chrome();
	  //dc.setBrowserName("chrome");
	  //dc.setPlatform(Platform.WINDOWS);
	  dc.setCapability(ChromeOptions.CAPABILITY, opt);
	  //System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
	  System.out.println("Starting Chrome browser");
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
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
