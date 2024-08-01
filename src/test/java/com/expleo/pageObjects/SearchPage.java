// @<COPYRIGHT>@search
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

//SearchPageStepDef is added by Ashwani Jain to search an item, folder, and LOV Property.
package com.expleo.pageObjects;

import org.openqa.selenium.By;
import com.expleo.drivers.ExtentManager;
import com.expleo.drivers.BasePage;
import com.expleo.drivers.WebDriverManager;
import com.expleo.helpers.HelperMethods;
import com.expleo.utils.XmlReader;
/**
 * Holds all the objects and methods related search
 */
public class SearchPage extends BasePage
{

	HomePage homePage=new HomePage();
	FolderPage folderPage=new FolderPage();

	By btnAdvanceSearch= By.xpath("//a[@title='Advanced Search']");
	By TCMDropdown= By.xpath("//div[@class='aw-base-scrollPanel']//ul//li[@aria-label='TCM Release Process']");
	By searchBoxFilter = By.xpath(XmlReader.getLocator("search", "filtersearchBox"));
	
	/**
	 * Clicks on the advance search link
	 * 
	 * @return null
	 */	
	public void clickAdvancedSearchLink()
	{
		try {
		new HelperMethods().click(WebDriverManager.getWebDriver(), btnAdvanceSearch);
		}  catch (Exception e) {
			ExtentManager.logFailure("Failing in iClickAdvancedSearchLink ");
			throw e;
		} 

	}
	/**
	 * Selects the value from LOV dropdown
	 * 
	 *  @return null
	 */	
	public void setLOVfromListBoxproperty(String PropertyValue, String PropertyName) 
	{
		try {
		By LOVValue= By.xpath("//div[contains(@class,'aw-layout-flexRowContainer')]//input[@aria-label='"+PropertyName+"']");
		new HelperMethods().waitForVisibility(WebDriverManager.getWebDriver(), LOVValue);
		new HelperMethods().clickAndSendKeys(WebDriverManager.getWebDriver(),LOVValue,PropertyValue);

		new HelperMethods().waitForVisibility(WebDriverManager.getWebDriver(),TCMDropdown);
		new HelperMethods().click(WebDriverManager.getWebDriver(),TCMDropdown);
			}  
			catch (Exception e) 
		{
			ExtentManager.logFailure("Failing in iSetandLOVPropertyfromconext ");
			throw e;
		} 

	}
	
	
	/**
	 * text to enter in search filter box
	 * 
	 * @param Value the value to enter in filterbox
	 */
	public void searchBoxLOVFilter(String Value)
	{		
		try 
		{
		new HelperMethods().clickAndSendKeys(WebDriverManager.getWebDriver(), searchBoxFilter, Value);
		}
		catch (Exception e) 
		{
			ExtentManager.logFailure("Failing in iSetandLOVPropertyfromconext ");
			throw e;
		} 
	}
	
}
