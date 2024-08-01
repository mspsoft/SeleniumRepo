// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.drivers;
import java.util.HashMap;
import java.util.Map;
/**
 * Sets the scenario context map for specified data
 */   
public class ScenarioContext {
    private Map<String, Object> data;
    /**
     * Creates a new instance of data hashmap
     */ 
    public ScenarioContext() {
        data = new HashMap<>();
    }
    /**
     * Adds key, value into the scenario context hashmap
     */ 
    public void setData(String key, Object value) {
        data.put(key, value);
    }
    /**
     * Returns the hashmap value for the referenced key
     */ 
    public Object getData(String key) {
    	 if (data.containsKey(key)) {
    		 return data.get(key);
    	 }
    	 else {
    		 return " ";
    	 }
    }
    /**
     * Checks if the searchcontext hashmap has the references key and returns its value
     */ 

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }
}
