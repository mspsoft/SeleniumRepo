// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.drivers;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
/**
 * Sets the webdriver driver threadsafe instance
 */ 
public class WebDriverManager {
		private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();	
		
		/**
		 * Sets the webdriver driver threadsafe instance
		 */ 
	    public static void setWebDriver(WebDriver driver) {
	        webDriver.set(driver);
	    }
		/**
		 * Gets the webdriver driver threadsafe current instance
		 */ 
	    public static WebDriver getWebDriver() {
	        return webDriver.get();
	    }
		/**
		 * Quits and closes the webdriver driver current instance
		 */ 
	    public static void quitWebDriver() {
	        WebDriver driver = webDriver.get();
	        if (driver != null) {
	            driver.quit();
	            webDriver.remove();
	        }
	    }
}
