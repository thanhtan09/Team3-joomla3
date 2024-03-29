package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebLinks_page extends Abstract_page {

	private WebDriver driver;
	
	public WebLinks_page (WebDriver driver) {
		this.driver = driver;
	}

	// Message
	private String MESSAGESUCCESS = "Weblink successfully saved";
	private String MESSAGEDELETE = "1 weblink deleted.";
	private String MESSAGECHECKIN =  "1 weblink successfully checked in";
	private String HELP_TITLE = "Joomla! Help";
	private String MESSAGE_ORDERING = "Ordering successfully saved.";

	// Status
	private String STATUS_TRASHED = "Trashed";
	private String STATUS_ALL = "All";

	/*
	 * Add new weblink
	 * 
	 * Author: Giang Nguyen
	 */
	public void addNewWebLink(String _title, String _url, String _content, String status, String cate, String button) {
		
		clickNewbutton();
		
		NewWebLinks_page wblink = Factory_page.getNewWebLinksPage(driver);
		wblink.addNewWebLink(_title, _url, _content, status, cate, button);
		
	}

	/*
	 * Click on New button
	 * 
	 * Author: Giang Nguyen
	 */
	public void clickNewbutton() {
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_NEW));
	}

	/*
	 * Is Weblink successfully saved displayed
	 * 
	 * Author: Giang
	 */
	public boolean isWebLinkDisplay(String weblink) {
		boolean show = false;
		searchforWeblink(weblink);
				String cell = getText(
						driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(weblink)) {
					show = true;
				}
		return show;
	}
	
	public WebLinks_page editWeblink(String oldtitle, String title,
			String url, String content, String status) {
		int iCount = 0;
		iCount = countElement(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR));
		for (int i = 1; i <= iCount; i++) {
			String cell = getText(
					driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
							+ "]/td[" + 2 + "]/a"));
			if (cell.equals(oldtitle)) {
				click(driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 1 + "]/input[@type='checkbox']"));
				break;
			}
		}
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_EDIT));

		NewWebLinks_page newweblink = Factory_page.getNewWebLinksPage(driver);
		newweblink.editWeblink(title, url, content, status);

		return new WebLinks_page(driver);
	}
	
	public boolean isMessageWeblinkDisplay() {
		if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
				.equals(MESSAGESUCCESS))
			return true;
		return false;
	}
	
	public void deleteWeblink(String _weblink) {
		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), "All");
		searchforWeblink(_weblink);
		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_TRASH));
		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), STATUS_TRASHED);

		click(driver, By.xpath(Interfaces.BannerPage.CHECKBOX_1));

		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_EMPTYTRASH));
		waitControlExist(
				driver,
				By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE
						+ "[contains(text(),'" + MESSAGEDELETE + "')]"));
	}
	
	public void searchforWeblink(String weblink) {
		enter(driver, By.xpath(Interfaces.WebLinksPage.TXT_SEARCH), weblink);
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_SEARCH));

	}
	
	/*
	 * Access to Weblink's Help window
	 * 
	 * Author: Giang
	 */
	public void accessToHelpWindow() {

		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_HELP));

	}
	/*
	 * Is HelpWindow displayed
	 * 
	 * Author: Giang
	 */
	public boolean isHelpWindow(){
		String currentWindows = getCurrentWindows(driver);
		accessToHelpWindow();
		switchToNewWindows(driver);
		if(getPageTitle(driver).equals(HELP_TITLE)){
			driver.close();
			driver.switchTo().window(currentWindows);
			return true;
		} else
		return false;
	}

	/*
	 * Is Search Weblink Display
	 * 
	 * Parameter: weblink name
	 * 
	 * Author: Giang Nguyen
	 */
	public boolean isSearchWeblinkDisplay(String weblink) {
		boolean search = false;
		if (getText(
				driver,
				By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1 + "]/td["
						+ 2 + "]/a")).equals(weblink)) {
			search = true;
		}
		return search;
	}
	
	//Search for weblinks using the filter dropdown list
	public void searchbyfilter (String _cate, String _stt){
		
		click(driver, By.xpath(Interfaces.WebLinksPage.BTN_CLEAR));
		select(driver, By.xpath(Interfaces.WebLinksPage.DROP_DISPLAY), STATUS_ALL); 
			if (_cate != null){
				select(driver, By.xpath(Interfaces.WebLinksPage.DROP_CATEGORY), _cate);
			} 
			if (_stt != null){
				select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), _stt);
			}
		
		}
	
	// Sort the weblink table by ID column
		public void clickSortID(){
			
			click(driver, By.xpath(Interfaces.WebLinksPage.LNK_SORTID));
		}
		
		// Is Sort DES order
		public boolean isWeblinkDESByID(){
			
			int count = countElement(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR));
			int firstrow=0;
			int secondrow=0;
			boolean descending = false;
			
			for(int i=1;i<count;i++){
				firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR+"["+i+"]/td[9]")));
				int j = i+1;
				secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR+"["+j+"]/td[9]")));
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
		public boolean isWeblinkASCByID(){
			
			int count = countElement(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR));
			int firstrow=0;
			int secondrow=0;
			boolean ascending = false;
			
			for(int i=1;i<count;i++){
				firstrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR+"["+i+"]/td[9]")));
				int j = i+1;
				secondrow = Integer.parseInt(getText(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR+"["+j+"]/td[9]")));
				if(firstrow<secondrow)
					ascending = true;
				else {
					ascending = false;
					break;
				}
			}
					
			return ascending;
		}
		
		//Select number of items displayed
		public void selectDisplayItem(String _item) {

			select(driver, By.xpath(Interfaces.WebLinksPage.DROP_DISPLAY), _item);

		}
		

		public boolean isPaging(int _item) {
			boolean paging = false;
			int row = countElement(driver,
					By.xpath(Interfaces.WebLinksPage.TABLE_TR));
			if (row <= _item) {
				paging = true;
			}
			return paging;
		}
		
		
		public void clickStatusIcon(String weblink) {
			searchforWeblink(weblink);
					click(driver,
							By.xpath(Interfaces.ArticlePage.TABLE_TR + "[" + 1
									+ "]/td[1]/input"));
					click(driver,
							By.xpath(Interfaces.ArticlePage.TABLE_TR + "[" + 1
									+ "]/td[3]/a/span"));
				
		}
		public WebLinks_page copyWeblink(String oldtitle, String title, String alias, String url) {
			searchforWeblink(oldtitle);
			click(driver,By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1
									+ "]/td[" + 1 + "]/input[@type='checkbox']"));
				
			click(driver, By.xpath(Interfaces.WebLinksPage.BTN_EDIT));

			NewWebLinks_page newweblink = Factory_page.getNewWebLinksPage(driver);
			newweblink.copyWeblink(title, alias, url);

			return new WebLinks_page(driver);
		}
		
		public void checkinWeblink (String weblink){
			searchforWeblink(weblink);
			click(driver,By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1
					+ "]/td[" + 1 + "]/input[@type='checkbox']"));

			click(driver, By.xpath(Interfaces.WebLinksPage.BTN_CHECKIN));
			
		}
		
		//Is Check in Message displayed
		public boolean isCheckinMessage() {
			if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
					.equals(MESSAGECHECKIN))
				return true;

			return false;
		}

		// Is Checked-in Weblink displayed
		public boolean isCheckinWeblink(String weblink) {
			boolean show = false;
			searchforWeblink(weblink);
					if (isControlNotExist(
							driver,
							By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1
									+ "]/td[" + 2 + "]/a/span/span")))
						show = true;
			return show;
		}		
		
		// Click a button on Toolbar
		public void clickButton(String weblink,String button) {
			searchforWeblink(weblink);
			click(driver,By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1 + "]/td[" + 1 + "]/input[@type='checkbox']"));
			click(driver, By.xpath(button));
		}	
		
		//Verify correct message displays
		public boolean isSuccessMessageDisplay (String message){
			if(getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
					.equals(message)){
				return true;
				}
			return false;
		}
		
		//Verify correct status displays
		public boolean isWeblinkStatus (String weblink, String status){
			boolean show = false;
			select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), status);
			searchforWeblink(weblink);
			String cell = getText(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + 1
					+ "]/td[" + 2 + "]/a"));
			if (cell.equals(weblink))
				show = true;
			return show;		
		}
		
		public boolean isOrderingWeblink (String weblink1, String weblink2){
			
			boolean show = false;
			click(driver, By.xpath(Interfaces.WebLinksPage.BTN_CLEAR));
			select(driver, By.xpath(Interfaces.WebLinksPage.DROP_STATUS), STATUS_ALL);
			selectDisplayItem("All");
			click(driver, By.xpath(Interfaces.WebLinksPage.HEADER_ORDERING));
			int i = getPositionWeblink(weblink1);
			click(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 5 + "]/span[" + 2 + "]/a/span"));
			if (getText(driver, By.xpath(Interfaces.WebLinksPage.CONTROL_MESSAGE))
					.equals(MESSAGE_ORDERING)){
			int i2 = getPositionWeblink(weblink1);
			int j = getPositionWeblink(weblink2);
			if (i2>j){
				show = true;
			}		
			}
			return show;
			
		}
		
		public int getPositionWeblink(String weblink) {
			int iCount = 0;
			int position = 0;
			iCount = countElement(driver, By.xpath(Interfaces.WebLinksPage.TABLE_TR));
			for (int i = 1; i <= iCount; i++) {
				String cell = getText(
						driver,
						By.xpath(Interfaces.WebLinksPage.TABLE_TR + "[" + i
								+ "]/td[" + 2 + "]/a"));
				if (cell.equals(weblink)) {
					position = i;
					break;
				}
			}
			return position;
		}
		
}

