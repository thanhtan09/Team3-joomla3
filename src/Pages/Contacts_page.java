package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Contacts_page extends Abstract_page {

	private WebDriver driver;

	// Message
	private String MESSAGESUCCESS = "Contact successfully saved";
	private String MESSAGEPUBLISH = "1 contact successfully published";
	private String MESSAGEUNPUBLISH = "1 contact successfully unpublished.";
	private String MESSAGEARCHIVE = "1 contact successfully archived";
	private String MESSAGEDELETE = "1 contact successfully deleted";
	//private String MESSAGECHECKIN = "1 contact successfully checked in";
	//private String MESSAGETRASHARTICLE = "1 contact successfully trashed";

	// Status
	private String STATUS_TRASHED = "Trashed";
	//private String STATUS_ARCHIVED = "Archived";
	//private String STATUS_ALL = "All";
	//private String PUBLISH = "Published";
	//private String UNPUBLISH = "Unpublished";
	//private String FRATURED = "Featured article";
	//private String UNFRATURED = "Unfeatured article";
	//private String ACCESS_PUBLIC = "Public";
	
	//Title
	private String HELP_TITLE = "Joomla! Help";

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
	
	// Is CONTACT successfully saved 
	 public boolean isContactDisplay(String contact) {
		boolean show = false;
		if (getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS)) {
			int iCount = 0;
			iCount = countElement(driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR));
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
	 
	 
	//Edit contact
	public Contacts_page editContact(String oldname, String name,
				String category, String status) {
			
		int iCount = 0;
			iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
				String cell = getText(
						driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(oldname)) {
					click(driver,
							By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
									+ "]/td[" + 1 + "]/input[@type='checkbox']"));
					break;
				}
			}
			click(driver, By.xpath(Interfaces.ContactsPage.BTN_EDIT));

			NewContacts_page newContact = Factory_page.getNewContactsPage(driver);
			newContact.editContact(name, category, status);

		return new Contacts_page(driver);
	}
		
	//Publish an unpublished contact
	public void publishContact (String contact){
			
		int iCount = 0;
			iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
			for (int i = 1; i <= iCount; i++) {
				String cell = getText(
					driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
				if (cell.equals(contact)) {
					click(driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
								+ "]/td[" + 1 + "]/input[@type='checkbox']"));
					break;
				}
			}
		click(driver, By.xpath(Interfaces.ContactsPage.BTN_PUBLISH));
	}
		
	// Is Message CONTACT SAVED SUCCESSFULLY displayed
	public boolean isMessagePublishContactDisplay(){
		if(getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGEPUBLISH)){
			return true;
			}
			return false;
	}
		
	// Is CONTACT successfully saved 
	 public boolean isPublishContactDisplay(String contact) {
		boolean show = false;
			if (getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
					.equals(MESSAGEPUBLISH)) {
				int iCount = 0;
				iCount = countElement(driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR));
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
		
		
	//Unpublish a published contact
	public void unpublishContact (String contact){
			
		int iCount = 0;
			iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
			for (int i = 1; i <= iCount; i++) {
				String cell = getText(
						driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(contact)) {
					click(driver,
							By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
									+ "]/td[" + 1 + "]/input[@type='checkbox']"));
					break;
				}
			}
			click(driver, By.xpath(Interfaces.ContactsPage.BTN_UNPUBLISH));
		}
		
	// Is Message CONTACT SAVED SUCCESSFULLY displayed
	public boolean isMessageUnpublishContactDisplay(){
		if(getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGEUNPUBLISH)){
			return true;
			}
		return false;
	}
		
	// Is CONTACT successfully saved 
	public boolean isUnpublishContactDisplay(String contact) {
		boolean show = false;
			if (getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
					.equals(MESSAGEPUBLISH)) {
				int iCount = 0;
				iCount = countElement(driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR));
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
		 
	//Archive Contact
	 public void archiveContact (String contact){
				
		int iCount = 0;
			iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
				for (int i = 1; i <= iCount; i++) {
					String cell = getText(
							driver,
							By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
									+ "]/td[" + 2 + "]/a"));
					if (cell.equals(contact)) {
						click(driver,
								By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
										+ "]/td[" + 1 + "]/input[@type='checkbox']"));
						break;
					}
				}
		click(driver, By.xpath(Interfaces.ContactsPage.BTN_ARCHIVE));
	}
		 
	// Is  "1 contact archived" message displayed
	public boolean isMessageArchivedContactDisplay(){
		if(getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
						.equals(MESSAGEARCHIVE)){
				return true;
				}
			return false;
	}
			
	// Is CONTACT successfully archived
	 public boolean isArchivedContactDisplay(String contact) {
		boolean show = false;
			if (getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
						.equals(MESSAGEPUBLISH)) {
					int iCount = 0;
					iCount = countElement(driver,
							By.xpath(Interfaces.ContactsPage.TABLE_TR));
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
			 
	//search for contact
	public void searchforContact(String contact) {
		
			enter(driver, By.xpath(Interfaces.ContactsPage.TXT_SEARCH), contact);
			click(driver, By.xpath(Interfaces.ContactsPage.BTN_SEARCH));

		}	
	 
	 
	// Delete Contact
	public void deleteContact(String _contact) {
		
			select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), "All");
			searchforContact(_contact);
			click(driver, By.xpath(Interfaces.ContactsPage.CHECKBOX_1));
			click(driver, By.xpath(Interfaces.ContactsPage.BTN_TRASH));
			select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS),
					STATUS_TRASHED);

			click(driver, By.xpath(Interfaces.ContactsPage.CHECKBOX_1));

			click(driver, By.xpath(Interfaces.ContactsPage.BTN_EMPTYTRASH));
					waitControlExist(
							driver,
							By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE
									+ "[contains(text(),'" + MESSAGEDELETE + "')]"));
	}
}