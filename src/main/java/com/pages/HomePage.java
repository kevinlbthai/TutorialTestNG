package com.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By boostrapProgressBar = By.linkText("Bootstrap Progress bar");

    public BootstrapProgressBarPage clickBootstrapProgressBar() {
        click(boostrapProgressBar);
        return new BootstrapProgressBarPage();
    }
}
