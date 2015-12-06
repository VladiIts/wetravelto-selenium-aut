package com.wetravelto_selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helper {
	private static WebDriver driver;

	public static WebElement findElement(By locator) {
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException notFound) {
			return null;
		}
	}

	// initialize Helper with your driver on startup
	public static void init(WebDriver yourDriver) {
		driver = yourDriver;
	}
}

