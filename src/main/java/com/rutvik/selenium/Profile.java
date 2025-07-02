package com.rutvik.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Profile {
    private WebDriver driver;
    private WebDriverWait wait;

    public Profile(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void runProfileFlow() throws InterruptedException {
        try {
            openMenu();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Edit Profile')]"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Save changes')]"))).click();
            System.out.println("- Profile updated");

            changePassword("Admin@111", "Admin@222");
            System.out.println("- Password changed to Admin@222");

            changePassword("Admin@222", "Admin@111");
            System.out.println("- Password changed to Admin@111");

            logout();
            System.out.println("- Logged out");

            loginAgain();
            System.out.println("- Logged in again");

             System.out.println("✅ 2 . Profile");

        } catch (Exception e) {
            System.out.println("❌ Error occurred:");
            e.printStackTrace();
        }
    }

    private void openMenu() {
        
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-container")));
        } catch (Exception ignored) {}

        wait.until(ExpectedConditions.elementToBeClickable(By.id("headlessui-menu-button-1"))).click();
    }

    private void changePassword(String oldPwd, String newPwd) throws InterruptedException {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Change Password')]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter old password']"))).sendKeys(oldPwd);
        driver.findElement(By.xpath("//input[@placeholder='Enter new password']")).sendKeys(newPwd);
        driver.findElement(By.xpath("//input[@placeholder='Enter confirm password']")).sendKeys(newPwd);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Save changes')]"))).click();
        Thread.sleep(2000);
    }

    private void logout() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Log Out')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Yes, Log Me Out!')]"))).click();
    }

    private void loginAgain() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Email']"))).sendKeys("admin@posiv.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Admin@111");
        driver.findElement(By.xpath("//*[contains(text(),'Log in')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Dashboard')]"))).click();
    }
}
