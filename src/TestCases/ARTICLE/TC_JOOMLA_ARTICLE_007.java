package TestCases.ARTICLE;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Article_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.NewArticle_page;

public class TC_JOOMLA_ARTICLE_007 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Article_page articlePage;
	private NewArticle_page newArticlePage;

	
	@BeforeMethod
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can access article's help section")
	public void TC_ARTICLE_008 (){
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(), "");
		articlePage = homePage.navigatetoArticlepage();
		
		articlePage.addNewArticle(article.getTitle(), article.getCategory(), "", article.getContent(),"","Save");
		log.info("Verify message Article successfully saved displayed");		
		newArticlePage = Factory_page.getNewArticlePage(driver);
		verifyTrue(newArticlePage.isMessageArticleDisplay());
		
		articlePage.TrashArticle(article.getTitle());
		log.info("The 1 article successfully trashed message is displayed");
		verifyTrue(articlePage.isTrashArticleMessage());
		log.info("Verify the deleted article is displayed on the table grid");
		verifyTrue(articlePage.isTrashedArticleinG(article.getTitle()));
	}
	
	@AfterMethod
	public void end(){
		articlePage.deleteArticle(article.getTitle());
		shutdown();
	}
}