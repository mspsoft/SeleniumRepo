// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.pageObjects;
import org.openqa.selenium.By;

import org.testng.Assert;

import com.expleo.drivers.BasePage;
import com.expleo.drivers.ExtentManager;
import com.expleo.drivers.WebDriverManager;
import com.expleo.helpers.HelperMethods;
import com.expleo.utils.XmlReader;
/**
 * Holds all the objects and methods related to folder operations
 */
public class FolderPage extends BasePage {
	
	HelperMethods helperMethods = new HelperMethods();
	
	
	By addToLink= By.xpath("//div[text()='Add to']");
	By folderOptionLink= By.xpath("//li[@aria-label=\\\"Folder\\\"]");
	By folderSearchText= By.xpath("//input[@name='searchBoxLOVFilterStr']");
	By panelAddButton= By.xpath("//button[@class='sw-button ']/div[text()='Add']");
	By nameBox= By.xpath("//*[@name='object_name']");
	By folderTextLink= By.xpath("(//div[@class='sw-cell-valName' and text()=\"Folder\"])[1]");
	
	By btnNewObject= By.xpath("//button[@button-id='Awp0NewGroup']");
	
	/**
	 * Clicks on the folder option from the list
	 * 
	 */
	public void ObjectTextLinkClick(String objectName) throws InterruptedException {
		try {
			ExtentManager.getTestThread().get().info("Step: Click on folder option from the list");
			By objectTextLink = By.xpath("//ul[@aria-label='typeListProp']//li[@aria-label='"+objectName+"']");
			helperMethods.click(WebDriverManager.getWebDriver(),objectTextLink);
	    	ExtentManager.logSuccess("Step: Click on folder option from the list completed successfully");
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to locate the folder search textbox");
		}
	}
	
	/**
	 * Clicks on the folder option from the list
	 * 
	 */
	public void FolderTextLinkClick() throws InterruptedException {
		try {
			Thread.sleep(3000);
			helperMethods.click(WebDriverManager.getWebDriver(),folderTextLink);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}	
	/**
	 * Clicks on the new button from the right panel list
	 *@return null
	 */
	public void newObjectClick() {	
		try {
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnNewObject);	
		helperMethods.click(WebDriverManager.getWebDriver(),btnNewObject);
		ExtentManager.logSuccess("Step: Clicked on the New button successfully");
	  	  } catch (Exception e) {
				 ExtentManager.logFailure("Fail: Failing in the clicking the new object button from right panel");
				 throw e;
				}
	}
	
	/**
	 * Clicks on the add to link
	 *@return null
	 */
	public void addToLink() {
		try {
		helperMethods.click(WebDriverManager.getWebDriver(),addToLink);
		ExtentManager.getTestThread().get().pass("Step: Click on Add to link completed successfully");
  	  } catch (Exception e) {
			 ExtentManager.logFailure("Fail: Failing in clicking the addToLink button " + e.getMessage());
			 throw e;
			}
	}
	/**
	 * Clicks on the listoptions for folder
	 *@return null
	 */
	public void listOptions() {
		helperMethods.click(WebDriverManager.getWebDriver(),folderOptionLink);
	}
	/**
	 * Clicks on the folder search text and enters option to be searched
	 *@param value - text to be entered in the search text box
	 *@return null
	 */
	public void folderSearchTextBox(String value) {
		try {
	    	ExtentManager.logInfo("Step: Input folder in the search text box");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),folderSearchText);
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),folderSearchText, value);
		ExtentManager.logSuccess("Step: Folder text has been entered successfully");
		  } catch (Exception e) {				
	  		ExtentManager.logFailure("Fail: Could not locate the filter text box");
		}
	}
	/**
	 * Clicks on the folder search text and enters name of the folder to be searched
	 * @param value - text to be entered in the folder text box
	 * @return null
	 */
	public void folderNameTextBox(String value) {
		try {
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),nameBox);
		// click(btnLoginLink);
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),nameBox);
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),nameBox,value);
		ExtentManager.logSuccess("Pass: Successfully entered folder name in textbox");
		 } catch (Exception e) {	
		ExtentManager.logFailure("Fail: Could not locate the filter text box");
		 }
	}
	
	/**
	 * Clicks on the panel add button
	 * @return null
	 */
	public void panelAddButton() {
		try {
			ExtentManager.getTestThread().get().info("Click on the add button for folder creation ");
		helperMethods.click(WebDriverManager.getWebDriver(),panelAddButton);
    	ExtentManager.getTestThread().get().pass("Click on the add button for folder creation successfully ");
	  } catch (Exception e) {
		 ExtentManager.logFailure("Fail: Failing in clicking the Add button for folder");
		 throw e;
		}  
	}
	/**
	 * Verify the property value
	 * @param property -property value
	 * @param containsFlag -contains flag value
	 * @param value - expected value
	 * @param context - context
	 * @return null
	 */
	//------------------------------------------------------Code by Ashwani Jain----------------------------------------------------------------------    	
	public void verifyPropertyValue( String property, String containsFlag, String value, String context ) throws InterruptedException 
	{
		 try {
		String expectedValue = value;
		Thread.sleep(3000);
		By labelName=By.xpath("//div[contains(@class,'sw-section-content')]//label//span[text()='"+property+"']//following-sibling::div");
		Thread.sleep(3000);
		String actualValue=helperMethods.ActualValue(WebDriverManager.getWebDriver(),labelName);

		if( containsFlag.equalsIgnoreCase( "should" ) ) //$NON-NLS-1$
		{
			if(!actualValue.contains(expectedValue)) 
			{
				Assert.assertTrue(false, "The value '" + actualValue + "' of property '" + property 
						+ "' doesn't match with the expected value of '" + expectedValue + "'");
			}
		}
		 } catch (Exception e) {
			 ExtentManager.logFailure("Fail: Failing in verifyPropertyValue");
			 throw e;
			}   
	}
	
	public void navigateToTile(  String commandName )
	{
		By commandButton= By.xpath("//div[@data-locator='tile-canvas']//div[@title='"+commandName+"']"); 
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),commandButton);
		helperMethods.click(WebDriverManager.getWebDriver(),commandButton);
	}
}

