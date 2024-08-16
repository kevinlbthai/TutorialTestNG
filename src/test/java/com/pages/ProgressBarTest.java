package com.pages;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProgressBarTest extends BaseTest {
    BootstrapProgressBarPage progressBarPage;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void testProgressBarPercentage() {
        progressBarPage = homePage.clickBootstrapProgressBar();
        progressBarPage.clickStartDownloadButton();

        String actualMessage = progressBarPage.getCompletedMessage();
        String actualPercentage = progressBarPage.getProgressBarPercentage();

        String expectedMessage = "Download completed!";
        String expectedPercentage = "100%";

        softAssert.assertEquals(actualMessage, expectedMessage,
                "\n The Message Is Not Complete Or Correct. \n");
        softAssert.assertEquals(actualPercentage, expectedPercentage,
                "\n The Percentage Is Not 100%");
        softAssert.assertAll();
    }
}
