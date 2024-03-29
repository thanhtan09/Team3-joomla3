package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewArticle_page extends Abstract_page {

	private WebDriver driver;
	private String MESSAGESUCCESS = "Article successfully saved";
	private String NEWARTICLEPAGE_TITLE = "joomla selenium advance - Administration - Article Manager: Edit Article";

	public NewArticle_page(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Create new article
	 * 
	 * Parameter: title, category, status, content
	 * 
	 * Author: Tan Vo
	 */
	public void addNewArticle(String _title, String _category,
			String _status, String _content, String _image, String button) {
		enterTitle(_title);
		selectCatetory(_category);
		if (_status != "") {
			selectStatus(_status);
		}
		enterArticleText(_content);
		switch (button) {
		case "Save":
			clickSavebutton();
			break;
		case "": case "SaveAndClose":
			clickSaveandClosebutton();
			break;
		}
	}
	
	public boolean isEditArticlePage(){
		if(getPageTitle(driver).equals(NEWARTICLEPAGE_TITLE))
			return true;
		return false;
	}

	/*
	 * Edit an article
	 * 
	 * Parameter: title, category, status, content
	 * 
	 * Author: Tan Vo
	 */
	public void editArticle(String title, String category,
			String status, String content) {

		enterTitle(title);
		selectCatetory(category);
		if (status != "") {
			selectStatus(status);
		}
		enterArticleText(content);
		clickSaveandClosebutton();
	}

	/*
	 * Enter title
	 * 
	 * Parameter: title
	 * 
	 * Author: Tan Vo
	 */
	public void enterTitle(String _title) {
		enter(driver, By.xpath(Interfaces.NewArticlePage.TXT_TITLE), _title);
	}

	/*
	 * Select Category
	 * 
	 * Parameter: category
	 * 
	 * Author: Tan Vo
	 */
	public void selectCatetory(String _cate) {
		select(driver, By.xpath(Interfaces.NewArticlePage.DROP_CATEGORY), _cate);
	}

	/*
	 * Select Status
	 * 
	 * Parameter: Status
	 * 
	 * Author: Tan Vo
	 */
	public void selectStatus(String _status) {
		select(driver, By.xpath(Interfaces.NewArticlePage.DROP_STATUS), _status);
	}

	/*
	 * Enter content
	 * 
	 * Parameter: content
	 * 
	 * Author: Tan Vo
	 */
	public void enterArticleText(String _content) {
		switchFrame(driver, By.xpath(Interfaces.NewArticlePage.FRAME_CONTENT));
		enter(driver, By.xpath(Interfaces.NewArticlePage.TXT_CONTENT), _content);
		driver.switchTo().defaultContent();
	}

	/*
	 * Click on Save button
	 * 
	 * Author: Tan Vo
	 */
	public void clickSavebutton() {
		click(driver, By.xpath(Interfaces.NewArticlePage.BTN_SAVE));
	}

	/*
	 * Click on Save&Close button
	 * 
	 * Author: Tan Vo
	 */
	public void clickSaveandClosebutton() {
		click(driver, By.xpath(Interfaces.NewArticlePage.BTN_SAVEANDCLOSE));
	}

	/*
	 * Click on Save&New button
	 * 
	 * Author: Tan Vo
	 */
	public void clickSaveandNewbutton() {
		click(driver, By.xpath(Interfaces.NewArticlePage.BTN_SAVEANDNEW));
	}

	/*
	 * Click on Cancel button
	 * 
	 * Author: Tan Vo
	 */
	public void clickCancelbutton() {
		click(driver, By.xpath(Interfaces.NewArticlePage.BTN_CANCEL));
	}
	
	/*
	 * Is message Article successfully saved displayed
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isMessageArticleDisplay(){
		if(getText(driver, By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS))
			return true;
		return false;
	}
}
