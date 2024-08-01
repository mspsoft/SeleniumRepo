package com.expleo.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.expleo.drivers.BasePage;
import com.expleo.helpers.HelperMethods;
import com.expleo.helpers.PropertyData;
import com.expleo.utils.ConfigManager;
import com.expleo.utils.XmlReader;

public class CreateItemPage extends BasePage{

    /**
     * HelperMethods class object instance	
     */
	HelperMethods helperMethods = new HelperMethods();
	
    /**
     * HomePage class object instance	
     */
	HomePage homePage = new HomePage();
	
    /**
     * ConfigManager class object instance	
     */
    ConfigManager configManger = new ConfigManager();	

    /**
     * PanelPage class object instance	
     */
   PanelPage panelPage = new PanelPage(); 
    /**
     * Property context
     */
    private static String NEW_TAB_PANEL = "New"; //$NON-NLS-1$
    
	 private static final String CREATECHANGE_COMMAND_TITLE = "Create Change";
	
    /**
     * Property context
     */
    private static String ADD_BUTTON_LABEL = "Add"; //$NON-NLS-1$	
	    
	By itemLocator = By.xpath(XmlReader.getLocator("panel", "itemlocator"));
	/**
	 * 
	 * @param propertyNames List of Property/Item Names
	 * @param context 
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	public void storeItemDetails(List<String> propertyNames, String context) throws Exception {

		helperMethods.sleep(10000);
		for (String propertyName : propertyNames) {
			
			if(propertyName.contains("prop_"))
			{
				propertyName = configManger.getGlobalDataConfigValue(propertyName);
			}
			
			String xpath = XmlReader.getDynamicLocator(itemLocator, propertyName);
			String item = getDriver().findElement(By.xpath(xpath)).getText();
			System.out.println("Item : "+item);
			helperMethods.sleep(3000);
			
			if (!item.isBlank()) {
				
				setItemDetails(propertyName, item);
				System.out.println("Stored value in Map");
				
			}
		}
	}
	
    /**
     * Creates object as per given input
     *
     * @param count The number of objects to be created
     * @param objectType type name
     * @param propValues create input
     * @throws Exception 
     */
    public void createObject( int count, String objectType, List<Map<String, String>> propValues ) throws Exception
    {
        List<PropertyData> propList = getPropertyDataAsList( propValues );
        createObjectWithPropData( count, objectType, propList );
    }

    /**
     * Convert map of property values to the property data first.
     *
     * @param propValues create input property values
     * @return the list of property data containing the property values
     */
    private List<PropertyData> getPropertyDataAsList( List<Map<String, String>> propValues )
    {
        List<PropertyData> propList = new ArrayList<>( propValues.size() );
        for( Map<String, String> propData : propValues )
        {
            PropertyData prop = new PropertyData();
            prop.property = propData.get( "property" ); //$NON-NLS-1$
            prop.value = propData.get( "value" ); //$NON-NLS-1$
            if( propData.get( "type" ) == null ) //$NON-NLS-1$
            {
                prop.type = "String"; //$NON-NLS-1$
            }
            else
            {
                prop.type = propData.get( "type" ); //$NON-NLS-1$
            }
            propList.add( prop );
        }
        return propList;
    }

    
    /**
     * Creates object as per given input without data table .
     *
     * @param count The number of objects to be created
     * @param objectType type name
     * @param propValues create input
     * @throws Exception 
     */
    public void createObjectWithPropData( int count, String objectType, List<PropertyData> propValues ) throws Exception
    {
    	
        for( int i = 0; i < count; i++ )
        {
        	
        	if(objectType.equalsIgnoreCase("Change")) {
        		homePage.executeCommand(CREATECHANGE_COMMAND_TITLE);
//                m_panelPage.filterTypeList( objectType,"Filter" );
//                m_panelPage.selectType( objectType );
        		fillPropertyValuesAndClickAdd( i, propValues);
//        		 m_panelPage.selectButton( CreateObjectPanelPage.CREATEandSUBMIT_BUTTON );
//				  SeleniumUtil.sleep(3000);
        	}else {

        		homePage.executeCommand(NEW_TAB_PANEL);  
           		homePage.executePopupCommand(ADD_BUTTON_LABEL);
           		panelPage.filterTypeList(objectType, "Search");      		
        		fillPropertyValuesAndClickAdd( i, propValues);
        	    panelPage.selectButton("footer", ADD_BUTTON_LABEL);
        		helperMethods.sleep(30000);
        		
        	}
        }
        	
        }
    
    /**
     * Fills in property values
     *
     * @param count count to data to generate sequence number _1, _2, _3, etc.
     * @param propValues List of propert with property names, values and type information
     * @throws Exception 
     */
    @SuppressWarnings("static-access")
	private void fillPropertyValuesAndClickAdd( int count, List<PropertyData> propValues ) throws Exception
    {
        for( PropertyData objProp : propValues )
        {
        	String value = null;
        	String propertyName = null;
        	switch( objProp.type )
            {
            case "String": //$NON-NLS-1$
            	if(objProp.value.contains("prop_") && (objProp.property.contains("prop_"))){
            		value = configManger.getGlobalDataConfigValue(objProp.value); 
            		propertyName = configManger.getGlobalDataConfigValue(objProp.property);
            		System.out.println(" : " +value+" : "+propertyName);
 //           		value = value.replace( "%s", String.valueOf( count + 1 ) ); //
            		panelPage.enterPropertyValue(value, propertyName);            		
            	}
            	else
            	{
                panelPage.enterPropertyValue(objProp.value, objProp.property);
            	}
            	
  
                break;

            case "Lov": //$NON-NLS-1$
            	if(objProp.value.contains("Excel_")) {
            		value = configManger.getGlobalDataConfigValue(objProp.value);            		
            	}
            	if(objProp.property.contains("Excel_"))
            	{
            		propertyName = configManger.getGlobalDataConfigValue(objProp.property);	
            	}
            	panelPage.SelectValuefromListBox(objProp.value, objProp.property, "panel");
                break;
            
            case "checkbox": //$NON-NLS-1$
            	
//            	if(objProp.value.contains("Excel_")) {
            		
     //            value =  DataStore.getCommonAttributesmap(objProp.value);
//            	}
//            	if(objProp.property.contains("Excel_"))
//            	{
   //         		propertyName = DataStore.getCommonAttributesmap(objProp.property);	
//            	}
       //     		m_propertyWidgetPage.checkUncheckCheckbox( propertyName, value, "panel", true, false ); //$NON-NLS-1$
            }
        }
//        m_panelPage.selectButton( CreateObjectPanelPage.getADD_BUTTON_LABEL() );
    }
}
