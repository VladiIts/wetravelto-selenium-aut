package workInProgress;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import org.testng.asserts.SoftAssert;

//import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//import common_methods.SoftAssertions;
//import org.testng.asserts.SoftAssert;

//Source: What is the Xpath expression to select all nodes that have text when using the Firefox WebDriver?

// http://stackoverflow.com/questions/11773593/what-is-the-xpath-expression-to-select-all-nodes-that-have-text-when-using-the-f


public class XpathTest

{
	//@formatter:off
	//@formatter:off
	final static String JS_SCRIPT_GET_TEXT  =  "function trim(str) {                                                       " +                                                                                                                                             
			  "     return str;           						" +                                                                                                                                             
			"}                                                                          " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"function extractText(element) {                                            " +                                                                                                                                             
			"    var text = '';                                                         " +                                                                                                                                             
			"    for ( var i = 0; i < element.childNodes.length; i++) {                 " +                                                                                                                                             
			"        if (element.childNodes[i].nodeType === Node.TEXT_NODE) {           " +                                                                                                                                             
			"            nodeText = trim(element.childNodes[i].textContent);            " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"            if (nodeText) {                                                " +                                                                                                                                             
			"                text += element.childNodes[i].textContent + ' ';           " +                                                                                                                                             
			"            }                                                              " +                                                                                                                                             
			"        }                                                                  " +                                                                                                                                             
			"    }                                                                      " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"    return trim(text);                                                     " +                                                                                                                                             
			"}                                                                          " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"function selectElementsHavingTextByXPath(expression) {                     " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"    result = document.evaluate(\".\" + expression, document.body, null,    " +                                                                                                                                             
			"            XPathResult.ANY_TYPE, null);                                   " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"    var nodesWithText = new Array();                                       " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"    var node = result.iterateNext();                                       " +                                                                                                                                             
			"    while (node) {                                                         " +                                                                                                                                             
			"        if (extractText(node)) {                                           " +                                                                                                                                             
			"            nodesWithText.push(node)                                       " +                                                                                                                                             
			"        }                                                                  " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"        node = result.iterateNext();                                       " +                                                                                                                                             
			"    }                                                                      " +                                                                                                                                             
			"                                                                           " +                                                                                                                                             
			"    return nodesWithText;                                                  " +                                                                                                                                             
			"}                                                                          " +                                                                                                                                             
			"return selectElementsHavingTextByXPath(arguments[0]);";                                                                                                                                                                                    
	//@formatter:on

	final WebDriver driver = new FirefoxDriver();

	String url;
	
	

    //Calling Soft Assertion
   // SoftAssertions sa = new SoftAssertions(); 
  //SoftAssert s_assert = new SoftAssert();
  	SoftAssert sa = new SoftAssert();

	@BeforeTest
	public void atBefore() throws Exception{


		url = "https://ww2.wetravel.to";
		//url = "http://www.s2server.de/stackoverflow/11773593.html";
		driver.manage().window().maximize();
	}

	@Test
	public void shouldNotSelectIgnoredTag()
	{

		this.driver.get(url);

		final List<WebElement> elements = (List<WebElement>) ((JavascriptExecutor) this.driver).executeScript(JS_SCRIPT_GET_TEXT, "//*");

		//AssertJUnit.assertFalse(elements.isEmpty()); 
		
		
		////Calling Soft Assertion
		sa.assertFalse(elements.isEmpty());

		for (final WebElement webElement : elements)
		{
			//AssertJUnit.assertEquals("span", webElement.getTagName());
			
			sa.assertEquals("span", webElement.getTagName());
			
		}
	}


	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
