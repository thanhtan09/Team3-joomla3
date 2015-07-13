package TestCases.CONTACTS;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces;
import Pages.Login_page;


public class CONTACTS_TC012 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
		
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}	
	
	@Test(description = "Verify user can paging the contacts using the paging control")
	public void TC_CONTACT_012 (){

		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();	
		
		contactsPage.selectDisplayItem("5");
		verifyTrue(contactsPage.isPaging(5));
		contactsPage.selectDisplayItem("All");
		verifyFalse(contactsPage.isControlExist(driver, By.xpath(Interfaces.ContactsPage.BAR_PAGING)));

	}
	
	@Test(description = "Verify user can add image to contact's information")
	public void TC_CONTACT_013 (){

		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();	
		
		contactsPage.addNewContact(contact.getName(), contact.getCategory(), "",contact.getImage(),"");
		log.info("Verify message Article successfully saved displayed");
		verifyTrue(contactsPage.isContactDisplay(contact.getName()));

	}
	@AfterClass
	public void end(){
		shutdown();
	}
}
