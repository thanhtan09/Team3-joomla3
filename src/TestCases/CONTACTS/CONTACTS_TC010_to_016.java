package TestCases.CONTACTS;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Interfaces;
import Pages.Login_page;

public class CONTACTS_TC010_to_016 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
		
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test(description = "Verify user can sort the contact table by ID column")
	public void TC_CONTACT_011 (){	
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");

		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();
		
		contactsPage.clickSortID();
		log.info("Enter Contacts page");
		verifyTrue(contactsPage.isContactASCByID(),"The contacts are sorted by ID in ascending order ");
		
		contactsPage.clickSortID();	
		log.info("Enter Contacts page");
		verifyTrue(contactsPage.isContactDESByID(), "The contacts is sorted by ID in descending order.");
	}
	
	@Test(description = "Verify user can paging the contacts using the paging control", dependsOnMethods = "TC_CONTACT_011")
	public void TC_CONTACT_012 (){
		
		log.info("Select '5' item of the 'Display' dropdown list");
		contactsPage.selectDisplayItem("5");
		verifyTrue(contactsPage.isPaging(5), "Verify the contact table is paging into 5 contacts per page");
		
		log.info("Select 'All' item of the 'Display' dropdown list");
		contactsPage.selectDisplayItem("All");
		verifyTrue(contactsPage.isControlNotExist(driver, By.xpath(Interfaces.ContactsPage.BAR_PAGING)), "Verify all contacts are displayed in one page");

	}
	
	@Test(description = "Verify user can change the order of contacts using the Ordering column", dependsOnMethods = "TC_CONTACT_011")
	public void TC_CONTACT_014 (){
		
		log.info("Create new contact");
		contactsPage.addNewContact(contact.getName(),contact.getCategory(),"","","");
		
		log.info("Verify Contact successfully saved");
		verifyTrue(contactsPage.isMessageContactDisplay());
		verifyTrue(contactsPage.isContactDisplay(contact.getName()));	
		
		log.info("Verify the first article changes its position with the second article");
		verifyTrue(contactsPage.isContactChangePosition(contact.getName()));
		
	}
	
	@Test(description = "Verify user can change the status of contacts using the Status column", dependsOnMethods="TC_CONTACT_011")
	public void TC_CONTACT_015 (){
		
		log.info("Create new contact");
		contactsPage.addNewContact(contact4.getName(),contact4.getCategory(),contact4.getStatus(),"","");
		
		log.info("Verify Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
		log.info("Verify Created contact is displayed on the contacts table");
		verifyTrue(contactsPage.isContactDisplay(contact4.getName()));	
		
		log.info("Click on the status icon of the selected contact in the Status column");
		contactsPage.clickStatusIcon(contact4.getName());
		
		log.info("Verify the contact is published successfully");
		verifyTrue(contactsPage.isMessageUnpublishContactDisplay());
		verifyTrue(contactsPage.isUnpublishContactDisplay(contact4.getName()));
	
		log.info("Click on the status icon of the selected contact in the Status column");
		contactsPage.clickStatusIcon(contact4.getName());
		
		log.info("Verify the contact is published successfully");
		verifyTrue(contactsPage.isMessagePublishContactDisplay(), "1 contact published");
		verifyTrue(contactsPage.isPublishContactDisplay(contact4.getName()),"The icon of the selected item is showed as 'Publish'. ");
	}
	
	@Test(description = "Verify user can change the feature property of contacts using the Featured column", dependsOnMethods = "TC_CONTACT_015")
	public void TC_CONTACT_016 (){
		
		log.info("Verify the contact is featured successfully");
		contactsPage.clickFeaturedIcon(contact4.getName());
		verifyTrue(contactsPage.isShowFeaturedIcon(contact4.getName()),"The icon of the selected item is showed as 'Featured'. ");		
		
		log.info("Verify the contact is unfeatured successfully");
		contactsPage.clickFeaturedIcon(contact4.getName());
		verifyTrue(contactsPage.isShowUnFeaturedIcon(contact4.getName()),"The icon of the selected item is showed as 'Unfeatured'. ");
	}
	
	@Test(description = "Verify user can search for contacts using the filter dropdown lists", dependsOnMethods = "TC_CONTACT_015")
	public void TC_CONTACT_010 (){
		
		log.info("Select Category");
		contactsPage.selectCategory("Sample Data-Contact");
		
		log.info("Select Status");
		contactsPage.selectStatus("Published");
		
		verifyTrue(contactsPage.isContactDisplay(contact4.getName()));
		
	}
	
	@AfterClass
	public void end(){
		contactsPage.deleteContact(contact4.getName());
		contactsPage.deleteContact(contact.getName());
		shutdown();
	}
}
