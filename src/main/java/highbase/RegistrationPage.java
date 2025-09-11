package highbase;

// RegistrationPage.java - Page Object Model

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;

/**
 * Page Object Model for Registration functionality
 */
public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Page Elements using @FindBy annotations
    @FindBy(xpath = "//button[@class='rounded-full px-6 py-2 font-bold border-2 border-hb-primary bg-hb-primary text-white hover:text-white hover:bg-hb-dark capitalize']")
    private WebElement registrationButton;

    @FindBy(xpath = "//input[@class='w-full rounded-lg h-12 border-1 text-[15px] border-[#919EAB33] text-hb-dark placeholder-[#919EAB]']")
    private WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lasttName;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement email;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement password;
    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@placeholder='Phone']")
    private WebElement phone;

    @FindBy(xpath = "//input[@id='accept']")
    private WebElement checkBox;


    @FindBy(xpath = "(//button[contains(text(),'Register')])[2]")
    private WebElement secondRegistrationButton;

    @FindBy(css = "div[class='w-full text-hb-primary space-y-5'] div:nth-child(1) div:nth-child(2) p:nth-child(2)")
    private WebElement manufacturerBrandOwnerOption;

    // Constructor
    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    /**
     * Click registration button to open registration form
     */
    public void clickRegistrationButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        registrationButton.click();
        Thread.sleep(1000);
        System.out.println("✓ Clicked registration button");
    }

    /**
     * Fill out the registration form with provided details
     */
    public void fillRegistrationForm(String cName,String fName,String lName, String mail , String pass , String confirmpass, String phoneNum ) throws InterruptedException {
        // Wait for form to load
        wait.until(ExpectedConditions.visibilityOf(companyName));

        companyName.clear();
        companyName.sendKeys(cName);

        // Fill name field
        firstName.clear();
        firstName.sendKeys(fName);


        // Fill email field
        lasttName.clear();
        lasttName.sendKeys(lName);
        System.out.println("✓ Entered lName: " + lName);

        // Fill password field
        email.clear();
        email.sendKeys(mail);
        System.out.println("✓ Entered password");

        password.clear();
        password.sendKeys(pass);

        confirmPassword.clear();
        confirmPassword.sendKeys(confirmpass);

        phone.clear();
        phone.sendKeys(phoneNum);
        manufacturerBrandOwnerOption.click();
        checkBox.click();



      Thread.sleep(3000);
        secondRegistrationButton.click();
        Thread.sleep(9000);

        // Select account type
//        Select accountTypeSelect = new Select(accountTypeDropdown);
//        accountTypeSelect.selectByVisibleText(accountType);
//        System.out.println("✓ Selected account type: " + accountType);
    }

    /**
     * Submit the registration form
     */
//    public void submitForm() {
//        wait.until(ExpectedConditions.elementToBeClickable(registrationButtonform));
//        registrationButtonform.click();
//        System.out.println("✓ Submitted registration form");
//    }

    /**
     * Verify successful registration by checking for success indicators
     */
    public void verifyRegistrationSuccess() throws InterruptedException {
        // Wait for redirect to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        Thread.sleep(3000);
        // Assert we're on the dashboard
        String expectedUrl = "https://staging.highbasemarket.com/dashboard";
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);
        Assert.assertEquals("Registration should redirect to dashboard", expectedUrl, actualUrl);
        System.out.println("✓ Registration successful - redirected to dashboard");
    }
}