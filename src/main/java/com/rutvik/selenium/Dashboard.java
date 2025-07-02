package com.rutvik.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard {

    private WebDriver driver;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
    }

    public void testDashboard() throws InterruptedException {
        clickDashboardItem("Total Customers");
        clickDashboardItem("Total Advertisment");
        clickDashboardItem("Total Post");
        clickDashboardItem("Total Earnings");
        clickDashboardItem("Total Contact Request");

         driver.findElement(By.linkText("Dashboard")).click();

        System.out.println("✅ 1 . Dashboard");
    }

    private void clickDashboardItem(String itemText) throws InterruptedException {
        driver.findElement(By.linkText("Dashboard")).click();
        driver.findElement(By.xpath("//*[text()='" + itemText + "']")).click();
        Thread.sleep(2000);
    }
}