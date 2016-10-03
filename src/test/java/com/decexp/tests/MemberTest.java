package com.decexp.tests;

/*
 * This test class is used to create prospect
 * or Member record
 */

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.decexp.beans.AddMemberDetails;
import com.decexp.fixtures.DataProviderAddMember;
import com.decexp.pages.AddMemberPage;
import com.decexp.pages.CRMLoginPage;
import com.decexp.pages.ContactsPage;
import com.decexp.pages.HomePage;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;
import com.decexp.utils.ExpressConstant;
import com.decexp.utils.Helper;
import com.decexp.utils.Utils;

public class MemberTest extends BaseTest {
	private static final Logger logger = Logger.getLogger("MemberTest.class");
	private CRMLoginPage loginPage = null;
	private HomePage homePage = null;
	public AddMemberPage addMemberPage = null;
	private Utils utils = null;
	private ContactsPage contactsPage = null;
	
	@BeforeClass
	public void beforeClass() {
		loginPage = new CRMLoginPage(driver);
		homePage = new HomePage(driver);
		addMemberPage = new AddMemberPage(driver);
		contactsPage = new ContactsPage(driver);
		utils = new Utils(driver);
}
	
	@Test(dataProvider="AddProspect", dataProviderClass=DataProviderAddMember.class, description="This test case is used to create prospect", priority=1)
	public void createNewProspect(AddMemberDetails newMember) throws AutomationException{
		if(!CRMLoginPage.isAlreadyLoggedIn){
			loginPage.loginAs("http://172.20.26.106/ASGBI/main.aspx#55488457");
			Assert.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");
		}
				
		utils.clickonAddMemberButton();
		Assert.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");
		
		addMemberPage.createContact(newMember,addMemberPage);
		utils.clickonContacts();
		boolean iscontactCreated = contactsPage.searchContactNo(ExpressConstant.contactID);
		Assert.assertTrue(iscontactCreated, "Contact Number is not found");	
		
		}

	@Test(dataProvider="AddMember", dataProviderClass=DataProviderAddMember.class, description="This test case is used to create Member", priority=2)
	public void createNewMember(AddMemberDetails newMember) throws AutomationException{
		if(!CRMLoginPage.isAlreadyLoggedIn){
			loginPage.loginAs("http://172.20.26.106/ASGBI/main.aspx#55488457");
			Assert.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");
		}
		utils.clickonMemberdhipWorkflowButton();
		
		utils.clickonAddMemberButton();
		Assert.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");
		
		addMemberPage.createContact(newMember,addMemberPage);
		utils.clickonContacts();
		boolean iscontactCreated = contactsPage.searchContactNo(ExpressConstant.contactID);
		Assert.assertTrue(iscontactCreated, "Contact Number is not found");	
		
		}
	
/*	@AfterTest
	public void tearDown(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE){
			String screenshot_path = Helper.captureScreenshot(driver,result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
				System.out.println("Test case is passed");
			}
		}
*/		//driver.quit();
}


