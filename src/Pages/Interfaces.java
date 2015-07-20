package Pages;

public class Interfaces {

	/*
	 * Element in login page
	 * 
	 * Author: Tan Vo
	 */
	public class LoginPage {
		public static final String TXT_USERNAME = "//input[@name='username']";
		public static final String TXT_PASSWORD = "//input[@name='passwd']";
		public static final String DROP_LANGUAGE = "//select[@id='lang']";
		public static final String BTN_LOGIN = "//div[@class='next']/descendant::a";
	}

	/*
	 * Element in home page
	 * 
	 * Author: Tan Vo
	 */
	public class HomePage {
		public static final String MENU_CONTENT = "//ul[@id='menu']/descendant::a[contains(text(),'Content')]";
		public static final String SUBMENU1_ARTICLEMANAGER = "//ul[@id='menu']/descendant::a[contains(text(),'Article Manager')]";
	}

	/*
	 * Element in article page
	 * 
	 * Author: Tan Vo
	 */
	public class ArticlePage {
		public static final String BTN_HELP = "//li[@id='toolbar-help']/a/span";
		public static final String BTN_NEW = "//span[@class='icon-32-new']";
		public static final String BTN_EDIT = "//li[@id='toolbar-edit']/a/span";
		public static final String BTN_PUBLISH = "//li[@id='toolbar-publish']/a/span";
		public static final String BTN_UNPUBLISH = "//li[@id='toolbar-unpublish']/a/span";
		public static final String BTN_ARCHIVE = "//li[@id='toolbar-archive']/a/span";
		public static final String BTN_CHECKIN = "//li[@id='toolbar-checkin']/a/span";
		public static final String BTN_TRASH = "//li[@id='toolbar-trash']/a/span";
		public static final String BTN_EMPTYTRASH = "//li[@id='toolbar-delete']/a/span";		
		public static final String DROP_STATUS = "//select[@name='filter_published']";
		public static final String CONTROL_MESSAGE = "//dd[@class='message message']/ul/li";
		public static final String TABLE = "//table[@class='adminlist']";
		public static final String TABLE_TR = "//table[@class='adminlist']/tbody/tr";	
		public static final String CBX_ALL = "//table[@class='adminlist']/thead/tr/th/input[@type='checkbox']";
		public static final String FILTER_ORDERING = "//table[@class='adminlist']/thead/tr/th/a[contains(text(),'Ordering')]";
		public static final String BTN_SEARCH = "//button[contains(text(),'Search')]";
		public static final String TXT_SEARCH = "//input[@id='filter_search']";
		public static final String DROP_DISPLAY = "//select[@id='limit']";
		public static final String BAR_PAGING = "//div[@class = 'pagination']/div[4]";
		public static final String CHECKBOX_1 = "//input[@id='cb0']";
	}

	/*
	 * Element in new article page
	 * 
	 * Author: Tan Vo
	 */
	public class NewArticlePage {
		public static final String TXT_TITLE = "//input[@id='jform_title']";
		public static final String TXT_ALIAS = "//input[@id='jform_alias']";
		public static final String DROP_CATEGORY = "//select[@id='jform_catid']";
		public static final String DROP_STATUS = "//select [@id='jform_state']";
		public static final String DROP_FRATURE = "//select[@id='jform_featured']";
		public static final String TXT_CONTENT = "//body[@id='tinymce']";
		public static final String BTN_SAVE = "//li[@id='toolbar-apply']/a/span";
		public static final String BTN_SAVEANDCLOSE = "//li[@id='toolbar-save']/a/span";
		public static final String BTN_SAVEANDNEW = "//li[@id='toolbar-save-new']/a/span";
		public static final String BTN_CANCEL = "//li[@id='toolbar-cancel']/a/span";
		public static final String BTN_CLOSE = "//li[@id='toolbar-cancel']/a/span";
		public static final String FRAME_CONTENT = "//iframe[@id='jform_articletext_ifr']";
		public static final String BTN_IMAGE = "//div[@id='editor-xtd-buttons']/descendant::a[contains(text(),'Image')]";
		public static final String BTN_INSERT = "//div[@class='fltrt']/button[contains(text(),'Insert')]";
		public static final String FRAME_IMAGE = "//iframe[@id='imageframe']";
	}
	
	/*
	 * Element in new client page
	 * 
	 * Author: Tan Vo
	 */
	public class ClientPage{
		public static final String BTN_NEW = "//span[@class='icon-32-new']";
		public static final String BTN_EDIT = "//li[@id='toolbar-edit']/a/span";
		public static final String BTN_PUBLISH = "//li[@id='toolbar-publish']/a/span";
		public static final String BTN_UNPUBLISH = "//li[@id='toolbar-unpublish']/a/span";
		public static final String BTN_ARCHIVE = "//li[@id='toolbar-archive']/a/span";
		public static final String BTN_CHECKIN = "//li[@id='toolbar-checkin']/a/span";
		public static final String BTN_TRASH = "//li[@id='toolbar-trash']/a/span";	
		public static final String BTN_EMPTYTRASH = "//li[@id='toolbar-delete']/a/span";
		public static final String BTN_HELP = "//li[@id='toolbar-help']/a/span";
		public static final String MESSAGE = "//dd[@class='message message']/ul/li";
		public static final String DROP_STATUS = "//select[@name='filter_state']";
		public static final String TABLE_TR = "//table[@class='adminlist']/tbody/tr";
		public static final String TXT_SEARCH = "//input[@id='filter_search']";
		public static final String BTN_SEARCH = "//button[contains(text(),'Search')]";
		public static final String CHECKBOX_1 = "//input[@id='cb0']";
	}
	
	/*
	 * Element in new client page
	 * 
	 * Author: Tan Vo
	 */
	public class NewClientPage{
		public static final String TXT_NAME = "//input[@id='jform_name']";	
		public static final String TXT_CONTACT = "//input[@id='jform_contact']";	
		public static final String TXT_EMAIL = "//input[@id='jform_email']";
		public static final String BTN_SAVE = "//li[@id='toolbar-apply']/a/span";
		public static final String BTN_SAVEANDCLOSE = "//li[@id='toolbar-save']/a/span";
		public static final String BTN_SAVEANDNEW = "//li[@id='toolbar-save-new']/a/span";
		public static final String BTN_CANCEL = "//li[@id='toolbar-cancel']/a/span";
		public static final String DROP_STATUS = "//select[@id='jform_state']";
	}
	
	/*
	 * Element in Category page
	 * 
	 * Author: Tan Vo
	 */
	public class CatetoryPage{
		public static final String BTN_NEW = "//span[@class='icon-32-new']";
		public static final String BTN_EDIT = "//li[@id='toolbar-edit']/a/span";
		public static final String BTN_PUBLISH = "//li[@id='toolbar-publish']/a/span";
		public static final String BTN_UNPUBLISH = "//li[@id='toolbar-unpublish']/a/span";
		public static final String BTN_ARCHIVE = "//li[@id='toolbar-archive']/a/span";
		public static final String BTN_CHECKIN = "//li[@id='toolbar-checkin']/a/span";
		public static final String BTN_TRASH = "//li[@id='toolbar-trash']/a/span";	
		public static final String BTN_EMPTYTRASH = "//li[@id='toolbar-delete']/a/span";
		public static final String MESSAGE = "//dd[@class='message message']/ul/li";
		public static final String DROP_STATUS = "//select[@name='filter_published']";
		public static final String TXT_SEARCH = "//input[@id='filter_search']";
		public static final String BTN_SEARCH = "//button[contains(text(),'Search')]";
		public static final String CHECKBOX_1 = "//input[@id='cb0']";
		public static final String TABLE_TR = "//table[@class='adminlist']/tbody/tr";
	}
	
	/*
	 * Element in New Category page
	 * 
	 * Author: Tan Vo
	 */
	public class NewCatetoryPage{
		public static final String TXT_TITLE = "//input[@id='jform_title']";
		public static final String BTN_SAVEANDCLOSE = "//li[@id='toolbar-save']/a/span";
		public static final String PAGE_TITLE = "//div[contains(@class, 'pagetitle')]/h2";
		public static final String BTN_SAVE = "//li[@id='toolbar-apply']/a/span";
		public static final String BTN_CLOSE = "//li[@id='toolbar-cancel']/a/span";
		public static final String MESSAGE = "//dd[@class='message message']/ul/li";
	}
	/*
	 * Element in Banner page
	 * 
	 * Author: Tan Vo
	 */
	public class BannerPage{
		public static final String BTN_NEW = "//span[@class='icon-32-new']";
		public static final String BTN_TRASH = "//li[@id='toolbar-trash']/a/span";
		public static final String BTN_EMPTYTRASH = "//li[@id='toolbar-delete']/a/span";
		public static final String BTN_UNPUBLISH = "//li[@id='toolbar-unpublish']/a/span";
		public static final String BTN_ARCHIEVE = "//li[@id='toolbar-archive']/a/span";
		public static final String BTN_CHECKIN = "//li[@id='toolbar-checkin']/a/span";
		public static final String MESSAGE = "//dd[@class='message message']/ul/li";
		public static final String DROP_STATUS = "//select[@name='filter_state']";
		public static final String DROP_DISPLAY = "//select[@id='limit']";
		public static final String TABLE_TR = "//table[@class='adminlist']/tbody/tr";
		public static final String TXT_SEARCH = "//input[@id='filter_search']";
		public static final String BTN_SEARCH = "//button[contains(text(),'Search')]";
		public static final String CHECKBOX_1 = "//input[@id='cb0']";
		public static final String BTN_HELP = "//li[@id='toolbar-help']/a/span";
		public static final String FILTER_CLIENT = "//select[@name='filter_client_id']";
		public static final String FILTER_CATEGORY = "//select[@name='filter_category_id']";
		public static final String FILTER_ID = "//form[@id='adminForm']/table/thead/tr/th/a[contains(text(),'ID')]";
		public static final String LINK_CLIENT = "//ul[@id='submenu']/li/a[contains(text(),'Clients')]";
	}
	
	/*
	 * Element in New Banner page
	 * 
	 * Author: Tan Vo
	 */
	public class NewBannerPage{
		public static final String BTN_SAVE = "//li[@id='toolbar-apply']/a/span";
		public static final String BTN_SAVEANDCLOSE = "//li[@id='toolbar-save']/a/span";
		public static final String DROP_CATEGORY = "//select[@id='jform_catid']";
		public static final String DROP_CLIENT = "//select[@id='jform_cid']";
		public static final String DROP_STATUS = "//select[@id='jform_state']";
		public static final String TXT_NAME = "//input[@id='jform_name']";
		public static final String MESSAGE = "//dd[@class='message message']/ul/li";
		public static final String TEXT_HEADER = "//div[@id='toolbar-box']/div/div/h2[contains(text(),'Banner Manager: Edit Banner')]";
		public static final String BTN_CLOSE = "//li[@id='toolbar-cancel']/a/span";
	}
	
	public class WebLinksPage{
		public static final String BTN_NEW = "//span[@class='icon-32-new']";
		public static final String BTN_EDIT = "//li[@id='toolbar-edit']/a/span";
		public static final String BTN_PUBLISH = "//li[@id='toolbar-publish']/a/span";
		public static final String BTN_UNPUBLISH = "//li[@id='toolbar-unpublish']/a/span";
		public static final String BTN_ARCHIVE = "//li[@id='toolbar-archive']/a/span";
		public static final String BTN_TRASH = "//li[@id='toolbar-trash']/a/span";	
		public static final String TABLE_TR = "//table[@class='adminlist']/tbody/tr";
		public static final String CONTROL_MESSAGE = "//dd[@class='message message']/ul/li";
		public static final String DROP_STATUS = "//select[@name='filter_published']";
		public static final String BTN_EMPTYTRASH = "//li[@id='toolbar-delete']/a/span";
		public static final String TXT_SEARCH = "//input[@id='filter_search']";
		public static final String BTN_SEARCH = "//button[contains(text(),'Search')]";
		public static final String BTN_HELP = "//li[@id='toolbar-help']/a/span";	
		public static final String BTN_CLEAR = "//button[contains(text(),'Clear')]";
		public static final String DROP_DISPLAY = "//select[@id='limit']";
		public static final String DROP_CATEGORY = "//select[@name='filter_category_id']";
		public static final String LNK_SORTID = "//a[contains(text(), 'ID')]"; 
		public static final String BAR_PAGING = "//div[@class = 'pagination']/div[4]";
	}
	
	public class NewWebLinksPage{
		public static final String TXT_TITLE = "//input[@id='jform_title']";
		public static final String TXT_URL = ".//input[@id='jform_url']";
		public static final String TXT_ALIAS = ".//input[@id='jform_alias']";
		public static final String DROP_CATEGORY = "//select[@id='jform_catid']";
		public static final String DROP_STATUS = "//select [@id='jform_state']";
		public static final String DROP_FRATURE = "//select[@id='jform_featured']";
		public static final String TXT_CONTENT = "//body[@id='tinymce']";
		public static final String BTN_SAVE = "//li[@id='toolbar-apply']/a/span";
		public static final String BTN_SAVEANDCLOSE = "//li[@id='toolbar-save']/a/span";
		public static final String BTN_SAVEANDNEW = "//li[@id='toolbar-save-new']/a/span";
		public static final String BTN_SAVEANDCOPY = "//li[@id='toolbar-save-copy']/a/span";
		public static final String BTN_CANCEL = "//li[@id='toolbar-cancel']/a/span";
		public static final String FRAME_CONTENT = "//iframe[@id='jform_description_ifr']";
		public static final String BTN_IMAGE = "//div[@id='editor-xtd-buttons']/descendant::a[contains(text(),'Image')]";
		public static final String BTN_INSERT = "//div[@class='fltrt']/button[contains(text(),'Insert')]";
	}
	
	/*
	 * Element in Contacts page
	 * 
	 * Author: Nga N
	 */
	public class ContactsPage{
		public static final String BTN_NEW = "//span[@class='icon-32-new']";
		public static final String BTN_EDIT = "//li[@id='toolbar-edit']/a/span";
		public static final String BTN_PUBLISH = "//li[@id='toolbar-publish']/a/span";
		public static final String BTN_UNPUBLISH = "//li[@id='toolbar-unpublish']/a/span";
		public static final String BTN_ARCHIVE = "//li[@id='toolbar-archive']/a/span";
		public static final String BTN_CHECKIN = "//li[@id='toolbar-checkin']/a/span";
		public static final String BTN_TRASH = "//span[@class='icon-32-trash']";
		public static final String BTN_EMPTYTRASH = "//li[@id='toolbar-delete']/a/span";
		public static final String BTN_HELP = "//li[@id='toolbar-help']/a/span";
		public static final String CONTROL_MESSAGE = "//dd[@class='message message']/ul/li";
		public static final String DROP_STATUS = "//select[@name='filter_published']";
		public static final String DROP_CATEGORY = "//select[@name='filter_category_id']";
		public static final String DROP_DISPLAY = "//select[@id='limit']";
		public static final String TABLE_TR = "//table[@class='adminlist']/tbody/tr";
		public static final String TXT_SEARCH = "//input[@id='filter_search']";
		public static final String BTN_SEARCH = "//button[contains(text(),'Search')]";
		public static final String BTN_CLEAR = "//button[contains(text(),'Clear')]";
		public static final String CHECKBOX_1 = "//input[@id='cb0']";
		public static final String LNK_SORTID = "//a[contains(text(), 'Title')]/img"; 
		public static final String BAR_PAGING = "//div[@class = 'pagination']/div[4]";

				
	}

	/*
	 * Element in NewContacts page
	 * 
	 * Author: Nga N
	 */

	public class NewContactspage {
		public static final String TXT_NAME = "//input[@id='jform_name']";
		public static final String TXT_ALIAS = "//input[@id='jform_alias']";
		public static final String BTN_SELECTUSER = "//a[contains(text(), 'Select User')]";
		public static final String DROP_CATEGORY = "//select[@id='jform_catid']";
		public static final String DROP_STATUS = "//select [@id='jform_published']";
		public static final String DROP_ACCESS = "//select[@id='jform_access']";
		public static final String DROP_FEATURED = "//select[@id='jform_featured']";
		public static final String DROP_LANGUAGE = "//select[@id='jform_language']";
		public static final String BTN_SAVE = "//li[@id='toolbar-apply']/a/span";
		public static final String BTN_SAVEANDCLOSE = "//li[@id='toolbar-save']/a/span";
		public static final String BTN_SAVEANDNEW = "//li[@id='toolbar-save-new']/a/span";
		public static final String BTN_CANCEL = "//li[@id='toolbar-cancel']/a/span";
		public static final String BTN_IMAGE = "//div[@id='editor-xtd-buttons']/descendant::a[contains(text(),'Image')]";
		//public static final String FRAME_IMAGE = "//iframe[@id='imageframe']";
		public static final String BTN_CLOSE = "//li[@id='toolbar-cancel']/a/span";
		public static final String BTN_BROWSE = "//input[@id='upload-file']";
		public static final String BTN_UPLOAD ="//input[@id='upload-submit']";
		public static final String BTN_CLOSEPOPUP ="//a[@id='sbox-btn-close']";
		
	}
}
