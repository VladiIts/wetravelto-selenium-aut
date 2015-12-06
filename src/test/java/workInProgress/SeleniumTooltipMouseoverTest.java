package workInProgress;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class SeleniumTooltipMouseoverTest {

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
	@Test (priority = 0, enabled=true)	
	public void WT_SU_01_00_00_00test() {

		//1. and 2. Check if webelement "Sign Up" Link on LP page is visible and enabled
		WebElement suLnk = driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a"));
		Assert.assertEquals(true, suLnk.isDisplayed());
		Assert.assertEquals(true, suLnk.isEnabled());
		//driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).isDisplayed();
		//driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).isEnabled();

		// 3. Find webelement "Sign Up" Link on LP page, and click on it
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).click();

		//4. Verify that "Sign Up" links landing page is loaded in the browser.
		String ActualUrl1 = driver.getCurrentUrl();
		String expectedURL1 = "https://ww2.wetravel.to/users/sign_up";
		Assert.assertEquals(ActualUrl1, expectedURL1);
	}

	@Test (priority = 2, enabled=true)
	// -- will use those parameters below  in the multibrowser tests - "SignUpTest.java" (will add parameters to testng.xml file as well), instead of hardcoding
	//@Parameters({ "sUsername", "sPassword" }) 
	public void WT_SU_01_00_00_02_Test() {

		// 1. Click on "Sign Up with Email" button on /sign_up/ page
		driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2.1. Assert that "First Name" field is visible and enabled on the Registration Form at /users/sign_up page, 
		WebElement fnFld = driver.findElement(By.xpath(".//*[@id='user_firstname']"));
		Assert.assertEquals(true, fnFld.isDisplayed());
		Assert.assertEquals(true, fnFld.isEnabled());
		//driver.findElement(By.xpath(".//*[@id='user_firstname']")).isDisplayed();
		//driver.findElement(By.xpath(".//*[@id='user_firstname']")).isEnabled();

		//2.1.1. Assert that FN fields label is "First Name".
		String ActualLabelFN = driver.findElement(By.xpath(".//*[@id='user_firstname']")).getAttribute("placeholder").toString(); 
		String ExpectedLabelFN = "First Name";
		Assert.assertEquals(ActualLabelFN, ExpectedLabelFN);
		System.out.println("FN Fields Actual Label is: " + ActualLabelFN + ", " + "\nFN Fields Expected Label is: " + ExpectedLabelFN + "\n");

		//2.1.2. Clear the FN field from previous entries, just in case if any
		driver.findElement(By.xpath(".//*[@id='user_firstname']")).clear();

		//2.1.3. Enter value into FN field
		driver.findElement(By.xpath(".//*[@id='user_firstname']")).sendKeys("traveleraut");

		//2.2. Assert that "Last Name" field is visible and enabled on the Registration Form at /users/sign_up page, 
		WebElement lnFld = driver.findElement(By.xpath(".//*[@id='user_lastname']"));
		Assert.assertEquals(true, lnFld.isDisplayed());
		Assert.assertEquals(true, lnFld.isEnabled());		
		//driver.findElement(By.xpath(".//*[@id='user_lastname']")).isDisplayed();
		//driver.findElement(By.xpath(".//*[@id='user_lastname']")).isEnabled();

		//2.2.1. Assert that LN fields label is "Last Name".
		String ActualLabelLN = driver.findElement(By.xpath(".//*[@id='user_lastname']")).getAttribute("placeholder").toString(); 
		String ExpectedLabelLN = "Last Name";
		Assert.assertEquals(ActualLabelLN, ExpectedLabelLN);
		System.out.println("LN Fields Actual Label is: " + ActualLabelLN + ", " + "\nLN Fields Expected Label is: " + ExpectedLabelLN + "\n");

		//2.2.2.Clear the LN field from previous entries, just in case if any
		driver.findElement(By.xpath(".//*[@id='user_lastname']")).clear();

		//2.2.3. Enter value into LN field
		driver.findElement(By.xpath(".//*[@id='user_lastname']")).sendKeys("1117");

		//2.3. "Email" field is visible and enabled on the Registration Form at /users/sign_up page
		WebElement emailFld = driver.findElement(By.xpath(".//*[@id='user_email']"));
		Assert.assertEquals(true, emailFld.isDisplayed());
		Assert.assertEquals(true, emailFld.isEnabled());		
		//driver.findElement(By.xpath(".//*[@id='user_email']")).isDisplayed();
		//driver.findElement(By.xpath(".//*[@id='user_email']")).isEnabled();

		//2.3.1. Assert that "Email" fields label is "Email".
		String ActualLabelEmail = driver.findElement(By.xpath(".//*[@id='user_email']")).getAttribute("placeholder").toString(); 
		String ExpectedLabelEmail = "Email";
		Assert.assertEquals(ActualLabelEmail, ExpectedLabelEmail);
		System.out.println("Email Fields Actual Label is: " + ActualLabelEmail + ", " + "\nEmail Fields Expected Label is: " + ExpectedLabelEmail + "\n");

		//2.3.2.Clear the "Email" field from previous entries, just in case if any
		driver.findElement(By.xpath(".//*[@id='user_email']")).clear();

		//2.3.3. Enter value into "Email" field
		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("%^&*()$#");

		//2.4. Assert that"Password" field is visible and enabled on the Registration Form at /users/sign_up page
		WebElement pwdFld = driver.findElement(By.xpath(".//*[@id='user_password']"));
		Assert.assertEquals(true, pwdFld.isDisplayed());
		Assert.assertEquals(true, pwdFld.isEnabled());	
		//driver.findElement(By.xpath(".//*[@id='user_password']")).isDisplayed();
		//driver.findElement(By.xpath(".//*[@id='user_password']")).isEnabled();

		//2.4.1. Assert that "Password" fields label is "Password".
		String ActualLabelPwd = driver.findElement(By.xpath(".//*[@id='user_password']")).getAttribute("placeholder").toString(); 
		String ExpectedLabelPwd = "Password";
		Assert.assertEquals(ActualLabelPwd, ExpectedLabelPwd);
		System.out.println("Password Fields Actual Label is: " + ActualLabelPwd + ", " + "\nPassword Fields Expected Label is: " + ExpectedLabelPwd + "\n");

		//2.4.2. Clear the Password field from the previous entries, just in case if any
		driver.findElement(By.xpath(".//*[@id='user_password']")).clear();

		//2.4.3. Enter value into Password field
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys("password1");

		//2.5. Assert that "Confirm Password" field is visible and enabled on the Registration Form at /users/sign_up page 
		WebElement confpwdFld = driver.findElement(By.xpath(".//*[@id='user_password_confirmation']"));
		Assert.assertEquals(true, confpwdFld.isDisplayed());
		Assert.assertEquals(true, confpwdFld.isEnabled());	
		//driver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).isDisplayed();
		//driver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).isEnabled();

		//2.5.1. Assert that "Confirm Password" fields label is "Confirm Password".
		String ActualLabelPwdConf = driver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).getAttribute("placeholder").toString(); 
		String ExpectedLabelPwdConf = "Confirm Password";
		Assert.assertEquals(ActualLabelPwdConf, ExpectedLabelPwdConf);
		System.out.println("Confirm Password Fields Actual Label is: " + ActualLabelPwdConf + ", " + "\nConfirm Password Fields Expected Label is: " + ExpectedLabelPwdConf + "\n");

		//2.5.2. Clear the "Confirm Password" field from the previous entries, just in case if any
		driver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).clear();

		//2.5.3. Enter value into "Confirm Password" field
		driver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).sendKeys("password1");	

	}

	@Test (priority = 3, enabled=true)
	public void WT_SU_01_00_00_03_Test() {

		//1. and 2. Assert that "Sign up" btn is visible and enabled on the Registration Form at /users/sign_up page
		WebElement suBnt = driver.findElement(By.xpath(".//*[@id='new_user']/div[6]/div[2]/input"));
		Assert.assertEquals(true, suBnt.isDisplayed());
		Assert.assertEquals(true, suBnt.isEnabled());
		//driver.findElement(By.xpath(".//*[@id='new_user']/div[6]/div[2]/input")).isDisplayed();
		//driver.findElement(By.xpath(".//*[@id='new_user']/div[6]/div[2]/input")).isEnabled();

		//3. Assert that "Sign up" buttons text is "Sign up".
		String ActualText1 = driver.findElement(By.xpath(".//*[@id='new_user']/div[6]/div[2]/input")).getAttribute("value").toString(); 
		String ExpectedText1 = "Sign up";
		Assert.assertEquals(ActualText1, ExpectedText1);
		System.out.println("Sign up btn Actual Text is: " + ActualText1 + ", " + "\nSign up btn  Expected Text is: " + ExpectedText1 + "\n");

		//4. Click at "Sign up" btn
		driver.findElement(By.xpath(".//*[@id='new_user']/div[6]/div[2]/input")).click();	
	}

	@Test (priority = 4, enabled=true)
	public void EmailTooltip() throws Exception {
		Actions ToolTip1 = new Actions(driver);
		WebElement Element1 = driver.findElement(By.xpath(".//*[@id='user_email']"));
		Thread.sleep(2000);
		ToolTip1.clickAndHold(Element1).perform();
		
		// Use Firebug, click on DOM, when clicked on email element, enter value "%^&*()$#", 
		//- under DOM ( Window > input#use...-control), shown attribute validationMessage with value "Please enter an email address."
		String ToolTipText = Element1.getAttribute("validationMessage");
		
		Assert.assertEquals(ToolTipText, "Please enter an email address.");
		
		System.out.println("Tooltip value is: " + ToolTipText);		
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}

}
