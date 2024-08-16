package com.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;
    private final String AUT_URL = "https://www.lambdatest.com/selenium-playground/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void loadApplication() {
        driver.get(AUT_URL);
        basePage = new BasePage();
        basePage.setDriver(driver);
        homePage = new HomePage();
        homePage.setDriver(driver);
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
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
