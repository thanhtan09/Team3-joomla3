package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Banner_page extends Abstract_page {

	private WebDriver driver;
	private String MESSAGE_SUCCESS = "Banner successfully saved";
	private String MESSAGE_EMPTYTRASH = "1 banner successfully deleted";
	private String MESSAGE_UNPUBLISH = "1 banner successfully unpublished";
	private String MESSAGE_ARCHIEVE = "1 banner successfully archived";
	private String MESSAGE_TRASHBANNER = "1 banner successfully trashed";
	private String MESSAGE_CHECKEDIN = "1 banner successfully checked in";
	private String STATUS_TRASHED = "Trashed";
	private String STATUS_ARCHIEVE = "Archived";
	private String CATETORY_DEFAULT = "- Select Category -";
	private String HELP_BANNERPAGE_TITLE = "Joomla! Help";
	private String CLIENTPAGE_TITLE = "joomla selenium advance - Administration - Banner Manager: Clients";

	/*
	 * Data to test for display banner
	 * 
	 * This category has 101 banners are created
	 */
	private String Data_Catetory = "115154248-Category Test 1";

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
		
		if (cell.equals(banner)) {
			selectClient("- Select Client -");
			selectCategory("- Select Category -");
			return true;
		}
		return false;
	}

	/*
	 * Unpublish banner
	 * 
	 * Author: Tan Vo
	 */
	public void unpublishBanner(String banner) {
		
		searchBanner(banner);
		clickFirstBanner();
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
	 * Archive banner
	 * 
	 * Author: Tan Vo
	 */
	public void archiveBanner(String banner) {
		
		searchBanner(banner);
		clickFirstBanner();
		clickArchieve();
	}

	/*
	 * Is message archieve display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isArchieveMessageDisplay() {
		
		if (getText(driver, By.xpath(Interfaces.BannerPage.MESSAGE)).equals(
				MESSAGE_ARCHIEVE))
			return true;
		return false;
	}

	/*
	 * Is banner archieved
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerArchieved(String banner) {
		
		selectStatus(STATUS_ARCHIEVE);
		searchBanner(banner);
		if (getText(driver,
				By.xpath(Interfaces.BannerPage.TABLE_TR + "/td[2]/a")).equals(
				banner)) {
			selectStatus("All");
			return true;
		}
		return false;
	}

	/*
	 * Delete banner
	 * 
	 * Author: Tan Vo
	 */
	public void deleteBanner(String banner) {
		
		if (isControlExist(driver, By.xpath(Interfaces.NewBannerPage.BTN_CLOSE)))
			click(driver, By.xpath(Interfaces.NewBannerPage.BTN_CLOSE));
		trashBanner(banner);
		emptytrashBanner(banner);
	}

	/*
	 * Trash banner
	 * 
	 * Author: Tan Vo
	 */
	public void trashBanner(String banner) {
		
		selectStatus("All");
		selectClient("- Select Client -");
		selectCategory(CATETORY_DEFAULT);
		searchBanner(banner);
		clickFirstBanner();
		clickTrash();
		waitControlExist(
				driver,
				By.xpath(Interfaces.BannerPage.MESSAGE + "[contains(text(),'"
						+ MESSAGE_TRASHBANNER + "')]"));
	}

	/*
	 * EMPTY Trash banner
	 * 
	 * Author: Tan Vo
	 */
	public void emptytrashBanner(String banner) {
		
		selectStatus(STATUS_TRASHED);
		searchBanner(banner);
		clickFirstBanner();
		clickEmptyTrash();
		waitControlExist(
				driver,
				By.xpath(Interfaces.BannerPage.MESSAGE + "[contains(text(),'"
						+ MESSAGE_EMPTYTRASH + "')]"));
	}

	/*
	 * Is message banner is trashed display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isTrashMessageDisplay() {
		
		if (getText(driver, By.xpath(Interfaces.BannerPage.MESSAGE)).equals(
				MESSAGE_TRASHBANNER))
			return true;
		return false;
	}

	/*
	 * Is help page display
	 * 
	 * Author: Tan Vo
	 */
	public boolean isHelpPage() {
		
		String currentWindows = getCurrentWindows(driver);
		clickHelp();
		switchToNewWindows(driver);
		if (getPageTitle(driver).equals(HELP_BANNERPAGE_TITLE)) {
			driver.close();
			driver.switchTo().window(currentWindows);
			if (isControlExist(driver,
					By.xpath(Interfaces.NewBannerPage.BTN_CLOSE)))
				click(driver, By.xpath(Interfaces.NewBannerPage.BTN_CLOSE));
			return true;
		} else
			return false;
	}

	/*
	 * Is banner sent to trash
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerSentToTrash(String banner) {
		
		selectStatus(STATUS_TRASHED);
		if (getText(driver,
				By.xpath(Interfaces.BannerPage.TABLE_TR + "/td[2]/a")).equals(
				banner))
			return true;
		return false;
	}

	/*
	 * Is banner locked
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerLocked(String banner) {
		
		searchBanner(banner);
		if (isControlExist(
				driver,
				By.xpath(Interfaces.BannerPage.TABLE_TR
						+ "/td[2]/a/span/span[contains(text(),'Checked out')]")))
			return true;
		return false;
	}

	/*
	 * Is message when checked in banner
	 * 
	 * Author: Tan Vo
	 */
	public boolean isMessageCheckedInDisplay() {
		
		if (getText(driver, By.xpath(Interfaces.BannerPage.MESSAGE)).equals(
				MESSAGE_CHECKEDIN))
			return true;
		return false;
	}

	/*
	 * Is banner checked in
	 * 
	 * Author: Tan Vo
	 */
	public boolean isCheckedInBanner(String banner) {
		
		searchBanner(banner);
		if (isControlNotExist(
				driver,
				By.xpath(Interfaces.BannerPage.TABLE_TR
						+ "/td[2]/a/span/span[contains(text(),'Checked out')]")))
			return true;
		return false;
	}

	/*
	 * Is Quantity of items displayed in table is changed
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerDisplayedInTable(String display) {

		if (getText(driver, By.xpath(Interfaces.BannerPage.TXT_SEARCH)) != null)
			enter(driver, By.xpath(Interfaces.BannerPage.TXT_SEARCH), "");

		selectCategory(Data_Catetory);

		select(driver, By.xpath(Interfaces.BannerPage.DROP_DISPLAY), display);
		
		int count = countElement(driver,
				By.xpath(Interfaces.BannerPage.TABLE_TR));
		int dis = Integer.parseInt(display);

		if (display.equals("All")) {
			if (count == 101) {
				selectCategory(CATETORY_DEFAULT);
				return true;
			}
			return false;
		} else {
			if (count == dis) {
				selectCategory(CATETORY_DEFAULT);
				return true;
			}
			return false;
		}
	}
	
	/*
	 * Items are sorted ascending by ID in banner table or not
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerAscendingByID(){
		
		int count = countElement(driver, By.xpath(Interfaces.BannerPage.TABLE_TR));
		int firstrow=0;
		int secondrow=0;
		boolean ascending = false;
		
		for(int i=1;i<count;i++){
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.BannerPage.TABLE_TR+"["+i+"]/td[13]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.BannerPage.TABLE_TR+"["+j+"]/td[13]")));
			if(firstrow<secondrow)
				ascending = true;
			else {
				ascending = false;
				break;
			}
		}
				
		return ascending;
	}
	
	/*
	 * Items are sorted descending by ID in banner table or not
	 * 
	 * Author: Tan Vo
	 */
	public boolean isBannerDescendingByID(){
		
		int count = countElement(driver, By.xpath(Interfaces.BannerPage.TABLE_TR));
		int firstrow=0;
		int secondrow=0;
		boolean descending = false;
		
		for(int i=1;i<count;i++){
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.BannerPage.TABLE_TR+"["+i+"]/td[13]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.BannerPage.TABLE_TR+"["+j+"]/td[13]")));
			if(firstrow>secondrow){
				descending = true;
			}
			else {
				descending = false;
				break;
			}
		}
				
		return descending;
	}
	
	/*
	 * Is client page
	 * 
	 * Author: Tan Vo
	 */
	public boolean isClientPage(){
		
		if(getPageTitle(driver).equals(CLIENTPAGE_TITLE))
			return true;
		return false;
	}

	/*
	 * Check in banner
	 * 
	 * Author: Tan Vo
	 */
	public void checkinBanner(String banner) {
		
		searchBanner(banner);
		clickFirstBanner();
		click(driver, By.xpath(Interfaces.BannerPage.BTN_CHECKIN));
	}

	/*
	 * Click first banner
	 * 
	 * Author: Tan Vo
	 */
	public void clickFirstBanner() {
		
		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));
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
	 * Click Archieve button
	 * 
	 * Author: Tan Vo
	 */
	public void clickArchieve() {
		
		click(driver, By.xpath(Interfaces.BannerPage.BTN_ARCHIEVE));
	}

	/*
	 * Click Help button
	 * 
	 * Author: Tan Vo
	 */
	public void clickHelp() {
		
		click(driver, By.xpath(Interfaces.BannerPage.BTN_HELP));
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

	/*
	 * Select status
	 * 
	 * Author: Tan Vo
	 */
	public void selectStatus(String status) {
		
		select(driver, By.xpath(Interfaces.BannerPage.DROP_STATUS), status);
	}

	/*
	 * Select client
	 * 
	 * Author: Tan Vo
	 */
	public void selectClient(String client) {
		
		select(driver, By.xpath(Interfaces.BannerPage.FILTER_CLIENT), client);
	}

	/*
	 * Select Category
	 * 
	 * Author: Tan Vo
	 */
	public void selectCategory(String cate) {
		
		select(driver, By.xpath(Interfaces.BannerPage.FILTER_CATEGORY), cate);
	}

	/*
	 * Open new banner help
	 * 
	 * Author: Tan Vo
	 */
	public void openNewBannerHelp() {
		
		clickNew();
		clickHelp();
	}

	/*
	 * Select Display
	 * 
	 * Author: Tan Vo
	 */
	public void selectDisplay(String number) {
		
		select(driver, By.xpath(Interfaces.BannerPage.DROP_DISPLAY), number);
	}
	
	/*
	 * Click ID link
	 * 
	 * Author: Tan Vo
	 * 
	 */
	public void clickID(){
		
		click(driver, By.xpath(Interfaces.BannerPage.FILTER_ID));
	}
	
	/*
	 * Click Client link
	 * 
	 * Author: Tan Vo
	 */
	public void clickClientLink(){
		
		click(driver, By.xpath(Interfaces.BannerPage.LINK_CLIENT));
	}
}
