// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.drivers;
/**
 * Sets the scenario context threadsafe instance
 */  
public class ScenarioContextManager {
    private static ThreadLocal<ScenarioContext> scenarioContext = ThreadLocal.withInitial(ScenarioContext::new);
    /**
     * Returns the scenario context threadsafe instance
     */  
    public static ScenarioContext getScenarioContext() {
        return scenarioContext.get();
    }
    /**
     * Closes the scenario context threadsafe instance
     */ 
    public static void removeScenarioContext() {
        scenarioContext.remove();
    }
}
