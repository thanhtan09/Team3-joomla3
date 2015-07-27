package TestCases.CATEGORYMANAGER;

import org.openqa.selenium.By;
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

public class CATEGORY_TC010_to_017 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page categoriesPage;
	private NewCategory_page newcatePage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can create new category with valid information")
	public void TC_CATEGORY_010 (){
		//INPROGRESS --- ADD SAVE AND NEW
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Category Manager page");
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		
		log.info("Create new Category");
		categoriesPage = categoriesPage.addNewCategory(category.getTitle(), category.getStatus());
		
		log.info("Verify message successful displays and category displays in table");
		verifyTrue(categoriesPage.isMessageDisplay(), "Category is saved successful");	
	}
	
	@Test(description = "Verify that user can browse 'New Category help' page")
	public void TC_CATEGORY_011 (){
		
		log.info("Access to New Client Help page");
		categoriesPage.click(driver, By.xpath(Interfaces.CatetoryPage.BTN_NEW));
		newcatePage = Factory_page.getNewCategoryPage(driver);
		
		log.info("Verify user can access weblink's help section");
		verifyTrue(newcatePage.isHelpWindow());
		
	}
}
