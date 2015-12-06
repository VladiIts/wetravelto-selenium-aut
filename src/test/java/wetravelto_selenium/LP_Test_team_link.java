package wetravelto_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class LP_Test_team_link {
	
	WebDriver driver;
	String url;
	
	@Test
	public void f() {
		//driver.getPageSource().contains("Text which you looking for");
		driver.getPageSource().contains("PAGE NOT FOUND");
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
		
	}

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		url = "https://ww2.wetravel.to/pages/team";
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		
		driver.quit();
	}


	@BeforeTest
	public void atBefore() throws Exception{

		
	}


	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
