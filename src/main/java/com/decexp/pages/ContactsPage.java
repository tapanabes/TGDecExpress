package com.decexp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	private static WebDriver driver;
	
	@FindBy(id="crmGrid_findCriteria")
	WebElement searchTextField;
	
	@FindBy(xpath=".//*[@id='addressContainer']/div[1]")
	WebElement searchCount;
	
	public ContactsPage(WebDriver driver){
	driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	public boolean searchContactNo(String contactID){
		searchTextField.clear();
		searchTextField.sendKeys(contactID);
		searchTextField.click();
		if(searchCount.getText().equalsIgnoreCase("1")){
			return true;
		}else{
		return false;
		}
	}
	
}