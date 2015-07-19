package TestCases.CATEGORYMANAGER;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Categories_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.WebLinks_page;

public class CATEGORY_TC001 extends Abstract_test{
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page categoriesPage;
	
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
	
	@AfterClass
	public void end(){
		shutdown();
	}
}
