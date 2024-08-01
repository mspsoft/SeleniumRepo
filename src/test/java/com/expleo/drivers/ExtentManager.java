// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.drivers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Class sets up extentmanager logger and configure it with all the appropriate options
 */
public class ExtentManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    public static final String ExtenReportFolderPath = createReportPath();
        
    /**
     * This method returns current thread instance of extent report
     * @return extent
     */
    public synchronized static ExtentReports getInstance() {

        if (extent == null) {
              extent = new ExtentReports();
        	File extentReportFile= new File(ExtenReportFolderPath+"\\eReport.html");
        	String finalReportPath=ExtenReportFolderPath+"\\eReport.html";
        	System.setProperty("EXTENT_LOG_FILE_PATH", finalReportPath);
        	ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Test Report");
            extent.attachReporter(spark);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter( System.getProperty("user.dir")+"\\extent_report_path.txt"))) {
                writer.write(finalReportPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return extent;

    }
    
    /**
     * This method closes the instance of extent report
     */
    public static synchronized void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }
    
    /**
     * This method returns the current testThread
     * @return testThread
     */
    public static ThreadLocal<ExtentTest> getTestThread() {
        return testThread;
    }
    /**
     * Logs the failure message for respective instance of extent manager with given message
     * @param msg - failure message to log 
     * @return null
     */    
    public static void logFailure(String msg)
    {
    	ExtentManager.getTestThread().get().fail(msg);    			
    }
    
    /**
     * Logs the failure message for respective instance of extent manager with given message
     * @param msg -  failure message to log 
     * @param e - exception to be logged into failure
     * @return null
     */    
    public static void logFailure(String msg, Exception e)
    {
    	ExtentManager.getTestThread().get().fail(msg + "-" + e.getMessage());    			
    }
    
    /**
     * Logs the success message for respective instance of extent manager with given message
     * @param msg - messages to be logged in report
     * @return null
     */        
    public static void logSuccess(String msg)
    {
    	ExtentManager.getTestThread().get().pass(msg);   	
    }
    
    /**
     * Logs the informative message for respective instance of extent manager with given message
     * @param msg - messages to be logged in report
     * @return null
     */       
    public static void logInfo(String msg)
    {
    	ExtentManager.getTestThread().get().info(msg);   	
    }
    
    /**
     * Generates report folder path for extent report
     * 
     * @return Returns folder path of final execution report.
     */  
    public static String createReportPath() {
		String timestamp = new SimpleDateFormat("yyyy_MM_dd hh_mm_ss").format(new Date());

		return System.getProperty("user.dir")+"\\target\\ExtentReport\\Spark_"+timestamp;
	}
}   
