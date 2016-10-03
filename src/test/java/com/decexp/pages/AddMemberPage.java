package com.decexp.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.Select;

import com.decexp.beans.AddMemberDetails;
import com.decexp.utils.AutomationException;
import com.decexp.utils.ExpressConstant;
import com.decexp.utils.Helper;

public class AddMemberPage {
	private static WebDriver driver;
	private static final Logger logger = Logger.getLogger("CreateNewMemberPage.class");
	private static String addMemberPageTitle = "Membership Workflow: New Membership Workflow - Microsoft Dynamics CRM";
	
	@FindBy(xpath="//div[@id='de_contactstatusref']/div[1]/span")
	WebElement statusText;
	
	@FindBy(id="de_forename")
	WebElement foreNameTextFieldEnable;
	
	@FindBy(xpath="//*[@id='de_forename']/div[1]/span")
	WebElement foreNameTextFieldEnable2;
	
	@FindBy(id="de_forename_i")
	WebElement foreNameTextField;
	
	@FindBy(id="de_surname")
	WebElement sureNameTextFieldEnable;
	
	@FindBy(id="de_surname_i")
	WebElement sureNameTextField;
	
	@FindBy(xpath="//*[@id='de_contacttitleref']/div[1]")
	WebElement titleTextFieldEnable;
	
	@FindBy(id="de_contacttitleref_ledit")
	WebElement titleTextField;
	
	@FindBy(xpath="//img[@id='de_contacttitleref_i']")
	WebElement titleSearchButton;
	
	@FindBy(xpath="ul[@id='de_contacttitleref_i_IMenu']/li/a[2]/span/nobr/span[text()='Mr']")
	WebElement titleMr;
		
	@FindBy(xpath="//div[@id='de_contactgender']/div[1]/span")
	WebElement genderTextFieldEnable;
	
	@FindBy(id="de_contactgender_i")
	WebElement genderButton;
	
	@FindBy(xpath="//div[@id='de_mobile']/div[1]/span")
	WebElement mobileTextFieldEnable;
	
	@FindBy(id="de_mobile_i")
	WebElement mobileTextField;
	
	@FindBy(xpath="//div[@id='de_postcode']/div[1]/span")
	WebElement postcodeTextFieldEnable;
	
	@FindBy(id="de_postcode_i")
	WebElement postcodeTextField;
	
	@FindBy(id="btnTermsAndConditions")
	WebElement lookupButton;
	
	@FindBy(xpath="//table[@id='addressList']/tbody/tr[1]/td/a")
	WebElement selectLookupAddress;
	
	@FindBy(xpath="//div[@id='de_email']/div[1]/span")
	WebElement emailTextFieldEnable;
	
	@FindBy(id="de_email_i")
	WebElement emailTextField;
	
	@FindBy(xpath="//div[@id='de_countryref']/div[1]")
	WebElement countryTextFieldEnable;
	
	@FindBy(id="de_countryref_ledit")
	WebElement countryTextField;
	
	@FindBy(id="de_countryref_i")
	WebElement countrySearchButton;			
	
	@FindBy(xpath="//div[@id='de_branchref']/div[1]")
	WebElement branchTextFieldEnable;
	
	@FindBy(id="de_branchref_ledit")
	WebElement branchTextField;
	
	@FindBy(id="de_branchref_i")
	WebElement branchSearchButton;
	
	@FindBy(id="btnDuplicateLookup")
	WebElement duplicateLookupButton;
	
	@FindBy(id="btnCancel")
	WebElement duplicateLookupCancelButton;
		
	@FindBy(id="btnTermsAndConditions")
	WebElement tandcButton;
	
	@FindBy(id="btnDeclineTC")
	WebElement declineButton;
	
	@FindBy(id="btnAcceptTC")
	WebElement acceptButton;
	
	@FindBy(id="OrderDetails_header_h2")
	WebElement orderText;
	
/*	@FindBy(xpath="//div[@id='de_productref']/div[1]")
	WebElement productTextFieldEnable;
	
	@FindBy(id="de_productref_ledit")
	WebElement productTextField;
	
	@FindBy(xpath="//div[@id='de_paymentmethodref']/div[1]")
	WebElement paymentTextFieldEnable;
	
	@FindBy(id="de_paymentmethodref_ledit")
	WebElement paymentTextField;
*/	
	@FindBy(xpath="//li[@id='de_membershipworkflow|NoRelationship|Form|Mscrm.Form.de_membershipworkflow.Save']/span/a")
	WebElement saveButton;
	
	@FindBy(xpath="//div[@id='de_contactno']/div[1]/span")
	WebElement contactidTextField;
	
	public AddMemberPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		addMemberPage = new AddMemberPage(driver);
	}
	
	public boolean verifyPageTitle(){
		return driver.getTitle().equalsIgnoreCase(addMemberPageTitle);
	}
	
	public void createContact(AddMemberDetails newMember, AddMemberPage addMemberPage) throws AutomationException{
		try{
		Helper.switchToFrame(10,"contentIFrame1");
		Helper.pageLoad(60,statusText,"Prospect");
		addMemberPage.setForeName(newMember.ForeName);
		addMemberPage.setSureName(newMember.SurName);
		addMemberPage.setTitle(newMember.Title);
		addMemberPage.setGender(newMember.Gender);
		addMemberPage.setMobile(newMember.Mobile);
		addMemberPage.setPostCode(newMember.PostCode);
		addMemberPage.setEmail(newMember.Email);
		addMemberPage.setCountry(newMember.Country);
		addMemberPage.setBranch(newMember.Branch);
		addMemberPage.setDuplicateLookup();
		boolean conditionFlag = addMemberPage.setTermsandCondition(newMember.TermsAndCondition);
		if(conditionFlag==false){
		//softAssertion.assertTrue(addMemberPage.getOrderText().contains("Order"), "Order Details section is not load");
		addMemberPage.selectProduct(newMember.Product);
		addMemberPage.selectPayment(newMember.PaymentMethod);
		}
		addMemberPage.clickSave();
		boolean alertPresent = Helper.isAlertPresent();
		if(alertPresent==true){
			System.out.println("Alert is handled properly");
		}
		String contactID = addMemberPage.getContactID();
		//System.setProperty("contactID", contactID);
		ExpressConstant.contactID = contactID;
		System.out.println("Contact ID is--> "+ExpressConstant.contactID);
		}catch(Exception e){
			e.printStackTrace();
			throw new AutomationException("Contact is not created successfully");
		}

	}
			
	private AddMemberPage switchToFrame(){
	logger.info("Switch to IFrame to fill the form:");
	driver.switchTo().frame("contentIFrame1");
	return this;
	}
			
	private AddMemberPage setForeName(String foreName){
	Actions action = new Actions(driver);
	action.moveToElement(foreNameTextFieldEnable).click().build().perform();
//	action.doubleClick(foreNameTextFieldEnable).build().perform();
	foreNameTextField.sendKeys(foreName);
	return this;
	}
			
	private AddMemberPage setSureName(String sureName){
	sureNameTextFieldEnable.click();
	sureNameTextField.sendKeys(sureName);
	return this;
	}
			
	private AddMemberPage setTitle(String title){
	try{
	titleTextFieldEnable.click();
	Thread.sleep(2000);
	titleTextField.sendKeys(title);
	Thread.sleep(2000);
	titleTextField.sendKeys(Keys.ENTER);
	WebDriverWait wait = new WebDriverWait(driver,20);
	WebElement  modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ms-crm-modalDialog")));
	modal.findElement(By.xpath("//ul[@id='de_contacttitleref_i_IMenu']/li/a[2]/span/nobr/span[text()='Mr']")).click();
	}catch(Exception e){
		e.printStackTrace();
	}
	return this;
	}
			
	private AddMemberPage setGender(String gender){		
	genderTextFieldEnable.click();
	WebElement toElement = driver.findElement(By.xpath(".//*[@id='de_contactgender_i']/option[3]"));
	Actions action = new Actions(driver);
	action.doubleClick(toElement).build().perform();
	return this;
	} 
	
	private AddMemberPage setMobile(String mobile){
	mobileTextFieldEnable.click();
	driver.findElement(By.xpath("//div[@class='ms-crm-Inline-Value ms-crm-Inline-EmptyValue ms-crm-Inline-EditHintState']")).click();
	mobileTextField.sendKeys(mobile);
	return this;	
	}
	        
	private AddMemberPage setPostCode(String postCode) throws AutomationException{
	postcodeTextFieldEnable.click();
	driver.findElement(By.xpath("//div[@class='ms-crm-Inline-Value ms-crm-Inline-EmptyValue ms-crm-Inline-EditHintState']")).click();
	postcodeTextField.sendKeys(postCode);
	driver.switchTo().frame("WebResource_ctlPostCodeLookup");
	lookupButton.click();
	Helper.switchToWindowsPopup();
	driver.switchTo().frame("InlineDialog_Iframe");
	selectLookupAddress.click();
	driver.switchTo().defaultContent();
	return this;
	}
			
	private AddMemberPage setEmail(String email){
	driver.switchTo().frame("contentIFrame1");
	driver.findElement(By.id("de_email_d")).click();
	emailTextField.sendKeys(email);
	return this;
	}
	        
	private AddMemberPage setCountry(String country){
	try{
	Actions action = new Actions(driver);
	action.moveToElement(countryTextFieldEnable).click().build().perform();
//	action.doubleClick(countryTextFieldEnable).build().perform();
//	countryTextFieldEnable.click();
	Thread.sleep(2000);
	countryTextField.sendKeys(country);
	Thread.sleep(2000);
	countryTextField.sendKeys(Keys.ENTER);
	WebDriverWait  wait = new WebDriverWait(driver,20);
	WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_countryref_i_IMenu")));
	modal.findElement(By.xpath("//ul[@id='de_countryref_i_IMenu']/li/a[2]/span/nobr/span[text()='Afghanistan']")).click();
	Thread.sleep(2000);
	}catch(Exception e){
		e.printStackTrace();
	}
	return this;
	}
	
	private AddMemberPage setBranch(String branch){
	try{
	Actions action = new Actions(driver);
	action.moveToElement(branchTextFieldEnable).click().build().perform();
//	action.doubleClick(branchTextFieldEnable).build().perform();
//	branchTextFieldEnable.click();
	Thread.sleep(2000);
	branchTextField.sendKeys(branch);
	Thread.sleep(2000);
	branchTextField.sendKeys(Keys.ENTER);
	WebDriverWait  wait = new WebDriverWait(driver,30);
	WebElement  modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_branchref_i_IMenu")));
	modal.findElement(By.xpath("//ul[@id='de_branchref_i_IMenu']/li/a[2]/span/nobr/span[text()='Channel Islands']")).click();
	Thread.sleep(2000);
	}catch(Exception e){
		e.printStackTrace();
	}
	return this;
	}
	
	private AddMemberPage setDuplicateLookup(){
	Helper.switchToFrame(20, "WebResource_ctlDuplicateLookup");
	duplicateLookupButton.click();
	Helper.switchToWindowsPopup();
	Helper.switchToFrame(10, "InlineDialog_Iframe");
	duplicateLookupCancelButton.click();
	driver.switchTo().defaultContent();
	return this;
	}
			
	private boolean setTermsandCondition(String tandC){ 
	boolean conditionFlag=true;
	try{
	Helper.switchToFrame(10, "contentIFrame1");	
	Helper.switchToFrame(10, "WebResource_TermsAndConditionsPanel");
	tandcButton.click();
	Helper.switchToWindowsPopup();
//	Thread.sleep(2000);
	Helper.switchToFrame(10, "InlineDialog_Iframe");
	if(tandC.equalsIgnoreCase("Decline")){
	declineButton.click();
	}else{
	acceptButton.click();
	Thread.sleep(2000);
	conditionFlag = false;
	}
	driver.switchTo().defaultContent();
	}catch(Exception e){
		e.printStackTrace();
	}
	return conditionFlag;
	}
	
	private String getOrderText(){
		return orderText.getText();
	}
	
	private AddMemberPage selectProduct(String product) throws AutomationException{
	try{
	Helper.switchToFrame(20, "contentIFrame1");	
	WebElement productTextFieldEnable = driver.findElement(By.xpath("//div[@id='de_productref']/div[1]"));
	WebElement productTextField = driver.findElement(By.id("de_productref_ledit"));
	productTextFieldEnable.click();
	Thread.sleep(2000);
	productTextField.sendKeys(product);
	Thread.sleep(2000);
	productTextField.sendKeys(Keys.ENTER);
	WebDriverWait  wait = new WebDriverWait(driver,30);
	WebElement  modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_productref_i_IMenu")));
	modal.findElement(By.xpath("//ul[@id='de_productref_i_IMenu']/li/a[2]/span/nobr/span[text()='ACPGBI: 1st year Consultant']")).click();
	Thread.sleep(2000);
	}catch(Exception e){
		e.printStackTrace();
	}
	return this;
	}
	
	private AddMemberPage selectPayment(String payment) throws AutomationException{
	try{
	WebElement paymentTextFieldEnable = driver.findElement(By.xpath("//div[@id='de_paymentmethodref']/div[1]"));
	WebElement paymentTextField = driver.findElement(By.id("de_paymentmethodref_ledit"));
		
	Actions actions = new Actions(driver);
	actions.moveToElement(paymentTextFieldEnable).click().build().perform();
//	paymentTextFieldEnable.click();
	Thread.sleep(2000);
	paymentTextField.sendKeys(payment);
	Thread.sleep(2000);
	paymentTextField.sendKeys(Keys.ENTER);
	WebDriverWait wait = new WebDriverWait(driver,20);
	WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_paymentmethodref_i_IMenu")));
	modal.findElement(By.xpath("//ul[@id='de_paymentmethodref_i_IMenu']/li/a[2]/span/nobr/span[text()='Cheque']")).click();
	}catch(Exception e){
		e.printStackTrace();
	}
	return this;
	} 	

			
	private AddMemberPage clickSave() throws AutomationException{		
	try{
	driver.switchTo().defaultContent();
	saveButton.click();
	Thread.sleep(10000);
	logger.info("New Member is created successfully");
//	System.out.println("New Memberhsip is created successfully");
	return this;
	}catch(Exception e){
		throw new AutomationException(e.getMessage());
	}
	}
			
			
	private String getContactID() throws AutomationException{
	try{
	driver.switchTo().frame("contentIFrame1");
	Thread.sleep(5000);
	String contactID = contactidTextField.getText();
	Thread.sleep(2000);
	return contactID;
	}catch(Exception e){
		throw new AutomationException("Contact ID is failed to fetch");
	}
	}
			
	}
	


