package TestCases.CATEGORYMANAGER;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Article_page;
import Pages.Categories_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces;
import Pages.Login_page;
import Pages.NewCategory_page;

public class CATEGORY_TC010_to_013 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page categoriesPage;
	private NewCategory_page newcatePage;
		
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify that user can create many categories by using 'Save & New' button")
	public void TC_CATEGORY_010 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Category Manager page");
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		
		log.info("Create new Category");
		categoriesPage = categoriesPage.addNewCategory(category.getTitle(), "", "", "", "SaveAndNew");
		newcatePage = Factory_page.getNewCategoryPage(driver);
		
		log.info("Verify message successful displays");
		verifyTrue(categoriesPage.isMessageDisplay(), "Category is saved successful");	
		
		log.info("Create new Category");
		newcatePage.addNew(category2.getTitle(), "", "", "", "");
		
		log.info("Verify message successful displays and category displays in table");
		verifyTrue(categoriesPage.isMessageDisplay(), "Category is saved successful");	
		verifyTrue(categoriesPage.isMultiCategoryDisplay(category.getTitle(), category2.getTitle()), "Two new categories are created and matched with entered data");	
		
	}
	
	@Test(description = "Verify that user can browse 'New Category help' page")
	public void TC_CATEGORY_011 (){
		
		log.info("Access to New Category Help page");
		categoriesPage.click(driver, By.xpath(Interfaces.CatetoryPage.BTN_NEW));
		newcatePage = Factory_page.getNewCategoryPage(driver);
		
		log.info("Verify user can access New Categor help section");
		verifyTrue(newcatePage.isHelpWindow(), "New Category help page appears");
		
	}
	
	@Test(description = "Verify that user can cancel adding action while adding a new create")
	public void TC_CATEGORY_012 (){
		
		log.info("Cancel adding action while adding a new create");
		newcatePage.addNew(category3.getTitle(), "", "", "", "Cancel");
		
		verifyTrue(categoriesPage.isCateManagerPage(), "Category Manager page shows");
		verifyTrue(categoriesPage.isCategoryNotInGrid(category3.getTitle()), "New category is not added to data");
		
	}
	
	@Test(description = "Verify that user can creat a new category by using 'Save as Copy' button")
	public void TC_CATEGORY_013 (){
		
		log.info("Create a cate with Save button");
		categoriesPage = categoriesPage.addNewCategory(category3.getTitle(), "", "", "", "Save");
		newcatePage = Factory_page.getNewCategoryPage(driver);
		
		log.info("A message Category successfully saved shows and Edit an Aritcal Category page displays");
		verifyTrue(newcatePage.isMessageSuccessDisplay(), "Message successfully save shows");
		verifyTrue(newcatePage.isEditPage(), "Edit an Aritcal Category page displays");
		
		newcatePage.addNew(category4.getTitle(), "", "", "", "SaveAsCopy");
		log.info("A message Category successfully saved shows and a new category is created without replacing the old category");
		verifyTrue(newcatePage.isMessageSuccessDisplay(), "Message successfully save shows");
		verifyTrue(categoriesPage.isMultiCategoryDisplay(category3.getTitle(), category4.getTitle() + " (2)"), "a new category is created without replacing the old category");
	}
	
	
	@AfterClass
	public void end (){
		
		categoriesPage.deleteCategory(category.getTitle());
		categoriesPage.deleteCategory(category2.getTitle());
		categoriesPage.deleteCategory(category3.getTitle());
		categoriesPage.deleteCategory(category4.getTitle());
		shutdown();
	}
}
