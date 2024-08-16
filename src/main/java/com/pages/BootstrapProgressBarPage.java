package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BootstrapProgressBarPage extends BasePage {
    private final By startDownloadButton = By.id("dwnBtn");
    private final By progressBarPercentage = By.cssSelector(".counter");
    private final By completedMessage = By.xpath("//p[contains(@class, 'success')]");

    public void clickStartDownloadButton() {
        find(startDownloadButton).click();
    }

    public String getProgressBarPercentage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(progressBarPercentage));

        boolean isProgressBarPercentageDisplayed =
                driver.findElement(progressBarPercentage).isDisplayed();
        Assert.assertTrue(isProgressBarPercentageDisplayed,
                "\n Prgress Bar Percentage Is Not Displayed In The AUT \n");

        return getText(progressBarPercentage);
    }

    public String getCompletedMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(completedMessage));

        return getText(completedMessage);
    }
}
