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
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
	}
	
	@Test (description = "Verify that user can create a new client")
	public void TC_CLIENTS_001(){
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Create new client");
		clientPage = homePage.navigatetoCLientpage();
		clientPage.addNewClient(client.getName(), client.getContact(), client.getEmail(), "", "Save");		
		newclientPage = Factory_page.getNewClientPage(driver);
		
		log.info("A message : Client successfully saved shows and edit client page displays");
		verifyTrue(clientPage.isMessageDisplay(),"Client successfully saved");
		verifyTrue(newclientPage.isEditClientPage(), "Edit client page displays");
		
	}
	
	@Test (description = "Verify that user can edit a client")
	public void TC_CLIENTS_002(){
		
		log.info("Edit client");
		newclientPage.addClient(client2.getName(), client2.getContact(), client2.getEmail(), "", "");
		
		log.info("A message : Client successfully saved shows and Client is edited");
		clientPage = Factory_page.getClientPage(driver);
		verifyTrue(clientPage.isMessageDisplay(),"Client successfully saved");
		verifyTrue(clientPage.isClientcreated(client2.getName()),"New client is created and matched with entered data");
	}
	
	@Test (description = "Verify that user can publish a client")
	public void TC_CLIENTS_003(){
		
		log.info("Publish a client");
		clientPage.publishClient(client2.getName());
		
		log.info("A message : 1 client successfully published shows and Client is published");
		verifyTrue(clientPage.isMessagePublishedClientDisplay(), "1 client successfully published");
		verifyTrue(clientPage.isClientPublished(client2.getName()), "Client is published");
	}
	
	@AfterClass
	public void end(){	
		clientPage.deleteClient(client2.getName());
		shutdown();
	}
}
