package com.decexp.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.decexp.beans.AddContactDetails;
import com.decexp.fixtures.AddContactFixture;
import com.decexp.pages.AddContactPOM;
import com.decexp.pages.CRMLoginPage;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;
import com.decexp.utils.Utils;

public class ContactsTest extends BaseTest {
	private Utils utils = null; 
	public AddContactPOM addContactPOM = null;
	public CRMLoginPage loginPage = null;
	
	@BeforeTest
	public void beforeTest(){
		utils = new Utils(driver);
		addContactPOM = new AddContactPOM(driver);
		loginPage = new CRMLoginPage(driver);
	}
	
	@Test(dataProvider="AddContact", dataProviderClass=AddContactFixture.class, description="This method is used to make contact from Contacts screen")
	public void addContact(AddContactDetails addContactDetails) throws AutomationException{
		if(!CRMLoginPage.isAlreadyLoggedIn){
			loginPage.loginAs("http://172.20.26.106/ASGBI/main.aspx#55488457");
			Assert.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");
		}
		utils.clickonContacts();
		utils.clickonAddContactButton();
		Assert.assertTrue(addContactPOM.verifyPageTitle(),"Add Contact page is failed to load");
		addContactPOM.createContact(addContactDetails, addContactPOM);
		
	}
}
