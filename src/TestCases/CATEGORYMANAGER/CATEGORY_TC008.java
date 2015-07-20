package TestCases.CATEGORYMANAGER;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Categories_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.WebLinks_page;

public class CATEGORY_TC008 extends Abstract_test {
	
	private Login_page loginPage;
	private Home_page homePage;
	private Categories_page catePage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can access category's help section")
	public void TC_WEBLINK_008 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		catePage = homePage.navigatetoCategoryManagerpage();
		
		log.info("Verify user can access weblink's help section");
		verifyTrue(catePage.isHelpWindow());
		
	}

	@AfterClass
	public void end(){
		shutdown();
	}
}