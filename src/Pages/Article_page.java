package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Article_page extends Abstract_page {

	private WebDriver driver;

	// Message
	private String MESSAGESUCCESS = "Article successfully saved";
	private String MESSAGEPUBLISH = "1 article published.";
	private String MESSAGEUNPUBLISH = "1 article unpublished.";
	private String MESSAGEARCHIVE = "1 article archived.";
	private String MESSAGEDELETE = "1 article deleted.";
	private String MESSAGETRASHARTICLE = "1 article trashed.";
	private String MESSAGECHECKIN = "1 article successfully checked in";
	private String HELP_TITLE = "Joomla! Help";

	// Status
	private String STATUS_TRASHED = "Trashed";
	private String STATUS_ARCHIVED = "Archived";
	private String STATUS_ALL = "All";
	private String PUBLISH = "Published";
	private String UNPUBLISH = "Unpublished";
	private String FRATURED = "Featured article";
	private String UNFRATURED = "Unfeatured article";
	private String ACCESS_PUBLIC = "Public";

	public Article_page(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Add new article
	 * 
	 * Author: Tan Vo
	 */
	public void addNewArticle(String _title, String _category, String _status,
			String _content, String _image, String button) {
		clickNewbutton();

		NewArticle_page newarticle = Factory_page.getNewArticlePage(driver);

		newarticle.addNewArticle(_title, _category, _status, _content, _image,
				button);

	}

	/*
	 * Click on New button
	 * 
	 * Author: Tan Vo
	 */
	public void clickNewbutton() {
		click(driver, By.xpath(Interfaces.ArticlePage.BTN_NEW));
	}

	/*
	 * Is message Article successfully saved displayed
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isMessageArticleDisplay() {
		if (getText(driver, By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS))
			return true;
		return false;
	}

	/*
	 * Is Article successfully saved displayed
	 * 
	 * Author: Tan Vo
	 */
	public boolean isArticleDisplay(String article) {

		searchforArticle(article);
		String cell = getText(driver,
				By.xpath(Interfaces.ArticlePage.TABLE_TR + "[1]/td[2]/a"));
		if (cell.equals(article))
			return true;
		return false;
	}

	/*
	 * Is article publish
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isMessagePublishDisplay() {

		if (getText(driver, By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE))
				.equals(MESSAGEPUBLISH))
			return true;
		return false;
	}

	/*
	 * Is article unpublish
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isMessageUnPublishDisplay() {

		if (getText(driver, By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE))
				.equals(MESSAGEUNPUBLISH))
			return true;
		return false;
	}

	/*
	 * Is selected article has publish icon
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isShowUnpublishIcon(String article) {

		searchforArticle(article);

		if (isControlExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR
				+ "[1]/td[3]/a/span/span[contains(text(),'Unpublished')]")))
			return true;
		return false;
	}

	/*
	 * Is selected article has unpublish icon
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isShowPublishIcon(String article) {

		searchforArticle(article);

		if (isControlExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR
				+ "[1]/td[3]/a/span/span[contains(text(),'Published')]")))
			return true;
		return false;
	}

	/*
	 * Is article archive
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isArchiveMessage() {
		if (getText(driver, By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE))
				.equals(MESSAGEARCHIVE))
			return true;

		return false;
	}

	/*
	 * Is article in archive list
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isArchiveList(String article) {

		select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS),
				STATUS_ARCHIVED);

		searchforArticle(article);
		String cell = getText(driver,
				By.xpath(Interfaces.ArticlePage.TABLE_TR + "[1]/td[2]/a"));

		try {
			if (cell.equals(article))
				return true;
			return false;
		} finally {
			select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS), "All");
		}
	}

	/*
	 * Enter edit article page
	 * 
	 * Parameter: article name
	 * 
	 * Author: Tan Vo
	 */
	public void editArticle(String oldtitle, String title,
			String category, String status, String content) {
		searchforArticle(oldtitle);
		clickFirstArticle();
		click(driver, By.xpath(Interfaces.ArticlePage.BTN_EDIT));

		NewArticle_page newarticle = Factory_page.getNewArticlePage(driver);
		newarticle.editArticle(title, category, status, content);

	}

	/*
	 * Delete article
	 * 
	 * Parameter: article name
	 * 
	 * Author: Tan Vo
	 */
	public void deleteArticle(String _article) {
		select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS), "All");
		searchforArticle(_article);
		
		String cell = getText(driver,
				By.xpath(Interfaces.ArticlePage.TABLE_TR + "[1]/td[2]/a"));

		if (cell.equals(_article)) {
			clickFirstArticle();
			click(driver, By.xpath(Interfaces.ArticlePage.BTN_TRASH));
			select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS),
					STATUS_TRASHED);

			clickFirstArticle();

			click(driver, By.xpath(Interfaces.ArticlePage.BTN_EMPTYTRASH));
			waitControlExist(
					driver,
					By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE
							+ "[contains(text(),'" + MESSAGEDELETE + "')]"));
		}
	}

	/*
	 * Publish article
	 * 
	 * Parameter: article name
	 * 
	 * Author: Giang Nguyen
	 */
	public void publishArticle(String article) {

		searchforArticle(article);
		clickFirstArticle();
		clickPublish();
	}

	/*
	 * UnPublish article
	 * 
	 * Parameter: article name
	 * 
	 * Author: Giang Nguyen
	 */
	public void unpublishArticle(String article) {

		searchforArticle(article);
		clickFirstArticle();
		clickUnPublish();
	}

	/*
	 * Archive article
	 * 
	 * Parameter: article name
	 * 
	 * Author: Nga Nguyen
	 */
	public void archiveArticle(String article) {

		searchforArticle(article);
		clickFirstArticle();
		clickArchieve();
	}

	/*
	 * Click on filter ordering
	 * 
	 * Author: Tan Vo
	 */
	public void clickOrdering() {
		click(driver, By.xpath(Interfaces.ArticlePage.FILTER_ORDERING));
	}

	public boolean isArticleChangePosition(String article) {
		
		searchforArticle("");
		selectDisplayItem("All");
		int first, second = 0;
		clickOrdering();
		first = getPositionArticle(article);
		clickOrdering();
		second = getPositionArticle(article);
		clickOrdering();
		if (first != second)
			return true;
		return false;
	}

	/*
	 * Get position of article in table
	 * 
	 * Parameter: article name
	 * 
	 * Author: Tan Vo
	 */
	public int getPositionArticle(String article) {
		int iCount = 0;
		int position = 0;
		iCount = countElement(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.ArticlePage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(article)) {
				position = i;
				break;
			}
		}
		return position;
	}

	/*
	 * Click on status icon
	 * 
	 * Parameter: article name
	 * 
	 * Author: Tan Vo
	 */
	public void clickStatusIcon(String article) {
		
		searchforArticle(article);
		clickFirstArticle();
		click(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"/td[3]/a/span"));
	}

	/*
	 * Click on Featured icon
	 * 
	 * Parameter: article name
	 * 
	 * Author: Tan Vo
	 */
	public void clickFeaturedIcon(String article) {
		
		select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS), STATUS_ALL);
		searchforArticle(article);
		click(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"/td[4]/a/img"));
	}

	/*
	 * Is Featured article
	 * 
	 * Parameter: article name
	 * 
	 * Author: Tan Vo
	 */
	public boolean isFeaturedArticle(String article) {

		searchforArticle(article);
		if(isControlExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"[1]/td[4]/a/img[@alt='" + FRATURED + "']")))
			return true;
		return false;
	}

	/*
	 * Is UnFeatured icon
	 * 
	 * Parameter: article name
	 * 
	 * Author: Tan Vo
	 */
	public boolean isUnFeaturedArticle(String article) {

		searchforArticle(article);
		if(isControlExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"[1]/td[4]/a/img[@alt='" + UNFRATURED + "']")))
			return true;
		return false;
	}

	/*
	 * Is Article is public access icon
	 * 
	 * Parameter: article name
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isPublicAccessArticle(String article) {

		searchforArticle(article);
		if(isControlExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"[1]/td[7][contains(text(),'Public')]")))
			return true;
		return false;
	}

	/*
	 * Search for an article
	 * 
	 * Parameter: article name
	 * 
	 * Author: Giang Nguyen
	 */
	public void searchforArticle(String article) {
		enter(driver, By.xpath(Interfaces.ArticlePage.TXT_SEARCH), article);
		click(driver, By.xpath(Interfaces.ArticlePage.BTN_SEARCH));

	}

	/*
	 * Is Search Article Display
	 * 
	 * Parameter: article name
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isSearchArticleDisplay(String article) {
		if (getText(
				driver,
				By.xpath(Interfaces.ArticlePage.TABLE_TR + "[" + 1 + "]/td["
						+ 2 + "]/a")).equals(article))
			return true;
		return false;
	}

	/*
	 * Check in article
	 * 
	 * Author: Nga Nguyen
	 */
	public void checkinArticle(String article) {
		
		searchforArticle(article);
		clickFirstArticle();
		clickCheckIn();
	}

	/*
	 * Is Check in archive
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isCheckinMessage() {
		if (getText(driver, By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE))
				.equals(MESSAGECHECKIN))
			return true;

		return false;
	}

	/*
	 * Is Checked archive list
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isCheckinArticle(String article) {
		
		searchforArticle(article);
		if(isControlNotExist(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"[1]/td[2]/a/span/span[contains(text(),'Checked out')]")))
			return true;
		return false;
	}

	public void selectDisplayItem(String _item) {

		select(driver, By.xpath(Interfaces.ArticlePage.DROP_DISPLAY), _item);

	}

	public boolean isPaging(int _item) {
		boolean paging = false;
		int row = countElement(driver,
				By.xpath(Interfaces.ArticlePage.TABLE_TR));
		if (row <= _item) {
			paging = true;
		}
		return paging;
	}

	/*
	 * Trash Article
	 * 
	 * Author: Nga Nguyen
	 */
	public void TrashArticle(String article) {
		select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS), "All");
		searchforArticle(article);
		click(driver, By.xpath(Interfaces.ArticlePage.CHECKBOX_1));
		click(driver, By.xpath(Interfaces.ArticlePage.BTN_TRASH));
	}

	/*
	 * Is Trash Article
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isTrashArticleMessage() {

		if (getText(driver, By.xpath(Interfaces.ArticlePage.CONTROL_MESSAGE))
				.equals(MESSAGETRASHARTICLE))
			return true;
		return false;
	}

	/*
	 * Is Trashed Article in Table Grid
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isTrashedArticleDisplay(String article) {

		select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS),
				STATUS_TRASHED);

		searchforArticle(article);
		String cell = getText(driver,
				By.xpath(Interfaces.ArticlePage.TABLE_TR + "[1]/td[2]/a"));

		try {
			if (cell.equals(article))
				return true;
			return false;
		} finally {
			select(driver, By.xpath(Interfaces.ArticlePage.DROP_STATUS), "All");
		}
	}

	/*
	 * Access to Article's Help window
	 * 
	 * Author: Nga Nguyen
	 */
	public void accessToHelpWindow() {

		click(driver, By.xpath(Interfaces.ArticlePage.BTN_HELP));

	}

	/*
	 * Is HelpWindow displayed
	 * 
	 * Author: Nga Nguyen
	 */
	public boolean isHelpWindow() {
		String currentWindows = getCurrentWindows(driver);
		accessToHelpWindow();
		switchToNewWindows(driver);
		
		try {
			if (getPageTitle(driver).equals(HELP_TITLE))
				return true;
			return false;
		} finally {
			driver.close();
			driver.switchTo().window(currentWindows);
		}
	}

	/*
	 * Click first article
	 * 
	 * Author: Tan Vo
	 */
	public void clickFirstArticle() {

		click(driver, By.xpath(Interfaces.ArticlePage.CHECKBOX_1));
	}

	/*
	 * Click publish button
	 * 
	 * Author: Tan VO
	 */
	public void clickPublish() {

		click(driver, By.xpath(Interfaces.ArticlePage.BTN_PUBLISH));
	}

	/*
	 * Click unpublish button
	 * 
	 * Author: Tan Vo
	 */
	public void clickUnPublish() {

		click(driver, By.xpath(Interfaces.ArticlePage.BTN_UNPUBLISH));
	}

	/*
	 * Click archieve button
	 * 
	 * Author: Tan Vo
	 */
	public void clickArchieve() {

		click(driver, By.xpath(Interfaces.ArticlePage.BTN_ARCHIVE));
	}
	
	/*
	 * Click Check in button
	 * 
	 * Author: Tan Vo
	 */
	public void clickCheckIn(){
		
		click(driver, By.xpath(Interfaces.ArticlePage.BTN_CHECKIN));
	}
	
	// Sort the weblink table by ID column
	public void clickSortID(){
		click(driver, By.xpath(Interfaces.ArticlePage.BTN_CLEAR));
		click(driver, By.xpath(Interfaces.ArticlePage.LNK_SORTID));
	}
	
	// Is Sort DES order
	public boolean isArticleDESByID(){
		
		int count = countElement(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR));
		int firstrow=0;
		int secondrow=0;
		boolean descending = false;
		
		for(int i=1;i<count;i++){
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"["+i+"]/td[12]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"["+j+"]/td[12]")));
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
	
	// Is Sort ASC order
	public boolean isArticleASCByID(){
		
		int count = countElement(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR));
		int firstrow=0;
		int secondrow=0;
		boolean ascending = false;
		
		for(int i=1;i<count;i++){
			firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"["+i+"]/td[12]")));
			int j = i+1;
			secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.ArticlePage.TABLE_TR+"["+j+"]/td[12]")));
			if(firstrow<secondrow)
				ascending = true;
			else {
				ascending = false;
				break;
			}
		}
				
		return ascending;
	}
}
