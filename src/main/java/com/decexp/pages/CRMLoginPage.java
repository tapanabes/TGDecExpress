package com.decexp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.decexp.utils.AutomationException;

public class CRMLoginPage {
	private static WebDriver driver;
	private final Logger logger = Logger.getLogger(CRMLoginPage.class);
	private static String homePageTitle = "Membership Workflow Active Membership Workflow - Microsoft Dynamics CRM"; 
		
	public CRMLoginPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void loginAs(String loginURL) throws AutomationException{
		try{
			Runtime.getRuntime().exec("D:\\Projects\\DecExpAutomation\\src\\main\\resources\\HandleAuthentication.exe");
			
			driver.get(loginURL);
			
			logger.info(loginURL +" is loading and wait for 5 seconds");
			
			Thread.sleep(5000);
		}catch(Exception e){
			throw new AutomationException(e.getMessage());
		}
		
		}
		
		public boolean verifyHomePage(){
				return driver.getTitle().equalsIgnoreCase(homePageTitle);
			}

}
