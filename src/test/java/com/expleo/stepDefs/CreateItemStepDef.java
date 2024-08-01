package com.expleo.stepDefs;

import java.util.List;

import com.expleo.pageObjects.CreateItemPage;
import com.expleo.utils.ConfigManager;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CreateItemStepDef {

	CreateItemPage createItempage = new CreateItemPage();
	
    /**
     * ConfigManager class object instance	
     */
    ConfigManager configManger = new ConfigManager();	
    
	/**
     * Step definition to store item details 
     * param
	 * @throws Exception 
     */		
	@When("^I store following item details from (page|panel)$")
	public void iGetItemDetails(String context, DataTable dataTable) throws Exception {
		    	
		List<String> propertyNames = dataTable.asList(String.class);
		createItempage.storeItemDetails(propertyNames, context);
		
	}
	//I store \"([^\"]*)\" from (header|page|panel) for Property Name \"([^\"]*)\
	
	@SuppressWarnings("static-access")
	@Given( "^I create (\\d*) (?:object|objects) of type \"([^\"]*)\" with$" )
    public void iCreateObject( int count, String objectType, DataTable objectProps ) throws Exception
    {
    	if(objectType.contains("prop_")) {
    		
    		createItempage.createObject( count, configManger.getGlobalDataConfigValue(objectType), objectProps.asMaps( String.class, String.class ) );
        }
    	else {
    		createItempage.createObject( count, objectType, objectProps.asMaps( String.class, String.class ) );

    	}
    }
	
}
