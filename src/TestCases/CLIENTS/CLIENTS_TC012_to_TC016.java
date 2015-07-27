package TestCases.CLIENTS;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Functions.Abstract_test;
import Pages.*;

public class CLIENTS_TC012_to_TC016 extends Abstract_test {
	
	private Login_page loginPage;
	private Home_page homePage;
	private Client_page clientPage;
	private NewClient_page newclientPage;
	//private String client1;
	
	@BeforeClass
	public void setup(){
		driver = openJoomla();
		//client1 = client.getName();
	}
	
	@Test (description = "Verify that user can browse 'New client help' page")
	public void TC_CLIENTS_012(){
		log.info("Login with valid account");
		loginPage = Factory_page.getLoginPage(driver);
		homePage = loginPage.loginValidAccount(user.getUsername(), user.getPassword(),"");
		
		log.info("Navigate to CLient page");
		clientPage = homePage.navigatetoCLientpage();
		
		log.info("Access to New Client Help page");
		clientPage.click(driver, By.xpath(Interfaces.ClientPage.BTN_NEW));
		verifyTrue(clientPage.isHelpPage(), "New Client Help page appears");
		
		clientPage.clickCancel();
		
	}
	
	@Test (description = "Verify that user can creat a new client by using 'Save as Copy' button")
	public void TC_CLIENTS_013(){
		log.info("Create a client banner");
		clientPage.addNewClient(client.getName(), client.getContact(), client.getEmail(), "", "Save");
		newclientPage = Factory_page.getNewClientPage(driver);
		
		log.info("Save message display");
		verifyTrue(clientPage.isMessageDisplay(),"Client successfully saved");
		
		log.info("Edit Page display");
		verifyTrue(newclientPage.isEditClientPage(), "Edit client page displays");
		
		log.info("Create a copy client banner");
		newclientPage.addClient(client2.getName(), "", "", "", "SaveAsCopy");
		
		verifyTrue(clientPage.isMessageDisplay(),"Client successfully saved");
				
		clientPage.clickCancel();
		
		verifyTrue(clientPage.isMultiClientsDisplay(client2.getName(), client.getName()), " A new category is created without replacing the old client");
		
	}
	
	@Test (description = "Verify that user cannot create a new client without entering the name of the client")
	public void TC_CLIENTS_014(){
		log.info(" create a new client without entering the name of the client");
		clientPage.addNewClient("", client.getContact(), client.getEmail(), "", "");
		newclientPage = Factory_page.getNewClientPage(driver);
		newclientPage.isControlChangedtoRed();
	}
	
	
	@AfterClass
	public void end(){
		clientPage.deleteClient(client.getName());
		clientPage.deleteClient(client2.getName());
	shutdown();
	}
	
}
