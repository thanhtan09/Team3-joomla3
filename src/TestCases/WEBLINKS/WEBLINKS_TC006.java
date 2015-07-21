package TestCases.WEBLINKS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.WebLinks_page;

public class WEBLINKS_TC006 extends Abstract_test{
	
	private Login_page loginPage;
	private Home_page homePage;
	private WebLinks_page weblinkPage;
	private String weblink2;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
		weblink2 = weblink1.getName();
	}
	
	@Test(description = "Verify user can check in a weblink with valid information")
	public void TC_WEBLINK_006 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		weblinkPage = homePage.navigatetoWeblinkpage();		
		
		log.info("Create new weblink");
		weblinkPage.addNewWebLink(weblink1.getName(), weblink1.getUrl(), weblink1.getContent(), weblink1.getStatus(), "" , "Save");
		
		log.info("Verify message Weblink successfully saved displayed");
		verifyTrue(weblinkPage.isMessageWeblinkDisplay(),"Weblink is created successfully");
		
		shutdown();
		driver = openJoomla();
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		weblinkPage = homePage.navigatetoWeblinkpage();	
		
		log.info("Check in weblink");
		weblinkPage.checkinWeblink(weblink2);
		
		log.info("Weblink is checked in successfully");
		verifyTrue(weblinkPage.isCheckinMessage());
		verifyTrue(weblinkPage.isCheckinWeblink(weblink2));
		
	}
	
	@AfterClass
	public void end(){
		weblinkPage.deleteWeblink(weblink2);
		shutdown();
	}
}
