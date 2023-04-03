package com.yogaclasswithomnify.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//TC_10 =  Verify that the system enforces the booking window restrictions, such as not allowing bookings before the booking window opens or after it closes.

public class BookingWindow {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testBookingWindow() {
        // Navigate to the class booking page
        driver.get("https://www.example.com/book-class");

        // Choose a class that has a booking window of 3 days
        WebElement classElement = driver.findElement(By.xpath("//div[@class='class' and @data-booking-window='3']"));
        classElement.click();

        // Get the start date and time of the class
        WebElement startElement = driver.findElement(By.xpath("//div[@class='class-details']//span[@class='start-time']"));
        String startString = startElement.getText();
        LocalDateTime startDateTime = LocalDateTime.parse(startString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Calculate the booking window
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime bookingWindowStart = startDateTime.minusDays(3);
        LocalDateTime bookingWindowEnd = startDateTime.minusHours(1);

        // Choose a date and time within the booking window
        WebElement dateElement = driver.findElement(By.xpath("//div[@class='booking-calendar']//button[@data-date='" + bookingWindowStart.format(DateTimeFormatter.ISO_LOCAL_DATE) + "']"));
        dateElement.click();

        WebElement timeElement = driver.findElement(By.xpath("//div[@class='booking-time-slots']//button[@data-time='" + bookingWindowStart.format(DateTimeFormatter.ISO_LOCAL_TIME) + "']"));
        timeElement.click();

        // Submit the booking form
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='book-button']"));
        submitButton.click();

        // Verify that the booking was successful
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='confirmation-message']"));
        Assert.assertEquals(confirmationMessage.getText(), "Booking successful!");

        // Choose a date and time outside the booking window
        WebElement invalidDateElement = driver.findElement(By.xpath("//div[@class='booking-calendar']//button[@data-date='" + bookingWindowEnd.format(DateTimeFormatter.ISO_LOCAL_DATE) + "']"));
        invalidDateElement.click();

        WebElement invalidTimeElement = driver.findElement(By.xpath("//div[@class='booking-time-slots']//button[@data-time='" + bookingWindowEnd.format(DateTimeFormatter.ISO_LOCAL_TIME) + "']"));
        invalidTimeElement.click();

        // Submit the booking form
        submitButton.click();

        // Verify that the booking was not successful
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message']"));
        Assert.assertEquals(errorMessage.getText(), "Booking window is closed.");
    }

    @AfterTest
    public void tearDown() {
        // Quit the driver
        driver.quit();
    }
}
