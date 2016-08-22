package com.decexp.tests;

//import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;







//import com.decexp.beans.MSCRMLogin;
import com.decexp.beans.NewMembershipWorkflow;
import com.decexp.fixtures.DataProviderCreateNewMember;
import com.decexp.pages.CRMLoginPage;
//import com.decexp.pages.CRMLoginPage;
//import com.decexp.pages.ContactsPage;
import com.decexp.pages.CreateNewMemberPage;
import com.decexp.pages.HomePage;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;
import com.decexp.utils.Utils;

import org.testng.Assert;

public class MemberTest extends BaseTest {
	private CRMLoginPage loginPage = null;
	private HomePage homePage = null;
	private CreateNewMemberPage newMemberPage = null;
	private Utils utils = null;
	@BeforeClass
	public void beforeClass() {
		loginPage = new CRMLoginPage(driver);
		homePage = new HomePage(driver);
		newMemberPage = new CreateNewMemberPage(driver);
		utils = new Utils(driver);
	}
	@Test(dataProvider="CreateNewMember", dataProviderClass=DataProviderCreateNewMember.class)
	public void createNewMember(NewMembershipWorkflow newMember) throws AutomationException{
		
		System.out.println("Entered url-->");
		loginPage.loginAs("http://172.20.26.106/ASGBI/main.aspx#55488457");
		Assert.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");

		//homePage.clickNewButton();
		utils.clickonNewButton();
		Assert.assertTrue(newMemberPage.verifyPageTitle(), "New Membership page is failed to load");
		
		newMemberPage.switchToFrame();
		newMemberPage.setForeName(newMember.ForeName);
		newMemberPage.setSureName(newMember.SurName);
		newMemberPage.setInitials(newMember.Initials);
		newMemberPage.setTitle(newMember.Title);
		newMemberPage.switchToFrame();
		newMemberPage.setGender(newMember.Gender);
		newMemberPage.switchToFrame();
		newMemberPage.setPostCode(newMember.PostCode);
		newMemberPage.switchToFrame();
		newMemberPage.setEmail(newMember.Email);
		newMemberPage.switchToFrame();
		newMemberPage.setCountry(newMember.Country);
		newMemberPage.switchToFrame();
		newMemberPage.setTermsandCondition(newMember.TermsAndCondition);
		newMemberPage.clickSave();
		String contactID = newMemberPage.getContactID();
		
		utils.clickonContacts();
		
		System.out.println("Hi i am in contacts");
		//Assert.assertTrue(newMemberPage.verifyNewMemberCreated(contactID));
	}

}
