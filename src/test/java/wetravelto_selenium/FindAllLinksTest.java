package wetravelto_selenium;

import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

public class FindAllLinksTest{
	
	public static WebDriver driver = new FirefoxDriver();
	public static String BaseUrl = "https://ww2.wetravel.to/";
	public static SoftAssert sa = new SoftAssert();
	
	@Test
	public static void allLinks() throws Exception {

		//WebDriver driver = new FirefoxDriver();
		driver.get(BaseUrl);
		//driver.findElement(By.id("lst-ib")).sendKeys("Vladimir Itsekson");
		//driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		FileOutputStream fos = new FileOutputStream("C:/workspace_wetravelto/Find_allWebLinks_on_LP_page.xlsx ");
		XSSFWorkbook wb = new XSSFWorkbook();
		//XSSFSheet s = wb.createSheet("allElements");
		XSSFSheet s = wb.createSheet("allLinks");
		//java.util.List<WebElement> links = driver.findElements(By.xpath("//*"));

		//Find total No of links on page and print Into Excel file
		List<WebElement> total_links = driver.findElements(By.tagName("a"));
		//System.out.println("Total Number of links found on page = " + total_links.size());

		// Set current date 
		Date date = new Date();

		//will store Total Number of links found on the page in excel at given path
		Row row1 = s.createRow(1);
		row1.createCell(1).setCellValue("Total no. of links found on the page - " + BaseUrl + " = "  + total_links.size() + ";");
		row1.createCell(3).setCellValue("Created on " + date.toString());

		//for loop to open all links one by one to check and get response code, and write data in excel at given path.
		boolean isValid = false;
		for (int j = 0; j < total_links.size(); j++) {
			String url = total_links.get(j).getAttribute("href");
			String text = total_links.get(j).getText();
			boolean LinkIsVisible = total_links.get(j).isDisplayed();
			boolean LinkIsEnabled = total_links.get(j).isEnabled();

			if (url != null) {

				//Call getResponseCode function for each URL to check response code.
				isValid = getResponseCode(url);

				//Call ResponseCode function for each URL to get response code.
				int resp_Code1 = ResponseCode(url);	
				
				// Set expected response code for soft assertion
				int resp_Code1Expected = 200;
				
				//Print message based on value of isValid which Is returned by getResponseCode function.
				if (isValid) {
					for (int i = j; i<total_links.size();i++)

					{
						Row row2 = s.createRow(i+3);	
						row2.createCell(1).setCellValue("Response Code Is : "+ resp_Code1);
						row2.createCell(2).setCellValue("Valid Link: ");	
						row2.createCell(3).setCellValue(url);
						row2.createCell(4).setCellValue("Link Text: ");
						row2.createCell(5).setCellValue(text);
						row2.createCell(6).setCellValue("Is Link visible = " + LinkIsVisible);
						row2.createCell(7).setCellValue("Is Link enabled = " + LinkIsEnabled);
					}
					//System.out.println("Valid Link: " + url + "\nLink Text: "+ text);
					//System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
					//System.out.println();
				} else {
					
					//Asserting if response code is 200 (OK), 
					//if response code != 200, when some links on the page are Broken, and Test will Fail
					sa.assertEquals(resp_Code1, resp_Code1Expected);
					
					Row row3 = s.createRow(j+3);
					row3.createCell(1).setCellValue("Response Code Is : "+ resp_Code1);
					row3.createCell(2).setCellValue("Broken Link ------> ");
					row3.createCell(3).setCellValue(url);
					row3.createCell(4).setCellValue("Link Text: ");
					row3.createCell(5).setCellValue(text);
					row3.createCell(6).setCellValue("Is Link visible = " + LinkIsVisible);
					row3.createCell(7).setCellValue("Is Link enabled = " + LinkIsEnabled);

					//System.out.println("Broken Link ------> " + url + "\nLink Text: "+ text);
					//System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
					//System.out.println();
				}
			} else {    
				//If <a> tag do not contain href attribute and value then print this message

				//System.out.println("String null");
				//System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
				//System.out.println();
				
				//Call ResponseCode function for each URL to get response code.
				int resp_Code2 = ResponseCode(url);	
				
				// Set expected response code for soft assertion on response code
				int resp_Code2Expected = 200;
				
				sa.assertEquals(resp_Code2, resp_Code2Expected);
				
				Row row4 = s.createRow(j+3);						
				row4.createCell(2).setCellValue("String null");

				continue;
			}
		}

		wb.write(fos);
		fos.close();
		wb.close();
		driver.quit();
		
		//Soft Assertions
		//Tests don’t stop running even if an assertion condition fails, 
		//but the test itself is marked as a failed test to indicate the right result.
		//the softAssert.assertAll() does the trick. This method collates all the failures and decides whether to fail the test or not at the end. 
		//So instead of writing a custom logic, the TestNG library itself offers the facility to perform Soft Assertions in your test.
		
		sa.assertAll();

	}

	public static boolean getResponseCode(String chkurl) throws IOException {
		boolean validResponse = false;
		int resp_Code = 0;
		try {   
			//Get response code of URL

			//DefaultHttpclient is deprecated.So, we can use the below code.
			HttpClient client = HttpClientBuilder.create().build();
					// HttpGet request = new HttpGet(chkurl);
			HttpResponse response = client.execute(new HttpGet(chkurl));
			
			resp_Code = response.getStatusLine().getStatusCode();

					//HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl));
					//int resp_Code = urlresp.getStatusLine().getStatusCode();

			//	System.out.println("Response Code Is : "+ resp_Code);

			if ((resp_Code == 404) || (resp_Code == 505)) {
				validResponse = false;
			} else {
				validResponse = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return validResponse;
	}

	public static int ResponseCode(String chkurl) throws IOException {
		int resp_Code2 = 0;
		try {   
			//Get response code of URL
			//HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl));
				//resp_Code2 = urlresp.getStatusLine().getStatusCode();
			HttpClient client = HttpClientBuilder.create().build();
				// HttpGet request = new HttpGet(chkurl);
			HttpResponse response = client.execute(new HttpGet(chkurl));
	
			resp_Code2 = response.getStatusLine().getStatusCode();

			//	System.out.println("Response Code Is : "+ resp_Code);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp_Code2;
	}

}


