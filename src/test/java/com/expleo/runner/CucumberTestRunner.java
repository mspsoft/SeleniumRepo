// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.expleo.browsers.ChromeBrowser;
import com.expleo.browsers.EdgeBrowser;
import com.expleo.drivers.ExtentManager;
import com.expleo.drivers.GetTestStepName;
import com.expleo.drivers.ScenarioContextManager;
import com.expleo.drivers.WebDriverManager;
import com.expleo.helpers.HelperMethods;
import com.expleo.utils.ConfigManager;
import com.expleo.utils.PropertyReader;
import com.expleo.utils.XmlReader;

import io.cucumber.java.Scenario;

/**
 * Cucumber runner class
 * <p>
 * Runs each cucumber scenario found in the features as separated test as per parameter set in config file.
 * parallel = true or false
 * 
 * Sets environment for execution before and after test case
 *
 */

@CucumberOptions(features = "src/test/resources/features",
				glue = {"com.expleo.runner", "com.expleo.stepDefs"},
				plugin = {"pretty", 
							"html:target/cucumber-reports.html",
							"json:target/cucumber.json",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
							"com.expleo.drivers.GetTestStepName"},
				tags="@Workflow",
				monochrome = true)


public class CucumberTestRunner extends AbstractTestNGCucumberTests {  
	
	ExtentTest test=null;
	WebDriver driver;
	
	 /**
     * sets scenarios
     * 
     * @return scenarios
     */
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
	   @BeforeClass
	    public void setup() {
	        XmlReader.loadLocators("src/test/resources/appLocators/locatorsAW.xml");
	    }
	   
	 /**
     * Executed before start of every scenario to set browser properties, reporting and driver
     * @param scenario - scenario for setup driver
     * 
     */
	@Before()
	// @Parameters("browser")
	public void setUpWebDriver(Scenario scenario) {
		long threadId = Thread.currentThread().getId();
		ScenarioContextManager.getScenarioContext(); 
		setExtentReport(scenario);
		System.out.println("---------------------------------------------------------------");
		System.out.println("Thread id for current thread is ->"+threadId);
		System.out.println("---------------------------------------------------------------");

		test.log(Status.INFO, "Thread id for current thread is ->"+threadId);

		String browser = PropertyReader.getProperty("BROWSERNAME");
			
		switch(browser.toLowerCase())
		{
			case "edge":
				EdgeOptions option = new EdgeBrowser().setEdgeOptions();				
				driver = new EdgeDriver(option);
				break;
				
			case "chrome":
				ChromeOptions options = new ChromeBrowser().setChromeOptions();								
				driver = new ChromeDriver(options);
				break;
				
			default:throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		
		WebDriverManager.setWebDriver(driver);       
	}
	
	  @AfterStep
	    public void afterStep(Scenario scenario) {
	        String stepName = GetTestStepName.getCurrentStepName();
	      //  logger.info("Finished step: " + stepName);
	        ExtentManager.logInfo("Step finished --> "+stepName);
	        // Optionally clear the step name after each step
	        // GetTestStepName.clearCurrentStepName();
	    }
	
	/**
     * Executed at end of every scenario to capture screenshot for failed scenario.
     * @param scenario - scenario in current thread
     * 
     */
	@After(order=1)
	public void tearDownWebDriver(Scenario scenario) {

		if (scenario.isFailed()) {
			try {
				 String failedStepName = GetTestStepName.getCurrentStepName();
				ExtentManager.logFailure("Scenario Failed, scenario name - "+ scenario.getName());
				ExtentManager.logFailure("Scenario Failed at step, step name - "+ failedStepName);
				ExtentManager.getTestThread().get().addScreenCaptureFromPath(HelperMethods.takeScreenshot(WebDriverManager.getWebDriver(), scenario.getName()),  scenario.getName());

			}
			catch (Exception e) {
				ExtentManager.logFailure("Failed to capture screenshot "+ e.getMessage());
			}    	 
		} else {
			//ExtentManager.getTestThread().get().pass("Scenario Passed, scenario name : -> "+scenario.getName());
			ExtentManager.logSuccess("Scenario Passed, scenario name : -> "+scenario.getName());
		}
		
		endExtentReport();
	}

	/**
     * Executed at end of every scenario to do clean up activity.
     * 
     * 
     */
	@After(order=0)
	public void tearDown() {
		new HelperMethods().cleanUp();
		new ConfigManager().cleanAllMap();
		ExtentManager.getInstance().flush();
		ExtentManager.getTestThread().remove();
		ScenarioContextManager.removeScenarioContext();
		WebDriverManager.quitWebDriver();
	}
   
	/**
     * This function sets different parameter of execution report.
     * @param scenario - scenario for current thread of execution
     * 
     */
	public void setExtentReport(Scenario scenario) {
    	test = ExtentManager.getInstance().createTest(scenario.getName()).assignAuthor("Expleo QA1");
		test.assignCategory("Regression Test");
		test.assignDevice("Windows");
		ExtentManager.getTestThread().set(test);
    }	 
	
	/**
     * This function is used to end report.
     * 
     */
	public void endExtentReport() {
		ExtentManager.endReport();
    }
}