@createFolder
Feature: Create a new folder in AWC
Scenario: Login with user- engineer1 and create a new folder with dynamic name
Given user is on the AW login page
When user enters username and password for "engineer1" user
When I click on "prop_Folders" command
When I click on "prop_New" command
And I click on "Add" command from popup
And I input "Folder" in the filter text box
And I Select the "Folder" Option and name it for folder
And I enter the "objectName" in the create object name text
And I click the Add button to add the folder
Then I logout of the application