package com.expleo.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.expleo.drivers.BasePage;
import com.expleo.drivers.WebDriverManager;
import com.expleo.helpers.HelperMethods;
import com.expleo.utils.XmlReader;

public class PanelPage extends BasePage{

    /**
     * HelperMethods class object instance	
     */
	HelperMethods helperMethods = new HelperMethods();
	
    /**
     * SearchPage class object instance	
     */
	SearchPage searchPage = new SearchPage();
	
	By fltrsearchBox = By.xpath(XmlReader.getLocator("panel", "fltrsearchBox"));
	By selectlistBox = By.xpath(XmlReader.getLocator("panel", "selectlistBox"));
	By filterBox = By.xpath(XmlReader.getLocator("panel", "filterBox"));
//	By propertyVal = By.xpath(XmlReader.getLocator("panel", "propertyVal"));
	By propertylistBox = By.xpath(XmlReader.getLocator("panel", "propertylistBox")); 
	By selectLovValue = By.xpath(XmlReader.getLocator("panel", "selectLovValue")); 
	By panelFooterButtonLocator = By.xpath(XmlReader.getLocator("panel", "panelFooterButtonLocator"));
	
	
	/**
	 * 
	 * @param propertyName Attribute name of item on page/panel
	 * @param propertyValue Attribute value of item on page/panel
	 */
	public void selectValuefromListBox(String propertyName, String propertyValue)
	{
		fltrsearchBox = By.xpath(XmlReader.getDynamicLocator(fltrsearchBox, propertyName));
		if(new HelperMethods().getText(getDriver(), fltrsearchBox).contains(propertyValue))
		{
			System.out.println("The Value "+propertyValue+" is already selected.");
		}
		else
		{			
		searchPage.searchBoxLOVFilter(propertyValue);
		selectlistBox = By.xpath(XmlReader.getDynamicLocator(selectlistBox, propertyValue));
		new HelperMethods().waitForVisibility(WebDriverManager.getWebDriver(), selectlistBox);
		new HelperMethods().click(WebDriverManager.getWebDriver(), selectlistBox);
		}
	}
	
    public void SelectValuefromListBox(String desiredValue, String propertyName, String Context) {
        
		propertylistBox = By.xpath(XmlReader.getDynamicLocator(propertylistBox, propertyName));
		helperMethods.click(getDriver(), propertylistBox);

		if (!helperMethods.isDisplayed(getDriver(), searchPage.searchBoxFilter)) {
			helperMethods.click(getDriver(), propertylistBox);
		}

		if (helperMethods.isDisplayed(getDriver(), searchPage.searchBoxFilter)) {

			searchPage.searchBoxLOVFilter(desiredValue);

		} else {
			System.out.println("Property " + propertyName + " is not a list with search input");
		}

		selectValue(desiredValue);
        
    }
	
    /**
     * Enter text to type list filter
     *
     * @param textToFilter text to filter in create panel
     */
    public void filterTypeList( String textToFilter, String placeholder )
    {
    	
        if(placeholder.contains("Search")) {

        	searchPage.searchBoxLOVFilter(textToFilter);
        	String xpath = XmlReader.getDynamicLocator(filterBox, textToFilter);
        	helperMethods.waitForVisibility(getDriver(), By.xpath(xpath));
//        	helperMethods.scrollInto(By.xpath(xpath));
        	helperMethods.click(getDriver(), By.xpath(xpath));
        	
        }
//        else {
//        	if(!placeholder.contains("filter"))
//        	{
            		
//        	}
//        	else
//        	{
	        		
//        	}
//        }
    }
    
    public void enterPropertyValue(String desiredValue, String propertyName) {
    	
        helperMethods.sleep(5000);
        By propertyVal = By.xpath("//input[contains(@data-locator,'"+propertyName+"') and @class='sw-property-val'] | //textarea[contains(@data-locator,'"+propertyName+"') and @class='sw-property-val']");      
    	helperMethods.waitForVisibility(getDriver(), propertyVal);
    	helperMethods.clickAndSendKeys(getDriver(), propertyVal, desiredValue);                
    }
	
    /**
     * Gets locator for panel body.
     *
     * @param panelArea title/header/body/footer
     * @return The locator given panel area.
     */
    public By  panelbuttonAreaLocator( String panelArea, String buttonName )
    {

    	By panelButtonLocator = null;
    	if( panelArea.equals( "title" ) ) //$NON-NLS-1$
        {
           
        }
        else if( panelArea.equals( "header" ) ) //$NON-NLS-1$
        {

        }
        else if( panelArea.equals( "body" ) ) //$NON-NLS-1$
        {
 
        }
        else if( panelArea.equals( "footer" ) ) //$NON-NLS-1$
        {
        	panelButtonLocator = By.xpath(XmlReader.getDynamicLocator(panelFooterButtonLocator, buttonName));
        }
        else
        {
            
        }
    	return panelButtonLocator;
    }
    
    public void selectButton(String panelArea, String buttonName)
    {
    	By panelButtonLocator = panelbuttonAreaLocator(panelArea,buttonName);
    	helperMethods.waitForVisibility(getDriver(), panelButtonLocator);
        helperMethods.click(getDriver(), panelButtonLocator);
    	
    }
    
    public void selectValue(String desiredValue)
    {    	
    	selectLovValue = By.xpath(XmlReader.getDynamicLocator(selectLovValue, desiredValue));
        helperMethods.click(getDriver(), selectLovValue);
    }

}
