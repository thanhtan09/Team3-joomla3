package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewCategory_page extends Abstract_page{

	private WebDriver driver;
	private String EDIT_TITLE = "Category Manager: Edit An Articles Category";
	private String MESSAGESUCCESS = "Category successfully saved";
	private String HELP_TITLE = "Joomla! Help";
	
	public NewCategory_page(WebDriver driver) {
		this.driver = driver;
	}
	
	public void addNew(String title, String status){
		enter(driver, By.xpath(Interfaces.NewCatetoryPage.TXT_TITLE), title);
		if (status!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_STATUS), status);
		}
		click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEANDCLOSE));
	}
	
	
	public void addNew(String title, String status, String access, String language){
		enter(driver, By.xpath(Interfaces.NewCatetoryPage.TXT_TITLE), title);
		if (status!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_STATUS), status);
		}
		
		if (access!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_ACCESS), access);
		}
		
		if (language!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_LANGUAGE), language);
		}
		
		click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEANDCLOSE));
		

	} 
	
	public void addNew(String title, String status, String access, String language, String button){
		enter(driver, By.xpath(Interfaces.NewCatetoryPage.TXT_TITLE), title);
		if (status!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_STATUS), status);
		}
		
		if (access!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_ACCESS), access);
		}
		
		if (language!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_LANGUAGE), language);
		}
		
		switch (button) {
		case "Save":
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVE));
			break;
		case "SaveAndNew":
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEANDNEW));
			break;
		
		case "SaveAsCopy":
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEASCOPY));
			break;
			
		case "Cancel":
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_CANCEL));
			break;
		case "":
		case "SaveAndClose":
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEANDCLOSE));
			break;
		}		
		

	} 
	
	public void editCategory (String title, String button){
		enter(driver, By.xpath(Interfaces.NewCatetoryPage.TXT_TITLE), title);
		
		if (button.equals("Save")){
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVE));
		}
		
		else if (button.equals("Cancel")){
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_CLOSE));
		}
		
		else {
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEANDCLOSE));
		}
	}
	
	public Categories_page clickSaveandClose(){
		click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEANDCLOSE));
		
		return Factory_page.getCategoriesPage(driver);
	}
	
	public boolean isEditPage(){
		boolean editpage = false;
		String title = getText(driver, By.xpath(Interfaces.NewCatetoryPage.PAGE_TITLE));
		
		if (title.equals(EDIT_TITLE)){
			editpage = true;
		}
		return editpage;
	}
	
	public boolean isMessageSuccessDisplay() {
		if (getText(driver, By.xpath(Interfaces.NewCatetoryPage.MESSAGE)).equals(
				MESSAGESUCCESS))
			return true;
		return false;
	}
	
	//NGA NGUYEN
	public void addSaveNew(String title, String status){
		enter(driver, By.xpath(Interfaces.NewCatetoryPage.TXT_TITLE), title);
		if (status!= ""){
			select(driver, By.xpath(Interfaces.NewCatetoryPage.DROP_STATUS), status);
		}
		click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_SAVEANDNEW));
	}
	
	/* 
	 * Is Help Window display
	 * Author: Nga Nguyen
	 */
	public boolean isHelpWindow(){
		String currentWindows = getCurrentWindows(driver);
		click(driver, By.xpath(Interfaces.CatetoryPage.BTN_HELP));
		switchToNewWindows(driver);
		if(getPageTitle(driver).equals(HELP_TITLE)){
			driver.close();
			driver.switchTo().window(currentWindows);
			return true;
		} else
		return false;
	}
}
