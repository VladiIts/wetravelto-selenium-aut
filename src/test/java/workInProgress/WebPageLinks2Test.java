package workInProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
* How to check broken links on webpages (Improved) - Added testNG @AfterTest
* @author user
*
* http://tutorial.techaltum.com/How-to-check-broken-links-on-a-web-page-in-Selenium.html
*/

public class WebPageLinks2Test {
	WebDriver driver;
	String url;

	@BeforeTest
	public void atBefore() throws Exception{

		driver = new FirefoxDriver();
		url = "https://ww2.wetravel.to";
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void TearDown() throws Exception{
		
		driver.quit();
		System.out.println("WebPageLinks2Test is finished");
	}

	@Test
	public void atTest() throws Exception{

		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

		driver.get(url);

		//driver.findElement(By.name("q")).sendKeys("JAVA");    //Write 'JAVA' keyword in search text box
		//driver.findElement(By.id("gbqfb")).click();
		//driver.findElement(By.name("q")).submit();
		//Thread.sleep(4000);

		List<String> Linkarray = new ArrayList<String>();

		List<WebElement> Linklist = driver.findElements(By.tagName("a"));    //Finding all element on the page with 'a' tag.

		for (WebElement link : Linklist) {

			String links = link.getText();

			if(links.isEmpty()){
				System.out.println("No Text.");    //Checking if any link doesn't have text
			}
			else{
				Linkarray.add(links );
				System.out.println(links);
			}

		}
		System.out.println("=============================================");

		for(int i=0; i<Linkarray.size(); i++)

		{
			System.out.println("Element at " + i  + " is : "+ Linkarray.get(i));
		}

		for (String linkToTest : Linkarray){
			try{
				driver.findElement(By.linkText(linkToTest)).click();    //Clicking on the link
			}
			catch(Exception e){

				// Print if any link not present on the page. 
				// Some links might be change in this case when navigate on the main page
				System.out.println("The link "  + " ' " + linkToTest.substring(0) + " ' " + " -- is not found in the page. "+ " ' " + driver.getCurrentUrl() + " ' "); 
			}

			Thread.sleep(15000L);

			if(driver.getTitle().contains("Problem")) {          //Checking if page title having 'Problem' word

				System.out.println("Fail - Problem word is shown in the page title on " + driver.getCurrentUrl());
			}

			else{
				System.out.println("Pass - Problem word isn't shown in the page title on "+ driver.getCurrentUrl());
			}

			driver.navigate().back();        //Navigate on main page
			Thread.sleep(5000L);
		}
	}

}
