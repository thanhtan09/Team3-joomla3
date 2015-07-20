package TestCases.BANNERS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.*;

public class BANNERS_TC001_to_TC009 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Client_page clientPage;
	private Categories_page categoriesPage;
	private Banner_page bannerPage;
	private NewBanner_page newbannerPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify that user can create new banner")
	public void TC_BANNERS_001 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Create new client");
		clientPage = homePage.navigatetoCLientpage();
		clientPage.addNewClient(client.getName(), client.getContact(), client.getEmail(), "","");
		
		log.info("A message : Client successfully saved shows and new client is created");
		verifyTrue(clientPage.isMessageDisplay());
		verifyTrue(clientPage.isClientcreated(client.getName()));
		
		log.info("Create new category");
		categoriesPage = clientPage.navigateCategoriespage();
		categoriesPage.addNewCategory(category.getTitle(),"");
		
		log.info("A message : Category successfully saved shows and new category is created");
		verifyTrue(categoriesPage.isMessageDisplay());
		verifyTrue(categoriesPage.isCategoryDisplay(category.getTitle()));
		
		log.info("Create new banner");
		bannerPage = categoriesPage.navigatetoBannerpage();
		bannerPage.addNewBanner(banner.getName(), category.getTitle(), client.getName(), "","");
		
		log.info("A message : Banner successfully saved shows and new banner is created");
		verifyTrue(bannerPage.isMessageSuccessDisplay());
		verifyTrue(bannerPage.isBannerDisplay(banner.getName()));
	}
	
	@Test(description = "Verify that user can edit a banner", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_002(){
		
		log.info("Add new banner");
		bannerPage.addNewBanner(banner2.getName(), category.getTitle(), client.getName(), "","Save");
		
		log.info("A message : Banner successfully saved shows and Edit Banner page displays");
		newbannerPage = Factory_page.getNewBannerPage(driver);
		verifyTrue(newbannerPage.isSuccessMessageDisplay());
		verifyTrue(newbannerPage.isEditBannerPage());
	}
	
	@Test(description = "Verify that user can create a new banner with unpublished status", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_003(){
		
		log.info("Add new banner with Unpublished status");
		bannerPage = Factory_page.getBannerPage(driver);
		bannerPage.addNewBanner(banner3.getName(), category.getTitle(), client.getName(),banner3.getStatus(), "");
		
		log.info("A message : Banner successfully saved shows and new banner is created");
		verifyTrue(bannerPage.isMessageSuccessDisplay());
		verifyTrue(bannerPage.isBannerDisplay(banner3.getName()));
		
	}
	
	@Test (description = "Verify that user can unpublish a banner", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_004(){
		
		log.info("Unpublish a banner");
		bannerPage.unpublishBanner(banner2.getName());
		
		log.info("A message : 1 banner successfully unpublished shows and banner is unpublished");
		verifyTrue(bannerPage.isMessaggeUnpublishDisplay());
		verifyTrue(bannerPage.isBannerUnpublish(banner2.getName()));
	}
	
	@Test (description = "Verify that user can archive a banner", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_005(){
		
		log.info("Archieve a banner");
		bannerPage.archiveBanner(banner2.getName());
		
		log.info("A message : 1 banner successfully archived shows");
		verifyTrue(bannerPage.isArchieveMessageDisplay());
		
		log.info("Banner is archived");
		verifyTrue(bannerPage.isBannerArchieved(banner2.getName()));
		
	}
	
	@Test (description = "Verify that user can send a banner to trash", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_006(){
		
		log.info("Trash a banner");
		bannerPage.trashBanner(banner2.getName());
		
		log.info("A message : 1 banner successfully sent to trash shows");
		verifyTrue(bannerPage.isTrashMessageDisplay());
		
		log.info("Banner is sent to trash");
		verifyTrue(bannerPage.isBannerSentToTrash(banner2.getName()));
	}
	
	@Test (description = "Verify that user can browse Banner help page", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_007(){
		
		log.info("Banner help page appears");
		verifyTrue(bannerPage.isHelpPage());
	}
	
	@Test (description = "Verify that user can search a banner by using filter textbox", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_008(){
		
		log.info("Search banner");
		bannerPage.searchBanner(banner2.getName());
		
		log.info("Recently created banner displays");
		bannerPage.isBannerDisplay(banner2.getName());
	}
	
	@Test (description = "Verify that user can search a banner by using filter dropdown lists", dependsOnMethods = "TC_BANNERS_001")
	public void TC_BANNERS_009(){
		
		log.info("Select Client that banner belong to in Client dropdown list");
		bannerPage.selectClient(client.getName());
		
		log.info("Select Category that banner belong to in Category dropdown list");
		bannerPage.selectCategory(category.getTitle());
		
		log.info("Recently created banner displays");
		verifyTrue(bannerPage.isBannerDisplay(banner2.getName()));
	}
	
	@AfterClass
	public void end(){
		log.info("Delete banner");
		bannerPage.deleteBanner(banner.getName());
		bannerPage.deleteBanner(banner2.getName());
		bannerPage.deleteBanner(banner3.getName());
		
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
