// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.stepDefs;
import java.util.Random;

import com.expleo.drivers.ExtentManager;
import com.expleo.drivers.ScenarioContext;
import com.expleo.drivers.ScenarioContextManager;
import com.expleo.drivers.WebDriverManager;
import com.expleo.helpers.HelperMethods;
import com.expleo.pageObjects.FolderPage;
import com.expleo.pageObjects.HomePage;
import com.expleo.utils.ConfigManager;
import com.expleo.utils.PropertyReader;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/**
 * Holds all the home page step definitions
 */

public class HomePageStepDefs {
    private ScenarioContext scenarioContext;
    Random rand;
    /**
     * constructor function used to define the search context instance
     */
    public HomePageStepDefs()
    {
        this.scenarioContext = ScenarioContextManager.getScenarioContext();
        rand = new Random();
    }
    

    /**
     * Step definition to create new item
     * @throws Exception 
     */
	@When("user creates a new item")
	public void userCreatesANewItemWithName() throws Exception {
		   
		   String itemName=ConfigManager.getTestDataValue("itemName");
		   ExtentManager.logInfo("Function: Create new item function started");		   
		   String newString= itemName + rand.nextInt(10000);
		   scenarioContext.setData("itemName", newString);		   
		   ExtentManager.logSuccess("Item to be created with item name: " + newString);
		   new HomePage().addNewItem(newString);
		   ExtentManager.logInfo("Function: Create new item function Completed");
	
	}	
    /**
     * Step definition to navigate to folder panel
     * @return null
     */	
	@When("user navigate to folder panel")
	public void user_navigate_to_folder_panel() throws InterruptedException {
		
		new HomePage().userClicksOnFolderIcon();
	
		
	}
	
    /**
     * Step definition to search an item with given name
     */		
	@When("^user clicks on the advanced search$")
	public void userClickOnAdvanceSearch() {
		    new HomePage().clickonAdvanceSearch();					   
	}
	
    /**
     * Step definition to search an item with given name
     */		
	@When("^user clicks on the clear all button$")
	public void userClickOnClearAllButton() {
		    new HomePage().clickonClearAllButton();					   
	}
	
    /**
     * Step definition to search an item with given name
     */		
	@When("^user Selects Folder item from the list$")
	public void userSelectsFolderFromList() {
		    new HomePage().SelectFolderItemFromList();					   
	}
	
    /**
     * Step definition to search an item with given name
     */		
	@When("^user Selects General in the advance query textbox$")
	public void userSelectsGeneralFromAdvanceQuery() {
		    new HomePage().SelectGeneralFromAdvanceSearch();					   
	}
	
	
    /**
     * Step definition to enter item in the advance query textbox
     */		
	@When("^user enters \"([^\"]*)\" in the advance query textbox$")
	public void userEnterItemInAdvanceSearchTextBox(String str) {
		    new HomePage().clickonAdvanceQuryNameTextBox(str);					   
	}
	
    /**
     * Step definition to enter item in the advance query textbox
     */		
	@When("^user enters an item with name \"([^\"]*)\"$")
	public void EnterItemNameInSearchTextBox(String objectName) {
		    new HomePage().EnterItemNameInSearchTextBox(objectName);					   
	}
	
    /**
     * Step definition to enter item in the advance query textbox
     */		
	@When("^user clicks on the search button$")
	public void userClickSearchButton() {
		    new HomePage().ClickOnSearchButton();					   
	}
	
    /**
     * Step definition to search an item with given name
     * @param string
     * @return null
     */		
	@When("^user search an item with name \"([^\"]*)\"$")
	public void userSearchAnItemWithName(String objectName) {
		
			ExtentManager.logInfo("Function: Search item started");
			if (scenarioContext.getData(objectName)!=" ")
			{
				objectName=(String) scenarioContext.getData(objectName);
			}
			ExtentManager.logInfo("SearchITem: Searching for item with name- "+objectName);			
		    new HomePage().searchAnItem(objectName);					   
	}
	
    /**
     * Step definition to search an item with given name
     * @param string
     * @return null
     */		
	@When("^verify search an item with name \"([^\"]*)\"$")
	public void userVerifySearchAnItemWithName(String objectName) {
		
			if (scenarioContext.getData(objectName)!=" ")
			{
				objectName=(String) scenarioContext.getData(objectName);
			}			
		    ExtentManager.logInfo("SearchITem: Now validating the results of search item- "+objectName);
			new HomePage().verifyItemCreated(objectName);
			
	}
	
    /**
     * Step definition to search a folder with neme
     */		
	@When("user search a folder with name {string}")
	public void userSearchFolderWithName(String string) {
	 
		  
		  ExtentManager.logInfo("Function: Now in Searchfolder function");
		   new HomePage().searchAFolder(string);
	  
		 
	}
   
    
    /*
     * This check is added to locale specific tests - Locale specific tests expects exacts matches while
     * nagivateToTile() internally uses contains method for string verification
     */
    @When( "^I navigate to \"([^\"]*)\" tile$" )
    public void iNavigateToPage( String tileName ) throws Exception
    {
    	FolderPage folderpage = new FolderPage();
        
    	if(tileName.contains("prop_")) {
    		tileName = ConfigManager.getGlobalDataConfigValue(tileName);
        	
		}
    	folderpage.navigateToTile( tileName );
		
    }
    /**
     * Step definition to click on new button
     */		    
    @When("I click on the New button")
    public void i_click_the_New_button() {
    	
    	FolderPage folderpage = new FolderPage();
    	ExtentManager.logInfo("Step: Click on the New button");
    	folderpage.newObjectClick();
    	
       
    }
    /**
     * Step definition to click Add to link under the CONTENTS Section
     */		    
     
    @Then("I click the Add to link under the CONTENTS Section")
    public void i_click_the_add_to_link_under_the_contents_section() {
    	
    	FolderPage folderpage = new FolderPage();
    	ExtentManager.getTestThread().get().info("Step: Click on Add to link");
    	folderpage.addToLink();
    	
       
    }
    /**
     * Step definition to send input folder in search text box
     */	    
    @When("^I input \"([^\"]*)\" in the filter text box$")
    public void i_input_folder_search_text_box(String itemTypeToSearch) throws Exception {    	
    	FolderPage folderpage = new FolderPage();
    	folderpage.folderSearchTextBox(itemTypeToSearch);    
    }

    /**
     * Step definition to select folder option and name it folder
     */	     
    @Then("^I Select the \"([^\"]*)\" Option and name it for folder$")
    public void i_select_the_folder_option_and_name_it_for_folder(String objectName) throws Exception {
    	FolderPage folderpage = new FolderPage();
    	folderpage.ObjectTextLinkClick(objectName);

    }
    
    /**
     * Step definition to send input folder in search text box
     */	    
    @When("^I enter the \"([^\"]*)\" in the create object name text$")
    public void i_input_objectName_text_box(String objectNameToCreate) throws Exception {
        	Random rand = new Random();
        	String objectName=objectNameToCreate+rand.nextInt(10000);
    	   scenarioContext.setData("objectName", objectName);	
	    	FolderPage folderpage = new FolderPage();
	    	folderpage.folderNameTextBox(objectName);

    }
    
    /**
     * Step definition to click on add button to add folder
     */	     
     
    @Then("I click the Add button to add the folder")
    public void i_click_the_add_button_to_add_the_folder() {
    	
    	FolderPage folderpage = new FolderPage();
    
    	folderpage.panelAddButton();    	
      
    }
    /**
     * Step definition to select property from panel
     */	    
    //------------------------------------------------------Code by Ashwani Jain----------------------------------------------------------------------    
    @Then("^Property \"([^\"]*)\" from (header|page|panel) (should|should not) contain \"([^\"]*)\"$")
	public void iverifyPropertyValue(String property, String context, String containsFlag, String value) throws InterruptedException {
   
    	FolderPage folderpage = new FolderPage();
    	folderpage.verifyPropertyValue(property, containsFlag, value, context);
	 
    }
    /**
     * Step definition to do quick search an item with item ID
     */	     
    @When( "^I Quick Search and \"([^\"]*)\" open from Item ID map property name \"([^\"]*)\" for revision \"([^\"]*)\"$" )
	public void SearchfromGivenItemID(String key, String propertyName, String revision ) throws InterruptedException
	{
    	
    	HomePage homePage=new HomePage();
		
    	homePage.quickSearch( key, propertyName, revision );
  	  
	}
    /**
     * Step definition to send input folder in search text box
     */	     
    @When("^I select and read (\\d+) specified objects in Table View$") 
    public void iSelectAndReadObjectInTableView( Integer itemCount) throws InterruptedException
    {
    	
    	new HelperMethods().selectAndReadObjectInTableView(WebDriverManager.getWebDriver(), itemCount);
   	 
    }
    /**
     * Step definition to click on a command
     * @throws Exception 
     */	
    @When( "^I click on \"([^\"]*)\" command$" )
    public void iClickOnCommand( String command ) throws Exception
    {
    	
			Thread.sleep(3000);
		
    	if(command.contains("prop_"))
		{
		 // command = PropertyReader.getProperty(command);
    		 command = ConfigManager.getGlobalDataConfigValue(command);
		}
    	new HomePage().executeCommand( command ); 
     	 
    	  
    }
    /**
     * Step definition to click on a command from popup
     * @throws Exception 
     */	    
    @When( "^I click on \"([^\"]*)\" command from popup$" )
    public void iClickOnPopupCommand( String command ) throws Exception
    {
    	
	    	if(command.contains("prop_"))
			{
	    		command = ConfigManager.getGlobalDataConfigValue(command); 
			}
	    	new HomePage().executePopupCommand(command );
    	  
    }
    
    /**
     * Step definition to click on a command from popup
     * @throws Exception 
     */	       
    
    @When( "^I enter \"([^\"]*)\" for \"([^\"]*)\" property from (page|panel|popup)$" )
    public void iSetPropertyValue2( String propertyValue, String propertyName, String context ) throws Exception
    {
    	
    	  if(propertyName.contains("prop_"))
			{
    		 // propertyValue = PropertyReader.getProperty(propertyValue);
    		  propertyValue = ConfigManager.getGlobalDataConfigValue(propertyValue);
			}
    	  if(propertyName.contains("prop_"))
			{
    		 // propertyName = PropertyReader.getProperty(propertyName);
    		  propertyName = ConfigManager.getGlobalDataConfigValue(propertyName);
			}
        
    	  new HomePage().enterPropertyValue(propertyValue, propertyName, context);
     	
    }
    
    /**
     * Step definition to click on a button from popup
     * @throws Exception 
     */	       
    @When( "^I click on \"([^\"]*)\" button$" )
    public void iClickButton( String buttonName ) throws Exception
    {
    	
    	if(buttonName.contains("prop_"))
		{
    		//buttonName = PropertyReader.getProperty(buttonName);
    		buttonName = ConfigManager.getGlobalDataConfigValue(buttonName);
		}
    	new HomePage().selectButton( buttonName );
   	 
    	  
    }
    /**
     * Step definition to select radio button from panel
     */	       
    @Then( "^I select \"([^\"]*)\" radio button on panel header$" )
    public void iSelectRadioOnPanelHeader( String panelRadioHeaderName ) throws InterruptedException
    {  
    	
    	if(panelRadioHeaderName.contains("prop_"))
		{
    		panelRadioHeaderName = PropertyReader.getProperty(panelRadioHeaderName);
		}
    	new HomePage().selectRadioOnPanelHeader( panelRadioHeaderName );
     	 
    	
    }

}
