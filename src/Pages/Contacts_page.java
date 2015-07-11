package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Contacts_page extends Abstract_page {

	private WebDriver driver;

	// Message
	private String MESSAGESUCCESS = "Contact successfully saved";
	//private String MESSAGEPUBLISH = "1 contact successfully published";
	//private String MESSAGEUNPUBLISH = "1 contact successfully unpublished.";
	//private String MESSAGEARCHIVE = "1 contact successfully archived";
	//private String MESSAGEDELETE = "1 contact successfully deleted";
	//private String MESSAGECHECKIN = "1 contact successfully checked in";
	//private String MESSAGETRASHARTICLE = "1 contact successfully trashed";

	// Status
	//private String STATUS_TRASHED = "Trashed";
	//private String STATUS_ARCHIVED = "Archived";
	//private String STATUS_ALL = "All";
	//private String PUBLISH = "Published";
	//private String UNPUBLISH = "Unpublished";
	//private String FRATURED = "Featured article";
	//private String UNFRATURED = "Unfeatured article";
	//private String ACCESS_PUBLIC = "Public";
	
	//Title
	//private String HELP_TITLE = "Joomla! Help";

	public Contacts_page(WebDriver driver) {
		this.driver = driver;
	}
	
	/*NGA*/
	public void addNewContact(String _name, String _category,
			String _status, String _image, String button) {

		clickNewbutton();
		
		NewContacts_page newcontact = Factory_page.getNewContactsPage(driver);
		
		newcontact.addNewContact(_name, _category, _status, _image, button);
		
	}
	
	/*NGA*/
	public void clickNewbutton() {
		click(driver, By.xpath(Interfaces.ContactsPage.BTN_NEW));
	}
	
	// Is Message CONTACT SAVED SUCCESSFULLY displayed
	public boolean isMessageContactDisplay(){
		if(getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS)){
		return true;
		}
		return false;
	}
	
	// Is CONTACT successfully saved displayed
	 public boolean isContactDisplay(String contact) {
		boolean show = false;
		if (getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS)) {
			int iCount = 0;
			iCount = countElement(driver,
					By.xpath(Interfaces.ArticlePage.TABLE_TR));
			for (int i = 1; i <= iCount; i++) {
				String cell = getText(
						driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(contact)) {
					show = true;
					break;
				}
			}
		}
		return show;
	}
}
