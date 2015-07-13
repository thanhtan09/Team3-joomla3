package TestCases.BANNERS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Banner_page;
import Pages.Categories_page;
import Pages.Client_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class BANNERS extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Client_page clientPage;
	private Categories_page categoriesPage;
	private Banner_page bannerPage;
	private String banner1;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
		banner1 = banner.getName();
	}
	
	@Test(description = "Verify that user can check in a banner")
	public void TC_BANNERS_010 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Create new client");
		clientPage = homePage.navigatetoCLientpage();
		clientPage.addNewClient(client.getName(), client.getContact(), client.getEmail(), "");
		
		log.info("A message : Client successfully saved shows and new client is created");
		verifyTrue(clientPage.isMessageDisplay(),"Client is added success");
		verifyTrue(clientPage.isClientcreated(client.getName()),"New client displays");
		
		log.info("Create new category");
		categoriesPage = clientPage.navigateCategoriespage();
		categoriesPage.addNewCategory(category.getTitle());
		
		log.info("A message : Category successfully saved shows and new category is created");
		verifyTrue(categoriesPage.isMessageDisplay(),"Cagetory is add success");
		verifyTrue(categoriesPage.isCategoryDisplay(category.getTitle()),"New category displays");
		
		log.info("Create new banner");
		bannerPage = categoriesPage.navigatetoBannerpage();
		bannerPage.addNewBanner(banner.getName(), category.getTitle(), client.getName(), "","Save");
		
		log.info("A message : Banner successfully saved shows and new banner is created");
		verifyTrue(bannerPage.isMessageSuccessDisplay(), "Banner successfully saved");
		
		log.info("Close browser");
		shutdown();
		
		log.info("Open Joomla");
		driver = openJoomla();
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Navigate to Banner page");
		bannerPage = homePage.navigatetoBannerpage();
		
		log.info("Recently created banner displays and locked");
		verifyTrue(bannerPage.isBannerLocked(banner1), "Recently created banner displays and locked");
		
		log.info("Check in banner");
		bannerPage.checkinBanner(banner1);
		
		log.info("A message 1 banner successfully checked in appears and banner is changed to unlock");
		verifyTrue(bannerPage.isMessageCheckedInDisplay(), "1 banner successfully checked in");
		verifyTrue(bannerPage.isCheckedInBanner(banner1), "Banner is changed to unlock");
	}
	
	@Test(description = "Verify that user can create many banners by using Save & New button")
	public void TC_BANNERS_011 (){
		
		log.info("Add new banner");
		bannerPage.addNewBanner(banner.getName(), banner.getCategory(), banner.getClient(), banner.getStatus(), "");
		
		log.info("A message : Banner successfully saved shows and new banner is created");
		verifyTrue(bannerPage.isMessageSuccessDisplay(),"Banner successfully saved");
		verifyTrue(bannerPage.isBannerDisplay(banner.getName()),"New banner displays");
	}
	
	@AfterClass
	public void end(){	
		log.info("Delete banner");
		bannerPage.deleteBanner(banner.getName());
		bannerPage.deleteBanner(banner1);
		shutdown();
		
		log.info("Delete client");
		homePage = Factory_page.getHomePage(driver);
		clientPage = homePage.navigatetoCLientpage();
		clientPage.deleteClient(client.getName());
		
		log.info("Delete category");
		categoriesPage = clientPage.navigateCategoriespage();
		categoriesPage.deleteCategory(category.getTitle());		
		
		shutdown();
	}
}
