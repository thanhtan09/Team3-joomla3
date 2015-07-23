package TestCases.WEBLINKS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.WebLinks_page;

public class WEBLINKS_TC001_002 extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private WebLinks_page weblinkPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can create new weblink with valid information")
	public void TC_WEBLINK_001 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		weblinkPage = homePage.navigatetoWeblinkpage();		
		
		log.info("Create new weblink");
		weblinkPage.addNewWebLink(weblink1.getName(), weblink1.getUrl(), weblink1.getContent(), weblink1.getStatus(), "" , "");
		
		log.info("Verify message Weblink successfully saved displayed");
		verifyTrue(weblinkPage.isMessageWeblinkDisplay(),"Weblink is created successfully");
		verifyTrue(weblinkPage.isWebLinkDisplay(weblink1.getName()));
	}
	
	@Test(description = "Verify user can edit a weblink", dependsOnMethods = "TC_WEBLINK_001")
	public void TC_WEBLINK_002 (){
			
		weblinkPage.editWeblink(weblink1.getName(), weblink2.getName(), weblink2.getUrl(), weblink2.getContent(), "");
		
		log.info("Verify message Edited Weblink successfully saved displayed");
		verifyTrue(weblinkPage.isMessageWeblinkDisplay());
		
		log.info("Verify Edited weblink is displayed on the table");
		verifyTrue(weblinkPage.isWebLinkDisplay(weblink2.getName()));
		
	}
	
	@AfterClass
	public void end(){
		weblinkPage.deleteWeblink(weblink2.getName());
		shutdown();
	}
}

