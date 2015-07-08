package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Banner_page extends Abstract_page {

	private WebDriver driver;
	private String MESSAGE_SUCCESS = "Banner successfully saved";
	private String MESSAGE_TRASH = "1 banner successfully deleted";
	private String MESSAGE_UNPUBLISH = "1 banner successfully unpublished";
	private String STATUS_TRASHED = "Trashed";

	public Banner_page(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Add new banner
	 * 
	 * Author: Tan Vo
	 */
	public void addNewBanner(String name, String category, String client,
			String status, String button) {

		clickNew();

		NewBanner_page newBanner = Factory_page.getNewBannerPage(driver);

		newBanner.addNew(name, category, client, status, button);
	}

	/*
	 * Is Message display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessageSuccessDisplay() {
		if (getText(driver, By.xpath(Interfaces.BannerPage.MESSAGE)).equals(
				MESSAGE_SUCCESS))
			return true;
		return false;
	}

	/*
	 * Is Banner display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerDisplay(String banner) {
		searchBanner(banner);
		String cell = getText(driver,
				By.xpath(Interfaces.ArticlePage.TABLE_TR + "[1]/td[2]/a"));
		if (cell.equals(banner))
			return true;
		return false;
	}

	/*
	 * Unpublish banner
	 * 
	 * Author: Tan Vo
	 */
	public void unpublishBanner(String banner) {
		searchBanner(banner);
		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));
		clickUnpublish();
	}

	/*
	 * Is message unpublish banner display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessaggeUnpublishDisplay() {
		if (getText(driver, By.xpath(Interfaces.BannerPage.MESSAGE)).equals(
				MESSAGE_UNPUBLISH))
			return true;
		return false;
	}

	/*
	 * Is banner unpublish
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerUnpublish(String banner) {
		searchBanner(banner);
		if (isControlExist(driver, By.xpath(Interfaces.BannerPage.TABLE_TR
				+ "[1]/td[3]/descendant::span[contains(text(),'Unpublished')]")))
			return true;
		return false;
	}

	/*
	 * Delete banner
	 * 
	 * Author: Tan Vo
	 */
	public void deleteBanner(String banner) {
		select(driver, By.xpath(Interfaces.BannerPage.DROP_STATUS), "All");
		searchBanner(banner);
		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));
		clickTrash();
		select(driver, By.xpath(Interfaces.BannerPage.DROP_STATUS),
				STATUS_TRASHED);
		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));
		clickEmptyTrash();
		waitControlExist(
				driver,
				By.xpath(Interfaces.BannerPage.MESSAGE + "[contains(text(),'"
						+ MESSAGE_TRASH + "')]"));
	}

	/*
	 * Click New button
	 * 
	 * Author: Tan Vo
	 */
	public void clickNew() {
		click(driver, By.xpath(Interfaces.BannerPage.BTN_NEW));
	}

	/*
	 * Click Trash button
	 * 
	 * Author: Tan Vo
	 */
	public void clickTrash() {
		click(driver, By.xpath(Interfaces.BannerPage.BTN_TRASH));
	}

	/*
	 * Click Empty Trash button
	 * 
	 * Author: Tan Vo
	 */
	public void clickEmptyTrash() {
		click(driver, By.xpath(Interfaces.BannerPage.BTN_EMPTYTRASH));
	}

	/*
	 * Click Unpublish button
	 * 
	 * Author: Tan Vo
	 */
	public void clickUnpublish() {
		click(driver, By.xpath(Interfaces.BannerPage.BTN_UNPUBLISH));
	}

	/*
	 * Search banner
	 * 
	 * Author: Tan Vo
	 */
	public void searchBanner(String banner) {
		enter(driver, By.xpath(Interfaces.BannerPage.TXT_SEARCH), banner);
		click(driver, By.xpath(Interfaces.BannerPage.BTN_SEARCH));
	}
}
