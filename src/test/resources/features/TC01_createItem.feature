@createItem
Feature: Create a new item in AWC
Scenario: Login with user- engineer2 and create a new Item
Given user is on the AW login page
When user enters username and password for "engineer1" user
And user is on home page
When I navigate to "prop_Folders" tile 
When I click on "prop_New" command
And I click on "Add" command from popup
And I input "Item" in the filter text box
And I Select the "Item" Option and name it for folder
And I enter the "objectName_Item" in the create object name text
And I click the Add button to add the folder
And user search an item with name "objectName"
Then I logout of the application
