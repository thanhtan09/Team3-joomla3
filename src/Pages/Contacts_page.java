package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Contacts_page extends Abstract_page {

	private WebDriver driver;

	// Message
	private String MESSAGESUCCESS = "Contact successfully saved";
	private String MESSAGEPUBLISH = "1 contact successfully published";
	private String MESSAGEUNPUBLISH = "1 contact successfully unpublished";
	private String MESSAGEARCHIVE = "1 contact successfully archived";
	private String MESSAGEDELETE = "1 contact successfully deleted";
	private String MESSAGECHECKIN = "1 contact successfully checked in";
	private String MESSAGETRASHCONTACT = "1 contact successfully trashed";

	// Status
	private String STATUS_TRASHED = "Trashed";
	private String STATUS_ARCHIVED = "Archived";
	private String STATUS_ALL = "All";
	//private String PUBLISH = "Published";
	//private String UNPUBLISH = "Unpublished";
	private String FEATURED = "Featured";
	private String UNFEATURED = "Unfeatured contact";
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
			
			searchforContact(oldname);
			clickFirstContact();
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
		
	// Is Message Contact Public Successfully displayed
	public boolean isMessagePublishContactDisplay(){
		if(getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGEPUBLISH)){
			return true;
			}
			return false;
	}
		
	// Is Publish Icon Shown
	 public boolean isPublishContactDisplay(String _contact) {

			searchforContact(_contact);

			if (isControlExist(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR
					+ "[1]/td[4]/descendant::span[contains(text(),'Published')]")))
				return true;
			return false;
	}
			
	//Unpublish a published contact
	public void unpublishContact (String _contact){
			
		searchforContact(_contact);
		clickFirstContact();
		click(driver, By.xpath(Interfaces.ContactsPage.BTN_UNPUBLISH));
		
		}
		
	// The "1 contact successfully unpublished" message is displayed
	public boolean isMessageUnpublishContactDisplay(){
		
		if(getText(driver, By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE))
				.equals(MESSAGEUNPUBLISH)){
			return true;
			}
		return false;
	}
		
	// The icon of the selected item is showed as 'Unpublish'
	public boolean isUnpublishContactDisplay(String _contact) {

		searchforContact(_contact);

		if (isControlExist(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR
				+ "[1]/td[4]/descendant::span[contains(text(),'Unpublished')]")))
			return true;
		return false;
	}
		 
	//Archive Contact
	 public void archiveContact (String _contact){
				
		searchforContact(_contact);
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
	 public boolean isArchivedContactDisplay(String _contact) {

			select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS),
					STATUS_ARCHIVED);

			searchforContact(_contact);
			String cell = getText(driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[1]/td[2]/a"));

			try {
				if (cell.equals(_contact))
					return true;
				return false;
			} finally {
				select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), "All");
			}
		}
			 
	//search for contact
	public void searchforContact(String _contact) {
		
			enter(driver, By.xpath(Interfaces.ContactsPage.TXT_SEARCH), _contact);
			click(driver, By.xpath(Interfaces.ContactsPage.BTN_SEARCH));

		}	
	 
	 
	// Delete Contact
	public void deleteContact(String _contact) {
		
			select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), "All");
			searchforContact(_contact);
			
			String cell = getText(driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[1]/td[2]/a"));
			
			if (cell.equals(_contact)) {
				clickFirstContact();
				click(driver, By.xpath(Interfaces.ContactsPage.BTN_TRASH));
				select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS),
						STATUS_TRASHED);

				clickFirstContact();

				click(driver, By.xpath(Interfaces.ContactsPage.BTN_EMPTYTRASH));
				waitControlExist(
						driver,
						By.xpath(Interfaces.ContactsPage.CONTROL_MESSAGE
								+ "[contains(text(),'" + MESSAGEDELETE + "')]"));
			}
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
	
	/*
	 * Click first item
	 * 
	 * Author: Nga Nguyen
	 */
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
		
		searchforContact(contact);
		if(isControlNotExist(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"[1]/td[2]/a/span/span[contains(text(),'Checked out')]")))
			return true;
		return false;
		
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
	public boolean isTrashedContactInGrid(String contact) {

		select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), STATUS_TRASHED);
		searchforContact(contact);
		String cell = getText(driver,
				By.xpath(Interfaces.ContactsPage.TABLE_TR + "[1]/td[2]/a"));

		try {
			if (cell.equals(contact))
				return true;
			return false;
		} 
			finally {
			select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), "All");
		}
	}
	
	// Select Category
	public void selectCategory (String _cate){
		
		select(driver, By.xpath(Interfaces.ContactsPage.DROP_CATEGORY), _cate);
	}

	// Select Category
	public void selectStatus (String _status){
		
		select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), _status);
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
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+i+"]/td[10]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+j+"]/td[10]")));
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
		
		int count = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
		int firstrow=0;
		int secondrow=0;
		boolean ascending = false;
		
		for(int i=1;i<count;i++){
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+i+"]/td[10]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"["+j+"]/td[10]")));
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
	
	public void clickOrdering() {
		click(driver, By.xpath(Interfaces.ContactsPage.FILTER_ORDERING));
	}

	// Change position of contact by using Ordering column
	public boolean isContactChangePosition(String _contact) {
		
		searchforContact("");
		selectDisplayItem("All");
		int first, second = 0;
		clickOrdering();
		first = getPositionContact(_contact);
		clickOrdering();
		second = getPositionContact(_contact);
		clickOrdering();
		if (first != second)
			return true;
		return false;
	}
	
	// Get position of Contact
	public int getPositionContact(String _contact) {
		int iCount = 0;
		int position = 0;
		iCount = countElement(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.ContactsPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(_contact)) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	// Click Status of Icon in GRID
	public void clickStatusIcon(String _contact) {
		
		searchforContact(_contact);
		clickFirstContact();
		click(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"/td[4]/a/span"));
	}
	
	// Click Featured Icon
	public void clickFeaturedIcon(String _contact) {
		
		select(driver, By.xpath(Interfaces.ContactsPage.DROP_STATUS), STATUS_ALL);
		searchforContact(_contact);
		click(driver, By.xpath(Interfaces.ContactsPage.TABLE_TR+"/td[5]/a/img"));
	}
	
	// Is Featured Icon
	public boolean isShowFeaturedIcon(String _contact) {

		//searchforContact(_contact);
		if(isControlExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"[1]/td[5]/a/img[@alt='" + FEATURED + "']")))
			return true;
		return false;
	}

	// Is UnFeatured Icon 
	public boolean isShowUnFeaturedIcon(String _contact) {

		//searchforContact(_contact);
		if(isControlExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"[1]/td[5]/a/img[@alt='" + UNFEATURED + "']")))
			return true;
		return false;
	}
}