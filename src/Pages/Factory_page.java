package Pages;

import org.openqa.selenium.WebDriver;


public class Factory_page {

	public static Login_page getLoginPage(WebDriver driver)
	{
		return new Login_page(driver);
	}
	
	public static Home_page getHomePage(WebDriver driver)
	{
		return new Home_page(driver);
	}
	
	public static Article_page getArticlePage(WebDriver driver)
	{
		return new Article_page(driver);
	}
	
	public static NewArticle_page getNewArticlePage(WebDriver driver)
	{
		return new NewArticle_page(driver);
	}
	
	public static Categories_page getCategoriesPage(WebDriver driver)
	{
		return new Categories_page(driver);
	}
	
	public static Client_page getClientPage(WebDriver driver){
		return new Client_page(driver);
	}
	
	public static NewClient_page getNewClientPage(WebDriver driver){
		return new NewClient_page(driver);
	}
	
	public static NewCategory_page getNewCategoryPage(WebDriver driver){
		return new NewCategory_page(driver);
	}
	
	public static NewBanner_page getNewBannerPage(WebDriver driver){
		return new NewBanner_page(driver);
	}
	
	public static Banner_page getBannerPage(WebDriver driver){
		return new Banner_page(driver);
	}
	
	public static WebLinks_page getWebLinkPage(WebDriver driver)
	{
		return new WebLinks_page(driver);
	}
	
	public static NewWebLinks_page getNewWebLinksPage(WebDriver driver)
	{
		return new NewWebLinks_page(driver);
	}
	
	public static Contacts_page getContactsPage(WebDriver driver){
		return new Contacts_page(driver);
		
	}
	
	public static NewContacts_page getNewContactsPage(WebDriver driver){
		return new NewContacts_page(driver);
		
	}

}
