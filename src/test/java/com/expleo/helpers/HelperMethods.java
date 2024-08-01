// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.helpers;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.expleo.drivers.WebDriverManager;
import com.expleo.drivers.ExtentManager;
import com.expleo.utils.PropertyReader;
/**
 * Sets all the webdriver specific methods
 */ 
public class HelperMethods {

	private static HashMap<String, String> itemDetailsMap = new HashMap<String,String>();	
	
	public void cleanUp()
	{
		
		itemDetailsMap.clear();
	}	

	/**
	 * Capture the screenshot and save at specific location
	 * @param driver -driver instance
	 * @param scenarioName -scenario name
	 * @return null
	 */ 
	public synchronized static String takeScreenshot(WebDriver driver, String scenarioName)
	{
		File sourceScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(ExtentManager.ExtenReportFolderPath+"\\Screenshots\\"+scenarioName+ ".png");

		try {
			FileUtils.copyFile(sourceScreenshotFile, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationFile.getAbsolutePath();
	}
	/**
	 * Opens the browser with given application link
	 * @param driver -driver instance
	 * @param appLink -application link
	 * @return null
	 */ 
	public void openBrowser(WebDriver driver, String appLink){
		driver.get(appLink);
	}
	/**
	 * Clicks on given webElement with given webdriver instance
	 * @param driver -driver instance
	 * @param webElement webElement
	 * @return null
	 */ 
	public void click(WebDriver driver, By webElement) {
		waitForVisibility(driver, webElement);
		driver.findElement(webElement).click();		
	}
	/**
	 * Get text from given webElement with webdriver
	 * @param driver -driver instance
	 * @param webElement webElement
	 * @return txt - element text
	 */
	public String getElementText(WebDriver driver, By webElement) {
		waitForVisibility(driver, webElement);
		String txt="";
		txt=driver.findElement(webElement).getText();
		return txt;
	}
	/**
	 * Waits for webElement with given timeout
	 * @param driver
	 * @param webElement
	 * 
	 * @return null
	 */
	public void waitForVisibility(WebDriver driver, By webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));          

	}
	/**
	 * Clicks on given webElement and enters the given text
	 * @param driver driver instance
	 * @param text_Element - Weblement
	 * @param txt  -text value to be entered
	 * @return null
	 */
	public void clickAndSendKeys(WebDriver driver, By txt_Element, String txt) {    	
		waitForVisibility(driver, txt_Element);
		WebElement textWebElement= driver.findElement(txt_Element);
		textWebElement.click();
		textWebElement.sendKeys(txt);
	}
	/**
	 * Clicks on given webElement and enters the given text and press enter key
	 * @param driver driver instance
	 * @param text_Element - Weblement
	 * @param txt  -text value to be entered
	 * @return null
	 */
	public void clickAndSendKeysWithEnter(WebDriver driver, By txt_Element, String txt) {
		WebElement textWebElement= driver.findElement(txt_Element);
		waitForVisibility(driver, txt_Element);
		textWebElement.sendKeys(txt);
		textWebElement.sendKeys(Keys.ENTER);
	}
	/**
	 * Waits for webElement with given timeout passed to method
	 * @param driver driver instance
	 * @param webElement -webelement
	 * @param time time for delay
	 * @return null
	 */
	public void waitForVisibility(WebDriver driver, By webElement, long time) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(webElement)));

	}
	/**
	 * Waits for webElement and returns true if the webelement found successfully
	 * @param driver driver instance
	 * @param webElement webelement
	 * @return null
	 */	
	public boolean waitAndCheckForObjectExistance(WebDriver driver,By webElement) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		if (wait.until(ExpectedConditions.visibilityOfElementLocated(webElement))!= null)
			return true;
		else
			return false;

	}

	//------------------------------------------------------------ Code by Ashwani Jain----------------------------------------------------------------------
	// Check in test case
	private static String m_stringItemValue;
	/**
	 * sets the item value which is used by selectAndReadObjectInTableView method
	 * @param stringVal - value to be set
	 * @return null
	 */
	public static void setStringItemvalue(String stringVal)
	{
		m_stringItemValue =stringVal;
	}
	/**
	 * Reads the values from given table object
	 * @param driver driver instance
	 * @param itemcount - timecounter
	 * @return null
	 */	
	public void selectAndReadObjectInTableView(WebDriver driver, Integer itemcount) throws InterruptedException {

		try {
		System.out.println("I am in ");
		Thread.sleep(10000);

		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='aw-splm-tableCellText']"));
		Thread.sleep(5000);
		int count = 0;
		System.out.println("I am in 1"+elements);
		for (WebElement items : elements) {

			System.out.println("items" + items);

			items.click();

			count++;

			if (count >= itemcount) {
				setStringItemvalue(items.getText());// reading the item id value from result base on itemcount
				break;
			}
		}
		 } catch (Exception e) {
  			 ExtentManager.logFailure("Fail: Failing in SelectAndReadObjectInTableView");
  			 throw e;
  			} 
	}
	
	/**
	 * Selects the value from LOV list
	 * @param driver -driver instance
	 * @param txt_Element -webElement
	 * @param txt - text to be selected from the list
	 * @return null
	 */
	public void clickLOVSendKeys(WebDriver driver, By txt_Element, String txt) {
		WebElement textWebElement= driver.findElement(txt_Element);
		try {
			String searchLOV = textWebElement.getAttribute("value");
			if (!searchLOV.contains(txt)) {
				textWebElement.click();
				Thread.sleep(2000);
				textWebElement.sendKeys(txt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * clears the webElement text value
	 * @param driver
	 * @param txt_Element
	 * @return null
	 */
	public void clear(WebDriver driver, By txt_Element) {
		WebElement textWebElement= driver.findElement(txt_Element);
		textWebElement.clear();
	}
	
	/**
	 * Reads the webElement text value and returns
	 * @param driver
	 * @param webElement
	 * @return string
	 */
	public String ActualValue(WebDriver driver, By webElement) {
		return driver.findElement(webElement).getText();
	}
	
	/**
	 * Checks if an object exists or not, returns true or false
	 * @param driver
	 * @param webElement
	 * return boolean
	 */
	public boolean objectExist(WebDriver driver, By webElement) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		boolean exists = driver.findElements(webElement).size() != 0;       
		return exists;
	}

	/**
	 * Opens given URL
	 * @param Webdriver driver
	 * @param URL
	 * @return null
	 */
	public void openUrl(WebDriver driver, String URL) {
		driver.get(URL);		
	}

	/**
	 * Gets URL of launched application
	 * @param Webdriver driver
	 * 
	 * @return URL of web page
	 */
	public String getUrl(WebDriver driver) {
		String url = driver.getCurrentUrl();
		return url;
	}
	
	/**
	 * Function returns window title
	 * @param Webdriver driver
	 * 
	 * @return title
	 */
	public String getWindowTitle(WebDriver driver) {
		String title = driver.getTitle();
		return title;
	}
	
	/**
	 * Function navigates to previous page
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	// navigates to last webpage
	public void previousPage(WebDriver driver) {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to navigate to back page");
		}
	}
	
	/**
	 * Function navigates to next page
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	// navigates to next webpage if available
	public static void nextPage(WebDriver driver) {
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to navigate forward");
		}
	}
	
	/**
	 * Function navigates to url in already opened browser
	 * @param Webdriver driver
	 * @param String URL
	 * 
	 * @return null
	 */
	// opens new url in already opened browser
	public void navigateToUrl(WebDriver driver, String url) {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to navigate to url");
		}
	}
	
	/**
	 * Function refreshes current webpage
	 * @param Webdriver driver
	 *  
	 * @return null
	 */
	public void refreshPage(WebDriver driver) {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function matches expected and actual URLs
	 * @param Webdriver driver
	 * @param String expected URL
	 * 
	 * @return boolean true or false
	 */
	public boolean matchUrl(WebDriver driver, String expected) {
		boolean flag = false;
		String actual = driver.getCurrentUrl();
		if (actual.equals(expected)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Function closes browser
	 * @param Webdriver driver
	 * 
	 * @return boolean true or false
	 */
	public void closeBrowser(WebDriver driver) {
		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("not able to close browser");
		}
	}
	
	/**
	 * Function waits till specified time during page load
	 * @param Webdriver driver
	 * @param Int time
	 * 
	 * @return null
	 */
	public void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}

	/**
	 * Function waits for minimum duration mentioned in configuration file
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	public void implicitMinWait(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(PropertyReader.getProperty("minWait"))));
	}

	/**
	 * Function waits for medium duration mentioned in configuration file
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	public void implicitMediumWait(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(PropertyReader.getProperty("medWait")) * 2));
	}

	/**
	 * Function waits for maximum duration mentioned in configuration file
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	public void implicitMaxWait(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(PropertyReader.getProperty("maxWait")) * 3));
	}

	/**
	 * Function explicitly waits for visibility of element for medium duration mentioned in configuration file
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return null
	 */
	public void explicitWait_Visibility(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(PropertyReader.getProperty("medWait")) * 3));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Function explicitly waits for ability to click element for medium duration mentioned in configuration file
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return null
	 */
	public void explicitWait_Clickability(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(PropertyReader.getProperty("medWait")) * 3));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Function is used for fluent wait. It waits for visibility of element for medium duration mentioned in configuration file
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return null
	 */
	public void fluentWaitforelementvisibility(WebDriver driver, By by) {
		Wait<WebDriver> wait = null;
		try {
			WebElement element = driver.findElement(by);
			wait = new FluentWait<WebDriver>((WebDriver) driver)
					.withTimeout(Duration.ofSeconds(Integer.parseInt(PropertyReader.getProperty("medWait")) * 3))
					.pollingEvery(Duration.ofSeconds(3)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {

		}
	}

	/**
	 * Function performs click operation on element using Action class.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return null
	 */
	public void actionClick(WebDriver driver, By by) throws Exception {
		try {
			WebElement ele = driver.findElement(by);
			Actions act = new Actions(driver);
			act.moveToElement(ele).click().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to click element using Action class.");
		}
	}
	
	/**
	 * Function performs click operation on element using Javascript executor.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return null
	 */
	public void JSclick(WebDriver driver, By by) throws Exception {
		try {
			WebElement ele = driver.findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to click element using javascript.");
		}
	}

	/**
	 * Function types given data in text box by clearing text box.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return null
	 */
	public void type(WebDriver driver, By by, String testData) {
		try {
			WebElement ele = driver.findElement(by);
			ele.clear();
			ele.sendKeys(testData);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("not able to type due to " + e);
		}
	}

	/**
	 * Function performs mouse over operation.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return null
	 */
	public void mouseover(WebDriver driver, By by) throws Exception {
		try {
			WebElement ele = driver.findElement(by);
			Actions ac = new Actions(driver);
			ac.moveToElement(ele).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("not able to mouse over ");
		}
	}

	/**
	 * Function performs keyboard operation - clicks Control key.
	 * @param Webdriver driver
	 * @param String input character
	 * 
	 * @return null
	 */
	public void keyboardCtrlOp(WebDriver driver, String keyChar) {
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.chord(Keys.CONTROL, keyChar)).build().perform();
	}
	
	/**
	 * Function performs keyboard operation - clicks Shift key.
	 * @param Webdriver driver
	 * @param String input character
	 * 
	 * @return null
	 */
	public void keyboardShiftOp(WebDriver driver, String keyChar) {
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.chord(Keys.SHIFT, keyChar)).build().perform();
	}
	
	/**
	 * Function performs keyboard operation using Action class - clicks TAB key.
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	public void keyboardTab(WebDriver driver) {
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.TAB).build().perform();
	}
	
	/**
	 * Function performs keyboard operation - clicks down arrow key.
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	public void keyboardDown(WebDriver driver) {
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).build().perform();
	}
	
	/**
	 * Function performs keyboard operation - clicks Enter key.
	 * @param Webdriver driver
	 * @param String input character
	 * 
	 * @return null
	 */
	public void keyboardEnter(WebDriver driver) {
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ENTER).build().perform();
	}
	
	/**
	 * Function gets text from element.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return String - text of element
	 */
	//used to retrieve text from WebElement
	public String getText(WebDriver driver, By by) {
		WebElement ele = driver.findElement(by);
		String text = ele.getText();
		return text;
	}
	
	/**
	 * Function gets current URL.
	 * @param driver webdriver driver current instance
	 * 
	 * @return String - URL of page
	 */
	public String getURL(WebDriver driver) {
		String text = driver.getCurrentUrl();
		return text;
	}
	
	/**
	 * Function checks whether element is displayed or not.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * 
	 * @return boolean
	 */
	public boolean isDisplayed(WebDriver driver, By by) {
		WebElement ele = driver.findElement(by);
		boolean t = ele.isDisplayed();
		return t;
	}
	
	/**
	 * Function checks if element is selected or not.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * 
	 * @return boolean
	 */
	public boolean isSelected(WebDriver driver, By by) {

		WebElement ele = driver.findElement(by);
		boolean t = ele.isSelected();
		return t;
	}
	
	/**
	 * Function checks if element is enabled or not.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * 
	 * @return boolean
	 */
	public boolean isEnabled(WebDriver driver, By by) {

		WebElement ele = driver.findElement(by);
		boolean t = ele.isEnabled();
		return t;

	}
	
	/**
	 * Function checks if element is present or not.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * 
	 * @return boolean
	 */
	public boolean isElementPresent(WebDriver driver, By by) {
		List<WebElement> list = driver.findElements(by);
		boolean present = false;
		if (list.size() > 0) {
			present = true;
		}
		return present;
	}
	
	/**
	 * Function scrolls control of element.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * 
	 * @return null
	 */
	public void scrollByVisibilityOfElement(WebDriver driver, By by) {
		try {
			WebElement ele = driver.findElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function selects value from dropdown using index.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * @param index index for selecting from list
	 * 
	 * @return null
	 */
	public void selectByIndex(WebDriver driver, By by, int index) {
		try {
			WebElement ele = driver.findElement(by);
			Select s = new Select(ele);
			s.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not able to select by Index");
		}
	}
	
	/**
	 * Function selects value from dropdown using value.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * @param value value to be selected
	 * 
	 * @return null
	 */
	public void selectByValue(WebDriver driver, By by, String value) {
		try {
			WebElement ele = driver.findElement(by);
			Select s = new Select(ele);
			s.selectByValue(value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not able to select by value");
		}
	}
	
	/**
	 * Function retrieves all options from dropdown.
	 * @param driver webdriver driver current instance
	 * @param by webElement
	 * 
	 * @return list of options
	 */
	//used to retrieve all options from dropdown using Select class
	public List<WebElement> getAllOptions(WebDriver driver, By by) {
		WebElement ele = driver.findElement(by);
		Select s = new Select(ele);
		List<WebElement> AllOptions = s.getOptions();
		return AllOptions;
	}
	
	/**
	 * Function checks if element is selected or not.
	 * @param driver webdriver instance
	 * @param by webElement for check
	 * 
	 * @return boolean
	 */
	public boolean check(WebDriver driver, By by) throws Exception {
		WebElement ele = driver.findElement(by);
		boolean flag = false;
		if (ele.isSelected()) {
			flag = true;

		} else {
			ele.click();
			flag = true;
		}
		return flag;
	}

	/**
	 * Function uncheck element.
	 * @param driver webdriver instance
	 * @param by 
	 * 
	 * @return boolean
	 */
	public boolean uncheck(WebDriver driver, By by) throws Exception {
		WebElement ele = driver.findElement(by);
		boolean flag = false;
		if (!ele.isSelected()) {

		} else {
			ele.click();
			flag = true;

		}
		return flag;
	}

	/**
	 * Function gets attribute of element.
	 * @param Webdriver driver
	 * @param Element
	 * @param String attribute name
	 * 
	 * @return String
	 */
	public String getAttribute(WebDriver driver, By by, String Attributename) {
		WebElement ele = driver.findElement(by);
		String AttrValue = ele.getAttribute(Attributename).toString();
		return AttrValue;
	}

	/**
	 * Function returns count of elements.
	 * @param Webdriver driver
	 * @param Element
	 * @param String attribute name
	 * 
	 * @return Int
	 */
	// Element Count
	public int ElementsCount(WebDriver driver, By by) {
		List<WebElement> elements = driver.findElements(by);
		int count = elements.size();
		return count;
	}

	/**
	 * Function matches substring.
	 * @param String actualText
	 * @param String expectedText
	 * 
	 * @return boolean
	 */
	public boolean matchSubStringAndVerify(String actualText, String expectedText) {

		boolean flag = false;
		if (actualText.contains(expectedText)) {
			flag = true;
		}
		return flag;

	}

	/**
	 * Function trims text.
	 * @param String text
	 * 
	 * @return String
	 */
	public static String trimText(String text) {
		String trimStr = text.trim();
		return trimStr;
	}

	/**
	 * Function gets column count of table.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return Int column count
	 */
	//check
	public int getColumncount(WebDriver driver, By by) {
		WebElement row = driver.findElement(by);
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		return a;
	}
	
	/**
	 * Function extracts text from list of webelements into another list.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return List of extracted text
	 */
/*	public List<String> extractText(WebDriver driver, By by) {
		List<WebElement> elements = driver.findElements(by);
		List<String> texts = elements.stream().map(element -> element.getText()).collect(Collectors.toList());
		List<String> text = texts.stream().sorted().toList();
		return text;

	}*/

	/**
	 * Function performs drag drop oprations.
	 * @param Webdriver driver
	 * @param Element From
	 * @param Element To
	 * 
	 * @return null
	 */
	public void DragAndDrop(WebDriver driver, WebElement From, WebElement To) {
		try {
			Actions ac = new Actions(driver);
			ac.dragAndDrop(From, To).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not able to drag and drop");
		}
	}

	/**
	 * Function switch to child window handles.
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	public void SwitchToChild(WebDriver driver) {
		try {
			String mainWindowHandle = driver.getWindowHandle();
			Set<String> allWindowHandles = driver.getWindowHandles();
			Iterator<String> iterator = allWindowHandles.iterator();

			while (iterator.hasNext()) {
				String ChildWindow = iterator.next();
				if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
					driver.switchTo().window(ChildWindow);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Can't switch to child window");
		}
	}
	
	/**
	 * Function switches back to main window.
	 * @param Webdriver driver
	 * 
	 * @return null
	 */
	public void SwitchBack(WebDriver driver) {
		try {
			String currentwindow = driver.getWindowHandle();
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();

			while (i1.hasNext()) {
				String ChildWindow = i1.next();
				if (!currentwindow.equalsIgnoreCase(ChildWindow)) {
					driver.switchTo().window(ChildWindow);
					driver.close();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Can't switch back");
		}
	}

	public void SwitchToParentFrame(WebDriver driver) {
		try {
			driver.switchTo().parentFrame();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Can't switch to parent frame");
		}
	}
	
	/**
	 * Function extracts text from table.
	 * @param Webdriver driver
	 * @param Element
	 * 
	 * @return List Text of table cell
	 */
	public List<String> printTable(WebDriver driver, By by) {
		List<WebElement> allRows = driver.findElements(by);
		List<String> list = new ArrayList();
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));

			for (WebElement cell : cells) {
				System.out.println();
				list.add(cell.getText());

			}
		}
		return list;
	}

		/**
     * Sleep
     *
     * @param interval the length of time to sleep in milliseconds
     */
	public void sleep( long interval )
    {
        try
        {
            Thread.sleep( interval );
        }
        catch( InterruptedException ex )
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException( ex );
        }
    }
    
	public static void setItemDetails(String PropertyNamekey, String PropertyValue) {

		if (StringUtils.isNotBlank(PropertyNamekey)) {

			HelperMethods.itemDetailsMap.put(PropertyNamekey, PropertyValue);
		}
	}
    
	public static String getItemIdMapData(String PropertyNamekey) {
		String itemIdValue = null;
		if (StringUtils.isNotBlank(PropertyNamekey)) {
			if (MapUtils.isNotEmpty(HelperMethods.itemDetailsMap)) {
				if (HelperMethods.itemDetailsMap.containsKey(PropertyNamekey)) {
					itemIdValue = (String) HelperMethods.itemDetailsMap.get(PropertyNamekey);
				}
			}

		}
		return itemIdValue;
	}
    
    /**
     * Scroll Into an Element using JavascriptExecutor
     *
     * @param element the Web Element
     */
	public static void scrollInto(By by) {
		WebElement element = WebDriverManager.getWebDriver().findElement(by);
		HelperMethods.executeScript("arguments[0].scrollIntoView(true);", element);
		// It might results in scrolling entire page, this is workaround to restore page
		// scroll position back
		HelperMethods.scrollWindow(0, -250);
	}

	public static Object executeScript(String script, Object... args) {
		try {
			WebDriver driver = WebDriverManager.getWebDriver();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			return jse.executeScript(script, args);
		} catch (Exception ex) {
			// This comes in case of Firefox when it try to scroll large number of
			// objects(>50).
			System.out.println(ex.getLocalizedMessage());
		}
		return null;
	}

    /**
     * Scrolls the document by the specified number of pixels.
     *
     * @param xnum The pixels to scroll by, along the x-axis (horizontal). Positive values will scroll to the right,
     *            while negative values will scroll to the left
     * @param ynum The pixels to scroll by, along the y-axis (vertical). Positive values will scroll down, while
     *            negative values scroll up
     */
	private static void scrollWindow( int xnum, int ynum )
	{
		HelperMethods.executeScript( "window.scrollBy(" + xnum + "," + ynum + ")", "" );
	}    

}