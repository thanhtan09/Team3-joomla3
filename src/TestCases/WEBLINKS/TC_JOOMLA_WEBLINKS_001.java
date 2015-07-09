package TestCases.WEBLINKS;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Article_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces.WebLinksPage;
import Pages.Login_page;
import Pages.WebLinks_page;

public class TC_JOOMLA_WEBLINKS_001 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private WebLinks_page weblinkPage;
	
	@BeforeMethod
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can create new article with valid information")
	public void TC_ARTICLE_001 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Article page");
		weblinkPage = homePage.navigatetoWeblinkpage();		
		
		log.info("Create new article");
		weblinkPage.addNewWebLink("abc", "http://abc.com", "", "", "test", "", "");
		
		log.info("Verify message Article successfully saved displayed");
		verifyTrue(weblinkPage.isWebLinkDisplay("abc"));
	}
	
	@AfterMethod
	public void end(){
		shutdown();
	}
}

