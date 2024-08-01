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
import com.expleo.utils.ConfigManager;
import com.expleo.utils.PropertyReader;
import com.expleo.utils.XmlReader;

/**
 * Holds all the objects and methods related to Login page
 */

public class LoginPage extends BasePage{

	private final static Object lock = new Object();

	By lgnprofile= By.xpath("(//button[@aria-label=\\\"Your Profile\\\"])[2]");
	By lgout= By.xpath("//div[@anchor='aw_userLink']//button[@button-id='cmdSignOut']");	
	By txt_username= By.xpath(XmlReader.getLocator("login", "userName"));
	By txt_password= By.xpath(XmlReader.getLocator("login", "password"));
	By btn_signin= By.xpath(XmlReader.getLocator("login", "signIn"));
	//By btn_profileIcon= By.xpath("//div[contains(@class,'global-navigation-toolbar')]//button[contains(@class,'sw-avatar-generic')]");
//	By btn_signout= By.xpath("//div[contains(@class,'global-navigation')]//aside[contains(@class,'aw-sidenav-layoutContainer')]//button[@aria-label='Sign Out']");
	By btn_profileIcon= By.xpath(XmlReader.getLocator("login", "loginProfile"));
	By btn_signout= By.xpath(XmlReader.getLocator("login", "signOut"));
	
	By txtTC_Home_Page_Title= By.xpath("//div[@class='sw-row flex-auto sw-header-titleContainer']//h1[text()='Teamcenter']");
	
	/**
	 * Enters username and password in synchronized way for parallel execution
	 * 
	 *  @return null
	 */	
	public void enterUserNameAndPassword(String userName, String password) throws InterruptedException
	{	
		synchronized(lock)
		{

			Thread.sleep(1000);
			
			boolean loginSuccessful=false;
			int retry=2;
			while(!loginSuccessful && retry>0){			
				new HelperMethods().clickAndSendKeys(WebDriverManager.getWebDriver(),txt_username,userName);
				new HelperMethods().clickAndSendKeys(WebDriverManager.getWebDriver(),txt_password,password);
				new HelperMethods().click(WebDriverManager.getWebDriver(),btn_signin);
								
				loginSuccessful=new HelperMethods().waitAndCheckForObjectExistance(WebDriverManager.getWebDriver(),txtTC_Home_Page_Title);
				
				if(loginSuccessful)
				{
					ExtentManager.logSuccess("Function: Home page opened successfuly ");
					System.out.println("--------------------------------");
					System.out.println("Login is successful");
					System.out.println("--------------------------------");
				}
				else
				{
					ExtentManager.logInfo("Function: Home page is not opened, retrying the login again");
					System.out.println("******************************");
					System.out.println("Login REtry is Now");
					System.out.println("******************************");
				}
				retry--;
			}			
			Thread.sleep(2000);			
		}		 
	}
	
	/**
	 * Reads the username and password and decrypt them
	 * 
	 *  @return null
	 * @throws Exception 
	 */	
	
	public void loginToApplication(String user) throws Exception
	{
		try {
		String newUser= user.toUpperCase()+"USERNAME";
		String newPass= user.toUpperCase()+"PASSWORD";
		ExtentManager.logInfo("Function: Login started, username- "+user);
		String userName = ConfigManager.getConfigValue(newUser);
		String password = ConfigManager.getDecryptedPassword("secret.key",newPass);
	
			enterUserNameAndPassword(userName,password);
				}  catch (Exception e) {
					ExtentManager.logFailure("Failing in entering username and password ");
					throw e;
				} 
	}
	/**
	 * Opens the application in the given browser instance
	 * 
	 *  @return null
	 */	

	public void launchBrowser(String appLink)
	{
		try {
		new HelperMethods().openBrowser(WebDriverManager.getWebDriver(),appLink);
		}  catch (Exception e) {
			
			ExtentManager.logFailure("Failing in the launchBrowser step.");
			//throw e;
		}   
	}
	
	public void logout(){
		new HelperMethods().click(WebDriverManager.getWebDriver(),lgnprofile);
		new HelperMethods().waitForVisibility(WebDriverManager.getWebDriver(),lgout);
		new HelperMethods().click(WebDriverManager.getWebDriver(),lgout);
	}
	/**
	 * Checks and verifies the TC home page
	 * 
	 *  @return null
	 */		
	public void verifyTCHomePage()
	{

		boolean blnHomePageVerify= new HelperMethods().objectExist(WebDriverManager.getWebDriver(),txtTC_Home_Page_Title);
		if (!blnHomePageVerify) {
			ExtentManager.logFailure("Login is failed, can not find the teamcenter home page");
			 Assert.assertTrue(blnHomePageVerify, "Login is failed, can not find the teamcenter home page");
		}
		else {
			ExtentManager.logSuccess("Login is successful, teamcenter home page found successfully");
		}
		
		
	}
	/**
	 * checks the profile image and logs out of the application
	 * 
	 *  @return null
	 */		
	public void iClickOnLogout()
	{
		try {
		new HelperMethods().click(WebDriverManager.getWebDriver(),btn_profileIcon);
		new HelperMethods().waitForVisibility(WebDriverManager.getWebDriver(),btn_signout);
		new HelperMethods().click(WebDriverManager.getWebDriver(),btn_signout);
		ExtentManager.logSuccess("Function logout, logout successful");
		}  catch (Exception e) {
			ExtentManager.logFailure("Failing in logout");		
		} 
	
	}
}
