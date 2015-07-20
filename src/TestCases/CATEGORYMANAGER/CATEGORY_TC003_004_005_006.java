package TestCases.CATEGORYMANAGER;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Categories_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces;
import Pages.Login_page;
import Pages.NewCategory_page;

public class CATEGORY_TC003_004_005_006 extends Abstract_test{
	
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page categoriesPage;
	private String MESSAGE_UNPUBLISHED = "1 category successfully unpublished";
	private String MESSAGE_PUBLISHED = "1 category successfully published";
	private String MESSAGE_ARCHIVE = "1 category successfully archived";
	private String MESSAGE_TRASHED = "1 category successfully trashed";
	private String STATUS_ARCHIVED = "Archived";
	private String STATUS_ALL = "All";
	private String STATUS_PUBLISH = "Published";
	private String STATUS_UNPUBLISH = "Unpublished";
	private String STATUS_TRASHED = "Trashed";
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can publish an unpublish category")
	public void TC_CATEGORY_003 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Category Manager page");
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		
		log.info("Create new Category");
		categoriesPage = categoriesPage.addNewCategory(category2.getTitle(),category2.getStatus());
		
		log.info("Verify message successful displays and category displays in table");
		verifyTrue(categoriesPage.isMessageDisplay(), "Category is saved successful");
		verifyTrue(categoriesPage.isCategoryDisplay(category2.getTitle()), "Category is displayed in table");
		
		log.info("Publish a category");
		categoriesPage.clickButton(category2.getTitle(), Interfaces.CatetoryPage.BTN_PUBLISH);
		
		log.info("Message publish successfully displays");
		verifyTrue(categoriesPage.isSuccessMessageDisplay(MESSAGE_PUBLISHED));
		verifyTrue(categoriesPage.isCategoryStatus(category2.getTitle(), STATUS_PUBLISH));
	}
	
	@Test(description = "Verify user can unpublish a publish category", dependsOnMethods= "TC_CATEGORY_003")
	public void TC_CATEGORY_004 (){
		
		log.info("Unpublish a category");
		categoriesPage.clickButton(category2.getTitle(), Interfaces.CatetoryPage.BTN_UNPUBLISH);
		
		log.info("Message unpublish successfully displays");
		verifyTrue(categoriesPage.isSuccessMessageDisplay(MESSAGE_UNPUBLISHED));
		verifyTrue(categoriesPage.isCategoryStatus(category2.getTitle(), STATUS_UNPUBLISH));
	}
	
	@Test(description = "Verify user can archive a category", dependsOnMethods= "TC_CATEGORY_003")
	public void TC_CATEGORY_005 (){
		
		log.info("Unpublish a category");
		categoriesPage.clickButton(category2.getTitle(), Interfaces.CatetoryPage.BTN_ARCHIVE);
		
		log.info("Message archive successfully displays");
		verifyTrue(categoriesPage.isSuccessMessageDisplay(MESSAGE_ARCHIVE));
		verifyTrue(categoriesPage.isCategoryStatus(category2.getTitle(), STATUS_ARCHIVED));
	} 
	
	@Test(description = "Verify user can trash a category", dependsOnMethods= "TC_CATEGORY_003")
	public void TC_CATEGORY_006 (){
		
		log.info("Unpublish a category");
		categoriesPage.clickButton(category2.getTitle(), Interfaces.CatetoryPage.BTN_TRASH);
		
		log.info("Message archive successfully displays");
		verifyTrue(categoriesPage.isSuccessMessageDisplay(MESSAGE_TRASHED));
		verifyTrue(categoriesPage.isCategoryStatus(category2.getTitle(), STATUS_TRASHED));
	} 
	
	@AfterClass
	public void end(){
		categoriesPage.deleteCategory(category2.getTitle());
		shutdown();
	}
}
