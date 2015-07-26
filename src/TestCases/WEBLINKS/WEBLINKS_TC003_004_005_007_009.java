package TestCases.WEBLINKS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces;
import Pages.Login_page;
import Pages.WebLinks_page;

public class WEBLINKS_TC003_004_005_007_009 extends Abstract_test {

	private Login_page loginPage;
	private Home_page homePage;
	private WebLinks_page weblinkPage;
	
	// Message
	private String MESSAGEPUBLISH = "1 weblink successfully published";
	private String MESSAGEUNPUBLISH = "1 weblink successfully unpublished";
	private String MESSAGEARCHIVE = "1 weblink successfully archived";
	private String MESSAGETRASHWEBLINK = "1 weblink successfully trashed";

	// Status
	private String STATUS_TRASHED = "Trashed";
	private String STATUS_ARCHIVED = "Archived";;
	private String STATUS_PUBLISH = "Published";
	private String STATUS_UNPUBLISH = "Unpublished";
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can publish an unpublished weblink")
	public void TC_WEBLINK_003 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		weblinkPage = homePage.navigatetoWeblinkpage();		
		
		log.info("Create new weblink");
		weblinkPage.addNewWebLink(weblink3.getName(), weblink3.getUrl(), weblink3.getContent(), weblink3.getStatus(),weblink3.getCategory(), "");
		
		log.info("Verify message Weblink successfully saved displayed");
		verifyTrue(weblinkPage.isMessageWeblinkDisplay(),"Weblink is created successfully");
		verifyTrue(weblinkPage.isWebLinkDisplay(weblink3.getName()));
		
		log.info("Publish a weblink");
		weblinkPage.clickButton(weblink3.getName(), Interfaces.WebLinksPage.BTN_PUBLISH);
		
		log.info("Verify weblink is publish successfully");
		verifyTrue(weblinkPage.isSuccessMessageDisplay(MESSAGEPUBLISH));
		verifyTrue(weblinkPage.isWeblinkStatus(weblink3.getName(), STATUS_PUBLISH));
	}

	
	@Test(description = "Verify user can unpublish a published weblink", dependsOnMethods = "TC_WEBLINK_003")
	public void TC_WEBLINK_004 (){
		
		log.info("Unpublish a weblink");
		weblinkPage.clickButton(weblink3.getName(), Interfaces.WebLinksPage.BTN_UNPUBLISH);
		
		log.info("Weblink is unpublished successfully");
		verifyTrue(weblinkPage.isSuccessMessageDisplay(MESSAGEUNPUBLISH));
		verifyTrue(weblinkPage.isWeblinkStatus(weblink3.getName(), STATUS_UNPUBLISH));		
	}
	
	@Test(description = "Verify user can archive a weblink", dependsOnMethods = "TC_WEBLINK_003")
	public void TC_WEBLINK_005 (){
		
		log.info("Archive a weblink");
		weblinkPage.clickButton(weblink3.getName(), Interfaces.WebLinksPage.BTN_ARCHIVE);
		
		log.info("Weblink is archived successfully");
		verifyTrue(weblinkPage.isSuccessMessageDisplay(MESSAGEARCHIVE));
		verifyTrue(weblinkPage.isWeblinkStatus(weblink3.getName(), STATUS_ARCHIVED));	
			
	}
	
	@Test(description = "Verify user can move a weblink to trash section", dependsOnMethods = "TC_WEBLINK_003")
	public void TC_WEBLINK_007 (){
		
		log.info("Trash a weblink");
		weblinkPage.clickButton(weblink3.getName(), Interfaces.WebLinksPage.BTN_TRASH);
		
		log.info("Weblink is trashed successfully");
		verifyTrue(weblinkPage.isSuccessMessageDisplay(MESSAGETRASHWEBLINK));
		verifyTrue(weblinkPage.isWeblinkStatus(weblink3.getName(), STATUS_TRASHED));	
			
	}
	
	@Test(description = "Verify user can can search for weblinks using the filter text field", dependsOnMethods = "TC_WEBLINK_003")
	public void TC_WEBLINK_009 (){
		
		log.info("Enter weblink into textfield and click Search button");
		weblinkPage.searchforWeblink(weblink3.getName());
		
		log.info("Verify the titles of displayed weblinks are partially matched with the entered keyword");
		verifyTrue(weblinkPage.isSearchWeblinkDisplay(weblink3.getName()));
		
	}
	
	@AfterClass
	public void end(){
		weblinkPage.deleteWeblink(weblink3.getName());
		shutdown();
	}
}
