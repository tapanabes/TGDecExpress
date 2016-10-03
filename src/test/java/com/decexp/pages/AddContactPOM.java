package com.decexp.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.decexp.beans.AddContactDetails;
import com.decexp.utils.AutomationException;
import com.decexp.utils.ExpressConstant;
import com.decexp.utils.Helper;

public class AddContactPOM {
	private static WebDriver driver = null;
	private static final Logger logger = Logger.getLogger("AddContactPM.class");
	private static String addContactPageTitle = "Contact: New Contact - Microsoft Dynamics CRM"; 
	
	@FindBy(id="fullname_d")
	WebElement fullNameTextFieldEnable;
	
	@FindBy(id="fullname_compositionLinkControl_flyoutLoadingArea_flyOut")
	WebElement nameModalDialogue;
	
	@FindBy(id="fullname_compositionLinkControl_firstname_i")
	WebElement foreNameTextField;
	
	@FindBy(xpath="//div[@id='de_surname']/div[1]/span")
	WebElement sureNameTextFieldEnable;
	
	@FindBy(id="de_surname_i")
	WebElement sureNameTextField;
	
	@FindBy(id="fullname_compositionLinkControl_flyoutLoadingArea-confirm")
	WebElement submitButtonField;
	
	@FindBy(id="de_initials_i")
	WebElement initialsTextField;
	
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
	
	@FindBy(xpath="//div[@id='de_productref']/div[1]")
	WebElement productTextFieldEnable;
	
	@FindBy(id="de_productref_ledit")
	WebElement productTextField;
	
	@FindBy(id="de_branchref_i")
	WebElement productSearchButton;
	
	public AddContactPOM(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyPageTitle(){
		return driver.getTitle().equalsIgnoreCase(addContactPageTitle);
	}
	
	public void createContact(AddContactDetails addContactDetails, AddContactPOM addContactPOM) throws AutomationException{
	try{
		addContactPOM.switchToFrame();
		addContactPOM.setFullName();
		addContactPOM.setForeName(addContactDetails.ForeName);
		addContactPOM.setSureName(addContactDetails.SurName);
		addContactPOM.setTitle(addContactDetails.Title);
		addContactPOM.setGender(addContactDetails.Gender);
		addContactPOM.setMobile(addContactDetails.Mobile);
		addContactPOM.setPostCode(addContactDetails.PostCode);
		addContactPOM.setEmail(addContactDetails.Email);
		//addContactPOM.setCountry(addContactDetails.Country);
		//addContactPOM.setBranch(addContactDetails.Branch);
		//addContactPOM.setDuplicateLookup();
/*		boolean conditionFlag = addContactPOM.setTermsandCondition(addContactDetails.TermsAndCondition);
		if(conditionFlag==false){
		//softAssertion.assertTrue(addContactPOM.getOrderText().contains("Order"), "Order Details section is not load");
		addContactPOM.selectProduct(addContactDetails.Product);
		addContactPOM.selectPayment(addContactDetails.PaymentMethod);
		}
		addContactPOM.clickSave();
		String contactID = addContactPOM.getContactID();
		//System.setProperty("contactID", contactID);
		ExpressConstant.contactID = contactID;
		System.out.println("MemberTest.createaddContactDetails() "+ExpressConstant.contactID);
		
		System.out.println("Created Contact ID is-->"+contactID);*/
		}catch(Exception e){
			throw new AutomationException("Contact is not created successfully");
		}
}

	private AddContactPOM switchToFrame(){
	logger.info("Switch to IFrame to fill the contact form:");
	driver.switchTo().frame("contentIFrame1");
	System.out.println("Switch to IFrame");
	return this;
	}
			
	private AddContactPOM setFullName(){
		Actions action = new Actions(driver);
		action.moveToElement(fullNameTextFieldEnable).click().build().perform();
//		fullNameTextFieldEnable.click();
		return this;
	}
	
	private AddContactPOM setForeName(String foreName) throws AutomationException{
	WebDriverWait wait = new WebDriverWait(driver,5);
	wait.until(ExpectedConditions.visibilityOf(nameModalDialogue));
	System.out.println("ForeName field is clicking:");
	foreNameTextField.sendKeys(foreName);
	return this;
	}
			
	private AddContactPOM setSureName(String sureName){
	System.out.println("Surname field is clicking:");
	sureNameTextFieldEnable.click();
	sureNameTextField.sendKeys(sureName);
	submitButtonField.click();
	driver.switchTo().defaultContent();
	return this;
	}
			
	private AddContactPOM setTitle(String title){
	titleTextFieldEnable.click();
//	Thread.sleep(2000);
	titleTextField.sendKeys(title);
	titleTextField.sendKeys(Keys.ENTER);
	System.out.println("User is switch to model content to select title");
//	Thread.sleep(5000);
	WebDriverWait wait = new WebDriverWait(driver,5);
	WebElement  modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ms-crm-modalDialog")));
	modal.findElement(By.xpath("//ul[@id='de_contacttitleref_i_IMenu']/li/a[2]/span/nobr/span[text()='Mr']")).click();
	return this;
	}
			
	private AddContactPOM setGender(String gender){		
	System.out.println("Gender is selecting-->");
	genderTextFieldEnable.click();
	WebElement toElement = driver.findElement(By.xpath(".//*[@id='de_contactgender_i']/option[3]"));
	Actions action = new Actions(driver);
	action.doubleClick(toElement).build().perform();
	
	return this;
	} 
	
	private AddContactPOM setMobile(String mobile){
	System.out.println("Enter mobile number-->");
	mobileTextFieldEnable.click();
	driver.findElement(By.xpath("//div[@class='ms-crm-Inline-Value ms-crm-Inline-EmptyValue ms-crm-Inline-EditHintState']")).click();
	mobileTextField.sendKeys(mobile);
	return this;	
	}
	        
	private AddContactPOM setPostCode(String postCode) throws AutomationException{
	try{
	System.out.println("Select PostCode-->");
	System.out.println("Window id is-->"+driver.getWindowHandle());
	postcodeTextFieldEnable.click();
	driver.findElement(By.xpath("//div[@class='ms-crm-Inline-Value ms-crm-Inline-EmptyValue ms-crm-Inline-EditHintState']")).click();
	postcodeTextField.sendKeys(postCode);
	driver.switchTo().frame("WebResource_ctlPostCodeLookup");
	Thread.sleep(2000);
	lookupButton.click();
	Helper.switchToWindowsPopup();
	Thread.sleep(2000);
	System.out.println("Move to dialog window");
	driver.switchTo().frame("InlineDialog_Iframe");
	Thread.sleep(2000);
	selectLookupAddress.click();
	driver.switchTo().defaultContent();
	return this;
	}catch(Exception e){
		throw new AutomationException(e.getMessage());
	}
	}
			
	private AddContactPOM setEmail(String email){
	System.out.println("Enter email value-->");
	driver.switchTo().frame("contentIFrame1");
	WebElement frameName = driver.findElement(By.tagName("iframe"));
	System.out.println("Frame name is-->"+frameName.getAttribute("id"));
	driver.findElement(By.id("de_email_d")).click();
	emailTextField.sendKeys(email);
	return this;
	}
	        
		private AddContactPOM setCountry(String country){
		try{
		countryTextFieldEnable.click();
		Thread.sleep(2000);
		countryTextField.sendKeys(country);
		countryTextField.sendKeys(Keys.ENTER);
		System.out.println("User is switch to model content to select country");

		WebDriverWait  wait = new WebDriverWait(driver,10);
		WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_countryref_i_IMenu")));
		modal.findElement(By.xpath("//ul[@id='de_countryref_i_IMenu']/li/a[2]/span/nobr/span[text()='United Kingdom']")).click();
		//Helper.explicitWait(10, Dialog_de_countryref_i_IMenu, text1, text2);
		}catch(Exception e){
			e.printStackTrace();
		}
		return this;
		}
		
		private AddContactPOM setBranch(String branch){
		try{
		branchTextFieldEnable.click();
		branchTextField.sendKeys(branch);
		branchTextField.sendKeys(Keys.ENTER);
		System.out.println("User is switch to model content to select Branch");

		WebDriverWait  wait = new WebDriverWait(driver,20);
		WebElement  modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_branchref_i_IMenu")));
		modal.findElement(By.xpath("//ul[@id='de_branchref_i_IMenu']/li/a[2]/span/nobr/span[text()='Ireland']")).click();
		}catch(Exception e){
			e.printStackTrace();
		}
		return this;
		}
		
		private AddContactPOM setDuplicateLookup(){
		try{
		driver.switchTo().frame("WebResource_ctlDuplicateLookup");
		Thread.sleep(2000);
		duplicateLookupButton.click();
		Thread.sleep(2000);
		Helper.switchToWindowsPopup();
		driver.switchTo().frame("InlineDialog_Iframe");
		Thread.sleep(2000);
		duplicateLookupCancelButton.click();
		Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
		return this;
		}
				
		private boolean setTermsandCondition(String tandC){ 
		boolean conditionFlag=true;
		try{
		System.out.println("Select Term and condition button-->");	
		Thread.sleep(3000);
		driver.switchTo().frame("contentIFrame1");
		driver.switchTo().frame("WebResource_TermsAndConditionsPanel");
		Thread.sleep(2000);
		tandcButton.click();
		Helper.switchToWindowsPopup();
		Thread.sleep(2000);
		driver.switchTo().frame("InlineDialog_Iframe");
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
		
		private AddContactPOM selectProduct(String product) throws AutomationException{
		try{
		
		driver.switchTo().frame("contentIFrame1");
		Thread.sleep(3000);
		productTextFieldEnable.click();
		Thread.sleep(2000);
		productTextField.sendKeys(product);
		productTextField.sendKeys(Keys.ENTER);
		System.out.println("User is switch to model content to select Product");

		WebDriverWait  wait = new WebDriverWait(driver,20);
		WebElement  modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_productref_i_IMenu")));
		modal.findElement(By.xpath("//ul[@id='de_productref_i_IMenu']/li/a[2]/span/nobr/span[text()='ACPGBI: 1st year Consultant']")).click();
		Thread.sleep(3000);
		}catch(Exception e){
			throw new AutomationException("Product is failed to load");
		}
		return this;
		}
		
		private AddContactPOM selectPayment(String payment) throws AutomationException{
		try{
		System.out.println("Payment is selecting-->");
//		paymentTextFieldEnable.click();
		Thread.sleep(3000);
//		paymentTextField.sendKeys(payment);
//		paymentTextField.sendKeys(Keys.ENTER);
		
		System.out.println("User is switch to model content to select Payment");
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Dialog_de_paymentmethodref_i_IMenu")));
		modal.findElement(By.xpath("//ul[@id='de_paymentmethodref_i_IMenu']/li/a[2]/span/nobr/span[text()='Cheque']")).click();
		}catch(Exception e){
			throw new AutomationException("payment method is failed to load");
		}
		return this;
		} 	

				
		private AddContactPOM clickSave() throws AutomationException{		
		try{
		driver.switchTo().defaultContent();
//		saveButton.click();
		Thread.sleep(5000);
		logger.info("New Member is created successfully");
		System.out.println("New Memberhsip is created successfully");
		return this;
		}catch(Exception e){
			throw new AutomationException(e.getMessage());
		}
		}
				
				
		private String getContactID(){
		driver.switchTo().frame("contentIFrame1");
		System.out.println("User is trying to fetch contact id-->");
//		String contactID = contactidTextField.getText();
//		return contactID;
		return "contactID";
		}
		
}
