package com.yogaclasswithomnify.org;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddClassLocationsTest {
    private WebDriver driver;
    private String baseUrl = "https://example.com/";
    
    @BeforeClass
    public void setUp() {
        // Set the path of the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        
        // Create a new ChromeDriver instance
        driver = new ChromeDriver();
        
        // Navigate to the base URL
        driver.get(baseUrl);
        
        // Log in as an admin user
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        
        usernameInput.sendKeys("admin");
        passwordInput.sendKeys("password");
        loginButton.click();
    }
    
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
    
    @Test
    public void testAddClassLocations() {
        // Click on the "Create Class" button
        WebElement createClassButton = driver.findElement(By.xpath("//button[contains(text(),'Create Class')]"));
        createClassButton.click();
        
        // Fill in the class details
        WebElement titleInput = driver.findElement(By.id("title"));
        WebElement descriptionInput = driver.findElement(By.id("description"));
        WebElement classColorInput = driver.findElement(By.id("color"));
        WebElement imageInput = driver.findElement(By.id("image"));
        WebElement locationInput = driver.findElement(By.id("location"));
        WebElement onlineOption = driver.findElement(By.id("online"));
        WebElement offlineOption = driver.findElement(By.id("offline"));
        WebElement trainerInput = driver.findElement(By.id("trainer"));
        WebElement serviceTypeDropdown = driver.findElement(By.id("serviceType"));
        WebElement freeOption = driver.findElement(By.xpath("//option[contains(text(),'Free')]"));
        WebElement paidOption = driver.findElement(By.xpath("//option[contains(text(),'Paid')]"));
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        
        titleInput.sendKeys("Yoga class with Omnify");
        descriptionInput.sendKeys("This is a yoga class with Omnify.");
        classColorInput.sendKeys("#123456");
        imageInput.sendKeys("/path/to/image.jpg");
        locationInput.sendKeys("Online");
        onlineOption.click();
        offlineOption.click();
        trainerInput.sendKeys("John Doe");
        serviceTypeDropdown.click();
        paidOption.click();
        saveButton.click();
        
        // Check that the class was created successfully
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Class created successfully.')]"));
        Assert.assertEquals(successMessage.getText(), "Class created successfully.", "Class creation failed");
        
        // Check that the class has multiple locations
        WebElement classLocations = driver.findElement(By.xpath("//div[contains(@class,'class-locations')]"));
        Assert.assertTrue(classLocations.getText().contains("Online"), "Online location not found");
        Assert.assertTrue(classLocations.getText().contains("Offline"), "Offline location not found");
    }
}