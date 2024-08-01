// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.stepDefs;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.expleo.drivers.ExtentManager;
import com.expleo.drivers.WebDriverManager;
import com.expleo.pageObjects.LoginPage;
import com.expleo.utils.ConfigManager;
import com.expleo.utils.ExcelReader;
import com.expleo.utils.PropertyReader;
import com.expleo.utils.TestDataExcel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/**
 * Login page methods
 *
 */
public class LoginPageStepDefs {
	//private WebDriver driver;
	/**
	 * Constructor LoginPageStepDefs, sets up the driver
	 *
	 */	
	LoginPage loginPageObj= new LoginPage();
	/**
	 * Function to launch application
	 *
	 *@return null
	 * @throws Exception 
	 */
	@Given("user is on the AW login page")
	public void user_is_on_the_aw_login_page() throws Exception {
	
	String sheetName="TC01Login";
	String appLink=ConfigManager.getConfigValue("APPLINK");			
	loginPageObj.launchBrowser(appLink);
	  Map<Integer, Map<Integer, String>> sheetData = ExcelReader.getSheetData(sheetName);
	    // Assuming first row and first column contains the data key-value pairs
	    for (Map.Entry<Integer, Map<Integer, String>> entry : sheetData.entrySet()) {
	        Integer rowNum = entry.getKey();
	        Map<Integer, String> rowData = entry.getValue();
	        TestDataExcel.set(rowData.get(0), rowData.get(1));
	    }	
	}
	/**
	 * Function to launch application
	 *@param user
	 *@return null
	 */
	@When("^user enters username and password for \\\"([^\\\"]*)\\\" user$")
	public void user_enters_username_and_password_for_user(String user) throws Exception {		
		Map<String, String> allData = TestDataExcel.getAllData();
		System.out.println("*****************************************************");
		System.out.println("Below is the data retrieved from the buffer -> ");
	    if (allData != null && !allData.isEmpty()) {
	        for (Map.Entry<String, String> entry : allData.entrySet()) {
	            System.out.println(entry.getKey() + " : " + entry.getValue());
	        }
	    } else {
	        System.out.println("No test data found.");
	    }
		
			loginPageObj.loginToApplication(user);							
	}
	
	/**
	 * Function to verify the home page
	 *@param user
	 *@return null
	 */
	@When("^user is on home page$")
	public void user_verify_home_page() throws Exception {									
		loginPageObj.verifyTCHomePage();
	}
	
	/**
	 * Function to logout of application
	 *
	 *@return null
	 */
	@Then( "^I logout of the application$" )
	public void iLogoutOfTheApplication()
	{	
		
		ExtentManager.logInfo("Function: logout checking if the logout button exists or not");
		loginPageObj.iClickOnLogout();
		ExtentManager.logSuccess("Function logout, logout successful");
		 
	}
		
}
