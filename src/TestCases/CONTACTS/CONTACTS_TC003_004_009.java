package TestCases.CONTACTS;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;

public class CONTACTS_TC003_004_009 extends Abstract_test{
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can unpublish a published contact")
	public void TC_CONTACT_003 (){
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();		
		
		log.info("Create new contact");
		contactsPage.addNewContact(contact2.getName(),contact2.getCategory(),contact2.getStatus(),"","");
		
		log.info("Verify Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
		log.info("Verify Created contact is displayed on the contacts table");
		verifyTrue(contactsPage.isContactDisplay(contact2.getName()));
		
		log.info("Publish an unpublish contact");
		contactsPage.publishContact(contact2.getName());
		
		log.info("Verify '1 contact successfully published' message is displayed");
		contactsPage.isMessagePublishContactDisplay();
		
		log.info("Verify the icon of the selected item is showed as 'Publish'");
		contactsPage.isPublishContactDisplay(contact2.getName());
			
	}
	
	@Test(description = "Verify user can edit a contact", dependsOnMethods = "TC_CONTACT_003")
	public void TC_CONTACT_004 (){

		log.info("UnPublish a publish contact");
		contactsPage.unpublishContact(contact2.getName());
		
		log.info("Verify '1 contact successfully unpublished' message is displayed");
		contactsPage.isMessageUnpublishContactDisplay();
		
		log.info("Verify the icon of the selected item is showed as 'Publish'");
		contactsPage.isUnpublishContactDisplay(contact2.getName());
		
	}
	
	@Test(description = "Verify user can move a contact to the archive", dependsOnMethods = "TC_CONTACT_004")
	public void TC_CONTACT_005 (){

		log.info("UnPublish a publish contact");
		contactsPage.unpublishContact(contact2.getName());
		
		log.info("Verify '1 contact successfully unpublished' message is displayed");
		contactsPage.isMessageUnpublishContactDisplay();
		
		log.info("Verify the icon of the selected item is showed as 'Publish'");
		contactsPage.isUnpublishContactDisplay(contact2.getName());
		
	}
	
	@AfterClass
	public void end(){
		shutdown();
	}
}
