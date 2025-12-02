import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class EditLicenseAndLogout {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();

        driver.get("http://localhost/orangehrm/orangehrm-5.8/web/index.php/auth/login");

        // ------------------- LOGIN -------------------
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("ramy2m");
        driver.findElement(By.name("password")).sendKeys("aS_d2025@");
        driver.findElement(By.tagName("button")).click();

        // ------------------- CLICK MY INFO -------------------
        WebElement myInfo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='My Info']")));
        js.executeScript("arguments[0].click();", myInfo);

        // ------------------- CLICK EDIT BUTTON -------------------
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h6[text()='Personal Details']/following::button[1]")));
        js.executeScript("arguments[0].click();", editBtn);

        // ------------------- ENTER RANDOM LICENSE NUMBER -------------------
        Random rand = new Random();
        int randomNum = 100000 + rand.nextInt(900000);
        String randomNumS = String.valueOf(randomNum);

        WebElement licenseField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()=\"Driver's License Number\"]/following::input")));

        // حاول sendKeys أول
        try {
            Thread.sleep(3000);
            licenseField.click(); // مهم للـ React/Vue fields
            Thread.sleep(1000);
            //licenseField.clear();

            licenseField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Thread.sleep(3000);
            licenseField.sendKeys(randomNumS);
        } catch (Exception e) {
            // لو sendKeys ما اشتغلش، استخدم JS
            Thread.sleep(3000);
            js.executeScript("arguments[0].value='" + randomNumS + "';", licenseField);
        }

        // ------------------- SAVE -------------------
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")));
        js.executeScript("arguments[0].click();", saveBtn);

        // ------------------- WAIT 3 SECONDS -------------------
        try { Thread.sleep(3000); } catch (Exception e) {}

        // ------------------- LOGOUT -------------------
        WebElement profileMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".oxd-userdropdown-tab")));
        js.executeScript("arguments[0].click();", profileMenu);

        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Logout']")));
        js.executeScript("arguments[0].click();", logoutBtn);
        Thread.sleep(2000);
        driver.quit();
    }
}
