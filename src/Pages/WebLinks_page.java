package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.Interfaces.NewWebLinksPage;

public class WebLinks_page extends Abstract_page {

	private WebDriver driver;
	
	public WebLinks_page (WebDriver driver) {
		this.driver = driver;
	}

	// Message
	private String MESSAGESUCCESS = "Weblink successfully saved";
	private String MESSAGEPUBLISH = "1 weblink successfully published";
	private String MESSAGEUNPUBLISH = "1 weblink successfully unpublished";
	private String MESSAGEARCHIVE = "1 weblink successfully archived";
	private String MESSAGEDELETE = "1 weblink deleted.";
	private String MESSAGETRASHWEBLINK = "1 weblink successfully trashed";
	private String MESSAGECHECKIN = "1 weblink successfully checked in";
	private String HELP_TITLE = "Joomla! Help";

	// Status
	private String STATUS_TRASHED = "Trashed";
	private String STATUS_ARCHIVED = "Archived";
	private String STATUS_ALL = "All";
	private String PUBLISH = "Published";
	private String UNPUBLISH = "Unpublished";
	private String FRATURED = "Featured weblink";
	private String UNFRATURED = "Unfeatured weblink";
	private String ACCESS_PUBLIC = "Public";
	
	/*
	 * Add new weblink
	 * 
	 * Author: Giang Nguyen
	 */
	public void addNewWebLink(String _title, String _url, String _content, String status, String button) {
		
		clickNewbutton();
		
		NewWebLinks_page wblink = Factory_page.getNewWebLinksPage(driver);
		wblink.addNewWebLink(_title, _url, _content, status, button);
		
	}

	/*
	 * Click on New button
	 * 
	 * Author: Giang Nguyen
	 */
	public void clickNewbutton() {
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_NEW));
	}

	/*
	 * Is Weblink successfully saved displayed
	 * 
	 * Author: Giang
	 */
	public boolean isWebLinkDisplay(String weblink) {
		boolean show = false;
		if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS)) {
			int iCount = 0;
			iCount = countElement(driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR));
			for (int i = 1; i <= iCount; i++) {
				String cell = getText(
						driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(weblink)) {
					show = true;
					break;
				}
			}
		}
		return show;
	}
	
	public WebLinks_page editWeblink(String oldtitle, String title,
			String url, String content, String status) {
		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(oldtitle)) {
				click(driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 1 + "]/input[@type='checkbox']"));
				break;
			}
		}
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_EDIT));

		NewWebLinks_page newweblink = Factory_page.getNewWebLinksPage(driver);
		newweblink.editWeblink(title, url, content, status);

		return new WebLinks_page(driver);
	}
	
	public boolean isMessageWeblinkDisplay() {
		if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS))
			return true;
		return false;
	}
	
	public void deleteWeblink(String _weblink) {
		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), "All");
		searchforWeblink(_weblink);
		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_TRASH));
		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), STATUS_TRASHED);

		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));

		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_EMPTYTRASH));
		waitControlExist(
				driver,
				By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE
						+ "[contains(text(),'" + MESSAGEDELETE + "')]"));
	}
	
	public void searchforWeblink(String weblink) {
		enter(driver, By.xpath(Interfaces.WebLinksPage.TXT_SEARCH), weblink);
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_SEARCH));

	}
	
	/*
	 * Publish Weblink
	 * 
	 * Parameter: Weblink name
	 * 
	 * Author: Giang Nguyen
	 */
	public void publishWeblink(String weblink) {
		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(weblink)) {
				click(driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 1 + "]/input[@type='checkbox']"));

				break;
			}
		}
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_PUBLISH));
	}

	/*
	 * UnPublish Weblink
	 * 
	 * Parameter: Weblink name
	 * 
	 * Author: Giang Nguyen
	 */
	public void unpublishWeblink(String weblink) {
		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(weblink)) {
				click(driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 1 + "]/input[@type='checkbox']"));

				break;
			}
		}
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_UNPUBLISH));
	}
	
	/*
	 * Is weblink publish
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isPublish(String weblink) {
		boolean show = false;
		if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGEPUBLISH)) {
			int iCount = 0;
			iCount = countElement(driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR));
			for (int i = 1; i <= iCount; i++) {
				String cell = getText(
						driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(weblink)) {
					if (isControlExist(
							driver,
							By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
									+ "]/td[3]/a/span/span[contains(text(),'"
									+ PUBLISH + "')]")))
						show = true;
					break;
				}
			}
		}

		return show;
	}

	/*
	 * Is weblink unpublish
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isUnPublish(String weblink) {
		boolean show = false;

		if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGEUNPUBLISH)) {
			int iCount = 0;
			iCount = countElement(driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR));
			for (int i = 1; i <= iCount; i++) {
				String cell = getText(
						driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(weblink)) {
					if (isControlExist(
							driver,
							By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
									+ "]/td[3]/a/span/span[contains(text(),'"
									+ UNPUBLISH + "')]")))
						show = true;
					break;
				}
			}
		}

		return show;
	}
	// Is Message Unpublished displayed
	public boolean isMessageUnpublishWeblinkDisplay(){
		if(getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGEUNPUBLISH)){
			return true;
			}
		return false;
	}
	// Is Message Published displayed
	public boolean isMessagePublishWeblinkDisplay(){
		if(getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGEPUBLISH)){
			return true;
			}
		return false;
	}
	
	/*
	 * Archive weblink
	 * 
	 * Parameter: weblink name
	 * 
	 * Author: giang
	 */
	public void archiveWeblink(String weblink) {
		
		searchforWeblink(weblink);
		click(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1 + "]/td[" + 1 + "]/input[@type='checkbox']"));
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_ARCHIVE));
	}
	
	/*
	 * Is message archive
	 * 
	 * Author: Giang
	 */
	public boolean isArchiveMessage() {
		if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGEARCHIVE))
			return true;

		return false;
	}

	/*
	 * Is weblink in archive list
	 * 
	 * Author: Giang
	 */
	public boolean isArchiveList(String weblink) {
		boolean show = false;

		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS),
				STATUS_ARCHIVED);
		searchforWeblink(weblink);
			String cell = getText(
					driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(weblink)) {
				show = true;
			}
		return show;
	}
	
	/*
	 * Trash Weblink
	 * 
	 * Author: Giang
	 */
	public void TrashWeblink(String weblink) {
		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), "All");
		searchforWeblink(weblink);
		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_TRASH));
	}

	/*
	 * Is Trash Weblink message
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isTrashWeblinkMessage() {
		if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGETRASHWEBLINK))
			return true;

		return false;
	}

	/*
	 * Is Trashed Weblink in Table Grid
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isTrashedWeblinkinTable(String weblink) {
		boolean show = false;
		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS),
				STATUS_TRASHED);
		searchforWeblink(weblink);
		String cell = getText(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1
						+ "]/td[" + 2 + "]/a"));
		if (cell.equals(weblink)) {
			show = true;
		}
		return show;
	}
	
	/*
	 * Access to Weblink's Help window
	 * 
	 * Author: Giang
	 */
	public void accessToHelpWindow() {

		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_HELP));

	}
	/*
	 * Is HelpWindow displayed
	 * 
	 * Author: Giang
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
	 * Is Search Weblink Display
	 * 
	 * Parameter: weblink name
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isSearchWeblinkDisplay(String weblink) {
		boolean search = false;
		if (getText(
				driver,
				By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1 + "]/td["
						+ 2 + "]/a")).equals(weblink)) {
			search = true;
		}
		return search;
	}
	
	
}
