package TestCases.ARTICLE;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.*;

public class Group_TC001_002_005 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Article_page articlePage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can create new article with valid information")
	public void TC_ARTICLE_001 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Article page");
		articlePage = homePage.navigatetoArticlepage();		
		
		log.info("Create new article");
		articlePage.addNewArticle(article.getTitle(), article.getCategory(), "",article.getContent(),"","");
		
		log.info("Verify message Article successfully saved displayed");
		verifyTrue(articlePage.isArticleDisplay(article.getTitle()));
	}
	
	@Test(description = "Verify user can edit an article", dependsOnMethods = "TC_ARTICLE_001")
	public void TC_ARTICLE_002 (){
			
		articlePage.editArticle(article.getTitle(),article2.getTitle(), article2.getCategory(), "", article2.getContent());
		
		log.info("Verify message Article successfully saved displayed");
		verifyTrue(articlePage.isArticleDisplay(article2.getTitle()));
	}
	
	@Test(description = "Verify user can move an article to the archive", dependsOnMethods = "TC_ARTICLE_002")
	public void TC_ARTICLE_005 (){
		
		log.info("Archive an article");
		articlePage.archiveArticle(article2.getTitle());
		
		log.info("Verify the confirm message is displayed");
		verifyTrue(articlePage.isArchiveMessage());
		
		log.info("Verify the archived article is displayed on the table grid");
		verifyTrue(articlePage.isArchiveList(article2.getTitle()));
	}
	
	@AfterClass
	public void end(){
		articlePage.deleteArticle(article2.getTitle());
		shutdown();
	}
}
