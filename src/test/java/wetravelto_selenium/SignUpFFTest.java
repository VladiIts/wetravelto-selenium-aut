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

public class SignUpFFTest {

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
	 *  "Sign up with Email" Test cases 
	 */

	@Test (priority = 0, enabled=true)	
	public void WT_SU_01_00_00_00_Test() {

		//1. and 2. Check if webelement "Sign Up" Link on LP page is visible and enabled
		WebElement suLnk = driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a"));
		Assert.assertEquals(true, suLnk.isDisplayed());
		Assert.assertEquals(true, suLnk.isEnabled());

		// 3. Find webelement "Sign Up" Link on LP page, and click on it
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).click();

		//4. Verify that "Sign Up" links landing page is loaded in the browser.
		String ActualUrl1 = driver.getCurrentUrl();
		String expectedURL1 = "https://ww2.wetravel.to/users/sign_up";
		Assert.assertEquals(ActualUrl1, expectedURL1);

	}

	@Test (priority = 1, enabled=true)
	public void WT_SU_01_00_00_01_Test() {

		//1. and 2. Assert that "Sign Up with Email" button element is visible and enabled		
		WebElement suweBnt = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a"));
		Assert.assertEquals(true, suweBnt.isDisplayed());
		Assert.assertEquals(true, suweBnt.isEnabled());

		//2. Verify that "Sign Up with Email" button has "Sign Up with Email" text on it
		String btntext1 = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/p[1]/a")).getText();	
		String expectedText1 = "Sign up with Email";
		Assert.assertEquals(btntext1, expectedText1);
		System.out.println("btntext1 - " + btntext1 + ", " + "\nExpectedText1 - " + expectedText1 );

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

		//2.2.1. Assert that LN fields label is "Last Name".
		String ActualLabelLN = driver.findElement(By.xpath(".//*[@id='user_lastname']")).getAttribute("placeholder").toString(); 
		String ExpectedLabelLN = "Last Name";
		Assert.assertEquals(ActualLabelLN, ExpectedLabelLN);
		System.out.println("LN Fields Actual Label is: " + ActualLabelLN + ", " + "\nLN Fields Expected Label is: " + ExpectedLabelLN + "\n");

		//2.2.2.Clear the LN field from previous entries, just in case if any
		driver.findElement(By.xpath(".//*[@id='user_lastname']")).clear();

		//2.2.3. Enter value into LN field
		driver.findElement(By.xpath(".//*[@id='user_lastname']")).sendKeys("1112");

		//2.3. "Email" field is visible and enabled on the Registration Form at /users/sign_up page
		WebElement emailFld = driver.findElement(By.xpath(".//*[@id='user_email']"));
		Assert.assertEquals(true, emailFld.isDisplayed());
		Assert.assertEquals(true, emailFld.isEnabled());		

		//2.3.1. Assert that "Email" fields label is "Email".
		String ActualLabelEmail = driver.findElement(By.xpath(".//*[@id='user_email']")).getAttribute("placeholder").toString(); 
		String ExpectedLabelEmail = "Email";
		Assert.assertEquals(ActualLabelEmail, ExpectedLabelEmail);
		System.out.println("Email Fields Actual Label is: " + ActualLabelEmail + ", " + "\nEmail Fields Expected Label is: " + ExpectedLabelEmail + "\n");

		//2.3.2.Clear the "Email" field from previous entries, just in case if any
		driver.findElement(By.xpath(".//*[@id='user_email']")).clear();

		//2.3.3. Enter value into "Email" field
		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("traveler1112aut@mailinator.com");

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

		//3. Assert that "Sign up" buttons text is "Sign up".
		String ActualText1 = driver.findElement(By.xpath(".//*[@id='new_user']/div[6]/div[2]/input")).getAttribute("value").toString(); 
		String ExpectedText1 = "Sign up";
		Assert.assertEquals(ActualText1, ExpectedText1);
		System.out.println("Sign up btn Actual Text is: " + ActualText1 + ", " + "\nSign up btn  Expected Text is: " + ExpectedText1 + "\n");

		//4. Click at "Sign up" btn
		driver.findElement(By.xpath(".//*[@id='new_user']/div[6]/div[2]/input")).click();	

		//5. Assert that click on Sign Up button leads to the page https://ww2.wetravel.to/ is loaded in the browser
		String ActualUrl1 = driver.getCurrentUrl();
		String ExpectedUrl1 = "https://ww2.wetravel.to/";
		Assert.assertEquals(ActualUrl1, ExpectedUrl1);
		System.out.println("LP page Actual URL is: " +  ActualUrl1 + ", " + "\nLP page   Expected URL is: " + ExpectedUrl1 + "\n");

		//6. Verify that webelement  alert "Welcome! You have signed up successfully." is visible and enabled on the page
		WebElement wsuAlert = driver.findElement(By.xpath("html/body/div[1]"));
		Assert.assertEquals(true, wsuAlert.isDisplayed());
		Assert.assertEquals(true, wsuAlert.isEnabled());
		//driver.findElement(By.xpath("html/body/div[1]")).isDisplayed();

		//7. Assert that alert with text "Welcome! You have signed up successfully." 
		//   is show on the LP page right after user clicked Sign Up button. 
		String alertText1 = driver.findElement(By.xpath("html/body/div[1]")).getText();
		String expectedText1 = "Welcome! You have signed up successfully.";
		Assert.assertEquals(alertText1, expectedText1);
		System.out.println("Actual Allert Message is: " +  alertText1 + ", " + "\nExpected Alert Message is: " + expectedText1 + "\n");

		//8. Click on user profile drop-down
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/p")).click();		

		//9. In opened drop-down menu, click at "Sing Out" menu
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/ul/li[8]/a")).click();

	}

	//*[contains(., 'Please enter an email address.')]
	//==========================================================================================================================================
	/*
	 *  "Sign up with Facebook" Test cases 
	 */

	@Test (priority = 4, enabled=false)	
	public void WT_SU_02_00_00_Test() {

		// Get to LP page
		driver.get(url); 

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

	@Test (priority = 5, enabled=false)
	public void WT_SU_02_00_01_Test() {

		//1. and 2. Assert that "Facebook" button element is visible and enabled		
		WebElement sufbBnt = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[1]/a"));
		Assert.assertEquals(true, sufbBnt.isDisplayed());
		Assert.assertEquals(true, sufbBnt.isEnabled());

		//3. Assert that "Facebook" button has "Facebook" text on it
		String btntext1 = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[1]/a")).getText();	
		String expectedText1 = "Facebook";
		Assert.assertEquals(btntext1, expectedText1);
		System.out.println("btntext1 - " + btntext1 + ", " + "\nExpectedText1 - " + expectedText1 );

	}

	@Test (priority = 6, enabled=false)
	// -- will use those parameters below  in the multibrowser tests - "SignUpTest.java" (will add parameters to testng.xml file as well), instead of hard coding
	//@Parameters({ "fbEmail", "fbPassword" }) 
	public void WT_SU_02_00_02_Test() throws Throwable {

		// 1. Click on "Facebook" button on /sign_up/ page
		driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[1]/a")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. Assert that Facebook login page with URL like "https://www.facebook.com/login.php" is shown on  in the browser 
		String url1 = driver.getCurrentUrl();
		String expectedURL1 = "https://www.facebook.com/login.php";			
		Assert.assertTrue(url1.contains(expectedURL1), "Failure message");
		System.out.println("\nActual URL is: " + url1 + "\nExpexted URL is :" + expectedURL1);

		// 3. Clear Email input
		driver.findElement(By.xpath(".//*[@id='email']")).clear();

		//4. Enter valid Facebook email value into Email input
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("wetravelqa3@gmail.com");

		// 5. Clear Password input
		driver.findElement(By.xpath(".//*[@id='pass']")).clear();

		//6. Enter valid Facebook password value into Password input
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("wetravelqa");

		Thread.sleep(2000);

		//7. Click on Log In button on Facebook log in page				
		driver.findElement(By.xpath(".//*[@id='u_0_2']")).click();		
		Thread.sleep(5000);

		// 8. Click on OK btn at Continue as Wetravel dialog
		driver.findElement(By.xpath(".//*[@id='platformDialogForm']/div[2]/table/tbody/tr/td[2]/button[2]")).click();
		Thread.sleep(5000);		
		// 9. Assert that click on OK button leads to the page https://ww2.wetravel.to/#_=_ is loaded in the browser
		String ActualUrl1=driver.getCurrentUrl();
		String ExpectedUrl1 = "https://ww2.wetravel.to/#_=_";
		Assert.assertEquals(ActualUrl1, ExpectedUrl1);
		System.out.println("LP page Actual URL is: " +  ActualUrl1 + ", " + "\nLP page   Expected URL is: " + ExpectedUrl1 + "\n");

		//10. and 11. Assert that webelement  alert "Successfully authenticated from Facebook account." is visible and enabled on the page
		WebElement wsuAlert = driver.findElement(By.xpath("html/body/div[1]"));
		Assert.assertEquals(true, wsuAlert.isDisplayed());
		Assert.assertEquals(true, wsuAlert.isEnabled());

		//12. Assert that alert "Successfully authenticated from Facebook account." is shown on the page https://ww2.wetravel.to/#_=_
		String alertText2 = driver.findElement(By.xpath("//html/body/div[1]")).getText();
		String expectedText2 = "Successfully authenticated from Facebook account.";
		Assert.assertEquals(alertText2, expectedText2);
		System.out.println("Actual Allert Message is: " +  alertText2 + ", " + "\nExpected Alert Message is: " + expectedText2 + "\n");		

		//13. Click on user profile drop-down
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/p")).click();		

		//14. In opened up drop-down menu, click at "Sing Out" menu
		driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/ul/li[8]/a")).click();
		
	}
		
		//==========================================================================================================================================
		/*
		 *  "Sign up with Google+" Test cases 
		 */

		@Test (priority = 7, enabled=true)	
		public void WT_SU_03_00_00_Test() {

			//0. Navigate to LP PAGE https://ww2.wetravel.to/ in the browser
			driver.get(url); 

			//1. and 2. Assert if webelement "Sign Up" Link on LP page is visible and enabled
			WebElement suLnk = driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a"));
			Assert.assertEquals(true, suLnk.isDisplayed());
			Assert.assertEquals(true, suLnk.isEnabled());
			
			// 3. Find webelement "Sign Up" Link on LP page, and click on it
			driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).click();

			//4. Verify that "Sign Up" links landing page is loaded in the browser.
			String ActualUrl1 = driver.getCurrentUrl();
			String expectedURL1 = "https://ww2.wetravel.to/users/sign_up";
			Assert.assertEquals(ActualUrl1, expectedURL1);
			System.out.println("\nActual URL is: " + ActualUrl1 + "\nExpexted URL is :" + expectedURL1);
		}		

		@Test (priority = 8, enabled=true)
		public void WT_SU_03_00_01_Test() {

			//1. and 2. Assert that "Google+" button element is visible and enabled	
			WebElement sugpBnt = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/a"));
			Assert.assertEquals(true, sugpBnt.isDisplayed());
			Assert.assertEquals(true, sugpBnt.isEnabled());
			
			//3. Assert that "Google+" button has "Google+" text on it
			String btntext2 = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/a")).getText();	
			String expectedText2 = "Google+";
			Assert.assertEquals(btntext2, expectedText2);
			System.out.println("btntext2 - " + btntext2 + ", " + "\nExpectedText2 - " + expectedText2 );

		}

		@Test (priority = 9, enabled=true)
		// -- will use those parameters below  in the multibrowser tests - "SignUpTest.java" (will add parameters to testng.xml file as well), instead of hard coding
		//@Parameters({ "gpEmail", "gpPassword" }) 
		public void WT_SU_03_00_02_Test() throws Throwable {

			// 1. Click on "Google+" button on /sign_up/ page
			driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/a")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 2. Assert that Sign in with your Google Account page with URL like "https://accounts.google.com/ServiceLogin?" is shown on  in the browser 
			String url1 = driver.getCurrentUrl();
			String expectedURL1 = "https://accounts.google.com/ServiceLogin?";			
			Assert.assertTrue(url1.contains(expectedURL1), "Failure message");
			System.out.println("\nActual URL is: " + url1 + "\nExpexted URL is :" + expectedURL1);

			// 3. Clear Email input
			driver.findElement(By.xpath(".//*[@id='Email']")).clear();

			//4. Enter valid Gmail email value into Email input
			driver.findElement(By.xpath(".//*[@id='Email']")).sendKeys("wetravelqa3@gmail.com");
			
			//5. Click Next btn
			driver.findElement(By.xpath(".//*[@id='next']")).click();
			Thread.sleep(2000);

			// 6. Clear Password input
			driver.findElement(By.xpath(".//*[@id='Passwd']")).clear();

			//7. Enter valid Gmail password value into Password input
			driver.findElement(By.xpath(".//*[@id='Passwd']")).sendKeys("wetravelqa");
			
			//8. Click on "Sign In" button on https://accounts.google.com/ServiceLogin page				
			driver.findElement(By.xpath(".//*[@id='signIn']")).click();				
			Thread.sleep(3000);

			// 9. Click on "Allow" btn at "Wetravel.to.dev would like to:" submit_approve_access authorization form
			driver.findElement(By.xpath(".//*[@id='submit_approve_access']")).click();
			Thread.sleep(5000);		
			
			// 10. Assert that click on Allow button leads to the page https://ww2.wetravel.to is finally loaded in the browser
			String ActualUrl1=driver.getCurrentUrl();				
			String ExpectedURL1 = "https://ww2.wetravel.to";			
			Assert.assertTrue(ActualUrl1.contains(expectedURL1), "Failure message");
			System.out.println("\nActual URL is: " + ActualUrl1 + "\nExpexted URL is :" + ExpectedURL1);

			//11. and 12. Assert that webelement  alert "Successfully authenticated from Google plus account." is visible and enabled on the page
			WebElement wsuAlert = driver.findElement(By.xpath("html/body/div[1]"));
			Assert.assertEquals(true, wsuAlert.isDisplayed());
			Assert.assertEquals(true, wsuAlert.isEnabled());

			//13. Assert that alert "Successfully authenticated from Google plus account." is shown on the page https://ww2.wetravel.to
			String alertText2 = driver.findElement(By.xpath("//html/body/div[1]")).getText();
			String expectedText2 = "Successfully authenticated from Google plus account.";
			Assert.assertEquals(alertText2, expectedText2);
			System.out.println("Actual Allert Message is: " +  alertText2 + ", " + "\nExpected Alert Message is: " + expectedText2 + "\n");		

			//14. Click on user profile drop-down
			driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/p")).click();		

			//15. In opened up drop-down menu, click at "Sing Out" menu
			driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/ul/li[8]/a")).click();

	}	
		
		/*
		 *  "Sign up with "Linkedin" Test cases (Can't be automated since "captcha" verification ON THE WAY
		 */

		@Test (priority = 10, enabled=false)	
		public void WT_SU_04_00_00_Test() {

			//0. Get to LP page
			driver.get(url); 

			//1. and 2. Check if webelement "Sign Up" Link on LP page is visible and enabled
			WebElement suLnk = driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a"));
			Assert.assertEquals(true, suLnk.isDisplayed());
			Assert.assertEquals(true, suLnk.isEnabled());
			
			// 3. Find webelement "Sign Up" Link on LP page, and click on it
			driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[2]/a")).click();

			//4. Verify that "Sign Up" links landing page is loaded in the browser.
			String ActualUrl1 = driver.getCurrentUrl();
			String expectedURL1 = "https://ww2.wetravel.to/users/sign_up";
			Assert.assertEquals(ActualUrl1, expectedURL1);

		}		

		@Test (priority = 11, enabled=false)
		public void WT_SU_04_00_01_Test() {

			//1. and 2. Assert that "Linkedin" button element is visible and enabled		
			WebElement suliBnt = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[3]/a"));
			Assert.assertEquals(true, suliBnt.isDisplayed());
			Assert.assertEquals(true, suliBnt.isEnabled());

			//3. Assert that "Linkedin" button has "Linkedin" text on it
			String btntext3 = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[3]/a")).getText();	
			String expectedText3 = "Linkedin";
			Assert.assertEquals(btntext3, expectedText3);
			System.out.println("btntext3 - " + btntext3 + ", " + "\nExpectedText3 - " + expectedText3 );

		}

		@Test (priority = 12, enabled=false)
		// -- will use those parameters below  in the multibrowser tests - "SignUpTest.java" (will add parameters to testng.xml file as well), instead of hard coding
		//@Parameters({ "liEmail", "liPassword" }) 
		public void WT_SU_04_00_02_Test() throws Throwable {

			// 1. Click on "Linkedin" button on /sign_up/ page
			driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div[3]/a")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 2. Assert that Linkedin login page with URL like "https://www.linkedin.com/uas/oauth/authorize" is shown in the browser ("Sign in to Linkedin and allow access:" log in form)
			String url1 = driver.getCurrentUrl();
			String expectedURL1 = "https://www.linkedin.com/uas/oauth/authorize";			
			Assert.assertTrue(url1.contains(expectedURL1), "Failure message");
			System.out.println("\nActual URL is: " + url1 + "\nExpexted URL is :" + expectedURL1);

			// 3. Clear Email input
			driver.findElement(By.xpath(".//*[@id='session_key-oauthAuthorizeForm']")).clear();

			//4. Enter valid Linkedin email value into Email input
			driver.findElement(By.xpath(".//*[@id='session_key-oauthAuthorizeForm']")).sendKeys("wetravelqa1@gmail.com");

			// 5. Clear Password input
			driver.findElement(By.xpath(".//*[@id='session_password-oauthAuthorizeForm']")).clear();

			//6. Enter valid Linkedin password value into Password input
			driver.findElement(By.xpath(".//*[@id='session_password-oauthAuthorizeForm']")).sendKeys("wetravelqa");

			Thread.sleep(5000);

			//7. Click on "Allow Access" button on Linkedin log in page				
			driver.findElement(By.xpath(".//*[@id='body']/div/form/div[2]/ul/li[1]/input")).click();		
			Thread.sleep(5000);

			// --! --  CAN NOT AUTOMATE ANY FATHER, since got https://www.linkedin.com/uas/connect-captcha# page, with contain captcha verification  ON THE WAY
			// Message: "We need to verify you're not a robot! Please complete this security check:" (Number shown as a picture, have to recognize and type the number in.
			
			// 8. Click on OK btn at Continue as Wetravel dialog
			driver.findElement(By.xpath(".//*[@id='platformDialogForm']/div[2]/table/tbody/tr/td[2]/button[2]")).click();
			Thread.sleep(5000);		
			// 9. Assert that click on OK button leads to the page https://ww2.wetravel.to/#_=_ is loaded in the browser
			String ActualUrl1=driver.getCurrentUrl();
			String ExpectedUrl1 = "https://ww2.wetravel.to/#_=_";
			Assert.assertEquals(ActualUrl1, ExpectedUrl1);
			System.out.println("LP page Actual URL is: " +  ActualUrl1 + ", " + "\nLP page   Expected URL is: " + ExpectedUrl1 + "\n");

			//10. and 11. Assert that webelement  alert "Successfully authenticated from Facebook account." is visible and enabled on the page
			WebElement wsuAlert = driver.findElement(By.xpath("html/body/div[1]"));
			Assert.assertEquals(true, wsuAlert.isDisplayed());
			Assert.assertEquals(true, wsuAlert.isEnabled());

			//12. Assert that alert "Successfully authenticated from Facebook account." is shown on the page https://ww2.wetravel.to/#_=_
			String alertText2 = driver.findElement(By.xpath("//html/body/div[1]")).getText();
			String expectedText2 = "Successfully authenticated from Facebook account.";
			Assert.assertEquals(alertText2, expectedText2);
			System.out.println("Actual Allert Message is: " +  alertText2 + ", " + "\nExpected Alert Message is: " + expectedText2 + "\n");		

			//13. Click on user profile drop-down
			driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/p")).click();		

			//14. In opened up drop-down menu, click at "Sing Out" menu
			driver.findElement(By.xpath(".//*[@id='travel-collapse']/ul[2]/li[1]/div/ul/li[8]/a")).click();
			
		}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}

}

