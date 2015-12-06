package wetravelto_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;



//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.AfterSuite;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class LogInFFTest {
	
	public WebDriver driver;
	public String url;

	@BeforeClass
	public void beforeClass() throws Exception{

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = "https://ww2.wetravel.to";		
		//Navigate to LP page https://ww2.wetravel.to/ in the browser
		driver.get(url); 			 					
		driver.manage().window().maximize();

	}
	
	/*
	 *  Email Log In (Treap creator)" Test cases (Firefox browser), covered //WT # LO.P. 1.01
	 *  Will make it Multi-browser (with Chrome browser too) on the next stage. 
	 *  WIll cover on the next stage TC: WT # LO.P. 1.00, WT # LO.P. 1.01, WT # LO.P. 1.02
	 */

	@Test (priority = 0, enabled=false)	
	public void WT_LO_P_01_00_00_Test() {
			//WT # LO.P. 1.01
		//1. and 2. Check if webelement "Log In" Link on LP page is visible and enabled
		WebElement suLnk = driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a"));
		Assert.assertEquals(true, suLnk.isDisplayed());
		Assert.assertEquals(true, suLnk.isEnabled());

		// 3. Find webelement "Sign Up" Link on LP page, and click on it
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).click();

		//4. Verify that "Log In" links landing page is loaded in the browser.
		String actualUrl1 = driver.getCurrentUrl();
		String expectedURL1 = "https://ww2.wetravel.to/users/sign_up";
		Assert.assertEquals(actualUrl1, expectedURL1);
		System.out.println("Actual 'Log In' links landing page URL is: " + actualUrl1 + "\nExpected 'Log In' links landing page URL is: " + expectedURL1 + "\n"  );

	}

}
