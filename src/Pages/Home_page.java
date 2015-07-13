package Pages;

import org.openqa.selenium.WebDriver;

public class Home_page extends Abstract_page{

	private WebDriver driver;
		
	public Home_page(WebDriver driver) {
		this.driver = driver;
	}
	
	/*
	 * Navigate to Article Page
	 * 
	 * Author: Tan Vo
	 */
	public Article_page navigatetoArticlepage(){
		navigateMenu(driver,"Content|Article Manager");		
		return new Article_page(driver);
	}
	
	/*
	 * Navigate to Banner Page
	 * 
	 * Author: Tan Vo
	 */
	public Banner_page navigatetoBannerpage(){
		navigateMenu(driver, "Components|Banners|Banners");
		return new Banner_page(driver);
	}
	
	/*
	 * Navigate to Article Page
	 * 
	 * Author: Tan Vo
	 */
	public Client_page navigatetoCLientpage(){
		navigateMenu(driver,"Components|Banners|Clients");
		return new Client_page(driver);
	}
	
	public WebLinks_page navigatetoWeblinkpage(){
		navigateMenu(driver,"Components|Weblinks");		
		return new WebLinks_page(driver);
	}
	
	/*
	 * Navigate to Contacts Page
	 * 
	 * Author: Nga Nguyen
	 */
	public Contacts_page navigatetoContactspage(){
		navigateMenu(driver,"Components|Contacts");		
		return new Contacts_page (driver);
	}
}
