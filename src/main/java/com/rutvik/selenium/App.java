package com.rutvik.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://admin.posiv.org.uk/#/");

        Login login = new Login(driver);
        login.loginAsAdmin("admin@posiv.com", "Admin@111");

        Thread.sleep(3000);

        Dashboard dashboard = new Dashboard(driver);
        dashboard.testDashboard();

        Thread.sleep(3000);
        driver.quit();
    }
}
