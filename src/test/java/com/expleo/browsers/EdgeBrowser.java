// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.browsers;

import org.openqa.selenium.edge.EdgeOptions;
/**
 * Sets all the browsing options for Edge browser.
 */
public class EdgeBrowser {
	/**
	 * Sets edge browser options
	 * @return null
	 */
    public EdgeOptions setEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-application-cache");
        options.addArguments("-inprivate");

        return options;
    }
}
