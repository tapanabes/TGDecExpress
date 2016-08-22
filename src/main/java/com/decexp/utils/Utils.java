package com.decexp.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;


public class Utils extends BaseTest {
	@FindBy(xpath="//[@id='de_membershipworkflow|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.de_membershipworkflow.NewRecord']/span/a")
	WebElement createMembershipNewButton;
	
	@FindBy(xpath="//[@id='contact']/span[2]")
	WebElement contactsButton;
	
	public Utils(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickonNewButton(){
		createMembershipNewButton.click();
	}
	
	public void clickonContacts(){
		contactsButton.click();
	}

}
