package com.decexp.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utils extends BaseTest {
	private static WebDriver driver;
	private static final Logger logger = Logger.getLogger(Utils.class);
	
	@FindBy(id="findHintText")
	WebElement searchContactID;
	
	@FindBy(xpath="//input[@id='search']")
	WebElement searchText;
	
	@FindBy(xpath="//*[@id='de_membershipworkflow|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.de_membershipworkflow.NewRecord']/span/a")
	WebElement addMemberButton;	
	
	@FindBy(xpath="//span[@id='TabMembership']/a")
	WebElement membershipDropdown;
	
	@FindBy(linkText="Membership Workflow")
	WebElement membershipWorkflowButton;
	
	@FindBy(linkText="Contacts")
	WebElement contactsButton;
	
	@FindBy(xpath="//li[@id='contact|NoRelationship|Form|de.contact.AddOrder.Button']/span/a")
	WebElement ordersButton;
	
	@FindBy(xpath="//li[@id='salesorder|NoRelationship|Form|de.salesorder.NewOrder.Button']/span/a")
	WebElement newOrder;
	
	@FindBy(xpath="//li[@id='contact|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.contact.NewRecord']/span/a")
	WebElement addContactButton;
	
	public Utils(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickonAddMemberButton(){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(addMemberButton));	
		addMemberButton.click();
		}
	
	public void clickonMemberdhipWorkflowButton(){
		driver.switchTo().defaultContent();
		logger.info("Click on Membership dropdown link");
		membershipDropdown.click();
		WebDriverWait visibleWait = new WebDriverWait(driver,10);
		visibleWait.until(ExpectedConditions.visibilityOf(membershipWorkflowButton));
		membershipWorkflowButton.click();
//		Thread.sleep(7000);
		}
	
	public void clickonContacts(){
		driver.switchTo().defaultContent();
		logger.info("Click on Membership dropdown link");
		membershipDropdown.click();
		WebDriverWait visibleWait = new WebDriverWait(driver,10);
		visibleWait.until(ExpectedConditions.visibilityOf(contactsButton));
		logger.info("Click on Contact link");
		contactsButton.click();
	}
	
	public void clickonOrdersLink() throws AutomationException{
		try{
		logger.info("Click on Orders link");
		//System.out.println("Click on Orders lik");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		ordersButton.click();
		Thread.sleep(5000);
		}catch(Exception e){	
			throw new AutomationException("Orders link is not clicked");
		
		}
	}
	
	public void searchRecord(String contactID) throws AutomationException{
		try{
		searchContactID.sendKeys(contactID);
		searchText.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.switchTo().frame("contentIFrame1");
		driver.findElement(By.xpath("//span[@id='attribone'][text()='20000021']")).click();
		Thread.sleep(5000);
		}catch(Exception e){
			throw new AutomationException("contactID is not find or not clicked");
		}
	}
	
	public void clickonAddOrder(){
		System.out.println("Click on Add Order button");
		driver.switchTo().defaultContent();
		newOrder.click();
	}
	
	public void clickonAddContactButton(){
		addContactButton.click();
	}

}
