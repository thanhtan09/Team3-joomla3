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
	private String MESSAGECHECKIN = "1 contact successfully checked in";
	private String MESSAGETRASHCONTACT = "1 contact successfully trashed";

	// Status
	private String STATUS_TRASHED = "Trashed";
	//private String STATUS_ARCHIVED = "Archived";
	private String STATUS_ALL = "All";
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
				.equals(MESSAGESUCCESS))
				return true;
			return false;
	}
	
	// Is CONTACT successfully saved 
	 public boolean isContactDisplay(String contact) {
			searchforContact(contact);
			String cell = getText(driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[1]/td[2]/a"));
			if (cell.equals(contact))
				return true;
			return false;
			
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
			
		searchforContact(contact);
		clickFirstContact();
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
				
		searchforContact(contact);
		clickFirstContact();
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
		
			//select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), "All");
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
	
	/*
	 * Access to Help window
	 * 
	 * Author: Nga Nguyen
	 */
	public void accessToHelpWindow() {

		click(driver, By.xpath(Interfaces.ArticlePage.BTN_HELP));

	}

	/*
	 * Is HelpWindow displayed
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isHelpWindow(){
		String currentWindows = getCurrentWindows(driver);
		accessToHelpWindow();
		switchToNewWindows(driver);
		if(getPageTitle(driver).equals(HELP_TITLE)){
			driver.close();
			driver.switchTo().window(currentWindows);
			return true;
		} else
		return false;
	}
	
	//Click first contact
	public void clickFirstContact(){
		click(driver, By.xpath(Interfaces.ContactsPage.CHECKBOX_1));
	}
	
	public void clickCheckin() {
		click(driver, By.xpath(Interfaces.ContactsPage.BTN_CHECKIN));
	}
	
	//Check in a contact
	public void checkinContact (String _contact){
		
			searchforContact(_contact);
			clickFirstContact ();
			clickCheckin();
			
	}
	
	//Is Check in Message displayed
	public boolean isCheckinMessage() {
		if (getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGECHECKIN))
			return true;

		return false;
	}

	// Is Checked-in Contact displayed
	public boolean isCheckinContact(String contact) {
		boolean show = false;

		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(contact)) {
				if (isControlExist(
						driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a/span/span")))
					show = true;
				break;
			}
		}
		return show;
	}
	
	// Trash Contact
	public void trashContact(String contact) {
		select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), "All");
		searchforContact(contact);
		click(driver, By.xpath(Interfaces.ContactsPage.CHECKBOX_1));
		click(driver, By.xpath(Interfaces.ContactsPage.BTN_TRASH));
	}

	// Is Trash Contact Message displayed
	public boolean isTrashContactMessage() {
		if (getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGETRASHCONTACT))
			return true;

		return false;
	}

	//Is Trashed Article in Table Grid displayed
	public boolean isTrashedContactinG(String contact) {
		boolean show = false;
		select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS),
				STATUS_TRASHED);
		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(contact)) {
				if (isControlExist(
						driver,
						By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a")))
					show = true;
				break;
			}
		}
		return show;
	}
	
		
	//Search for contacts using the filter dropdown list
	public void searchbyfilter (String _name, String _cate, String _stt){
		
		click(driver, By.xpath(Interfaces.ContactsPage.BTN_CLEAR));
		//enter(driver, By.xpath(Interfaces.ContactsPage.TXT_SEARCH), _name);
		select(driver, By.xpath(Interfaces.ContactsPage.DROP_DISPLAY), STATUS_ALL); 
			if (_cate != null){
				select(driver, By.xpath(Interfaces.ContactsPage.DROP_CATEGORY), _cate);
			} 
			if (_stt != null){
				select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), _stt);
			}
		
		}
	
	//Is the filtered contact displayed == FAILED
	public boolean isFilteredContact (String _cate, String _stt){
		boolean show = false;
		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String category = getText(
					driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
							+ "]/td[" + 6 + "]"));
			
			String status = getText(
					driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
							+ "]/td[" + 4 + "]/a/span/span"));
			
			if (category.equals(_cate) && status.equals(_stt)) {
				show = true;
			}
		}
		return show;		
	}
	
	// Sort the contacts table by ID column
	public void clickSortID(){
		
		click(driver, By.xpath(Interfaces.ContactsPage.LNK_SORTID));
	}
	
	// Is Sort DES order
	public boolean isContactDESByID(){
		
		int count = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
		int firstrow=0;
		int secondrow=0;
		boolean descending = false;
		
		for(int i=1;i<count;i++){
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+i+"]/td[13]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+j+"]/td[13]")));
			if(firstrow>secondrow){
				descending = true;
			}
			else {
				descending = false;
				break;
			}
		}
				
		return descending;
	}
	
	// Is Sort ASC order
	public boolean isContactASCByID(){
		
		int count = countElement(driver, By.xpath(Interfaces.BannerPage.TABLE_TR));
		int firstrow=0;
		int secondrow=0;
		boolean ascending = false;
		
		for(int i=1;i<count;i++){
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+i+"]/td[13]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+j+"]/td[13]")));
			if(firstrow<secondrow)
				ascending = true;
			else {
				ascending = false;
				break;
			}
		}
				
		return ascending;
	}
	
	
	//Select number of items displayed
	public void selectDisplayItem(String _item) {

		select(driver, By.xpath(Interfaces.ContactsPage.DROP_DISPLAY), _item);

	}
	

	public boolean isPaging(int _item) {
		boolean paging = false;
		int row = countElement(driver,
				By.xpath(Interfaces.ContactsPage.TABLE_TR));
		if (row <= _item) {
			paging = true;
		}
		return paging;
	}
}