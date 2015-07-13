package TestCases.CONTACTS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class CONTACTS_TC006_to_009 extends Abstract_test{
		
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
	private String contact2;
		
	@BeforeClass
	public void setup(){
		driver = openJoomla();
		contact2 = contact.getName();
	}
		

	@Test(description = "Verify user can check in a contact")
	public void TC_CONTACT_006 (){
			
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(), "");
		contactsPage = homePage.navigatetoContactspage();
		
		log.info("Create new contact");
		contactsPage.addNewContact(contact.getName(),contact.getCategory(),contact.getStatus(),"","Save");
		
		log.info("Verify Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
			
		shutdown();
		driver = openJoomla();
			
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
			
		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();
			
		log.info("Check in contact");
		contactsPage.checkinContact(contact2);
			
		log.info("Verify 1 contact successfully checked in message is displayed");
		verifyTrue(contactsPage.isCheckinMessage());
			
		log.info("Verify the lock icon next to the contact is removed");
		verifyFalse(contactsPage.isCheckinContact(contact2));
	}
		
	@Test(description = "Verify user can move a contact to trash section")
	public void TC_CONTACT_007 (){
			
		log.info("Move a contact to trash");
		contactsPage.trashContact(contact2);
			
		log.info("Verify '1 contact successfully trashed' message is displayed");
		verifyTrue(contactsPage.isTrashContactMessage());
			
		log.info("Verify the deleted contact is displayed on the table grid");
		verifyTrue(contactsPage.isTrashedContactinG(contact2));
	}
		
	@Test(description = "Verify user can access Contact's help section")
	public void TC_CONTACT_008 (){

		log.info("Verify the contact's help window is displayed");
		verifyTrue(contactsPage.isHelpWindow());
	}
	
	@Test(description = "Verify user can search for contacts using the filter text field")
	public void TC_CONTACT_009 (){

		contactsPage.searchforContact(contact2);
		
		log.info("Verify the titles of displayed contacts are partially matched with the entered keyword");
		verifyTrue(contactsPage.isContactDisplay(contact2));
	}
		
	
	@AfterClass
	public void end(){
		contactsPage.deleteContact(contact2);
		shutdown();
	}

}
