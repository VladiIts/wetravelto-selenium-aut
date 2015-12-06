package workInProgress;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import common_methods.SoftAssertions;

public class XpathTest1 {

	final WebDriver webDriver   = new FirefoxDriver();
	
	    //Calling Soft Assertion
    //SoftAssertions sa = new SoftAssertions(); 
	//SoftAssert s_assert = new SoftAssert();
	SoftAssert sa = new SoftAssert();

    @Test
    public void shouldNotSelectIgnoredTag() throws Exception{

        //this.webDriver.get("http://www.s2server.de/stackoverflow/11773593.html");
    	this.webDriver.get("https://ww2.wetravel.to/");
        

        System.out.println(this.webDriver.getPageSource());

        final List<WebElement> allelements = this.webDriver.findElements(By.xpath("//*[text()]"));

        for (final WebElement element : allelements) {
        	//sa.assertEquals("span", element.getText()); //
        	
        	System.out.println(element.getAttribute("src") + " " + element.getText());
        }
    }

    @AfterMethod
    public void tearDown() {
        this.webDriver.quit();
    }
 }
