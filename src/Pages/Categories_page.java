package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Categories_page extends Abstract_page {

	private WebDriver driver;
	private String MESSAGESUCCESS = "Category successfully saved";
	private String MESSAGE_TRASH = "1 category successfully deleted";
	private String STATUS_TRASHED = "Trashed";
	private String MESSAGE_UNPUBLISHED = "1 category successfully unpublished";
	private String MESSAGE_PUBLISHED = "1 category successfully published";
	private String MESSAGE_ARCHIVE = "1 category successfully archived";
	private String MESSAGE_TRASHED = "1 category successfully trashed";

	public Categories_page(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Click New button
	 * 
	 * Author: Tan Vo
	 */
	public Categories_page addNewCategory(String title) {
		clickNew();

		NewCategory_page newCate = Factory_page.getNewCategoryPage(driver);
		newCate.addNew(title);
		return new Categories_page(driver);
	}
	
	/*
	 * Navigate to Banner page
	 * 
	 * Author: Tan Vo
	 */
	public Banner_page navigatetoBannerpage(){
		
		navigateMenu(driver, "Components|Banners|Banners");		
		
		return new Banner_page(driver);
	}

	/*
	 * Check message display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessageDisplay() {
		if (getText(driver, By.xpath(Interfaces.CatetoryPage.MESSAGE)).equals(
				MESSAGESUCCESS))
			return true;
		return false;
	}

	/*
	 * Check category is created
	 * 
	 * Author: Tan Vo
	 */
	public boolean isCategoryDisplay(String cate) {
		
		searchCategory(cate);
		String cell = getText(driver,
				By.xpath(Interfaces.CatetoryPage.TABLE_TR + "[1]/td[2]/a"));
		if (cell.equals(cate))
			return true;
		return false;

	}
	
	/*
	 * Delete Category
	 * 
	 * Author: Tan Vo
	 */
	public void deleteCategory(String cate){
		select(driver, By.xpath(Interfaces.CatetoryPage.DROP_STATUS), "All");
		searchCategory(cate);
		click(driver, By.xpath(Interfaces.CatetoryPage.CHECKBOX_1));
		clickTrash();
		select(driver, By.xpath(Interfaces.CatetoryPage.DROP_STATUS), STATUS_TRASHED);
		
		click(driver, By.xpath(Interfaces.CatetoryPage.CHECKBOX_1));
		clickEmptyTrash();
		waitControlExist(
				driver,
				By.xpath(Interfaces.CatetoryPage.MESSAGE
						+ "[contains(text(),'" + MESSAGE_TRASH + "')]"));
	}

	/*
	 * Click New button
	 * 
	 * Author: Tan Vo
	 */
	public void clickNew() {
		click(driver, By.xpath(Interfaces.CatetoryPage.BTN_NEW));
	}
	
	/*
	 * Click Trash button
	 * 
	 * Author: Tan Vo
	 */
	public void clickTrash(){
		click(driver, By.xpath(Interfaces.CatetoryPage.BTN_TRASH));
	}
	
	/*
	 * Click Empty Trash button
	 * 
	 * Author: Tan Vo
	 */
	public void clickEmptyTrash(){
		click(driver, By.xpath(Interfaces.CatetoryPage.BTN_EMPTYTRASH));
	}
	
	/*
	 * Search banner
	 * 
	 * Author: Tan Vo
	 */
	public void searchCategory(String cate){
		enter(driver, By.xpath(Interfaces.CatetoryPage.TXT_SEARCH), cate);
		click(driver, By.xpath(Interfaces.CatetoryPage.BTN_SEARCH));
	}
	
	public NewCategory_page clickEdit(String oldtitle) {
		searchCategory(oldtitle);
		click(driver,
						By.xpath(Interfaces.CatetoryPage.TABLE_TR + "[" + 1
								+ "]/td[" + 1 + "]/input[@type='checkbox']"));
		click(driver, By.xpath(Interfaces.CatetoryPage.BTN_EDIT));
		
		return Factory_page.getNewCategoryPage(driver);
		
	}
	
	public void clickButton(String cate,String button) {
		searchCategory(cate);
		click(driver,By.xpath(Interfaces.CatetoryPage.TABLE_TR + "[" + 1 + "]/td[" + 1 + "]/input[@type='checkbox']"));
		click(driver, By.xpath(button));
	}
	
	
	
}
