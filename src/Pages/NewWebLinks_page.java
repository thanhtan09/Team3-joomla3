package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewWebLinks_page extends Abstract_page{
	
	private WebDriver driver;
	
	public NewWebLinks_page (WebDriver driver) {
		this.driver = driver;
	}

	public WebLinks_page addNewWebLink(String _title, String _category,
			String _status, String _content, String _image, String button) {
		enterTitle(_title);
		selectCatetory(_category);
		if (_status != "") {
			selectStatus(_status);
		}
		enterArticleText(_content);

		//if (_image != "") {
		///	insertImage(_image);
		//}
		switch (button) {
		case "Save":
			clickSavebutton();
			break;
		case "": case "SaveAndClose":
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
	public void enterArticleText(String _content) {
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
	


}
