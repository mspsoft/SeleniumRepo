@searchfolder
Feature: search folder AWC application
Scenario: Login with user- engineer4 and search an existing foler
Given user is on the AW login page
When user enters username and password for "engineer4" user
And user clicks on the advanced search
And user clicks on the clear all button
And user Selects General in the advance query textbox
And user Selects Folder item from the list
And user enters an item with name "item_16_May_1"
And user clicks on the search button
#And user search a folder with name "item_16_May_1"
Then I logout of the application
