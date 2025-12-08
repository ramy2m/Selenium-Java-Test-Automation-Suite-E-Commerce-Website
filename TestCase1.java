package FinalProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TestCase1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.urlContains("/dashboard/index"));
        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Support']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(text(),'Support')]")));


        driver.navigate().back();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Dashboard']")));

        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("dashboard"), "Dashboard URL is wrong after return!");
    }



    }
