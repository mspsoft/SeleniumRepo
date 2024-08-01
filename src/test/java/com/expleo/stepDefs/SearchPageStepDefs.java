// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

//SearchPageStepDef is added by Ashwani Jain to search an item, folder, and LOV Property.
package com.expleo.stepDefs;
import com.expleo.drivers.ExtentManager;
import com.expleo.pageObjects.SearchPage;

import io.cucumber.java.en.When;
/**
 * Holds all the search page step definitions
 */
public class SearchPageStepDefs {
	
	SearchPage searchPage=new SearchPage();
	/**
	 * Step definition to click on the advance search link
	 */	
	@When( "^I click Advanced Search link$" )
    public void iClickAdvancedSearchLink()
    {
		
		searchPage.clickAdvancedSearchLink();
		
		
    }
	
	/**
	 * Step definition to set a value in LOV property
	 */		
	
	@When("^I Set LOV \"([^\"]*)\" value for LOV \"([^\"]*)\" Property$")
	public void iSetandLOVPropertyfromconext(String PropertyValue, String PropertyName) {
		
		searchPage.setLOVfromListBoxproperty(PropertyValue, PropertyName);
		
	}
	
	
    

}
