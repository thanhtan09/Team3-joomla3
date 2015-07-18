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

public class WEBLINKS_TC011 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private WebLinks_page weblinkPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can sort the weblink table by ID column")
	public void TC_WEBLINK_011 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Weblink page");
		weblinkPage = homePage.navigatetoWeblinkpage();	
		
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
		verifyFalse(weblinkPage.isControlExist(driver, By.xpath(Interfaces.ContactsPage.BAR_PAGING)));
	}
	
	@AfterClass
	public void end(){
		shutdown();
	}
}
