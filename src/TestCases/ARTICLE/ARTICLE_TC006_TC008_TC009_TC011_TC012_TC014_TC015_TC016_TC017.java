package TestCases.ARTICLE;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Article_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces;
import Pages.Login_page;
import Pages.NewArticle_page;

public class ARTICLE_TC006_TC008_TC009_TC011_TC012_TC014_TC015_TC016_TC017 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Article_page articlePage;
	private NewArticle_page newarticlePage;
	private String article1;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
		article1 = article.getTitle();
	}
	
	@Test(description = "Verify user can check in an article")
	public void TC_ARTICLE_006 (){
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(), "");
		articlePage = homePage.navigatetoArticlepage();
		
		log.info("Add new article with save button");
		articlePage.addNewArticle(article.getTitle(), article.getCategory(), "", article.getContent(),"","Save");
		
		log.info("Verify message Article successfully saved displayed");		
		newarticlePage = Factory_page.getNewArticlePage(driver);
		verifyTrue(newarticlePage.isMessageArticleDisplay(),"Article successfully saved");
		verifyTrue(newarticlePage.isEditArticlePage(),"Edit article display");
		
		shutdown();
		driver = openJoomla();
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(), "");
		articlePage = homePage.navigatetoArticlepage();
		
		log.info("Check in an article");
		articlePage.checkinArticle(article1);
	
		log.info("Verify the '1 article successfully checked in' message is displayed");
		verifyTrue(articlePage.isCheckinMessage(),"1 article successfully checked in");
		
		log.info("Verify the lock icon next to the article is removed");
		verifyTrue(articlePage.isCheckinArticle(article1),"The lock icon next to the article is removed");
	}
	
	@Test(description = "Verify user can access article's help section")
	public void TC_ARTICLE_008 (){
		
		log.info("Verify the article's help window is displayed");
		verifyTrue(articlePage.isHelpWindow(),"The article's help window is displayed");
	}
	
	@Test(description = "User can search for articles using the filter text field", dependsOnMethods = "TC_ARTICLE_006")
	public void TC_ARTICLE_009 (){
		
		log.info("Search for an article");
		articlePage.searchforArticle(article1);
		
		log.info("Verify the title of search article is partially matched with the entered keyword");
		verifyTrue(articlePage.isSearchArticleDisplay(article1),"The titles of displayed articles are partially matched with the entered keyword");
	}
	
	@Test(description = "Verify user can sort the article table by ID column")
	public void TC_ARTICLE_011 (){
		
		log.info("Click ID header to sort ASC");
		articlePage.clickSortID();
		
		log.info("Verify ID is sorted ASC");
		verifyTrue(articlePage.isArticleASCByID());
		
		log.info("Click ID header to sort DES");
		articlePage.clickSortID();
		
		log.info("Verify ID is sorted DES");
		verifyTrue(articlePage.isArticleDESByID());
		
	}
	
	@Test(description = "User can paging the article using the paging control", dependsOnMethods = "TC_ARTICLE_006")
	public void TC_ARTICLE_012 (){
		
		articlePage.selectDisplayItem("5");
		verifyTrue(articlePage.isPaging(5),"The article table is paging into 5 articles per page");
		articlePage.selectDisplayItem("All");
		verifyFalse(articlePage.isControlExist(driver, By.xpath(Interfaces.ArticlePage.BAR_PAGING)),"All articles are displayed in one page");
	}
	
	@Test(description = "Verify user can change the order of articles using the Ordering column", dependsOnMethods = "TC_ARTICLE_006")
	public void TC_ARTICLE_014 (){		
		
		log.info("Add new article");
		articlePage.addNewArticle(article4.getTitle(), article4.getCategory(), article4.getStatus(), article4.getContent(), "", "");
	
		log.info("Verify the article is saved successfully");
		verifyTrue(articlePage.isMessageArticleDisplay(),"Article successfully saved");
		verifyTrue(articlePage.isArticleDisplay(article4.getTitle()),"Created article is displayed on the articles table");
		
		log.info("Verify the first article changes its position with the second article");
		verifyTrue(articlePage.isArticleChangePosition(article4.getTitle()));
	}
	
	@Test(description = "Verify user can change the status of articles using the Status column", dependsOnMethods = "TC_ARTICLE_006")
	public void TC_ARTICLE_015 (){
		
		log.info("Click on the status icon of the selected article in the Status column");
		articlePage.clickStatusIcon(article4.getTitle());
		
		log.info("Verify the article is published successfully");
		verifyTrue(articlePage.isMessageUnPublishDisplay(), "1 article unpublished");
		verifyTrue(articlePage.isShowUnpublishIcon(article4.getTitle()),"The icon of the selected item is showed as 'Unpublish'. ");
	
		log.info("Click on the status icon of the selected article in the Status column");
		articlePage.clickStatusIcon(article4.getTitle());
		
		log.info("Verify the article is published successfully");
		verifyTrue(articlePage.isMessagePublishDisplay(), "1 article published");
		verifyTrue(articlePage.isShowPublishIcon(article4.getTitle()),"The icon of the selected item is showed as 'Publish'. ");
	}
	
	@Test(description = "Verify user can change the feature property of articles using the Featured column", dependsOnMethods = "TC_ARTICLE_006")
	public void TC_ARTICLE_016 (){
		
		log.info("Verify the article is featured successfully");
		articlePage.clickFeaturedIcon(article4.getTitle());
		verifyTrue(articlePage.isFeaturedArticle(article4.getTitle()),"The icon of the selected item is showed as 'Featured'. ");		
		
		log.info("Verify the article is unfeatured successfully");
		articlePage.clickFeaturedIcon(article4.getTitle());
		verifyTrue(articlePage.isUnFeaturedArticle(article4.getTitle()),"The icon of the selected item is showed as 'Unfeatured'. ");
	}
	
	@Test(description = "Verify user can change the feature property of articles using the Featured column")
	public void TC_ARTICLE_017 (){
		
		articlePage.addNewArticle(article5.getTitle(), article5.getCategory(), article5.getStatus(), article5.getContent(), "", "");
		
		log.info("Verify the Access Level of the article is displayed as 'Public'");
		verifyTrue(articlePage.isPublicAccessArticle(article5.getTitle()));
	}
	
	@AfterClass
	public void end(){
		articlePage.deleteArticle(article1);
		articlePage.deleteArticle(article4.getTitle());
		articlePage.deleteArticle(article5.getTitle());
		shutdown();
	}
}
