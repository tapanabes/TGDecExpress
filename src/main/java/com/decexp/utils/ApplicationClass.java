package com.decexp.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ApplicationClass {
	private static WebDriver driverForApplication;
	public static String browserName;
	
	public static void setDriverForApplication(String browserType){
		browserName=browserType;
	}
	
	public static WebDriver getDriverForApplication() throws AutomationException{
		if(browserName.equalsIgnoreCase("firefox"))
			return methodForFirefox();
		else if(browserName.equalsIgnoreCase("chrome"))
			return methodForChrome();
		else if(browserName.equalsIgnoreCase("ie"))
			return methodForIE();
		else
			return methodForFirefox();
	}
	
	public static WebDriver methodForFirefox(){
		driverForApplication = new FirefoxDriver();
		driverForApplication.manage().window().maximize();
		driverForApplication.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driverForApplication;
		
	}
	
	public static WebDriver methodForChrome() throws AutomationException{
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		/*chromeCapabilities.setCapability("webdriver.chrome.driver", System.getProperty("user.dir")+
				"//src//main//resources");*/
		chromeCapabilities.setCapability("webdriver.chrome.driver", 
									ConfigurationDataProvider.getReader().getChromeDriver());
		driverForApplication = new ChromeDriver(chromeCapabilities);
		driverForApplication.manage().window().maximize();
		driverForApplication.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driverForApplication;
	}
	
	public static WebDriver methodForIE() throws AutomationException{
		/*System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")
						+"//src//main//resources");*/
		System.setProperty("webdriver.ie.driver", 
							ConfigurationDataProvider.getReader().getIEDriver());
		DesiredCapabilities ieCapability = DesiredCapabilities.internetExplorer();
		ieCapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driverForApplication = new InternetExplorerDriver(ieCapability);
		driverForApplication.manage().window().maximize();
		driverForApplication.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driverForApplication;
	}

}
