/**
 * @Author Gladson Antony
 * @Date 10-Sep-2017 
 * @Time 11:06:48 AM
 */
package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import controllers.BaseMethod;

public class ExplicitWaiting extends BaseMethod
{
	public static WebDriver driver;
	
	/*Select using WebElements*/
	/** To Wait Until Element to be Clickable */	
	public static void explicitWaitElementToBeClickable(WebElement element, int time) 
	{
		driver=getWebDriver();
		WebDriverWait clickableWait = new WebDriverWait(driver, time);
		clickableWait.until(ExpectedConditions.elementToBeClickable(element));
	}


	
	
	/** To Wait Until Element to be Selectable */
	public static void explicitWaitElementToBeSelected(WebElement element, int time) {
		driver=getWebDriver();
		WebDriverWait selectableWait = new WebDriverWait(driver, time);
		selectableWait.until(ExpectedConditions.elementToBeSelected(element));
	}
	

	/** To Wait Until Element has the text */
	public static void explicitWaitTextToBePresentInElement(WebElement element, int time, String text) {
		driver=getWebDriver();
		WebDriverWait textToBePresent = new WebDriverWait(driver, time);
		textToBePresent.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	
	/** To Wait Until Title contains the text */
	public static void explicitWaitTitleContains(WebElement element, int time, String title) {
		driver=getWebDriver();
		WebDriverWait titleContains = new WebDriverWait(driver, time);
		titleContains.until(ExpectedConditions.titleContains(title));
	}
	
	
	/** To Wait Until Title is */
	public static void explicitWaitTitleIs(WebElement element, int time, String title) {
		driver=getWebDriver();
		WebDriverWait titleIs = new WebDriverWait(driver, time);
		titleIs.until(ExpectedConditions.titleIs(title));
	}
	
	
	/** To Wait Until Element to be Visible */
	public static void explicitWaitVisibilityOfElement(WebElement element, int time) {
		driver=getWebDriver();
		WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
		elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
	}

	/** To Wait Until Element to be Visible */
	public static void explicitWaitInVisibilityOfElement(WebElement element, int time) {
		driver=getWebDriver();
		WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
		elementToBeVisible.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	/** To Wait Until Element is Selected */
	public static void explicitWaitSelectionStateToBe(WebElement element, int time, boolean selected) {
		driver=getWebDriver();
		WebDriverWait elementIsSelected = new WebDriverWait(driver, time);
		elementIsSelected.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
	}
	
	
	/** To Wait Until Elements to be Visible */
	public static void explicitWaitVisibilityOfElements(List<WebElement> element, int time) {
		driver=getWebDriver();
		WebDriverWait elementsToBeVisible = new WebDriverWait(driver, time);
		elementsToBeVisible.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	
	/*Select using By Method*/
	/** To Wait Until Element to be Clickable */	
	public static void explicitWaitElementToBeClickable(By element, int time) {
		driver=getWebDriver();
		WebDriverWait clickableWait = new WebDriverWait(driver, time);
		clickableWait.until(ExpectedConditions.elementToBeClickable(element));
	}




	/** To Wait Until Element to be Selectable */
	public static void explicitWaitElementToBeSelected(By element, int time) {
		driver=getWebDriver();
		WebDriverWait selectableWait = new WebDriverWait(driver, time);
		selectableWait.until(ExpectedConditions.elementToBeSelected(element));
	}


	/** To Wait Until Title contains the text */
	public static void explicitWaitTitleContains(By element, int time, String title) {
		driver=getWebDriver();
		WebDriverWait titleContains = new WebDriverWait(driver, time);
		titleContains.until(ExpectedConditions.titleContains(title));
	}


	/** To Wait Until Title is */
	public static void explicitWaitTitleIs(By element, int time, String title) {
		driver=getWebDriver();
		WebDriverWait titleIs = new WebDriverWait(driver, time);
		titleIs.until(ExpectedConditions.titleIs(title));
	}


	/** To Wait Until Element to be Visible */
	public static void explicitWaitVisibilityOfElement(By element, int time) {
		driver=getWebDriver();
		WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
		elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
	}


	/** To Wait Until Element is Selected */
	public static void explicitWaitSelectionStateToBe(By element, int time, boolean selected) {
		driver=getWebDriver();
		WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
		elementToBeVisible.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
	}

	
	/** To Wait for the Alert */
	public static void explicitWaitForAlert(int time) {
		driver=getWebDriver();
		WebDriverWait waitForAlert = new WebDriverWait(driver, time);
		waitForAlert.until(ExpectedConditions.alertIsPresent());
	}
	public static void waitInSeconds(int numberOfSeconds){
		for(int i=0;i<numberOfSeconds;++i)
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
	}

	public static String executeJavaScriptResultString(String command){
		return ((JavascriptExecutor) wd).executeScript(command).toString();
	}

	public static int executeJavaScriptResultInt(String command){
		return Integer.parseInt(((JavascriptExecutor) wd).executeScript(command).toString());
	}

	public static boolean waitForJStoLoad() {

		WebDriverWait wait = new WebDriverWait(getWebDriver(), 30);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return (executeJavaScriptResultInt("return jQuery.active") == 0);
				}
				catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return executeJavaScriptResultString("return document.readyState")
						.toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

}
