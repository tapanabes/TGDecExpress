package com.decexp.tests;

/*
 * This test case is used to check order is created or not 
 */
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.decexp.beans.AddOrderDetails;
import com.decexp.fixtures.FixtureAddOrder;
import com.decexp.pages.ContactsPage;
import com.decexp.pages.OrdersPOM;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;
import com.decexp.utils.ExpressConstant;
import com.decexp.utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrdersTest extends BaseTest {
	private Utils utils = null;
	private ContactsPage contactsPage = null;
	private OrdersPOM ordersPage = null;
	private ExtentReports extentReport = null;
	private ExtentTest extentLogger = null;
	private SoftAssert softAssertion = null;
	private static String orderStatusExpected="Confirmed";
	private static String lapseorderStatusExpected="Cancelled";
	
	@BeforeClass
	public void beforeClass(){
		utils = new Utils(driver);
		contactsPage = new ContactsPage(driver);
		ordersPage = new OrdersPOM(driver);
	}
	
	@Test(priority=0)
	public void verifyOrderCreation() throws AutomationException{
		try{
		//String contactID = System.getProperty("contactID");
		String contactID = ExpressConstant.contactID;
		extentReport = new ExtentReports(System.getProperty("user.dir")+"\\Report\\Orders.html");
		extentLogger = extentReport.startTest("Verify order is created on Orders screen");
		
		System.out.println("Search the Contact ID from Global Search");
		extentLogger.log(LogStatus.INFO, "Search the Contact ID from Global Search");
		//utils.clickonContacts();
		utils.searchRecord(contactID);
		
		System.out.println("Click on Orders link");
		extentLogger.log(LogStatus.INFO, "Click on Orders link");
		utils.clickonOrdersLink();
		
/*		System.out.println("Search the record on Orders screen");
		extentLogger.log(LogStatus.INFO, "Search the Record on Orders screen");
//		ordersPage.searchOrder();
*/		
		}catch(Exception e){
			throw new AutomationException("User is not able to search and create order");
		}
	}
	
	@Test(dataProvider="AddOrder", dataProviderClass=FixtureAddOrder.class, description="This test method is used to add new order", priority=1)
	public void createOrder(AddOrderDetails addOrder) throws AutomationException{
		try{
			System.out.println("Create the Order");
			extentLogger.log(LogStatus.INFO, "Create the Order using order screen");
			utils.clickonAddOrder();
			Thread.sleep(10000);
			ordersPage.createOrder(addOrder, ordersPage);
			Assert.assertTrue(ordersPage.verifyOrderStatus(orderStatusExpected), "Order status is not correct");
		}catch(Exception e){
			throw new AutomationException("Order is not created successfully");
		}
	}
	
	@Test(description="This test method is used to lapse the order", priority=2)
	public void lapseOrder() throws AutomationException{
		try{
			System.out.println("Lapse the Order");
			extentLogger.log(LogStatus.INFO, "Lapse the Order using order screen");
			ordersPage.lapseConfirmedOrder();
			Assert.assertTrue(ordersPage.verifyOrderStatus(lapseorderStatusExpected), "Lapse Order status is not correct");
			Thread.sleep(5000);
			
			}catch(Exception e){
			throw new AutomationException("Order is not lapsed successfully");
		}
	}
}
