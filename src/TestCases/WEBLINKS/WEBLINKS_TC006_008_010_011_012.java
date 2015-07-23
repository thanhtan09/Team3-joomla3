package TestCases.WEBLINKS;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces;
import Pages.Login_page;
import Pages.WebLinks_page;

public class WEBLINKS_TC006_008_010_011_012 extends Abstract_test{
	
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
	
	@Test(description = "Verify user can access weblink's help section")
	public void TC_WEBLINK_008 (){
		
		log.info("Verify user can access weblink's help section");
		verifyTrue(weblinkPage.isHelpWindow());
		
	}
	
	@Test(description = "Verify user can search for weblinks using the filter dropdown lists")
	public void TC_WEBLINK_010 (){	
		
		log.info("Create new weblink");
		weblinkPage.addNewWebLink(weblink4.getName(), weblink4.getUrl(), weblink4.getContent(), weblink4.getStatus(),weblink4.getCategory(), "");
		
		log.info("Verify message Weblink successfully saved displayed");
		verifyTrue(weblinkPage.isWebLinkDisplay(weblink4.getName()));
		
		log.info("Select an item from the 'Category' filter dropdown list, Select an item from the 'Status' filter dropdown list");
		weblinkPage.searchbyfilter("Sample Data-Weblinks", "Published");
		
		log.info("Verify the property of displayed weblinks are matched with the selected criteria from the filter dropdown lists");
		verifyTrue(weblinkPage.isWebLinkDisplay(weblink4.getName()));
	}
	
	@Test(description = "Verify user can sort the weblink table by ID column")
	public void TC_WEBLINK_011 (){
		
		log.info("Click on the Header link of ID column");
		weblinkPage.clickSortID();
		
		log.info("Verify the weblinks is sorted by ID in ascending order");
		verifyTrue(weblinkPage.isWeblinkASCByID(), "weblinks is sorted by ID in ascending order");
		
		log.info("Click on the Header link of ID column");
		weblinkPage.clickSortID();
		
		log.info("Verify the weblinks is sorted by ID in descending order");
		verifyTrue(weblinkPage.isWeblinkDESByID(), "weblinks is sorted by ID in descending order");
		
	}
	
	@Test(description = "Verify user can paging the weblinks using the paging control")
	public void TC_WEBLINK_012 (){
	
		weblinkPage.selectDisplayItem("5");
		verifyTrue(weblinkPage.isPaging(5));
		weblinkPage.selectDisplayItem("All");
		verifyFalse(weblinkPage.isControlExist(driver, By.xpath(Interfaces.WebLinksPage.BAR_PAGING)));
	}
	
	@AfterClass
	public void end(){
		weblinkPage.deleteWeblink(weblink2);
		weblinkPage.deleteWeblink(weblink4.getName());
		shutdown();
	}
}
