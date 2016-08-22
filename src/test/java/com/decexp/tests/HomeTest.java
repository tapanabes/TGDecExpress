package com.decexp.tests;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import com.decexp.pages.HomePage;
import com.decexp.utils.BaseTest;

public class HomeTest extends BaseTest {
	
	HomePage homePage = new HomePage(driver);
	
	public void clickonAddButton(){
		homePage.clickNewButton();
	}
	
}
