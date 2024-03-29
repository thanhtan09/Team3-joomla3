package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty7.util.log.Log;

public class Abstract_page {

	private int timeout = 5;
	private String XPATH_MENU = "//ul[@id='menu']/li";
	private Log log;

	/*
	 * Enter value to element
	 * 
	 * Parameter: driver, by, value
	 * 
	 * creator: Tan Vo
	 */
	public void enter(WebDriver driver, By by, String _value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(_value);
	}

	/*
	 * Click on element
	 * 
	 * Parameter: driver, by
	 * 
	 * Creator: Tan Vo
	 */
	public void click(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	/*
	 * Hover on element
	 * 
	 * Parameter: driver, by
	 * 
	 * Creator: Tan Vo
	 */
	public void hover(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).perform();
	}

	/*
	 * Select element
	 * 
	 * Parameter: driver, by
	 * 
	 * Creator: Tan Vo
	 */
	public void select(WebDriver driver, By by, String _value) {
		WebElement element = driver.findElement(by);
		// element.sendKeys(_value);
		Select drop = new Select(element);
		drop.selectByVisibleText(_value);
	}

	/*
	 * Wait control exists
	 * 
	 * Parameter: driver, by
	 * 
	 * Creator: Tan Vo
	 */
	public void waitControlExist(WebDriver driver, By by) {
		try {
			WebElement element = driver.findElement(by);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			log.debug("Element doesn't exist");
		}

	}

	/*
	 * Switch to new frame
	 * 
	 * Parameter: driver, by
	 * 
	 * Author: Tan Vo
	 */
	public void switchFrame(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		driver.switchTo().frame(element);
	}

	/*
	 * Get text
	 * 
	 * Parameter: driver, by
	 * 
	 * Author: Tan Vo
	 */
	public String getText(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		return element.getText();
	}

	/*
	 * Count element
	 * 
	 * Parameter: driver, by
	 * 
	 * Author: Tan Vo
	 */
	public int countElement(WebDriver driver, By by) {
		int count = 0;
		count = driver.findElements(by).size();
		return count;
	}

	/*
	 * Sleep
	 * 
	 * Parameter: timeout
	 * 
	 * Author: Tan Vo
	 */
	public void sleep(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Refresh page
	 * 
	 * Parameter: timeout
	 * 
	 * Author: Tan Vo
	 */
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	/*
	 * Is Control Exist
	 * 
	 * Parameter: driver, By
	 * 
	 * Return: true/false
	 * 
	 * Author: Tan Vo
	 */
	public boolean isControlExist(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			log.debug("Control doesn't exist");
			return false;
		}
	}
	
	/*
	 * Is Control Not Exist
	 * 
	 * Parameter: driver, By
	 * 
	 * Return: true/false
	 * 
	 * Author: Tan Vo
	 */
	public boolean isControlNotExist(WebDriver driver, By by){
		try{
			driver.findElement(by);
			return false;
		}catch (Exception e) {
			log.debug("Control is exist");
			return true;
		}
	}
	
	/*
	 * Navigate menu
	 * 
	 * Parameter: menu (e.g: Content|Article Manager)
	 * 
	 * Author: Tan Vo
	 */
	public void navigateMenu(WebDriver driver, String list) {
		String menu = list;
		String tab = "";
		String lastItem = "";
		String[] subMenu = menu.split("[|]");
		for (String r : subMenu) {
			hover(driver,
					By.xpath(XPATH_MENU+tab+"/a[contains(text(),'"+r+"')]"));
			lastItem = XPATH_MENU+tab+"/a[contains(text(),'"+r+"')]";
			tab = tab+"/ul/li";
		}
		click(driver,By.xpath(lastItem));
	}

	/*
	 * Switch windows
	 * 
	 * Parameter: driver
	 * 
	 * Author: Tan Vo
	 */
	public void switchToNewWindows(WebDriver driver) {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}
	
	/*
	 * Get current windows 
	 * 
	 * Parameter: driver
	 * 
	 * Author: Tan Vo
	 */
	public String getCurrentWindows(WebDriver driver){
		String parentHandle = driver.getWindowHandle();
		return parentHandle;
	}

	/*
	 * Get Page title
	 * 
	 * Parameter: menu (e.g: Content|Article Manager)
	 * 
	 * Author: Tan Vo
	 */
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	/*
	 * Get Control Css
	 * 
	 * Parameter: dllName
	 * 
	 * Author: Nga Nguyen
	 */
	public String getControlCss(WebElement ddlName, String css)
	{		
		return ddlName.getCssValue(css);
	}
	
	/*
	 * Convert Rgba to Hex
	 * 
	 * Parameter: rgba
	 * 
	 * Author: Nga Nguyen
	 */
	
	 public static String convertRgbaToHex(String rgba)
	 {
	 	String hex = Color.fromString(rgba).asHex();
	 	return hex;
	 }
}
