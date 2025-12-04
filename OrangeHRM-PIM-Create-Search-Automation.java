package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
        import org.testng.Assert;
import org.testng.annotations.*;

        import java.time.Duration;
import java.util.List;

public class OrangeHRMTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ==========================
    // LOGIN
    // ==========================
    public void login() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // أهم سطر — إجبار Selenium ينتظر تحميل الصفحة
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        submit.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
    }

    // ============================================================
    // Test Case 1 — Add Employee + Verify Name + Verify Employee ID
    // ============================================================
    @Test(priority = 1)
    public void testAddEmployee() {

        login();

        // Go to PIM
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='PIM']"))).click();

        // IMPORTANT → انتظر لحد ما شاشة Employee Information تظهر
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[text()='Employee Information']")));

        // Add Employee
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'pim/addEmployee')]"))).click();

        String firstName = "MohamedAuto";
        String lastName = "ShaarawyAuto";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName"))).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);

        // Capture Employee ID before Save
        WebElement idField = driver.findElement(
                By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        String beforeSaveID = idField.getAttribute("value");

        // Save
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verify name in Personal Details
        String headerName = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//h6"))).getText();

        Assert.assertTrue(headerName.contains(firstName) && headerName.contains(lastName),
                "Employee name is not shown correctly!");

        // Verify Employee ID after Save
        String afterSaveID = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//label[text()='Employee Id']/../following-sibling::div/input"
                ))).getAttribute("value");

        Assert.assertEquals(afterSaveID, beforeSaveID, "Employee ID mismatch!");
    }


    // ===============================================================
    // Test Case 2 — Search with (Employment Status + SubUnit + Name)
    // ===============================================================
    @Test(priority = 2)
    public void testAdvancedSearch() {

        login();

        // Go to PIM
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='PIM']"))).click();

        // IMPORTANT → انتظر تحميل صفحة Employee Information
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[text()='Employee Information']")));

        // الآن افتح Employee List
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'pim/viewEmployeeList')]"))).click();

        // IMPORTANT → انتظر تحميل جدول البحث
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[text()='Employee Information']")));

        // Employment Status
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Employment Status']/../following-sibling::div")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Full-Time Permanent']"))).click();

        // Sub Unit
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Sub Unit']/../following-sibling::div")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Human Resources']"))).click();

        // Name Hint
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[text()='Employee Name']/../following-sibling::div//input")
        ));
        String nameHint = "a";
        nameField.sendKeys(nameHint);

        // Search
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Wait for results
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='oxd-table-body']/div")
        ));

        Assert.assertTrue(rows.size() > 0, "No employees found!");

        // Validate each row
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//div[@role='cell']"));

            String empName = cells.get(2).getText().toLowerCase();
            String status = cells.get(3).getText();
            String subUnit = cells.get(5).getText();

            Assert.assertTrue(empName.contains(nameHint), "Name filter mismatch!");
            Assert.assertEquals(status, "Full-Time Permanent", "Status mismatch!");
            Assert.assertEquals(subUnit, "Human Resources", "Sub Unit mismatch!");
        }
    }
}