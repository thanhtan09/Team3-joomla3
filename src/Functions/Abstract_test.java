package Functions;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import Databases.*;

public abstract class Abstract_test {

	protected final Log log;
	protected WebDriver driver;
	
	//Data
	protected ReadData data = new ReadData();
	protected String url;
	protected User user;
	
	//Status
	protected String STATUS_UNPUBLISH = "Unpublished";
	protected String STATUS_PUBLISH = "Published";
	
	//Data content
	protected Article article,article2,article3,article4,article5,article6,article7;
	protected Client client;
	protected Category category;
	protected Banner banner,banner2,banner3,banner4;
	protected Contact contact, contact2, contact3, contact4;
	protected Weblink weblink1, weblink2, weblink3, weblink4;
	
	protected Abstract_test() {
		log = LogFactory.getLog(getClass());
	}

	/*
	 * Open Joomla page
	 * 
	 * Parameter: url
	 * 
	 * Author: Tan Vo
	 */
	protected WebDriver openJoomla() {
		
		//Get data
		getData();
		
		//Start Joomla
		driver = new FirefoxDriver();
		url = data.getUrl("Logigear_url");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}

	/*.
	 * Close browser
	 * 
	 * Author: Tan Vo
	 */
	protected void shutdown() {
		driver.close();
		try {
			Thread.sleep(5000);
			driver.quit();
		} catch (Exception e) {
		}
	}

	/*.
	 * Verify True
	 * 
	 * Author: Tan Vo
	 */
	protected void verifyTrue(boolean condition) {
		try {
			Assert.assertTrue(condition);
		} catch (Exception e) {
			log.info("FAIL: "+e);
		}
	}
	
	/*
	 * Verify True
	 * 
	 * Parameter: condition, message
	 * 
	 * Author: Tan Vo
	 */
	protected void verifyTrue(boolean condition, String message){
		try{
			Assert.assertTrue(condition, message);
		} catch(Exception e){
			log.info("FAIL:" + e);
		}
	}
	
	/*
	 * Author: Giang NGuyen
	 */
	protected void verifyFalse(boolean condition) {
		try {
			Assert.assertFalse(condition);
		} catch (Exception e) {
			log.info("FAIL: "+e);
		}
	}
	
	/*.
	 * Get data
	 * 
	 * Author: Tan Vo
	 */
	public void getData(){
		user = data.getUser("Tan");
		article = data.getArticle("Article1");
		article2 = data.getArticle("Article2");
		article3 = data.getArticle("Article3");
		article4 = data.getArticle("Article4");
		article5 = data.getArticle("Article5");
		article6 = data.getArticle("Article6");
		article7 = data.getArticle("Article7");
		client = data.getClient("Client1");
		category = data.getCategory("Cate1");
		banner = data.getBanner("Banner1");
		banner2 = data.getBanner("Banner2");
		banner3 = data.getBanner("Banner3");
		banner4 = data.getBanner("Banner4");
		contact = data.getContact("Contact1");
		contact2 = data.getContact("Contact2");
		contact3 = data.getContact("Contact3");
		contact4 = data.getContact("Contact4");
		weblink1 = data.getWeblink("Weblink1");
		weblink2 = data.getWeblink("Weblink2");
		weblink3 = data.getWeblink("Weblink3");
		weblink4 = data.getWeblink("Weblink4");
	}
}
