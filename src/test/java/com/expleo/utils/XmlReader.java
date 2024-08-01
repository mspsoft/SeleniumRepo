package com.expleo.utils;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XmlReader {
    private static final Map<String, Map<String, String>> locators = new ConcurrentHashMap<>();

    public static void loadLocators(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);
            NodeList pageNodes = document.getElementsByTagName("page");

            for (int i = 0; i < pageNodes.getLength(); i++) {
                Element pageElement = (Element) pageNodes.item(i);
                String pageName = pageElement.getAttribute("name");
                NodeList locatorNodes = pageElement.getElementsByTagName("locator");

                Map<String, String> pageLocators = new HashMap<>();
                for (int j = 0; j < locatorNodes.getLength(); j++) {
                    Element locatorElement = (Element) locatorNodes.item(j);
                    String locatorName = locatorElement.getAttribute("name");
                    String locatorValue = locatorElement.getTextContent();
                    pageLocators.put(locatorName, locatorValue);
                }
                locators.put(pageName, pageLocators);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLocator(String pageName, String locatorName) {
        return locators.getOrDefault(pageName, new HashMap<>()).get(locatorName);
    }
    
    public static String getDynamicLocator(By locatorName, String... dynamicValues) {
    	String locator= locatorName.toString();
    	System.out.println("locator"+locator);
    	String[] splitLocator=locator.split(":");
    	System.out.println("splitLocator"+splitLocator[1].toString());
       // String locator = locators.get(pageName).get(locatorName);
        for (String dynamicValue : dynamicValues) {
            locator = splitLocator[1].replaceFirst("\\{[^}]+\\}", dynamicValue);
        }
        return locator;
    }
}
