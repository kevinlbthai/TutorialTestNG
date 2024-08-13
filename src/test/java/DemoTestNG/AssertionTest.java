package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionTest {
    SoftAssert softAssert = new SoftAssert();
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSingleCheckBox() {
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.id("isAgeSelected")).click();
        String actualMessage = driver.findElement(By.id("txtAge")).getText();

        Assert.assertEquals(actualMessage,
                driver.findElement(By.id("txtAge")).getText());

        Assert.assertTrue(actualMessage.contains("Checked"),
                "\n Message Does Not Contain Checked \n");

    }

    @Test
    public void testRadioButtons() {
        findAllRadioButton(driver);
        String actualGender = clickGender(driver);
        String actualAgeGroup = findAgeGroup(driver);

        softAssert.assertEquals(actualGender, "Male",
                "\n Actual Gender Is Not Correct \n");

        softAssert.assertTrue(actualAgeGroup.contains("15"),
                "\n Actual Age Group Is Not Correct \n");

        softAssert.assertAll("\n Test Soft Assert");
    }

    private String findAgeGroup(WebDriver driver) {
        return driver.findElement(By.cssSelector(".groupradiobutton")).getText();
    }

    private String clickGender(WebDriver driver) {
        return driver.findElement(By.cssSelector(".genderbutton")).getText();
    }

    private void findAllRadioButton(WebDriver driver) {
        driver.findElement(By.linkText("Radio Buttons Demo")).click();
        driver.findElement(By.cssSelector("input[value='Other']")).click();
        driver.findElement(By.xpath("//input[@value='5 - 15']")).click();
        driver.findElement(By.xpath("//button[text()='Get values']")).click();
    }
}
