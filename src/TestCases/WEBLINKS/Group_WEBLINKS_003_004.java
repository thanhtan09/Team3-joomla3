package TestCases.WEBLINKS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;
import Pages.WebLinks_page;

public class Group_WEBLINKS_003_004 extends Abstract_test {

	private Login_page loginPage;
	private Home_page homePage;
	private WebLinks_page weblinkPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can publish an unpublished weblink")
	public void TC_WEBLINK_003 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Article page");
		weblinkPage = homePage.navigatetoWeblinkpage();		
		
		log.info("Create new article");
		weblinkPage.addNewWebLink(weblink3.getName(), weblink3.getUrl(), weblink3.getContent(), weblink3.getStatus(), "");
		
		log.info("Verify message Article successfully saved displayed");
		verifyTrue(weblinkPage.isWebLinkDisplay(weblink3.getName()));
		
		log.info("Publish a weblink");
		weblinkPage.publishWeblink(weblink3.getName());
		
		log.info("Verify weblink is publish successfully");
		verifyTrue(weblinkPage.isMessagePublishWeblinkDisplay());
		verifyTrue(weblinkPage.isPublish(weblink3.getName()));
	}
	
	@Test(description = "Verify user can unpublish a published weblink", dependsOnMethods = "TC_WEBLINK_003")
	public void TC_WEBLINK_004 (){
		
		log.info("Unpublish a weblink");
		weblinkPage.unpublishWeblink(weblink3.getName());
		
		log.info("Weblink is unpublished successfully");
		weblinkPage.isMessageUnpublishWeblinkDisplay();
		weblinkPage.isUnPublish(weblink3.getName());		
	}
	
	@Test(description = "Verify user can archive a weblink", dependsOnMethods = "TC_WEBLINK_003")
	public void TC_WEBLINK_005 (){
		
		log.info("Archive a weblink");
		weblinkPage.archiveWeblink(weblink3.getName());
		
		log.info("Weblink is unpublished successfully");
		verifyTrue(weblinkPage.isArchiveMessage());
		verifyTrue(weblinkPage.isArchiveList(weblink3.getName()));
			
	}
	
	
	@AfterClass
	public void end(){
		weblinkPage.deleteWeblink(weblink3.getName());
		shutdown();
	}
}
