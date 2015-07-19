package TestCases.CLIENTS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.*;

public class Client_test extends Abstract_test{

	private Login_page loginPage;
	private Home_page homePage;
	private Client_page clientPage;
	private NewClient_page newclientPage;
	private String client1;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
		client1 = client.getName();
	}
	
	@Test (description = "Verify that user can browse Banner client help page")
	public void TC_CLIENTS_007(){
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Navigate to CLient page");
		clientPage = homePage.navigatetoCLientpage();
		
		log.info("Banner client help page appears");
		verifyTrue(clientPage.isHelpPage(), "Banner client help page appears");
	}
	
	@Test (description = "Verify that user can check in a client")
	public void TC_CLIENTS_010(){
		
		log.info("Add a new client");
		clientPage.addNewClient(client.getName(), client.getContact(), client.getEmail(), client.getStatus(), "Save");
		
		log.info("A message : Client successfully saved shows and edit page is displayed");
		newclientPage = Factory_page.getNewClientPage(driver);
		
		log.info("A message : Client successfully saved shows and edit client page displays");
		verifyTrue(clientPage.isMessageDisplay(),"Client successfully saved");
		verifyTrue(newclientPage.isEditClientPage(), "Edit client page displays");
		
		shutdown();
		driver = openJoomla();
		
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Navigate to Client page");
		clientPage = homePage.navigatetoCLientpage();
		
		log.info("Recently created client displays and locked");
		verifyTrue(clientPage.isLockedClient(client1),"Recently created client displays and locked");
		
		log.info("Check in client");
		clientPage.checkinClient(client1);
		
		log.info("A message 1 client successfully checked in appears and client is changed to unlock");
		verifyTrue(clientPage.isMessageCheckInClientDisplay(),"1 client successfully checked in");
		verifyTrue(clientPage.isUnlockClient(client1),"Client is changed to unlock");
		
	}
	
	@AfterClass
	public void end(){
		clientPage.deleteClient(client1);
		shutdown();
	}
}
