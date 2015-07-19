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
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test (description = "Verify that user can browse Banner client help page")
	public void TC_CLIENTS_007(){
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Navigate to CLient page");
		clientPage = homePage.navigatetoCLientpage();
		
		log.info("Banner client help page appears");
		//verifyTrue(clientPage.isHelpPage(), "Banner client help page appears");
	}
	
	@Test (description = "Verify that user can check in a client")
	public void TC_CLIENTS_010(){
		
		log.info("Add a new client");
		clientPage.addNewClient(client3.getName(), client3.getContact(), client3.getEmail(), client3.getStatus(), "");
		
		log.info("A message : Client successfully saved shows and new client is created");
		verifyTrue(clientPage.isMessageDisplay(),"Client successfully saved");
		verifyTrue(clientPage.isClientcreated(client3.getName()),"New client is created");
		
		log.info("Select unpublish status");
		clientPage.selectUnpublishStatus();
		
		log.info("Recently created client displays");
		verifyTrue(clientPage.isClientcreated(client3.getName()), "Recently created client displays");
	}
	
	@AfterClass
	public void end(){
		
		clientPage.deleteClient(client3.getName());
		shutdown();
	}
}
