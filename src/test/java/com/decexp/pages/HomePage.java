package com.decexp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import com.decexp.tests.MemberTest;


public class HomePage {
	private static WebDriver driver;
	
	@FindBy(xpath="//[@id='de_membershipworkflow|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.de_membershipworkflow.NewRecord']/span/a")
	WebElement newButton;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickNewButton(){
		newButton.click();
	}
	
}
