package wetravelto_selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.AssertJUnit;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class LP_verify_title_Test {

	WebDriver driver;

	@BeforeClass
	public void setupBeforeTest() {
		driver = new FirefoxDriver();
		driver.navigate().to("https://ww2.wetravel.to");
		driver.manage().window().maximize();
		   //Set  timeout   
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
	}

	@Test (priority = 0, enabled=false)

	public void findLpPageTitleTest(){

		if(driver.getTitle().contains("Free Group Trip Planner & Itinerary Builder | Wetravel"))
			//Pass
			System.out.println("PASS: Actual Page Title is: "+ driver.getTitle() + "; " + ("\r\n") + "Page title contains \"Free Group Trip Planner & Itinerary Builder | Wetravel\" ");
		else
			//Fail
			System.out.println("FAILED: Actual Page Title is: " + " '" + driver.getTitle() + "'; " + "Page title doesn't contains \" expected text\" " ); 	   
			System.out.print("Word\n");
			
		org.testng.Assert.assertEquals(driver.getTitle(), "Free Group Trip Planner & Itinerary Builder | Wetravel 123");;
				
	}

	@Test (priority = 1, enabled=false)
	public void findLinksTest(){
		//Get all the links displayed
		List<WebElement> links = driver.findElements(By.tagName("a"));
		org.testng.Assert.assertEquals(39, links.size());
		
		for(WebElement link : links)
			System.out.print(link.getAttribute("href") + "  <<------------->>  " + link.getAttribute("class") + ("\r\n"));	
	}

	@Test (priority = 2, enabled=true)
	
	public void findBrokenLinksTest() {
		
		// to be started
	}
	
	@AfterClass

	public void TearDownTest() {

		driver.quit();
	}

}
