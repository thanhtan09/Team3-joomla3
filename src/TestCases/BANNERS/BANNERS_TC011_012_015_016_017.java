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

public class BANNERS_TC011_012_015_016_017 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Client_page clientPage;
	private Categories_page categoriesPage;
	private Banner_page bannerPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify that user can create many banners by using Save & New button")
	public void TC_BANNERS_011 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Create new client");
		clientPage = homePage.navigatetoCLientpage();
		clientPage.addNewClient(client.getName(), client.getContact(), client.getEmail(), "","");
		
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
		bannerPage.addNewBanner(banner.getName(), category.getTitle(), client.getName(), "","");
		
		log.info("A message : Banner successfully saved shows and new banner is created");
		verifyTrue(bannerPage.isMessageSuccessDisplay(),"Banner successfully saved");
		verifyTrue(bannerPage.isBannerDisplay(banner.getName()),"New banner displays");
		
		log.info("Create new banner");
		bannerPage = categoriesPage.navigatetoBannerpage();
		bannerPage.addNewBanner(banner4.getName(), category.getTitle(), client.getName(), "","");
		
		log.info("A message : Banner successfully saved shows and new banner is created");
		verifyTrue(bannerPage.isMessageSuccessDisplay(),"Banner successfully saved");
		verifyTrue(bannerPage.isBannerDisplay(banner.getName()),"New banner displays");
	}
	
	@Test(description = "Verify that user can browse New Banner help page in New banner page", dependsOnMethods = "TC_BANNERS_011")
	public void TC_BANNERS_012 (){
		
		log.info("Open new banner help page");
		bannerPage.openNewBannerHelp();
		
		log.info("New banner help page appears");
		verifyTrue(bannerPage.isHelpPage(), "New banner help page appears");
	}
	
	@Test(description = "Verify that user can change the quantity of items displayed in banner table")
	public void TC_BANNERS_015 (){
		
		log.info("Quantity of items displayed in table is changed");
		verifyTrue(bannerPage.isBannerDisplayedInTable("20"),"Quantity of items displayed in table is changed");
	}
	
	@Test(description = "Verify that user can sort items displayed in banner table by ID")
	public void TC_BANNERS_016 (){
		
		log.info("Click ID link in the top of table");
		bannerPage.clickID();
		
		log.info("Items are sorted ascending by ID in banner table");
		verifyTrue(bannerPage.isBannerAscendingByID(), "Items are sorted ascending by ID in banner table");
		
		log.info("Click ID link in the top of table");
		bannerPage.clickID();
		
		log.info("Items are sorted descending by ID in banner table");
		verifyTrue(bannerPage.isBannerDescendingByID(), "Items are sorted descending by ID in banner table");
	}
	
	@Test(description = "Verify that user can access Banner clients page while browsing Banner page")
	public void TC_BANNERS_017 (){
		
		log.info("Click Clients link in the left top ");
		bannerPage.clickClientLink();
		
		log.info("Client page displays");
		verifyTrue(bannerPage.isClientPage(), "Client page displays");
	}
	
	@AfterClass
	public void end(){	
		log.info("Delete banner");
		bannerPage.deleteBanner(banner.getName());
		bannerPage.deleteBanner(banner4.getName());
		
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
