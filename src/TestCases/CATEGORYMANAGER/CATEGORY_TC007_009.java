package TestCases.CATEGORYMANAGER;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Categories_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;;

public class CATEGORY_TC007_009 extends Abstract_test {
	
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page catePage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can access category's help section")
	public void TC_WEBLINK_008 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		catePage = homePage.navigatetoCategoryManagerpage();
		
		log.info("Verify user can access weblink's help section");
		verifyTrue(catePage.isHelpWindow());
		
	}
	
	@Test(description = "Verify that user can search a category by using filter dropdown lists")
	public void TC_WEBLINK_009 (){
		
		log.info("Create new category");
		catePage.addNewCategory(category3.getTitle(), category3.getStatus(), category3.getAccess(), category3.getLanguage());
		
		log.info("Searh for a category by using filter dropdown lists ");
		catePage.searchByFilter(category3.getStatus(), category3.getAccess(), category3.getLanguage());
		
		log.info("Verify category displays in table");
		catePage.isCategoryDisplay(category3.getTitle());
		
	}

	@AfterClass
	public void end(){
		catePage.deleteCategory(category3.getTitle());
		shutdown();
	}
}