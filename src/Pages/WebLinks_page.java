package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.Interfaces.NewWebLinksPage;

public class WebLinks_page extends Abstract_page {

	private WebDriver driver;
	
	public WebLinks_page (WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Add new article
	 * 
	 * Author: Tan Vo
	 */
	public void addNewWebLink(String _title, String _category,
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
	
}
