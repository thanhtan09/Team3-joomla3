package TestCases.CONTACTS;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.Contacts_page;
import Pages.Factory_page;
import Pages.Home_page;
import Pages.Login_page;


public class CONTACTS_TC011 extends Abstract_test {
	private Login_page loginPage;
	private Home_page homePage;
	private Contacts_page contactsPage;
		
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}	
	
	@Test(description = "Verify user can search for contacts using the filter dropdown lists")
	public void TC_CONTACT_011 (){

		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Enter Contacts page");
		contactsPage = homePage.navigatetoContactspage();	
		
		contactsPage.sortbyID();
		//Verify point
		contactsPage.sortbyID();
		//Verify point
	}
	
	@AfterClass
	public void end(){
		shutdown();
	}
}
