package com.rutvik.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://admin.posiv.org.uk/#/");

        // ========== Login ==========
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("admin@posiv.com");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Admin@111");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[translate(normalize-space(text()), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ') = 'LOG IN']")
        ));
        loginButton.click();

        Thread.sleep(3000); // wait after login

        // ========== Use Dashboard ==========
        Dashboard dashboard = new Dashboard(driver);
        dashboard.testDashboard();

        Thread.sleep(3000);
        driver.quit();
    }
}
