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

public class CONTACTS_TC010_to_016 extends Abstract_test {
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
	
	@Test(description = "Verify user can sort the contact table by ID column", dependsOnMethods = "TC_CONTACT_012")
	public void TC_CONTACT_011 (){		
		
		contactsPage.clickSortID();
		log.info("Enter Contacts page");
		verifyTrue(contactsPage.isContactASCByID(),"The contacts are sorted by ID in ascending order ");
		
		contactsPage.clickSortID();	
		log.info("Enter Contacts page");
		verifyTrue(contactsPage.isContactDESByID(), "The contacts is sorted by ID in descending order.");
	}
	
	@Test(description = "Verify user can search for contacts using the filter dropdown lists", dependsOnMethods = "TC_CONTACT_011")
	public void TC_CONTACT_010 (){
		
		log.info("Create new contact");
		contactsPage.addNewContact(contact4.getName(),contact4.getCategory(),contact4.getStatus(),"","");
		
		log.info("Verify Contact successfully saved message is displayed");
		verifyTrue(contactsPage.isMessageContactDisplay());
		
		log.info("Verify Created contact is displayed on the contacts table");
		verifyTrue(contactsPage.isContactDisplay(contact4.getName()));	
		
		contactsPage.searchbyfilter(contact4.getName(),"Sample Data-Contact", "Published");
		
		verifyTrue(contactsPage.isContactDisplay(contact4.getName()));	
		
	}	
		
	@AfterClass
	public void end(){
		contactsPage.deleteContact(contact4.getName());
		shutdown();
	}
}
