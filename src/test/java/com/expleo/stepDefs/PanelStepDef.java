package com.expleo.stepDefs;

import com.expleo.drivers.ExtentManager;
import com.expleo.pageObjects.PanelPage;

import io.cucumber.java.en.When;

public class PanelStepDef {

	
	PanelPage panelPage = new PanelPage();
	
	
	/**
	 * Step definition to select a value in ListBox
	 */		
	
	@When("^I select \"([^\"]*)\" value for \"([^\"]*)\" Property$")
	public void iSelectValuefromListBox(String PropertyValue, String PropertyName) {
		try {
			panelPage.selectValuefromListBox(PropertyValue, PropertyName);
		}  catch (Exception e) {
			ExtentManager.logFailure("Failing in iSelectValuefromListBox ");
			throw e;
		} 
	}
}
