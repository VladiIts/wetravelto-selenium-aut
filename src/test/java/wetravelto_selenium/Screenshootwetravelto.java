package wetravelto_selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Screenshootwetravelto {

	@Test
	public void TestJavaS1(){

		// Open Firefox
		WebDriver driver=new FirefoxDriver();

		// call method
		Screenshootwetravelto.captureScreenShot(driver);

		// Maximize the window
		driver.manage().window().maximize();
		Screenshootwetravelto.captureScreenShot(driver);

		// Pass the url
		driver.get("https://ww2.wetravel.to/");
		Screenshootwetravelto.captureScreenShot(driver);

	}

	public static void captureScreenShot(WebDriver ldriver){
		// Take screenshot and store as a file format             
		File src=((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);           
		try {
			// now copy the  screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File("C:/workspace_wetravelto/TestReports/"+System.currentTimeMillis()+".png"));                              

		} catch (IOException e)

		{
			System.out.println(e.getMessage()); 
		}
	}
}

