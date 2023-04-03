package com.yogaclasswithomnify.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClassCreationTest {
    private WebDriver driver;
    private String baseUrl = "https://example.com/";
    
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        
        // perform login as admin
        // ...
    }
    
    @Test
    public void testAddTrainer() {
        // navigate to create new class page
        driver.findElement(By.linkText("Create New Class")).click();
        
        // enter class details
        driver.findElement(By.id("title")).sendKeys("Yoga class with Omnify");
        driver.findElement(By.id("description")).sendKeys("Join our yoga class and improve your health and well-being");
        driver.findElement(By.id("class-color")).sendKeys("blue");
        // add image
        // ...
        
        // add class details
        driver.findElement(By.id("location-online")).click();
        driver.findElement(By.id("location-offline")).click();
        // select trainer
        WebElement trainerDropdown = driver.findElement(By.id("trainer"));
        trainerDropdown.click();
        WebElement trainerOption = trainerDropdown.findElement(By.xpath("//option[text()='John Doe']"));
        trainerOption.click();
        // select service type
        driver.findElement(By.id("service-type-free")).click();
        
        // add class schedule
        driver.findElement(By.id("class-schedule-day")).sendKeys("Monday");
        driver.findElement(By.id("class-schedule-start-date")).sendKeys("2023-04-10");
        driver.findElement(By.id("class-schedule-start-time")).sendKeys("09:00 AM");
        driver.findElement(By.id("class-schedule-end-date")).sendKeys("2023-04-10");
        driver.findElement(By.id("class-schedule-end-time")).sendKeys("10:00 AM");
        
        // add booking window
        driver.findElement(By.id("booking-window-open-date")).sendKeys("2023-04-03");
        driver.findElement(By.id("booking-window-close-date")).sendKeys("2023-04-09");
        
        // save the class
        driver.findElement(By.id("save-class")).click();
        
        // verify trainer information is displayed on the class page
        WebElement classPage = driver.findElement(By.xpath("//h1[text()='Yoga class with Omnify']"));
        classPage.click();
        WebElement trainerInfo = classPage.findElement(By.xpath("//p[text()='Trainer: John Doe']"));
        Assert.assertTrue(trainerInfo.isDisplayed());
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
