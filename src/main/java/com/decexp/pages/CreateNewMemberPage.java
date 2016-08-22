package com.decexp.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import com.decexp.beans.NewMembershipWorkflow;
import com.decexp.utils.AutomationException;

public class CreateNewMemberPage {
	private static WebDriver driver;
	private static final Logger logger = Logger.getLogger("CreateNewMemberPage.class");
	private static String newMembershipPageTitle = "Membership Workflow: New Membership Workflow - Microsoft Dynamics CRM";
	
	@FindBy(id="de_surname")
	WebElement foreNameTextField;
	
	@FindBy(id="de_surname_i")
	WebElement sureNameTextField;
	
	@FindBy(id="de_initials_i")
	WebElement initialsTextField;
	
	@FindBy(id="de_contacttitleref_ledit")
	WebElement titleTextField;
	
	@FindBy(id="de_contacttitleref_i")
	WebElement titleSearchButton;
	
	@FindBy(id="de_contactgender")
	WebElement genderButton;
	
	@FindBy(id="de_postcode_i")
	WebElement postcodeTextField;
	
	@FindBy(id="btnTermsAndConditions")
	WebElement lookupButton;
	
	@FindBy(id="de_email_i")
	WebElement emailTextField;
	
	@FindBy(id="de_countryref_ledit")
	WebElement countryTextField;
	
	@FindBy(id="de_countryref_i")
	WebElement countrySearchButton;			
	
	@FindBy(id="btnTermsAndConditions")
	WebElement tandcButton;
	
	@FindBy(id="btnDeclineTC")
	WebElement declineButton;
	
	@FindBy(xpath="//[@id='addressContainer']/div[1]")
	WebElement saveButton;
	
	@FindBy(xpath=".//*[@id='addressContainer']/div[1]")
	WebElement contactidTextField;
	
	
	public CreateNewMemberPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//logger.info("Enter the values in New Membership Form:");
			
	public CreateNewMemberPage switchToFrame(){
	logger.info("Switch to IFrame to fill the form:");
	driver.switchTo().frame("contentIFrame1");
	return this;
	}
			
	public CreateNewMemberPage setForeName(String foreName){
	foreNameTextField.clear();
	foreNameTextField.sendKeys(foreName);
	return this;
	}
			
	public CreateNewMemberPage setSureName(String sureName){
	sureNameTextField.clear();
	sureNameTextField.sendKeys(sureName);
	return this;
	}
			
	public CreateNewMemberPage setInitials(String initials){
	initialsTextField.clear();
	initialsTextField.sendKeys(initials);
	return this;
	}
			
	public CreateNewMemberPage setTitle(String title){
	initialsTextField.clear();
	titleTextField.sendKeys(title);
	titleSearchButton.click();
			
	logger.info("User is switch to model content to select title");
	WebDriverWait  block = new WebDriverWait(driver,5);
	WebElement  modal = block.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
	//Thread.sleep(5000);
			    
	WebElement a=modal.findElement(By.xpath("//td[text()='Mr']"));
	a.click();
	return this;
	}
			
	public CreateNewMemberPage setGender(String gender){		
	genderButton.clear();
	genderButton.sendKeys(gender);
	return this;
	} 
	        
	public CreateNewMemberPage setPostCode(String postCode){
	postcodeTextField.clear();
	postcodeTextField.sendKeys(postCode);
	lookupButton.click();
	return this;
	}
			
	public CreateNewMemberPage setEmail(String email){		
	emailTextField.clear();
	emailTextField.sendKeys(email);
	return this;
	}
	        
	public CreateNewMemberPage setCountry(String country){
	countryTextField.clear();
	countryTextField.sendKeys(country);
	countrySearchButton.click();
	return this;
	}		
			
	public CreateNewMemberPage setTermsandCondition(String tandC){        
	tandcButton.click();
	declineButton.click();
	return this;
	}
			
	public CreateNewMemberPage clickSave(){		
	saveButton.click();
	logger.info("New Member is created successfully");
	return this;
	}
			
			
	public String getContactID(){
	String contactID = contactidTextField.getText();
	return contactID;
	}
	
	public boolean verifyPageTitle(){
		return driver.getTitle().equalsIgnoreCase(newMembershipPageTitle);
	}
		
	}
	


