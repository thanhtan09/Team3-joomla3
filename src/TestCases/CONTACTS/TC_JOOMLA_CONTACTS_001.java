package TestCases.CONTACTS;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class TC_JOOMLA_CONTACTS_001 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
	
	@BeforeMethod
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
		
		log.info("Create new article");
		contactsPage.addNewContact(contact.getName(),contact.getCategory(),"","","");
		
		log.info("Verify Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
		log.info("Verify Created contact is displayed on the contacts table");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
	}
	
	@AfterMethod
	public void end(){
		shutdown();
	}
	
}
