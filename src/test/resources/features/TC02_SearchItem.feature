@searchitem
Feature: Search an existing item in AWC
Scenario: Login with user- engineer3 and search an existing item
Given user is on the AW login page
When user enters username and password for "engineer3" user
And user clicks on the advanced search
And user clicks on the clear all button
And user enters "Item" in the advance query textbox
And user enters an item with name "item_16_May_1"
And user clicks on the search button
And verify search an item with name "item_16_May_1"
Then I logout of the application
