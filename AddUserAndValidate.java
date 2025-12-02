import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddUserAndValidate {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // --------- إعداد بيانات المستخدم الجديد ---------
        String newUsername = "ahmed" + (int)(Math.random() * 100000);
        String password = "Ramy@2025_Test";
        String employeeName = "ahmed ";
        String userRole = "Admin"; // أو "ESS"
        String status = "Enabled";

        try {
            // 1) فتح الموقع
            driver.get("http://localhost/orangehrm/orangehrm-5.8/web/index.php/auth/login");


            // 2) تسجيل الدخول كـ Admin
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("ramy2m");
            driver.findElement(By.name("password")).sendKeys("aS_d2025@");
            driver.findElement(By.tagName("button")).click();

            // 3) الدخول على Admin Module
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Admin']/ancestor::a"))).click();

            // 4) الضغط على Add User
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(., 'Add')]"))).click();

            // 5) اختيار User Role
            WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]")));
            userRoleDropdown.click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@role='option']/span[text()='" + userRole + "']"))).click();

            // 6) اختيار Status
            WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]")));
            statusDropdown.click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@role='option']/span[text()='" + status + "']"))).click();

// 7) اختيار Employee Name (auto-complete)
            WebElement employeeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Employee Name']/following::input[1]")));
            employeeInput.sendKeys(employeeName);

// انتظار ظهور الاقتراحات التي تحتوي كلمة ahmed
            WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='option']//span[contains(text(),'" + employeeName.trim() + "')]")
            ));
            suggestion.click();

            // 8) كتابة Username
            WebElement usernameInput = driver.findElement(
                    By.xpath("//label[text()='Username']/following::input[1]"));
            usernameInput.sendKeys(newUsername);

            // 9) كتابة Password و Confirm Password
            driver.findElement(By.xpath("//label[text()='Password']/following::input[1]")).sendKeys(password);
            driver.findElement(By.xpath("//label[text()='Confirm Password']/following::input[1]")).sendKeys(password);

            Thread.sleep(1000);

            // 10) الضغط على Save
            driver.findElement(By.xpath("//button[contains(., 'Save')]")).click();
//
            boolean userCreated = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'oxd-toast-content')]")
                    )
            ) != null;

            System.out.println("User created: " + userCreated);

            // 11) Logout
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='oxd-userdropdown-name']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']"))).click();

            // 12) تسجيل الدخول بالمستخدم الجديد
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(newUsername);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.tagName("button")).click();

            // 13) التحقق من نجاح تسجيل الدخول
            boolean loggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[text()='Dashboard']"))) != null;

            if (loggedIn) {
                System.out.println("🎉 SUCCESS: User was created and login worked.");
            } else {
                System.out.println("❌ ERROR: Login failed for new user.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(3000);
            driver.quit();
        }
    }
}
