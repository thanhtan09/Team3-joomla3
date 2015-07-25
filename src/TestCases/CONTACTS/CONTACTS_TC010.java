package TestCases.CONTACTS;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class CONTACTS_TC010 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
		
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can search for contacts using the filter dropdown lists")
	public void TC_CONTACT_010 (){

		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();		
		
		log.info("Create new contact");
		contactsPage.addNewContact(contact4.getName(),contact4.getCategory(),contact4.getStatus(),"","");
		
		log.info("Verify Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
		log.info("Verify Created contact is displayed on the contacts table");
		verifyTrue(contactsPage.isContactDisplay(contact4.getName()));	
		
		contactsPage.searchbyfilter(contact4.getName(),"Sample Data-Contact", "Published");
		
		verifyTrue(contactsPage.isFilteredContact("Sample Data-Contact", "Published"));
		
	}
	
	@AfterClass
	public void end(){
		contactsPage.deleteContact(contact4.getName());
	}
}
