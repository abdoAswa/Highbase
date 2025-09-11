package tests;

import highbase.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
        import java.time.Duration;

/**
 * Test automation for Brand Owner registration flow
 * Tests successful registration process for Brand Owner account type
 */
public class BrandOwnerRegistrationTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private RegistrationPage registrationPage;

    @BeforeMethod
    public void setUp() {
        // Setup ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Initialize page object
        registrationPage = new RegistrationPage(driver, wait);
    }

    @Test(description = "Successful Brand Owner registration")
    public void testBrandOwnerRegistration() throws InterruptedException {
        // Navigate to home page
        driver.get("https://highbasemarket.com/");

        // Click registration button to open form
        registrationPage.clickRegistrationButton();

        // Fill registration form & Submit form
        String email2 = "testbrandowner" + System.currentTimeMillis() + "@example.com";
        registrationPage.fillRegistrationForm("test1", "omar","ahmed",email2,"TqsF6wuyeaE5GhQ","TqsF6wuyeaE5GhQ","1111111111");


        // Verify successful registration
        registrationPage.verifyRegistrationSuccess();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

