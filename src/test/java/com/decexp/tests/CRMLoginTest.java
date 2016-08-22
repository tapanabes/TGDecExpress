package com.decexp.tests;

import com.decexp.pages.CRMLoginPage;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.decexp.beans.MSCRMLogin;
import com.decexp.fixtures.DataProviderLogin;
import com.decexp.tests.HomeTest;

public class CRMLoginTest extends BaseTest {
	
	@Test(dataProvider="CRMloginPageDetails", dataProviderClass=DataProviderLogin.class, priority=0)
	
	public void validLogin(MSCRMLogin mscrmlogin) throws AutomationException{
		
		CRMLoginPage loginPage = new CRMLoginPage(driver);
		try{
			if(((RemoteWebDriver)driver).getSessionId()!=null){
			
			loginPage.loginAs(mscrmlogin.loginURL);
			
			Assert.assertTrue(loginPage.verifyHomePage(), mscrmlogin.loginURL +" is failed to load");
			}
		}catch(Exception e){
			throw new AutomationException(e.getMessage());
		}
		
		
	} 
		
}
