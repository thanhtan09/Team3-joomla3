package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.Interfaces.NewWebLinksPage;

public class WebLinks_page extends Abstract_page {

	private WebDriver driver;
	
	public WebLinks_page (WebDriver driver) {
		this.driver = driver;
	}

	private String MESSAGESUCCESS = "Weblink successfully saved";
	
	/*
	 * Add new article
	 * 
	 * Author: Tan Vo
	 */
	public void addNewWebLink(String _title, String _url,  String _category,
			String _status, String _content, String _image, String button) {
		clickNewbutton();
		
		NewWebLinks_page newweblink = Factory_page.getNewWebLinksPage(driver);
		
	
		
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
	
}
