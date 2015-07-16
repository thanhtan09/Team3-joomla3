package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Client_page extends Abstract_page {

	private WebDriver driver;

	private String MESSAGESUCCESS = "Client successfully saved";
	private String MESSAGE_PUBLISH = "1 client successfully published";
	private String MESSAGE_UNPUBLISH = "1 client successfully unpublished";
	private String MESSAGE_ARCHIEVE = "1 client successfully archived";
	private String MESSAGE_DELETED = "1 client successfully deleted";
	private String MESSAGE_TRASH = "1 client successfully trashed";
	private String STATUS_TRASH = "Trashed";
	private String STATUS_ARCHIEVE = "Archived";
	private String STATUS_DEFAULT = "- Select Status -";

	public Client_page(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * ======================CHECK ACTION=====================
	 * 
	 * Is messagge success displays
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessageDisplay() {
		if (getText(driver, By.xpath(Interfaces.ClientPage.MESSAGE)).equals(
				MESSAGESUCCESS))
			return true;
		return false;
	}

	/*
	 * Is new client created
	 * 
	 * Author: Tan Vo
	 */
	public boolean isClientcreated(String client) {
		boolean display = false;

		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.ArticlePage.TABLE_TR + "[" + i
							+ "]/td[2]/a"));
			if (cell.equals(client)) {
				display = true;
				break;
			}
		}

		return display;
	}

	/*
	 * Is Message published Client display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessagePublishedClientDisplay() {

		if (getText(driver, By.xpath(Interfaces.ClientPage.MESSAGE)).equals(
				MESSAGE_PUBLISH))
			return true;
		return false;
	}

	/*
	 * Is Message unpublished Client display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessageUnPublishedClientDisplay() {

		if (getText(driver, By.xpath(Interfaces.ClientPage.MESSAGE)).equals(
				MESSAGE_UNPUBLISH))
			return true;
		return false;
	}

	/*
	 * Is Message archieved Client display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessageArchievedClientDisplay() {

		if (getText(driver, By.xpath(Interfaces.ClientPage.MESSAGE)).equals(
				MESSAGE_ARCHIEVE))
			return true;
		return false;
	}

	/*
	 * Is client published
	 * 
	 * Author: Tan Vo
	 */
	public boolean isClientPublished(String client) {

		searchClient(client);
		if (isControlExist(driver, By.xpath(Interfaces.ClientPage.TABLE_TR
				+ "[1]/td[4]/descendant::span[contains(text(),'Published')]")))
			return true;
		return false;
	}

	/*
	 * Is client Unpublished
	 * 
	 * Author: Tan Vo
	 */
	public boolean isClientUnPublished(String client) {

		searchClient(client);
		if (isControlExist(driver, By.xpath(Interfaces.ClientPage.TABLE_TR
				+ "[1]/td[4]/descendant::span[contains(text(),'Unpublished')]")))
			return true;
		return false;
	}

	/*
	 * Is client Unpublished
	 * 
	 * Author: Tan Vo
	 */
	public boolean isClientArchieved(String client) {

		selectStatus(STATUS_ARCHIEVE);
		searchClient(client);
		if (getText(driver,
				By.xpath(Interfaces.ClientPage.TABLE_TR + "/td[2]/a")).equals(
				client)) {
			selectStatus("All");
			return true;
		}
		return false;
	}
	
	/*
	 * Is client trashed
	 * 
	 * Author: Tan Vo
	 */
	public boolean isClientTrashed(){
		
		if(getText(driver, By.xpath(Interfaces.ClientPage.MESSAGE)).equals(MESSAGE_TRASH))
			return true;
		return false;
	}
	
	/*
	 * Is client sent to trash
	 * 
	 * Author: Tan Vo
	 */
	public boolean isClientSentToTrash(String client){
		
		selectStatus(STATUS_TRASH);
		if (getText(driver,
				By.xpath(Interfaces.ClientPage.TABLE_TR + "/td[2]/a")).equals(
				client))
			return true;
		return false;
	}

	/*
	 * ======================ACTION ON CLIENT ACTION=================
	 * 
	 * Open New client page
	 * 
	 * Author: Tan Vo
	 */
	public void addNewClient(String name, String contact, String email,
			String status, String button) {

		clickNew();

		NewClient_page newClient = Factory_page.getNewClientPage(driver);
		newClient.addClient(name, contact, email, status, button);
	}

	/*
	 * Open Category page
	 * 
	 * Author: Tan Vo
	 */
	public Categories_page navigateCategoriespage() {

		navigateMenu(driver, "Components|Banners|Categories");

		return new Categories_page(driver);
	}

	/*
	 * Delete client
	 * 
	 * Author: Tan Vo
	 */
	public void deleteClient(String client) {

		selectStatus("All");
		searchClient(client);

		if (getText(driver,
				By.xpath(Interfaces.ClientPage.TABLE_TR + "/td[2]/a")).equals(
				client)) {

			clickFirstClient();
			clickTrash();

			selectStatus(STATUS_TRASH);
			clickFirstClient();
			clickEmptyTrash();

			waitControlExist(
					driver,
					By.xpath(Interfaces.ClientPage.MESSAGE
							+ "[contains(text(),'" + MESSAGE_DELETED + "')]"));
		}
	}

	/*
	 * Trash client
	 * 
	 * Author: Tan Vo
	 */
	public void trashClient(String client) {

		selectStatus("All");
		searchClient(client);

		if (getText(driver,
				By.xpath(Interfaces.ClientPage.TABLE_TR + "/td[2]/a")).equals(
				client)) {

			clickFirstClient();
			clickTrash();
		}
	}

	/*
	 * Publish a client
	 * 
	 * Author: Tan Vo
	 */
	public void publishClient(String client) {

		searchClient(client);
		clickFirstClient();
		clickPublish();
	}

	/*
	 * UNPublish a client
	 * 
	 * Author: Tan Vo
	 */
	public void unpublishClient(String client) {

		searchClient(client);
		clickFirstClient();
		clickUnblish();
	}

	/*
	 * Archieve a client
	 * 
	 * Author: Tan Vo
	 */
	public void archieveClient(String client) {

		searchClient(client);
		clickFirstClient();
		clickArchive();
	}

	/*
	 * ======================STEP ON ONE ACTION=========================
	 * 
	 * 
	 * CLick on New button
	 * 
	 * Author: Tan Vo
	 */
	public void clickNew() {
		click(driver, By.xpath(Interfaces.ClientPage.BTN_NEW));
	}

	/*
	 * Select drop-down status
	 * 
	 * Author: Tan Vo
	 */
	public void selectStatus(String status) {
		select(driver, By.xpath(Interfaces.ClientPage.DROP_STATUS), status);
	}

	/*
	 * Click first check box
	 * 
	 * Author: Tan Vo
	 */
	public void clickFirstClient() {
		click(driver, By.xpath(Interfaces.ClientPage.CHECKBOX_1));
	}

	/*
	 * CLick on Edit button
	 * 
	 * Author: Tan Vo
	 */
	public void clickEdit() {
		click(driver, By.xpath(Interfaces.ClientPage.BTN_EDIT));
	}

	/*
	 * CLick on Publish button
	 * 
	 * Author: Tan Vo
	 */
	public void clickPublish() {
		click(driver, By.xpath(Interfaces.ClientPage.BTN_PUBLISH));
	}

	/*
	 * CLick on UnPublish button
	 * 
	 * Author: Tan Vo
	 */
	public void clickUnblish() {
		click(driver, By.xpath(Interfaces.ClientPage.BTN_UNPUBLISH));
	}

	/*
	 * CLick on Archive button
	 * 
	 * Author: Tan Vo
	 */
	public void clickArchive() {
		click(driver, By.xpath(Interfaces.ClientPage.BTN_ARCHIVE));
	}

	/*
	 * CLick on Trash button
	 * 
	 * Author: Tan Vo
	 */
	public void clickTrash() {
		click(driver, By.xpath(Interfaces.ClientPage.BTN_TRASH));
	}

	/*
	 * Click Empty Trash button
	 * 
	 * Author: Tan Vo
	 */
	public void clickEmptyTrash() {
		click(driver, By.xpath(Interfaces.ClientPage.BTN_EMPTYTRASH));
	}

	/*
	 * Search client
	 * 
	 * Author: Tan Vo
	 */
	public void searchClient(String client) {
		enter(driver, By.xpath(Interfaces.ClientPage.TXT_SEARCH), client);
		click(driver, By.xpath(Interfaces.ClientPage.BTN_SEARCH));
	}
}
