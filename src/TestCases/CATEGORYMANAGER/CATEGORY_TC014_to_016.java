package TestCases.CATEGORYMANAGER;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Article_page;
import Pages.Categories_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class CATEGORY_TC014_to_016 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page categoriesPage;
	private Article_page articlePage;
	private String articleName = "NGUYENVUHONGNGA";
	private String articleContent = "NGA NGO";
	private String articleCategory = "- Sample Data-Articles";
	@BeforeClass
	public void setup(){
		driver = openJoomla();

	}
	
	@Test(description = "Verify that user can move may articles to another category")
	public void TC_CATEGORY_014 (){	
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Pre-condition: create data for tc014 - 015" );
		
		log.info("Enter Category Manager page");
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		log.info("Create new Category");
		categoriesPage = categoriesPage.addNewCategory(category.getTitle(), "", "", "", "");
		categoriesPage = categoriesPage.addNewCategory(category2.getTitle(), "", "", "", "");
	
		articlePage = categoriesPage.navigatetoArticlePage();
		
		log.info("Create 2 articles");
		articlePage.addNewArticle(articleName + "1", articleCategory, "", articleContent, "", "");
		articlePage.addNewArticle(articleName + "2", articleCategory, "", articleContent, "", "");
		
		log.info ("move many articles to a category");	
		articlePage.actionArticlestoCate(articleName, category.getTitle(), "Move");
	
		log.info("Selected articles are moved to destination category ");	
		verifyTrue(articlePage.isArticlemovedtoCate(articleName, category.getTitle()));

	}
	
	@Test(description = "Verify that user can copy may articles to another category", dependsOnMethods = "TC_CATEGORY_014")
	public void TC_CATEGORY_015 (){
		
		log.info ("copy many articles to a category");	
		articlePage.actionArticlestoCate(articleName, category2.getTitle(), "Copy");
	
		log.info("Selected articles are moved to destination category ");	
		verifyTrue(articlePage.isArticlemovedtoCate(articleName, category2.getTitle()));
		
	}
	
	@Test(description = "Verify that user can copy may articles to another category", dependsOnMethods = "TC_CATEGORY_014")
	public void TC_CATEGORY_016 (){
		
		log.info ("Select Access level in Access level dropdown list in the left bottom");	
		articlePage.accessLevel(articleName, "Special");
	
		log.info("Selected articles are moved to destination category ");	
		verifyTrue(articlePage.isAccessLeveltoArticles(articleName, "Special"));
		
	}
	
	@AfterClass
	public void end (){
		
		articlePage.deleteAllArticles(articleName);
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		categoriesPage.deleteCategory(category.getTitle());
		categoriesPage.deleteCategory(category2.getTitle());
		shutdown();
	}
}
