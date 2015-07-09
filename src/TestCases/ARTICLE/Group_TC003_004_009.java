package TestCases.ARTICLE;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.*;

public class Group_TC003_004_009 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Article_page articlePage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test
	public void TC_ARTICLE_004 (){
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(), "");
		articlePage = homePage.navigatetoArticlepage();
		
		articlePage.addNewArticle(article.getTitle(), article.getCategory(), "", article.getContent(),"","");
		log.info("Verify message Article successfully saved displayed");
		verifyTrue(articlePage.isArticleDisplay(article.getTitle()));
		
		log.info("UNPublish an article");
		articlePage.unpublishArticle(article.getTitle());
		
		log.info("Verify the article is unpublished successfully");
		verifyTrue(articlePage.isUnPublish(article.getTitle()));
	}
	
	@Test(description = "Verify user can publish an unpublished article", dependsOnMethods = "TC_ARTICLE_004")
	public void TC_ARTICLE_003 (){
		
		log.info("Publish an article");
		articlePage.publishArticle(article.getTitle());
		
		log.info("Verify the article is published successfully");
		verifyTrue(articlePage.isPublish(article.getTitle()));
	}
	
	@Test(description = "Verify user can search for articles using the filter text field", dependsOnMethods = "TC_ARTICLE_003")
	public void TC_ARTICLE_009 (){
			
		log.info("Search for an article");
		articlePage.searchforArticle(article.getTitle());
		
		log.info("Verify the title of search article is partially matched with the entered keyword");
		articlePage.isSearchArticleDisplay(article.getTitle());
	}
	
	@AfterClass
	public void end(){
		articlePage.deleteArticle(article.getTitle());
		shutdown();
	}
}