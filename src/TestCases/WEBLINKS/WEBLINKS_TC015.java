package TestCases.WEBLINKS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.WebLinks_page;

public class WEBLINKS_TC015 extends Abstract_test{
	
	private Login_page loginPage;
	private Home_page homePage;
	private WebLinks_page weblinkPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can change the status of weblinks using the Status column")
	public void TC_WEBLINK_015 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		weblinkPage = homePage.navigatetoWeblinkpage();		
		
		log.info("Create new weblink");
		weblinkPage.addNewWebLink(weblink4.getName(), weblink4.getUrl(), weblink4.getContent(), weblink4.getStatus(),weblink4.getCategory(), "");
		
		log.info("Verify message Weblink successfully saved displayed");
		verifyTrue(weblinkPage.isWebLinkDisplay(weblink4.getName()));
		
		log.info("Click on the status icon of the selected weblink in the Status column");
		weblinkPage.clickStatusIcon(weblink4.getName());
		
		log.info("Verify the web link is unpublished successfully");
		verifyTrue(weblinkPage.isUnPublish(weblink4.getName()));
		
		log.info("Click on the status icon of the selected weblink in the Status column");
		weblinkPage.clickStatusIcon(weblink4.getName());
		
		log.info("Verify the web link is published successfully");
		verifyTrue(weblinkPage.isPublish(weblink4.getName()));
		
	}
	
	@AfterClass
	public void end(){
		weblinkPage.deleteWeblink(weblink3.getName());
		shutdown();
	}
}
