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

public class CreateData extends Abstract_test{

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
		
		System.out.println(category.getTitle());
		
		log.info("Create new banner");
		for(int i=1;i<=101;i++){
			bannerPage = categoriesPage.navigatetoBannerpage();
			bannerPage.addNewBanner("Banner 0"+i, category.getTitle(), client.getName(), "","");
		}
	}
	
	@AfterClass
	public void end(){	
		shutdown();
	}
}
