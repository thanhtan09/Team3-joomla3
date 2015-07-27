package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewClient_page extends Abstract_page{

	private WebDriver driver;
	private String PAGE_TITLE = "joomla selenium advance - Administration - Banner Manager: Edit Client";
	
	private WebElement TXT_NAME;
	
	public NewClient_page(WebDriver driver){
		this.driver = driver;
	}
	
	/*
	 * Enter Client name
	 * 
	 * Parameter: client name
	 * 
	 * Author: Tan Vo
	 */
	public Client_page addClient(String name, String contact, String email, String status, String button){
		
		if(name!="")
			enterClientName(name);
		if(contact!="")
			enterContactName(contact);
		if(email!="")
			enterEmail(email);
		if(status!="")
			selectStatus(status);
		
		switch (button) {
		case "Save":
			clickSave();
			break;
		case "SaveAsCopy":
			clickSaveAsCopy();
			break;
		case "":
		case "SaveAndClose":
			clickSaveandClose();
			break;
		}		
		return new Client_page(driver);
	}
	
	/*
	 * Is edit client page
	 */
	public boolean isEditClientPage(){
		
		if(getPageTitle(driver).equals(PAGE_TITLE))
			return true;		
		return false;
	}
	
	/*
	 * Enter Client name
	 * 
	 * Parameter: client name
	 * 
	 * Author: Tan Vo
	 */
	public void enterClientName(String name){
		enter(driver, By.xpath(Interfaces.NewClientPage.TXT_NAME), name);
	}
	
	/*
	 * Enter Contact name
	 * 
	 * Parameter: client name
	 * 
	 * Author: Tan Vo
	 */
	public void enterContactName(String name){
		enter(driver, By.xpath(Interfaces.NewClientPage.TXT_CONTACT), name);
	}
	
	/*
	 * Enter Email name
	 * 
	 * Parameter: client name
	 * 
	 * Author: Tan Vo
	 */
	public void enterEmail(String email){
		enter(driver, By.xpath(Interfaces.NewClientPage.TXT_EMAIL), email);
	}
	
	/*
	 * Click on Save and Close
	 * 
	 * Parameter: client name
	 * 
	 * Author: Tan Vo
	 */
	public void clickSaveandClose(){
		click(driver, By.xpath(Interfaces.NewClientPage.BTN_SAVEANDCLOSE));
	}
	
	/*
	 * Select status
	 * 
	 * Parameter: client name
	 * 
	 * Author: Tan Vo
	 */
	public void selectStatus(String status){
		select(driver, By.xpath(Interfaces.NewClientPage.DROP_STATUS), status);
	}
	
	/*
	 * Click Save button
	 * 
	 * Author: Tan Vo
	 */
	public void clickSave(){
		click(driver, By.xpath(Interfaces.NewClientPage.BTN_SAVE));
	}
	
	/*
	 * Click Save As Copy button
	 * 
	 * Author: Nga Nguyen
	 */
	public void clickSaveAsCopy() {

		click(driver, By.xpath(Interfaces.NewClientPage.BTN_SAVEASCOPY));
	}
	
	/*
	 * Get color of Name control
	 * 
	 * Author: Nga Nguyen
	 */
	public String getColorOfTitle()
	{
		String hex = convertRgbaToHex(getControlCss(TXT_NAME, "color"));
		System.out.println(hex);
		return hex;		
	}
	
	/*
	 * is Name control changed to red
	 * 
	 * Author: Nga Nguyen
	 */
	
	public boolean isControlChangedtoRed(){
		String color = getColorOfTitle();
		if (color.equals("FF0000"))
			return true;
		return false;
		
	}
}
