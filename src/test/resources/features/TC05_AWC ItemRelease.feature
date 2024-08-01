@createItemRelease
Feature: TC07_AWC ItemRelease

  @Workflow
  Scenario: Verify that Owning user should able to release item revision from created Item in AW
    Given user is on the AW login page
	  When user enters username and password for "engineer5" user
	  And I Quick Search and "004890" open from Item ID map property name "Item ID" for revision ""
    And I select and read 1 specified objects in Table View
	  When I click on "prop_Open" command
    And I click on "prop_Open" command from popup
    When I click on "prop_New" command
    And I click on "prop_SaveAsOrRevise" command from popup
    When I enter "prop_NewRevisionDesc_Value" for "prop_Description" property from panel
    And I click on "prop_Save" button 
    When I click on "prop_Manage" command
    And I click on "prop_SubmittoWorkflow" command from popup
    And I select "prop_All" radio button on panel header
    And I Set LOV "TCM" value for LOV "Template" Property
    And I click on "prop_Submit" button 
	  Then Property "Release Status" from page should contain "TCM Released"
    And I logout of the application