package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewWebLinks_page extends Abstract_page{
	
	private WebDriver driver;
	
	public NewWebLinks_page (WebDriver driver) {
		this.driver = driver;
	}

	public WebLinks_page addNewWebLink(String _title, String _url, String _content, String _status, String _cate, String button) {
		
		enterTitle(_title);
		enterURL(_url);
		enterWLText(_content);
		
		if (_status != "") {
			selectStatus(_status);
		}
		
		if (_cate != "") {
			selectCatetory(_cate);
		}
		
		switch (button) {
		case "Save":
			clickSavebutton();
			break;
		case "": 
		case "SaveAndClose":
			clickSaveandClosebutton();
			break;
		}

		return new WebLinks_page(driver);
	}

	
	/*
	 * Enter title
	 * 
	 * Parameter: title
	 * 
	 * Author: Giang Nguyen
	 */
	public void enterTitle(String _title) {
		enter(driver, By.xpath(Interfaces.NewWebLinksPage.TXT_TITLE), _title);
	}

	public void enterURL(String _url) {
		enter(driver, By.xpath(Interfaces.NewWebLinksPage.TXT_URL), _url);
	}
	
	public void enterAlias(String _alias) {
		enter(driver, By.xpath(Interfaces.NewWebLinksPage.TXT_ALIAS), _alias);
	}
	/*
	 * Select Category
	 * 
	 * Parameter: category
	 * 
	 * Author: Giang Nguyen
	 */
	public void selectCatetory(String _cate) {
		select(driver, By.xpath(Interfaces.NewWebLinksPage.DROP_CATEGORY), _cate);
	}

	/*
	 * Select Status
	 * 
	 * Parameter: Status
	 * 
	 * Author: Giang Nguyen
	 */
	public void selectStatus(String _status) {
		select(driver, By.xpath(Interfaces.NewWebLinksPage.DROP_STATUS), _status);
	}

	/*
	 * Enter content
	 * 
	 * Parameter: content
	 * 
	 * Author: Giang
	 */
	public void enterWLText(String _content) {
		switchFrame(driver, By.xpath(Interfaces.NewWebLinksPage.FRAME_CONTENT));
		enter(driver, By.xpath(Interfaces.NewWebLinksPage.TXT_CONTENT), _content);
		driver.switchTo().defaultContent();
	}

	/*
	 * Click on Save button
	 * 
	 * Author: Giang
	 */
	public void clickSavebutton() {
		click(driver, By.xpath(Interfaces.NewWebLinksPage.BTN_SAVE));
	}

	/*
	 * Click on Save&Close button
	 * 
	 * Author: Giang
	 */
	public void clickSaveandClosebutton() {
		click(driver, By.xpath(Interfaces.NewWebLinksPage.BTN_SAVEANDCLOSE));
	}

	/*
	 * Click on Save&New button
	 * 
	 * Author: Giang
	 */
	public void clickSaveandNewbutton() {
		click(driver, By.xpath(Interfaces.NewWebLinksPage.BTN_SAVEANDNEW));
	}

	/*
	 * Click on Cancel button
	 * 
	 * Author: Giang
	 */
	public void clickCancelbutton() {
		click(driver, By.xpath(Interfaces.NewWebLinksPage.BTN_CANCEL));
	}
	
	
	public void clickSaveasCopybutton() {
		click(driver, By.xpath(Interfaces.NewWebLinksPage.BTN_SAVEANDCOPY));
	}
	
	public WebLinks_page editWeblink(String title, String url,
			String content, String status) {

		enterTitle(title);
		enterURL(url);
		enterWLText(content);
		clickSaveandClosebutton();

		return new WebLinks_page(driver);
	}
	
	public WebLinks_page copyWeblink(String title, String alias, String url) {

		enterTitle(title);
		enterURL(url);
		enterAlias(alias);
		clickSaveasCopybutton();

		return new WebLinks_page(driver);
	}
}
