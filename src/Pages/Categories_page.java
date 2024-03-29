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
	
	private String STATUS_ARCHIVED = "Archived";
	private String STATUS_ALL = "All";
	private String STATUS_PUBLISH = "Published";
	private String STATUS_UNPUBLISH = "Unpublished";
	private String HELP_TITLE = "Joomla! Help";
	private String CATEMANAGER_TITLE = "joomla selenium advance - Administration - Category Manager: Articles";
	
	public Categories_page(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Click New button
	 * 
	 * Author: Tan Vo
	 */
	public Categories_page addNewCategory(String title, String status) {
		clickNew();

		NewCategory_page newCate = Factory_page.getNewCategoryPage(driver);
		newCate.addNew(title, status);
		return new Categories_page(driver);
	}
	
	
	public Categories_page addNewCategory(String title, String status, String access, String language) {
		clickNew();

		NewCategory_page newCate = Factory_page.getNewCategoryPage(driver);
		newCate.addNew(title, status, access, language);
		return new Categories_page(driver);
	} 
	
	public Categories_page addNewCategory(String title, String status, String access, String language, String button) {
		
		clickNew();

		NewCategory_page newCate = Factory_page.getNewCategoryPage(driver);
		newCate.addNew(title, status, access, language, button);
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
	 * Navigate to Article page
	 * 
	 * Author: Tan Vo
	 */
	public Article_page navigatetoArticlePage(){
		
		navigateMenu(driver, "Content|Article Manager");		
		
		return new Article_page(driver);
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
	 * Check multi categories are created
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isMultiCategoryDisplay(String _cate1, String _cate2) {
		
		if(isControlExist(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_CANCEL)))
			click(driver, By.xpath(Interfaces.NewCatetoryPage.BTN_CANCEL));
		
		searchCategory(_cate1);
		String cell1 = getText(driver,
				By.xpath(Interfaces.CatetoryPage.TABLE_TR + "[1]/td[2]/a"));
		
		searchCategory(_cate2);
		String cell2 = getText(driver,
				By.xpath(Interfaces.CatetoryPage.TABLE_TR + "[1]/td[2]/a"));
		
		//click(driver, By.xpath(Interfaces.CatetoryPage.BTN_CLEAR));
		
		if (cell1.equals(_cate1) && cell2.equals(_cate2))
			return true;
		return false;

	}
	
	/*
	 * Is new category not created
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isCategoryNotInGrid(String cate) {
		
		if(isControlExist(driver, By.xpath(Interfaces.NewClientPage.BTN_CANCEL)))
				click(driver, By.xpath(Interfaces.NewClientPage.BTN_CANCEL));

		searchCategory(cate);

		if(isControlNotExist(driver, By.xpath(Interfaces.CatetoryPage.CHECKBOX_1)))
			return true;
		return false;
	}
	
	/*
	 * Is Categories Manager page display
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isCateManagerPage() {
		
		if(getPageTitle(driver).equals(CATEMANAGER_TITLE))
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
	
	public boolean isSuccessMessageDisplay (String message){
		if(getText(driver, By.xpath(Interfaces.CatetoryPage.MESSAGE))
				.equals(message)){
			return true;
			}
		return false;
	}
	
	public boolean isCategoryStatus (String cate, String status){
		boolean show = false;
		select(driver, By.xpath(Interfaces.CatetoryPage.DROP_STATUS), status);
		searchCategory(cate);
		String cell = getText(driver, By.xpath(Interfaces.CatetoryPage.TABLE_TR + "[" + 1
				+ "]/td[" + 2 + "]/a"));
		if (cell.equals(cate))
			show = true;
		return show;		
	}
	
	public boolean isHelpWindow(){
		String currentWindows = getCurrentWindows(driver);
		click(driver, By.xpath(Interfaces.CatetoryPage.BTN_HELP));
		switchToNewWindows(driver);
		if(getPageTitle(driver).equals(HELP_TITLE)){
			driver.close();
			driver.switchTo().window(currentWindows);
			return true;
		} else
		return false;
	}
	
	public void searchByFilter (String status, String access, String language){
		
		select(driver, By.xpath(Interfaces.CatetoryPage.DROP_STATUS), status);
		select(driver, By.xpath(Interfaces.CatetoryPage.DROP_ACCESS), access);
		select(driver, By.xpath(Interfaces.CatetoryPage.DROP_LANGUAGE), language);
	}
	
}
