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
 * Holds all the objects and methods related to AW Home page
 */
public class HomePage extends BasePage{

	HelperMethods helperMethods = new HelperMethods();

	By commandButton= By.xpath(XmlReader.getLocator("home", "cmdbtn"));
	By btnFolders= By.xpath("//div[@class='aw-commands-showIconLabel aw-commands-commandIconButtonText' and text()='Folders']");
	By btnNewObject= By.xpath("//div[@class='aw-commands-showIconLabel aw-commands-commandIconButtonText' and text()='New']");
	By lnkAddObject= By.xpath("//li[@button-id='Awp0ShowCreateObject']");
	By lnkItemObject= By.xpath("//div[@title='Item']");
	By txtITemObjectName= By.xpath("//textarea[@name='object_name']");
	By btnItemAdd= By.xpath("//div[@class='aw-panel-footer sw-row flex-wrap']//div[text()='Add']");
	By txtSearchAdd= By.xpath("//input[@name='searchBoxLOVFilterStr']");
	By txtItemSuccessMessage= By.xpath("//span[@class='noty_text']");
	By btnItemSuccessPopupClose= By.xpath("//div[@class='noty_close']");
	By txtItemNameFromRightPanel= By.xpath("//span[@data-locator='Name']");
	By btnAdvanceSearch= By.xpath("//a[@title='Advanced Search']");
	By btnClearAll= By.xpath("//a[@title='Clear All']");
	By btnAdvanceQueryName= By.xpath("//input[@aria-label='Advanced Query Name']");
	By lstItemFromAdvanceSearchList= By.xpath("//div[@title='Item...']");
	By txtAdvanceSearchSearchTextBox= By.xpath("//textarea[@aria-label='Name']");
	By btnSearchButton= By.xpath("//div[text()='Search']");
	By btnItemListItem= By.xpath("//div[@title='Item...']");
	By btnFolderListItem= By.xpath("//div[@title='General...']");
	By btnHome= By.xpath("//div[@anchor='aw_globalNavigationbar']//button[@button-id='Awp0GoHome']");
	By LOVSearch= By.xpath("//input[@aria-label='searchBoxLOVFilterStr']");
	By panelFooterButton = By.xpath("//div[@class='aw-layout-declarativePanelRoot aw-layout-panel'] //div[contains(@class,'aw-panel-footer')]//button");
	By btnItemIDListItem= By.xpath("//div[@title='Item ID']");
	By btn_HomeFolder= By.xpath("//button[@button-id='Awp0ShowHomeFolder']");
	
	/**
	 * Clicks on the folder icon from left panel
	 * @return null
	 */
	public void userClicksOnFolderIcon() throws InterruptedException
	{
		try {
		helperMethods.click(WebDriverManager.getWebDriver(),btn_HomeFolder);
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: failing in navigate to folder panel");
	    	 throw e;
	     	} 

	}
	/**
	 * adds new item in the in the AW
	 * @param str
	 * @return null
	 */
	public void addNewItem(String str)
	{
		try {
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnFolders);
		helperMethods.click(WebDriverManager.getWebDriver(),btnFolders);
		ExtentManager.logSuccess("Successfully clicked element - button folders.");
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnNewObject);
		helperMethods.click(WebDriverManager.getWebDriver(),btnNewObject);
		ExtentManager.logSuccess("Successfully clicked element - button new object.");
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),lnkAddObject);
		helperMethods.click(WebDriverManager.getWebDriver(),lnkAddObject);
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),lnkItemObject);
		helperMethods.click(WebDriverManager.getWebDriver(),lnkItemObject);    	    	
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),txtITemObjectName);
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),txtITemObjectName,str);
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnItemAdd);
		helperMethods.click(WebDriverManager.getWebDriver(),btnItemAdd);
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnItemSuccessPopupClose);
		helperMethods.click(WebDriverManager.getWebDriver(),btnItemSuccessPopupClose);
	
	  } catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to create new item");
		// throw e;
   	}
	}
	
	/**
	 * Click on advance search
	 */
	public void clickonAdvanceSearch()
	{
		try {
		helperMethods.click(WebDriverManager.getWebDriver(),btnHome);
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceSearch);
		helperMethods.click(WebDriverManager.getWebDriver(),btnAdvanceSearch);
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to click on advance search button");
		// throw e;
		}
	}
		
	

	/**
	 * Click on clear all button
	 */
	public void clickonClearAllButton()
	{
		try {
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnClearAll);
		helperMethods.click(WebDriverManager.getWebDriver(),btnClearAll);
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to click on clear all button");
		// throw e;
		}
	}
	
	/**
	 * Click on SelectGeneralFromAdvanceSearch
	 */
	public void SelectGeneralFromAdvanceSearch()
	{
		try {
			ExtentManager.logInfo("Step: Enter General... into the query");		
			helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceQueryName);
			helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),btnAdvanceQueryName,"General...");
			ExtentManager.logSuccess("Step: Enter General... into the query is successful");
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to Enter General... into the query is");
		// throw e;
		}
	}
	
	/**
	 * Click on SelectGeneralFromAdvanceSearch
	 */
	public void SelectFolderItemFromList()
	{
		try {
			helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnFolderListItem);
			helperMethods.click(WebDriverManager.getWebDriver(),btnFolderListItem);
			ExtentManager.logSuccess("Step: Select folder from the list item is successful");
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to Select folder from the list item");
		// throw e;
		}
	}
	
	
	/**
	 * EnterItem in the advance query searchtextbox
	 */
	public void clickonAdvanceQuryNameTextBox(String objectTypeToSearch)
	{
		try {
			helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceQueryName);
			helperMethods.click(WebDriverManager.getWebDriver(),btnAdvanceQueryName);
			helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),txtSearchAdd,objectTypeToSearch);
			helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnItemListItem);
			helperMethods.click(WebDriverManager.getWebDriver(),btnItemListItem);

		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to clickonAdvanceQuryNameTextBox");
		// throw e;
		}
	}
	
	/**
	 * EnterItemNameInSearchTextBox
	 */
	public void EnterItemNameInSearchTextBox(String str)
	{
		try {
			helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox);		
			helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox,str);
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to EnterItemNameInSearchTextBox");
		// throw e;
		}
	}

		
	/**
	 * ClickOnSearchButton
	 */
	public void ClickOnSearchButton()
	{
		try {
			helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnSearchButton);
			helperMethods.click(WebDriverManager.getWebDriver(),btnSearchButton);
		} catch (Exception e) {
			ExtentManager.logFailure("Fail: Failed to click on ClickOnSearchButton");
		// throw e;
		}
	}
	
	
	/**
	 * Searches an item in the in the AW
	 * @param str
	 * @return null
	 */
	public void searchAnItem(String str)
	{
		try {
		helperMethods.click(WebDriverManager.getWebDriver(),btnHome);
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceSearch);
		helperMethods.click(WebDriverManager.getWebDriver(),btnAdvanceSearch);

		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnClearAll);
		helperMethods.click(WebDriverManager.getWebDriver(),btnClearAll);

		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceQueryName);
		helperMethods.click(WebDriverManager.getWebDriver(),btnAdvanceQueryName);

		helperMethods.clickLOVSendKeys(WebDriverManager.getWebDriver(),LOVSearch,"Item");

		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnItemListItem);
		helperMethods.click(WebDriverManager.getWebDriver(),btnItemListItem);

		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox);		
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox,str);

		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnSearchButton);
		helperMethods.click(WebDriverManager.getWebDriver(),btnSearchButton);
		  } catch (Exception e) {
				ExtentManager.logFailure("Fail: Failed in search item");
			// throw e;
	   	}
	}
	/**
	 * Search a folder  in the AW
	 * @param str
	 * @return null
	 */
	public void searchAFolder(String str)
	{
		try {
		ExtentManager.logInfo("Step: Click on the advance search button");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceSearch);
		helperMethods.click(WebDriverManager.getWebDriver(),btnAdvanceSearch);
		ExtentManager.logSuccess("Step: Click on the advance search button is successful");
		
		ExtentManager.logInfo("Step: Click on clear all button");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnClearAll);
		helperMethods.click(WebDriverManager.getWebDriver(),btnClearAll);
		ExtentManager.logSuccess("Step: Click on clear all button is successful");
		
		ExtentManager.logInfo("Step: Enter General... into the query");		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceQueryName);
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),btnAdvanceQueryName,"General...");
		ExtentManager.logSuccess("Step: Enter General... into the query is successful");

		ExtentManager.logInfo("Step: Select folder from the list item");
		
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnFolderListItem);
		helperMethods.click(WebDriverManager.getWebDriver(),btnFolderListItem);
		ExtentManager.getTestThread().get().pass("Step: Select folder from the list item is successful");

		System.out.println("Now waiting for search textbox ");
		ExtentManager.getTestThread().get().info("Step: Enter the folder name to search -> "+str);
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox);
		System.out.println("Now adding values into for search textbox ");
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox,str);
		ExtentManager.getTestThread().get().pass("Step: Enter the folder name to search -> "+str+ " successful");
		System.out.println("Now clicking on search button ");
		ExtentManager.getTestThread().get().info("Step: Click on the search button -> "+str+ " successful");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnSearchButton);
		helperMethods.click(WebDriverManager.getWebDriver(),btnSearchButton);
		  } catch (Exception e) {
				ExtentManager.logFailure("Fail: Failed in search folder");
			 throw e;
	   	}
	}
	/**
	 * Verifies if the item has been created successfully
	 * @param str
	 * @return null
	 */

	public void verifyItemCreated(String str)
	{
		try {
		String txtFromApp="";
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),txtItemNameFromRightPanel);
		txtFromApp = helperMethods.getElementText(WebDriverManager.getWebDriver(),txtItemNameFromRightPanel);
		Assert.assertEquals(txtFromApp, str);
		 ExtentManager.logSuccess("Pass: Search item successful, item name "+str);
	  } catch (Exception e) {
		 ExtentManager.logFailure("Fail: Failing in search item");
		 //throw e;
		}  

	}
	/**
	 * Searches  the given object with property name and add revision for the same
	 * @param objectNameOrId
	 * @param propertyName
	 * @param revision
	 * @return null
	 */
	//------------------------------------------------------Code by Ashwani Jain----------------------------------------------------------------------       
	public void quickSearch( String objectNameOrId, String propertyName, String revision ) throws InterruptedException
	{
		try {
		System.out.println("Now searching an new item: "+objectNameOrId);
		ExtentManager.getTestThread().get().info("Step: Click on the advance search button");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceSearch);
		helperMethods.click(WebDriverManager.getWebDriver(),btnAdvanceSearch);
		ExtentManager.getTestThread().get().pass("Step: Click on the advance search button successful");
		System.out.println("Click on advance search completed");

		ExtentManager.getTestThread().get().info("Step: Click on the advance query text");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnAdvanceQueryName);
		helperMethods.click(WebDriverManager.getWebDriver(),btnAdvanceQueryName);
		ExtentManager.getTestThread().get().pass("Step: Click on the advance query text successful");    	
		Thread.sleep(5000);

		ExtentManager.getTestThread().get().info("Step: Select Item from the list box");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),LOVSearch);
		helperMethods.clickLOVSendKeys(WebDriverManager.getWebDriver(),LOVSearch,"Item");
		ExtentManager.getTestThread().get().pass("Step: Select Item from the list box successful");
		//search(LOVSearch,"Item");
		System.out.println("Click Query name is set item successfully");

		System.out.println("Now click the list item item ID from the list box ");
		Thread.sleep(5000);
		ExtentManager.getTestThread().get().info("Step: Select Item from the popup list");
		helperMethods.click(WebDriverManager.getWebDriver(),btnItemIDListItem);
		ExtentManager.getTestThread().get().pass("Step: Select Item from the popup list successful");

		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnClearAll);
		helperMethods.click(WebDriverManager.getWebDriver(),btnClearAll);
		System.out.println("Click on clear all completed");

		By txtAdvanceSearchSearchTextBox= By.xpath("//textarea[@aria-label='"+propertyName+"']");
		System.out.println("Now waiting for search textbox ");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox);
		System.out.println("Now adding values into for search textbox ");
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),txtAdvanceSearchSearchTextBox,objectNameOrId);
		System.out.println("Now clicking on search button ");
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),btnSearchButton);
		helperMethods.click(WebDriverManager.getWebDriver(),btnSearchButton);
		} catch (Exception e) {
			 ExtentManager.logFailure("Fail: Failing in quickSearch");
			 throw e;
			} 
	}
	/**
	 * Clicks on any option from the vertical command bar
	 * @param commandName
	 * @return null
	 * 
	 */
	public void executeCommand(  String commandName )
	{
		try {
		String xpath = XmlReader.getDynamicLocator(commandButton, commandName);
        By commandButton = By.xpath(xpath);
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),commandButton);
		helperMethods.click(WebDriverManager.getWebDriver(),commandButton);
		 } catch (Exception e) {
   			 ExtentManager.logFailure("Fail: Failing in iClickOnCommand");
   			 throw e;
   			} 
	}
	/**
	 * Selects the value of the pop up container
	 * @param commandName
	 * @return null
	 */

	public void executePopupCommand( String commandName )
	{
		try {
		By commandButton= By.xpath("//div[@class='sw-popup-contentContainer']//li[@aria-label='"+commandName+"']"); 
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),commandButton);
		helperMethods.click(WebDriverManager.getWebDriver(),commandButton);
		  } catch (Exception e) {
 			 ExtentManager.logFailure("Fail: Failing in iClickOnPopupCommand");
 			// throw e;
 			} 
	}
	
	/**
	 * Enters the property value in the left declarative panel
	 * @param desiredValue
	 * @param propertyName
	 * @param context
	 * @return null
	 */
	public void enterPropertyValue(String desiredValue, String propertyName, String context) throws InterruptedException {
		try{
		Thread.sleep(5000);
		By textButton= By.xpath("//div[@class='aw-layout-declarativePanelRoot aw-layout-panel']//textarea[@aria-label='"+propertyName+"']");
		Thread.sleep(5000);
		helperMethods.clear(WebDriverManager.getWebDriver(),textButton);
		helperMethods.clickAndSendKeys(WebDriverManager.getWebDriver(),textButton,desiredValue);
		  } catch (Exception e) {
	 			 ExtentManager.logFailure("Fail: Failing in iSetPropertyValue2");
	 			 throw e;
	 			} 

	}
	
	/**
	 * Clicks on the panel footer button
	 * @param buttonName
	 * @return null
	 */

	public void selectButton( String buttonName )
	{
		try {
		helperMethods.waitForVisibility(WebDriverManager.getWebDriver(),panelFooterButton);
		helperMethods.click(WebDriverManager.getWebDriver(),panelFooterButton);
		 } catch (Exception e) {
			 ExtentManager.logFailure("Fail: Failing in iClickButton");
			 throw e;
			} 
	}
	/**
	 * Selects the radio button from the  panel header
	 * @param radioButtonName
	 * @return null
	 */

	public void selectRadioOnPanelHeader( String radioButtonName ) throws InterruptedException
	{
		try{
		By panelRadioButton= By.xpath("//div[@class='aw-layout-declarativePanelRoot aw-layout-panel']//label[@class='sw-radio']//span[text()='"+radioButtonName+"']");
		Thread.sleep(5000);
		//waitForVisibility(panelRadioButton);
		helperMethods.click(WebDriverManager.getWebDriver(),panelRadioButton);
		helperMethods.click(WebDriverManager.getWebDriver(),panelRadioButton);
	
	 } catch (Exception e) {
			 ExtentManager.logFailure("Fail: Failing in iClickButton");
			 throw e;
			} 
	}
}
