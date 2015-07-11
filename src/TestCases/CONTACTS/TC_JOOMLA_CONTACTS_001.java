package TestCases.CONTACTS;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class TC_JOOMLA_CONTACTS_001 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can create new contact with valid information")
	public void TC_CONTACT_001 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();		
		
		log.info("Create new contact");
		contactsPage.addNewContact(contact.getName(),contact.getCategory(),"","","");
		
		log.info("Verify Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
		log.info("Verify Created contact is displayed on the contacts table");
		verifyTrue(contactsPage.isContactDisplay(contact.getName()));
		
	}
	
	@Test(description = "Verify user can edit a contact", dependsOnMethods = "TC_CONTACT_001")
	public void TC_CONTACT_002 (){

		log.info("Edit a contact");
		contactsPage.editContact(contact.getName(),contact3.getName(),contact3.getCategory(),"");
		
		log.info("Verify Edited Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
		log.info("Verify Edited contact is displayed on the contacts table");
		verifyTrue(contactsPage.isContactDisplay(contact3.getName()));
		
	}
	
	@AfterClass
	public void end(){
		contactsPage.deleteContact(contact3.getName());
		shutdown();
	}
	
}
