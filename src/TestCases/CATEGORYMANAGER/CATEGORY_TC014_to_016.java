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

public class CATEGORY_TC014_to_016 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page categoriesPage;
	private NewCategory_page newcatePage;
	private Article_page articlePage;
	private String article1;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
		article1 = article.getTitle();
	}
	
	@Test(description = "Verify that user can move may articles to another category")
	public void TC_CATEGORY_014 (){	
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Category Manager page");
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		
		log.info("Pre-condition: create a category and many articles " );
		
		log.info("Create new Category");
		categoriesPage = categoriesPage.addNewCategory(category.getTitle(), "", "", "", "");	
	
		articlePage = categoriesPage.navigatetoArticlePage();
		
		articlePage.addMultiArticle(article.getTitle(), "", "", article.getContent(), "", "");
		
		log.info ("move many articles to a category");	
		articlePage.moveArticlestoCate(article1, category3.getTitle());
	
		log.info("Selected articles are moved to destination category ");	
		verifyTrue(articlePage.isArticlemovedtoCate(article1, category3.getTitle()));

	}
	
	@AfterClass
	public void end (){
		
		articlePage.deleteArticle(article1);
		categoriesPage = homePage.navigatetoCategoryManagerpage();	
		categoriesPage.deleteCategory(category3.getTitle());
		shutdown();
	}
}
