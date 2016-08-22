package com.decexp.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.decexp.tests.CRMLoginTest;

public class BaseTest {
	private static final Logger logger = Logger.getLogger("BaseTest.class");
	public static WebDriver driver;
			
	@Parameters("browserName")
	@BeforeSuite(alwaysRun=true)
		public static void browserSetup(@Optional String browserType)
		throws AutomationException{
		try{
			if(browserType!=null && !browserType.isEmpty()){
				ApplicationClass.setDriverForApplication(browserType);
				try{
					driver = ApplicationClass.getDriverForApplication();
				}catch(Exception e){
					throw new AutomationException(
							"browser driver is containing null value");
				}
				
				}
		}catch(Exception e){
			throw new AutomationException(e.getMessage());
		}
	}
	
	@AfterTest()
	public void closeCurrentDriver() throws AutomationException{
		try{
			if(((RemoteWebDriver)driver).getSessionId()!=null){
				logger.info("AfterTest---> Quitting the driver");
				driver.quit();
				}	
			}catch(Exception e){
				throw new AutomationException("Session ID is null");
		}
	}

}
