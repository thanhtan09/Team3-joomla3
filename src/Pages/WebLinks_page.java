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
	private String MESSAGEPUBLISH = "1 weblink published.";
	private String MESSAGEUNPUBLISH = "1 weblink unpublished.";
	private String MESSAGEARCHIVE = "1 weblink archived.";
	private String MESSAGEDELETE = "1 weblink deleted.";
	private String MESSAGETRASHARTICLE = "1 weblink trashed.";
	private String MESSAGECHECKIN = "1 weblink successfully checked in";
	private String HELP_TITLE = "Joomla! Help";

	// Status
	private String STATUS_TRASHED = "Trashed";
	private String STATUS_ARCHIVED = "Archived";
	private String STATUS_ALL = "All";
	private String PUBLISH = "Published";
	private String UNPUBLISH = "Unpublished";
	private String FRATURED = "Featured article";
	private String UNFRATURED = "Unfeatured article";
	private String ACCESS_PUBLIC = "Public";
	
	/*
	 * Add new weblink
	 * 
	 * Author: Tan Vo
	 */
	public void addNewWebLink(String _title, String _url, String _content, String button) {
		
		clickNewbutton();
		
		NewWebLinks_page wblink = Factory_page.getNewWebLinksPage(driver);
		wblink.addNewWebLink(_title, _url, _content, button);
		
	}

	/*
	 * Click on New button
	 * 
	 * Author: Tan Vo
	 */
	public void clickNewbutton() {
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_NEW));
	}

	/*
	 * Is Article successfully saved displayed
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
	
}
