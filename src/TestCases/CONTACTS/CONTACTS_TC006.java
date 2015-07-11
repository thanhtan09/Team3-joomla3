package TestCases.CONTACTS;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class CONTACTS_TC006 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
	
	@BeforeMethod
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can access article's help section")
	public void TC_ARTICLE_008 (){
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(), "");
		contactsPage = homePage.navigatetoContactspage();
			
		log.info("Verify the article's help window is displayed");
		contactsPage.verifyHelpwindow(driver);
	
	}
	
	@AfterMethod
	public void end(){
		shutdown();
	}
}