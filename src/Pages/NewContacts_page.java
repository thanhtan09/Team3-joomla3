package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import autoIT.autoIT;

public class NewContacts_page extends Abstract_page {

	private WebDriver driver;
	
	public NewContacts_page(WebDriver driver) {
		this.driver = driver;
	}
	
	public Contacts_page addNewContact(String _name, String _category,
			String _status, String _image, String button) {
		enterName(_name);
		selectCatetory(_category);
		if (_status != "") {
			selectStatus(_status);
		}
		
		if (_image != "") {
			insertImage(_image);
		}
		switch (button) {
		case "Save":
			clickSavebutton();
			break;
		case "": case "SaveAndClose":
			clickSaveandClosebutton();
			break;
		}

		return new Contacts_page(driver);
	}
	
	//Edit Contact
	public Contacts_page editContact(String name, String category,
			String status) {

		enterName(name);
		selectCatetory(category);
		if (status != "") {
			selectStatus(status);
		}
		
		clickSaveandClosebutton();

		return new Contacts_page(driver);
	}
		
	//enter Name
	public void enterName(String _name) {
		enter(driver, By.xpath(Interfaces.NewContactspage.TXT_NAME), _name);
	}
	
	public void selectCatetory(String _category) {
		select(driver, By.xpath(Interfaces.NewContactspage.DROP_CATEGORY), _category);
	}

	// Status
	public void selectStatus(String _status) {
		select(driver, By.xpath(Interfaces.NewContactspage.DROP_STATUS), _status);
	}
	
	

	// Insert image
	public void insertImage(String image) {

		click(driver, By.xpath(Interfaces.NewContactspage.BTN_IMAGE));
		click(driver, By.xpath(Interfaces.NewContactspage.BTN_BROWSE));
		autoIT au = new autoIT();
		au.uploadFile("D:/CONTENT/MINSU/cat.jpg");
		click(driver, By.xpath(Interfaces.NewContactspage.BTN_UPLOAD));
		click(driver, By.xpath(Interfaces.NewContactspage.BTN_CLOSEPOPUP));
	}

	// Save only
	public void clickSavebutton() {
		click(driver, By.xpath(Interfaces.NewContactspage.BTN_SAVE));
	}

	// Save and Close
	public void clickSaveandClosebutton() {
		click(driver, By.xpath(Interfaces.NewContactspage.BTN_SAVEANDCLOSE));
	}

	// Save and New
	public void clickSaveandNewbutton() {
		click(driver, By.xpath(Interfaces.NewContactspage.BTN_SAVEANDNEW));
	}

	// Cancel
	public void clickCancelbutton() {
		click(driver, By.xpath(Interfaces.NewContactspage.BTN_CANCEL));
	}
	
}