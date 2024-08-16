package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class TakeFailedScreenShots {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test
    public void testSimpleFormDemo(){
        //driver.findElement(By.xpath("//a[text()='Simple Form Demo']")).click();
        driver.findElement(By.linkText("Simple Form Demo")).click();
        driver.findElement(By.xpath(
                "//p[text()='Enter Message']/following-sibling::input"))
                .sendKeys("LamdaTest Is Awesome!!");

        driver.findElement(By.id("showInput")).click();

        String actualMessage=
                driver.findElement(By.id("message")).getText();
        Assert.assertEquals(actualMessage, "LamdaTest Is Awesome!!!",
                "\n Messgae is not LamdaTest Is Awesome!!! \n");
    }

    @AfterMethod
    public void takeScreenshotForFailures(ITestResult iTestResult) {

        if (ITestResult.FAILURE == iTestResult.getStatus()) {
            TakesScreenshot screenShots = (TakesScreenshot) driver;
            File source = screenShots.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") +
                    "/src/test/resources/screenshots/" +
                    iTestResult.getName() + ".jpg");

            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        driver.quit();
    }
}
