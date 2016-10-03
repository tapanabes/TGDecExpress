package com.decexp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.decexp.beans.AddOrderDetails;
import com.decexp.utils.AutomationException;
//import com.decexp.utils.Utils;
import com.decexp.utils.Helper;

public class OrdersPOM{
	private static WebDriver driver;
	private static String paymentMethod = "Cheque";
	private static String paymentStatus="Confirmed";
	SoftAssert assertion = new SoftAssert();
	
	@FindBy(xpath="//table[@id='tblCustomerOrderList']/tbody/tr[1]/td[4]")
	WebElement paymentMethodText;
	
	@FindBy(xpath="//table[@id='tblCustomerOrderList']/tbody/tr[1]/td[8]")
	WebElement paymentMethodStatus;
	
	@FindBy(id="de_subscription_i'")
	WebElement OrderTypeDropdown;
	
	@FindBy(xpath="//input[@id='de_paymentmethodref_ledit']")
	WebElement paymentMethodTextField;
	
	@FindBy(id="Dialog_de_paymentmethodref_i_IMenu")
	WebElement paymentMethodModalDialogue;
	
	@FindBy(xpath="//ul[@id='de_paymentmethodref_i_IMenu']/li/a[2]/span/nobr/span[text()='Cheque']")
	WebElement paymentMethodSelectedText;
	
	@FindBy(xpath="//div[@id='dvAddNew']/a/img")
	WebElement newOrderLine;
	
	@FindBy(id="InlineDialog")
	WebElement addOrderLinePopup;
	
	@FindBy(xpath="//a[@id='InlineDialogCloseLink']")
	WebElement cancelPopupButton;
	
	@FindBy(id="selectProductType")
	WebElement productTypeDropdown;
	
	@FindBy(id="selectProduct")
	WebElement productDropdown;
	
	@FindBy(id="selectVatCode")
	WebElement vatcodeTypeDropdown;
	
	@FindBy(id="btnSave")
	WebElement saveOrderLineButton;
	
	@FindBy(xpath="//li[@id='salesorder|NoRelationship|Form|Mscrm.Form.salesorder.Save']/span/a")
	WebElement saveOrderButton;
	
	@FindBy(xpath="//li[@id='salesorder|NoRelationship|Form|de.salesorder.Confirm.Button']/span/a")
	WebElement confirmOrderButton;
	
	@FindBy(xpath="//label[@id='Order Status_label']/following-sibling::span")
	WebElement orderStatus;
	
	@FindBy(xpath="//li[@id='salesorder|NoRelationship|Form|de.salesorder.LapseOrder.Button']/span/a")
	WebElement lapseButton;
	
	@FindBy(id="lapseReasonddl")
	WebElement lapseReasonDropdown;
	
	@FindBy(xpath="//select[@id='lapseReasonddl']/option[2]")
	WebElement lapseReasonSelection;
	
	@FindBy(id="btnLapseReasonOk")
	WebElement lapseReasonOKButton;
	
	
/*	@FindBy(name="rdbLapseReason")
	WebElement radioButtonName;
	
	@FindBy()
	WebElement radioButtonSelection;*/
	
	
	/*private static String orderStatusExpected="Confirmed";
	private static String lapseorderStatusExpected="Cancelled";*/
	
	public OrdersPOM(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchOrder(){
		driver.switchTo().frame("contentIFrame1");
		assertion.assertTrue(paymentMethodText.getText().equalsIgnoreCase(paymentMethod), "Payment method is not correct");
		assertion.assertTrue(paymentMethodStatus.getText().equalsIgnoreCase(paymentStatus), "Payment status is not correct");
		assertion.assertAll();
		System.out.println("Payment Method and Payment status is correct");
	}
	
	

	public void createOrder(AddOrderDetails addOrder, OrdersPOM ordersPage) throws AutomationException{
		try{
		ordersPage.selectOrderType(addOrder.OrderType);
//		ordersPage.selectPaymentMethod(addOrder.PaymentMethod);
		ordersPage.clickAddOrderLineButton();
		ordersPage.selectProductType(addOrder.ProductType);
		ordersPage.selectProduct(addOrder.Product);
		ordersPage.selectVatCode(addOrder.VatCode);
		Thread.sleep(3000);
		ordersPage.saveOrderLine();
		Thread.sleep(3000);
		ordersPage.acceptPopup();
		ordersPage.saveOrder();
		Thread.sleep(3000);
		ordersPage.confirmOrder();
		Thread.sleep(5000);
		}catch(Exception e){
			throw new AutomationException("Order is not created successfully"+e.getStackTrace());
		}
	}

	private OrdersPOM selectOrderType(String OrderType){
		driver.switchTo().frame("contentIFrame1");
		driver.findElement(By.xpath("//div[@id='de_subscription']")).click();
		WebElement toElement = driver.findElement(By.xpath("//select[@id='de_subscription_i']/option[2]"));
		Actions action = new Actions(driver);
		action.doubleClick(toElement).build().perform();
		return this;
	} 
	
	private OrdersPOM selectPaymentMethod(String paymentMethod) throws AutomationException{
		
		try {
			driver.findElement(By.xpath("//div[@id='de_paymentmethodref']")).click();
			Thread.sleep(2000);
			System.out.println("User is switch to model content to select payment method");
			Thread.sleep(5000);
			Helper.explicitWait(5, paymentMethodModalDialogue, paymentMethodSelectedText);
			} catch (Exception e) {
				throw new AutomationException("Payment Method is not selected");
			}
			return this;
	}
	
	private OrdersPOM clickAddOrderLineButton() throws AutomationException, InterruptedException{
		driver.switchTo().frame("WebResource_OrderDetailList");
		newOrderLine.click();
		//Helper.explicitWait(5, addOrderLinePopup, cancelPopupButton);
		Thread.sleep(3000);
		Helper.switchToWindowsPopup();
		driver.switchTo().frame("InlineDialog_Iframe");
		return this;
	}
	
	private OrdersPOM selectProductType(String productType) {
		Helper.selectValueFromDropdown(productTypeDropdown, productType);
		return this;
	}
	
	private OrdersPOM selectProduct(String product){
		Helper.selectValueFromDropdown(productDropdown, product);
		return this;
	}
	
	private OrdersPOM selectVatCode(String vatcode){
		Helper.selectValueFromDropdown(vatcodeTypeDropdown, vatcode);
		return this;
	}
	
	private OrdersPOM saveOrderLine(){
		saveOrderLineButton.click();
		return this;
	}
	
	private OrdersPOM acceptPopup(){
		Helper.handlePopup();
		return this;
	}
	
	private OrdersPOM saveOrder(){
		driver.switchTo().defaultContent();
		saveOrderButton.click();
		return this;
	}

	private OrdersPOM confirmOrder(){
		confirmOrderButton.click();
		return this;
	}
	
	public boolean verifyOrderStatus(String expectedStatus){
		driver.switchTo().frame("contentIFrame1");
		return orderStatus.getText().equalsIgnoreCase(expectedStatus);
	}
	
	public OrdersPOM lapseConfirmedOrder(){
		lapseButton.click();
		Helper.switchToWindowsPopup();
		driver.switchTo().frame("InlineDialog_Iframe");
		Helper.selectRadioButton("rdbLapseReason","4" );
		//Helper.selectValueFromDropdown(lapseReasonDropdown, lapseReasonSelection);
		lapseReasonOKButton.click();
		return this;
	}
}
