package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewBanner_page extends Abstract_page {

	private WebDriver driver;

	private String MESSAGE_SUCCESS = "Banner successfully saved";
	private String TITLE_EDITBANNER = "joomla selenium advance - Administration - Banner Manager: New Banner";

	public NewBanner_page(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Create new banner
	 * 
	 * Author: Tan Vo
	 */
	public void addNew(String name, String category, String client, String status,
			String button) {
		enter(driver, By.xpath(Interfaces.NewBannerPage.TXT_NAME), name);
		selectCategory(category);
		selectClient(client);
		if(status!="")
			selectStatus(status);
		
		switch (button) {
		case "Save":
			clickSave();
			break;
		case "":
		case "SaveAndClose":
			clickSaveandClose();
			break;
		}
	}

	/*
	 * Is message success display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isSuccessMessageDisplay() {
		if (getText(driver, By.xpath(Interfaces.NewBannerPage.MESSAGE)).equals(
				MESSAGE_SUCCESS))
			return true;

		return false;
	}

	/*
	 * Is edit banner page display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isEditBannerPage() {
		if(getPageTitle(driver).equals(TITLE_EDITBANNER)){
			return true;
		}
		click(driver, By.xpath(Interfaces.NewBannerPage.BTN_CLOSE));
		return false;

	}

	/*
	 * Click Save button
	 * 
	 * Author: Tan Vo
	 */
	public void clickSave() {
		click(driver, By.xpath(Interfaces.NewBannerPage.BTN_SAVE));
	}

	/*
	 * Click on Save and Close
	 * 
	 * Author: Tan VO
	 */
	public void clickSaveandClose() {
		click(driver, By.xpath(Interfaces.NewBannerPage.BTN_SAVEANDCLOSE));
	}

	/*
	 * Select category
	 * 
	 * Author: Tan Vo
	 */
	public void selectCategory(String cate) {
		select(driver, By.xpath(Interfaces.NewBannerPage.DROP_CATEGORY), "- "
				+ cate);
	}

	/*
	 * Select client
	 * 
	 * Author: Tan Vo
	 */
	public void selectClient(String client) {
		select(driver, By.xpath(Interfaces.NewBannerPage.DROP_CLIENT), client);
	}
	
	/*
	 * Select status
	 * 
	 * Author: Tan Vo
	 */
	public void selectStatus(String status){
		select(driver, By.xpath(Interfaces.NewBannerPage.DROP_STATUS), status);
	}
}
