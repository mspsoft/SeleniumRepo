// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Holds all the methods to load the the configuration property file 
 */
public class PropertyReader {
	  private static final ThreadLocal<Properties> propertiesThreadLocal = ThreadLocal.withInitial(() -> {
	        Properties props = new Properties();
	        try (FileInputStream fis = new FileInputStream("src/test/resources/properties/config.properties")) {
	            props.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return props;
	    });
	  /**
	   * Method to get property value of given key 
	   * 
	   * @param key
	   * @return propertyValue
	   */	  
	  public static String getProperty(String key) {
	        return propertiesThreadLocal.get().getProperty(key);
	    }
}
