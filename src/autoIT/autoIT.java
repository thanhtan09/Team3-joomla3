package autoIT;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import autoitx4java.AutoItX;
import com.jacob.com.LibraryLoader;

public class autoIT {

public static String jvmBitVersion(){
	 return System.getProperty("sun.arch.data.model");
}

private static WebDriver driver = null;

public static void main(String[] args) throws IOException, InterruptedException {

    driver = new FirefoxDriver();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.get("http://www.toolsqa.com/automation-practice-form");

    driver.findElement(By.id("photo")).click();
    
	autoIT au = new autoIT();
	au.uploadFile("D:/CONTENT/MINSU/cat.jpg");
   
   Thread.sleep(5000);

    driver.close();
}	

public void init (){
	// TODO Auto-generated method stub

	String jacobDllVersionToUse;
	if (jvmBitVersion().contains("32")) {
	jacobDllVersionToUse = "jacob-1.18-M3-x86.dll";
	} else {
	jacobDllVersionToUse = "jacob-1.18-M3-x64.dll";
	}


	System.out.println(" Jacod DLL to use : " + jacobDllVersionToUse);
	File file = new File("libs", jacobDllVersionToUse);
	System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

	}

public void uploadFile(String fileName)
{
	AutoItX x = new AutoItX();
	x.winActivate("File Upload");
	x.controlClick("File Upload", "", "1148");
	x.send(fileName);
	x.controlClick("File Upload", "&Open","1");
}

}