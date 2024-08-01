// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.drivers;


import org.openqa.selenium.WebDriver;

import com.expleo.helpers.HelperMethods;

	
/**
 * Sets up and returns the driver from WebDriverManager.
 */
public class BasePage extends HelperMethods {

	private WebDriver driver;

	
	/**
	 * This sets the current webdriver.
	 * @return null
	 */
	public BasePage() {
		setDriver(WebDriverManager.getWebDriver());
	}

	/**
	 * This returns current webdriver.
	 * 
	 * @return null
	 */
	public WebDriver getDriver() {
		return driver;
	}
	/**
	 * This sets the current webdriver.
	 * 
	 * @param driver - driver instance
	 * @return null
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}

