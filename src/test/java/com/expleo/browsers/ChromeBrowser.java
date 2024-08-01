// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.browsers;

import org.openqa.selenium.chrome.ChromeOptions;
/**
 * Sets all the browsing options for chrome browser.
 */
public class ChromeBrowser {

	/**
	 * Sets all the browsing options for chrome browser.
	 * @return options
	 */
	public ChromeOptions setChromeOptions() {
		
        ChromeOptions options = new ChromeOptions();
        
        options.addArguments("--start-maximized");
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-translate");
        options.addArguments("--always-authorize-plugins");
        options.addArguments("--disable-infobars");
        options.addArguments("--enable-automation");

        return options;
    }
}