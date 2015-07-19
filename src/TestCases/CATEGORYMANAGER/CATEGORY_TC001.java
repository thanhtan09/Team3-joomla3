package TestCases.CATEGORYMANAGER;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Categories_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.NewCategory_page;
import Pages.WebLinks_page;

public class CATEGORY_TC001 extends Abstract_test{
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page categoriesPage;
	private NewCategory_page newcatePage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can create new category with valid information")
	public void TC_CATEGORY_001 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Category Manager page");
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		
		log.info("Create new Category");
		categoriesPage = categoriesPage.addNewCategory(category.getTitle());
		
		log.info("Verify message successful displays and category displays in table");
		verifyTrue(categoriesPage.isMessageDisplay(), "Category is saved successful");
		verifyTrue(categoriesPage.isCategoryDisplay(category.getTitle()), "Category is displayed in table");
	}
	
	@Test(description = "Verify that user can edit a category")
	public void TC_CATEGORY_002 (){
		log.info("Edit a category");
		categoriesPage.editCategort(category.getTitle(), category2.getTitle(), "Save");
		
		log.info("A message Category successfully saved shows and Edit an Aritcal Category page displays");
		verifyTrue(newcatePage.isMessageDisplay());
		verifyTrue(newcatePage.isEditPage());
		
		log.info("Click Save and Closed");
		newcatePage.clickSaveandClose();
		
		log.info("A message Category successfully saved shows and new category displays in table");
		verifyTrue(categoriesPage.isMessageDisplay());
		verifyTrue(categoriesPage.isCategoryDisplay(category2.getTitle()));
		
	}
	@AfterClass
	public void end(){
		categoriesPage.deleteCategory(category2.getTitle());
		shutdown();
	}
}
