package com.decexp.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.decexp.utils.AutomationException;

public class ContactsPage {
	private static WebDriver driver;
	private static final Logger logger = Logger.getLogger(ContactsPage.class);
	
	
	@FindBy(id="crmGrid_findCriteria")
	WebElement searchTextField;
	
	@FindBy(xpath="//td[@id='crmGrid_RecordSelectInfo']/span[1]")
	WebElement searchCount;
	
	@FindBy(xpath="//table[@id='gridBodyTable']/tbody/tr/td[2]/nobr")
	WebElement clickRecord;
	
	public ContactsPage(WebDriver driver){
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	public boolean searchContactNo(String contactID){
	try{
		logger.info("Switch to IFrame");
		driver.switchTo().frame("contentIFrame1");
		logger.info("Clear the Search Text Field");
		searchTextField.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		logger.info("Search the Contact ID"+contactID);
		searchTextField.sendKeys(contactID);
		searchTextField.sendKeys(Keys.ENTER);
		Thread.sleep(7000);
		if(searchCount.getText().equalsIgnoreCase("1")){
			System.out.println("Created record is found");
			return true;
		}else{
			return false;
		}
		}catch(Exception e){
		e.printStackTrace();
		return false;
		}
		
	}
	
	public void clickRecord() throws AutomationException{
		try{
		logger.info("Click the search record");
		clickRecord.click();
		Thread.sleep(5000);
		}catch(Exception e){
			throw new AutomationException("Not able to click on Search Record");
		}
	}

	
}
	
