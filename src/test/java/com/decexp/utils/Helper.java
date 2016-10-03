package com.decexp.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper extends BaseTest {

	private static Object firstHandle;
	private static Object lastHandle;

	public static void switchToWindowsPopup() {
	    Set<String> winhandles = driver.getWindowHandles();
	    Iterator<String> itr = winhandles.iterator();
	    firstHandle = itr.next();
	    lastHandle = firstHandle;
	    while (itr.hasNext()) {
	        lastHandle = itr.next();
	    }
	    driver.switchTo().window(lastHandle.toString());
//	    System.out.println("Swithching window id is-->"+driver.getWindowHandle());
	}

	public static void switchToMainWindow() {
	    driver.switchTo().window(firstHandle.toString());
	    //System.out.println("Default Window id is-->"+driver.switch);
	}
	
	public static String currentWindow(){
		return driver.getWindowHandle();
	}
	
	public static String captureScreenshot(WebDriver driver, String screenshotName){
		try{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir")+"\\Screenshot\\"+screenshotName+".png";
			File destination = new File(dest);
			FileUtils.copyFile(src, destination);
			System.out.println("Screenshot Taken");
			return dest;
		}catch(Exception e){
			System.out.println("Exception while taking screenshot");
			return e.getMessage();
			}
	}
	
	public static void selectValueFromDropdown(WebElement dropdownLocator, String value){
		Select dropdown = new Select(dropdownLocator);
		List<WebElement> allOptions = dropdown.getOptions();
		for(WebElement option:allOptions){
			System.out.println("Option values are-->"+option.getText());
			if(option.getText().equalsIgnoreCase(value)){
				System.out.println("Option value is-->"+option.getText());
				dropdown.selectByVisibleText(value);
				break;
			}
		}
	}
	
	public static void switchToFrame(int timeInSec, String frameLocator ){
		WebDriverWait wait = new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}
	
	public static void pageLoad(int timeInSec, WebElement element, String elementText){
		WebDriverWait wait = new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.textToBePresentInElement(element, elementText));
		}
	
	public static void explicitWait(int time, WebElement modalDialogueXpath, WebElement selectedTextXpath){
	WebDriverWait  wait = new WebDriverWait(driver,time);
	WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("modalDialogueXpath")));
	modal.findElement(By.xpath("selectedTextXpath")).click();
	}
	
	public static void handlePopup(){
		Alert alert = driver.switchTo().alert(); 
		alert.accept();
	}
	
	public static void selectRadioButton(String oRadioButtonName, String RadioButtonInnerHTML){
		List<WebElement> oRadioButton = driver.findElements(By.name(oRadioButtonName));
		int rSize = oRadioButton.size();
		 
		 for(int i=0; i < rSize ; i++ ){	 
			 String rValue = oRadioButton.get(i).getAttribute("value");	
			 System.out.println("Radio button value is-->"+rValue);
			 if (rValue.equalsIgnoreCase("4")){
				 oRadioButton.get(i).click();
		 		 break;
				 }		 
		 }
	}
	
	public static boolean isAlertPresent(){
	    boolean presentFlag = false;
	    try {
	        // Check the presence of alert
	        Alert alert = driver.switchTo().alert();
	        // Alert present; set the flag
	        presentFlag = true;
	        // if present consume the alert
	        alert.accept();
	        //( Now, click on ok or cancel button )
	    } catch (NoAlertPresentException ex) {
	        // Alert not present
	        ex.printStackTrace();
	    }
	    return presentFlag;
	}
	
}