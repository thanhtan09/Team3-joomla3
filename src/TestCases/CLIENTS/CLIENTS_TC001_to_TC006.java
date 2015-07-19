package TestCases.CLIENTS;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.*;

public class CLIENTS_TC001_to_TC006 extends Abstract_test{

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
	
	@Test (description = "Verify that user can edit a client", dependsOnMethods = "TC_CLIENTS_001")
	public void TC_CLIENTS_002(){
		
		log.info("Edit client");
		newclientPage.addClient(client2.getName(), client2.getContact(), client2.getEmail(), "", "");
		
		log.info("A message : Client successfully saved shows and Client is edited");
		clientPage = Factory_page.getClientPage(driver);
		verifyTrue(clientPage.isMessageDisplay(),"Client successfully saved");
		verifyTrue(clientPage.isClientcreated(client2.getName()),"New client is created and matched with entered data");
	}
	
	@Test (description = "Verify that user can publish a client", dependsOnMethods = "TC_CLIENTS_001")
	public void TC_CLIENTS_003(){
		
		log.info("Publish a client");
		clientPage.publishClient(client2.getName());
		
		log.info("A message : 1 client successfully published shows and Client is published");
		verifyTrue(clientPage.isMessagePublishedClientDisplay(), "1 client successfully published");
		verifyTrue(clientPage.isClientPublished(client2.getName()), "Client is published");
	}
	
	@Test (description = "Verify that user can unpublish a client", dependsOnMethods = "TC_CLIENTS_001")
	public void TC_CLIENTS_004(){
		
		log.info("UnPublish a client");
		clientPage.unpublishClient(client2.getName());
		
		log.info("A message : 1 client successfully unpublished shows and Client is unpublished");
		verifyTrue(clientPage.isMessageUnPublishedClientDisplay(), "1 client successfully unpublished");
		verifyTrue(clientPage.isClientUnPublished(client2.getName()), "Client is unpublished");
	}
	
	@Test (description = "Verify that user can archive a client", dependsOnMethods = "TC_CLIENTS_001")
	public void TC_CLIENTS_005(){
		
		log.info("Archieve a client");
		clientPage.archieveClient(client2.getName());
		
		log.info("A message : 1 client successfully archived shows");
		verifyTrue(clientPage.isMessageArchievedClientDisplay(), "1 client successfully archived");
		
		log.info("Client is archived");
		verifyTrue(clientPage.isClientArchieved(client2.getName()), "Client is archived");
	}
	
	@Test (description = "Verify that user can search a client  by using filter textbox", dependsOnMethods = "TC_CLIENTS_001")
	public void TC_CLIENTS_008(){
		
		log.info("Search client");
		clientPage.searchClient(client2.getName());
		
		log.info("Recently created client displays");
		verifyTrue(clientPage.isClientcreated(client2.getName()),"Rencenty created client displays");
	}
	
	@Test (description = "Verify that user can search a client by using filter dropdown list", dependsOnMethods = "TC_CLIENTS_001")
	public void TC_CLIENTS_009(){
		
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
	
	@Test (description = "Verify that user can send a client to trash", dependsOnMethods = "TC_CLIENTS_009")
	public void TC_CLIENTS_006(){
		
		log.info("Archieve a client");
		clientPage.trashClient(client2.getName());
		
		log.info("A message : 1 client successfully sent to trash shows");
		verifyTrue(clientPage.isClientTrashed(), "1 client successfully sent to trash");
		
		log.info("Client is sent to trash");
		verifyTrue(clientPage.isClientSentToTrash(client2.getName()), "Client is sent to trash");
	}
	
	@AfterClass
	public void end(){	
		clientPage.deleteClient(client2.getName());
		clientPage.deleteClient(client3.getName());
		shutdown();
	}
}
