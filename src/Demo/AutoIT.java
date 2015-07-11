package Demo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutoIT {
	private static WebDriver driver = null;
	 
	public static void main(String[] args) throws IOException, InterruptedException {
 
	    driver = new FirefoxDriver();
 
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
	    driver.get("http://www.toolsqa.com/automation-practice-form");
 
	    driver.findElement(By.id("photo")).click();
 
	   Runtime.getRuntime().exec("C:\\Users\\giang.nguyen\\git\\Team3-joomla3\\src\\Demo\\autoittest.exe");
	    
	   //Runtime.getRuntime().exec("../Team3Joomla/src/Demo/autoittest.exe");
	   Thread.sleep(5000);
 
	    driver.close();
	}
}
