// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.expleo.drivers.ExtentManager;
/**
 * Holds all the methods to load the the configuration property file or test data file
 */
public class ConfigManager {

    private static final ConcurrentHashMap<String, String> configData = new ConcurrentHashMap<>();
    private static final String CONFIG_FILE_PATH = "src/test/resources/properties/config.properties";
	
	
	private static final ConcurrentHashMap<String, String> testData = new ConcurrentHashMap<>();
    private static final String TESTDATA_FILE_PATH = "src/test/resources/properties/testData.properties";
    
	private static final ConcurrentHashMap<String, String> globalPropertiesData = new ConcurrentHashMap<>();
    private static final String GLOBAL_PROPERTIES_FILE_PATH = "src/test/resources/properties/globalAttribute.properties";
    
    static {
        try {
        	
        	  Properties properties = new Properties();
              properties.load(Files.newInputStream(Paths.get(CONFIG_FILE_PATH)));
              for (String key : properties.stringPropertyNames()) {
              	configData.put(key, properties.getProperty(key));
              }
            Properties dataProperties = new Properties();
            dataProperties.load(Files.newInputStream(Paths.get(TESTDATA_FILE_PATH)));
            for (String key : dataProperties.stringPropertyNames()) {
            	testData.put(key, dataProperties.getProperty(key));
            }
            
            Properties globalProperties = new Properties();
            globalProperties.load(Files.newInputStream(Paths.get(GLOBAL_PROPERTIES_FILE_PATH)));
            for (String key : globalProperties.stringPropertyNames()) {
            	globalPropertiesData.put(key, globalProperties.getProperty(key));
            }
        } catch (IOException e) {
        	ExtentManager.logFailure("Failing in loading the property files ");
			
        }
    }
    /**
     * Get the test data value for given key
     * @param key -key for which the value to be retrieved
     * 
     * @return value
     */
    public static String getTestDataValue(String key) throws Exception {
    	return testData.get(key);
    }
    /**
     * Get the config value for given key
     *  @param key- key for which the value to be retrieved
     *  
     * @return value
     */    
    public static String getConfigValue(String key)  throws Exception {
        return configData.get(key);
    }
    /**
     * Get the global data value for given key
     *  @param key- key for which the value to be retrieved
     *  
     * @return value
     */    
    public static String getGlobalDataConfigValue(String key)  throws Exception {
        return globalPropertiesData.get(key);
    }
    /**
     * Get the config value for given key
     *  @param secreKey - secret key to be set
     *  @param passwordKey - password key to be converted into password
     * @return value
     * @throws Exception 
     */       
    public static String getDecryptedPassword(String secreKey, String passwordKey) throws Exception {
        try {
            String secretKey = getConfigValue(secreKey);
            String encryptedPassword = getConfigValue(passwordKey);
            return EncryptUtil.decrypt(encryptedPassword, secretKey);
        } catch (Exception e) {
        	ExtentManager.logFailure("Failing in laoding the property files ");
			throw e;
        }
    }
    /**
     * clear all the maps
     */       
    
    public void cleanAllMap()
    {
        testData.clear();
        configData.clear();
        globalPropertiesData.clear();
    }
 
}
