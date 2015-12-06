package common_methods;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TakesScreenshotTest {

	public static void main(String[] args) throws IOException {
		WebDriver myTestDriver = new FirefoxDriver();
		myTestDriver.get("http://www.careerbuilder.co.in");

		File scrFile = ((TakesScreenshot)myTestDriver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File("c:\\careerbuilder.jpg"));

	    myTestDriver.quit();
	}

}