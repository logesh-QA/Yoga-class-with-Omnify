package com.baseclass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	

	public static void browser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\loges\\eclipse-workspace\\Selenium\\driver\\chromedriver.exe");

			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();

		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\loges\\eclipse-workspace\\Selenium\\driver\\geckodriver.exe");

			driver = new FirefoxDriver();
			//driver.manage().window().maximize();

		}

	}

	public static void getUrl(String url) {

		driver.get(url);
	}

	public static void getTitle() {

		String title = driver.getTitle();
		System.out.println(title);
	}

	public static void getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
	}

//	public static void screenshot(String name) throws IOException {
//
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		
//		File des = new File("C:\\Users\\loges\\eclipse-workspace\\TestNg\\ScreenShot\\" + name + ".png");
//		FileUtils.copyFile(src, des);
//	}
	
	
	public static void screenshot(String name) throws IOException {

			long nanoTime = System.nanoTime();
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File des = new File("C:\\Users\\loges\\eclipse-workspace\\TestNg\\ScreenShot\\" + name + nanoTime+".png");
			FileUtils.copyFile(src, des);
	}

	public static void selection(WebElement element, String options, String value) {

		Select s = new Select(element);

		if (options.equalsIgnoreCase("byIndex")) {

			int parseInt = Integer.parseInt(value);
			s.selectByIndex(parseInt);

		}

		else if (options.equalsIgnoreCase("byValue")) {

			s.selectByValue(value);

		}

		else if (options.equalsIgnoreCase("byVisibleText")) {

			s.selectByVisibleText(value);
		}

		else {
			
			System.out.println("invalid");
		}

	}
	
	public static void scrollByElement(WebElement element) {

		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].scrollIntoView();", element);
	}
	public static void alerts(String options) throws Exception{
		
			if(options.equalsIgnoreCase("accept")) {
				WebDriver driver1 = driver;
				Alert alert = driver1.switchTo().alert();
				alert.accept();
				
			}
			else if (options.equalsIgnoreCase("dismiss")) {
				driver.switchTo().alert().dismiss();
			} 
			else if (options.equalsIgnoreCase("sendkeys")) {
				
				driver.switchTo().alert().sendKeys("");
			}
		
		
	}
	
	public static void acceptAlert() {

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (Exception e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void clickOnElement(WebElement element) {

		element.click();
		
	}
	
	
	public void implicitWait() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	
	
	
	

}



