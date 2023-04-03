package com.yogaclasswithomnify.org;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;
import java.io.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CreateNewClassTest {
	
	
	// TC01 = Verify that the admin can successfully create a new class with all the required features, including Title, Description, Class Color, Image, Class Details, Class Schedule, and Booking Window, as an admin user to create a class called “Yoga class with Omnify”."

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // set up WebDriver and open the website
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.example.com/admin-login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // log in as admin
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("login-btn")).click();
    }

    @AfterClass
    public void tearDown() {
        // close the browser
        driver.quit();
    }

    @Test
    public void testCreateNewClass() throws IOException {
        // navigate to "New Class" page
        driver.findElement(By.id("new-class")).click();

        // enter class information
        driver.findElement(By.id("title")).sendKeys("Yoga class with Omnify");
        driver.findElement(By.id("description")).sendKeys("A relaxing yoga class for all levels");
        driver.findElement(By.id("class-color")).sendKeys("#ff0000");
        // upload an image file
        WebElement fileInput = driver.findElement(By.id("image"));
        fileInput.sendKeys("path/to/image.jpg");
        // select "Online" for Class Details
        Select classDetails = new Select(driver.findElement(By.id("class-details")));
        classDetails.selectByVisibleText("Online");
        // select "Free" for Service Type
        Select serviceType = new Select(driver.findElement(By.id("service-type")));
        serviceType.selectByVisibleText("Free");

        // add class schedule
        driver.findElement(By.id("add-schedule-btn")).click();
        WebElement scheduleForm = driver.findElement(By.id("schedule-form"));
        scheduleForm.findElement(By.id("day-of-week")).sendKeys("Monday");
        scheduleForm.findElement(By.id("start-date")).sendKeys("2023-04-10");
        scheduleForm.findElement(By.id("start-time")).sendKeys("09:00 AM");
        scheduleForm.findElement(By.id("end-date")).sendKeys("2023-04-10");
        scheduleForm.findElement(By.id("end-time")).sendKeys("10:00 AM");

        // set booking window restrictions
        driver.findElement(By.id("booking-window-open")).sendKeys("3");
        driver.findElement(By.id("booking-window-close")).sendKeys("1");

        // submit the form to create the new class
        driver.findElement(By.id("create-class-btn")).click();

        // verify that the new class is successfully created
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-message")));
        Assert.assertTrue(successMessage.isDisplayed(), "New class was not created successfully.");
    }
}