package com.decexp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.decexp.utils.AutomationException;
import com.decexp.utils.Helper;

public class CRMLoginPage {
	private static WebDriver driver;
	public static boolean isAlreadyLoggedIn=false;
	private final Logger logger = Logger.getLogger(CRMLoginPage.class);
	private static String homePageTitle = "Membership Workflow Active Membership Workflow - Microsoft Dynamics CRM"; 
		
	public CRMLoginPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void loginAs(String loginURL) throws AutomationException{
		try{
			if(!isAlreadyLoggedIn)
			Runtime.getRuntime().exec("D:/Projects/DecExpAutomation/src/test/resources/HandleAuthenticationWindow.exe");
			driver.get(loginURL);
			isAlreadyLoggedIn = true;
			
			}catch(Exception e){
			throw new AutomationException(e.getMessage());
		}
	}
		
		public boolean verifyHomePage(){
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.titleContains(homePageTitle));
				return driver.getTitle().equalsIgnoreCase(homePageTitle);
			}

}
