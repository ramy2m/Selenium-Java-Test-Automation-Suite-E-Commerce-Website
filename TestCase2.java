package FinalProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



import java.time.Duration;

public class TestCase2 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Forgot your password? ']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Reset Password']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("username"))).sendKeys("UnknownUser123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(@class,'oxd-text--danger')]")));

        Assert.assertTrue(errorMsg.getText().toLowerCase().contains("error")
                        || errorMsg.getText().toLowerCase().contains("invalid"),
                "Expected error message NOT shown for invalid username!");
    }
}
