package TestCases.ARTICLE;

import org.testng.annotations.*;

import Functions.Abstract_test;
import Pages.Article_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class ARTICLE_TC001_to_TC005_TC007 extends Abstract_test{

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
		
		log.info("Verify the article is saved successfully");
		verifyTrue(articlePage.isMessageArticleDisplay(),"Article successfully saved");
		verifyTrue(articlePage.isArticleDisplay(article.getTitle()),"Created article is displayed on the articles table");
	}
	
	@Test(description = "Verify user can edit an article", dependsOnMethods = "TC_ARTICLE_001")
	public void TC_ARTICLE_002 (){
		
		log.info("Edit an article");
		articlePage.editArticle(article.getTitle(),article2.getTitle(), article2.getCategory(), "", article2.getContent());
		
		log.info("Verify the article is saved successfully");
		verifyTrue(articlePage.isMessageArticleDisplay(),"Article successfully saved");
		verifyTrue(articlePage.isArticleDisplay(article2.getTitle()),"Edited article is displayed on the articles table");
	}
	
	@Test(description = "Verify user can unpublish a published article", dependsOnMethods = "TC_ARTICLE_001")
	public void TC_ARTICLE_004 (){
		
		log.info("Publish an article");
		articlePage.unpublishArticle(article2.getTitle());
		
		log.info("Verify the article is published successfully");
		verifyTrue(articlePage.isMessageUnPublishDisplay(), "1 article unpublished");
		verifyTrue(articlePage.isShowUnpublishIcon(article2.getTitle()),"The icon of the selected item is showed as 'Unpublish'. ");
		
	}
	
	@Test(description = "Verify user can publish an unpublished article", dependsOnMethods = "TC_ARTICLE_004")
	public void TC_ARTICLE_003 (){
		
		log.info("Publish an article");
		articlePage.publishArticle(article2.getTitle());
		
		log.info("Verify the article is published successfully");
		verifyTrue(articlePage.isMessagePublishDisplay(), "1 article published");
		verifyTrue(articlePage.isShowPublishIcon(article2.getTitle()),"The icon of the selected item is showed as 'Publish'. ");
	}
	
	@Test(description = "Verify user can move an article to the archive", dependsOnMethods = "TC_ARTICLE_001")
	public void TC_ARTICLE_005 (){
		
		log.info("Archive an article");
		articlePage.archiveArticle(article2.getTitle());
		
		log.info("Verify the confirm message is displayed");
		verifyTrue(articlePage.isArchiveMessage(), "1 article archived");
		
		log.info("Verify the archived article is displayed on the table grid");
		verifyTrue(articlePage.isArchiveList(article2.getTitle()),"The archived article is displayed on the table grid");
	}
	
	@Test(description = "Verify user can move an article to trash section", dependsOnMethods = "TC_ARTICLE_001")
	public void TC_ARTICLE_007 (){
		
		log.info("Trash an article");
		articlePage.TrashArticle(article2.getTitle());
		
		log.info("The 1 article successfully trashed message is displayed");
		verifyTrue(articlePage.isTrashArticleMessage(),"1 article successfully trashed");
		
		log.info("Verify the deleted article is displayed on the table grid");
		verifyTrue(articlePage.isTrashedArticleDisplay(article2.getTitle()),"The deleted article is displayed on the table grid");
	}
	
	@AfterClass
	public void end(){
		articlePage.deleteArticle(article2.getTitle());
		shutdown();
	}
}
