package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewClient_page extends Abstract_page{

	private WebDriver driver;
	private String PAGE_TITLE = "Joomla - Administration - Banner Manager: Edit Client";
	
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
		if(status!="")
			selectStatus(status);
		
		switch (button) {
		case "Save":
			clickSave();
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
}
