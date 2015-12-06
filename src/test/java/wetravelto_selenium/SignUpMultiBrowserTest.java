package wetravelto_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

//import java.util.List;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.AfterSuite;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class SignUpMultiBrowserTest {

	public WebDriver driver;
	public String url;
	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browser) throws Exception{

		// Passing Browser parameter from TestNG xml
		
		// If the browser is Firefox, then do this
		if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			System.out.println("<== FF test is started. ==>" + "\n");

			// If browser is Chrome, then do this	  
		}else if (browser.equalsIgnoreCase("chrome")) { 
			// Here I am setting up the path for my IEDriver
			System.setProperty("webdriver.chrome.driver", "C:\\workspace_wetravelto\\wetravelto-selenium-aut\\lib\\chromedriver_win32_2.20__11_16_2015\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("\n<== Chrome test is started. ==>" + "\n");
		} 

		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Doesn't the browser type, launch the Website
		url = "https://ww2.wetravel.to";
		//Navigate to LP page https://ww2.wetravel.to/ in the browser
		driver.get(url); 			 					
		driver.manage().window().maximize();
	}

	@Test (priority = 0, enabled=true)
	public void WT_SU_01_00_00_test() {

		// Check if element are visible and enabled
				driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).isDisplayed();
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).isEnabled();
		
		// 1. and 2. Find webelement "Sign Up" Link on LP page, and click on it
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).click();

		//3. Verify that "Sign Up" links landing page is loaded in the browser.
		String url1 = driver.getCurrentUrl();
		String expectedURL = "https://ww2.wetravel.to/users/sign_up";
		Assert.assertEquals(url1, expectedURL);
		
	}

	@Test (priority = 1, enabled=true)
	public void WT_SU_01_00_01_test() {
		// Check if element are visible and enabled
		driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a")).isDisplayed();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a")).isEnabled();
		
		// 1. Find webelement "Sign Up with Email" button on /sign_up/ page, 
		driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a"));

		//2. Verify that "Sign Up with Email" button has "Sign Up with Email" text on it
		String btntext1 = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a")).getText();	
		String expectedText1 = "Sign up with Email";
		Assert.assertEquals(btntext1, expectedText1);
		System.out.println("Actual btn text - " + btntext1 + "; " + "\nExpected btn Text - " + expectedText1 + "\n");	
		
		// Click on btn "Sign Up with Email"
		driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@BeforeMethod
	//public void beforeMethod() {
	//}

	//@AfterMethod
	//public void afterMethod() {

	//}


	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}

}
