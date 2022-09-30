package com.app.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager() {
    }

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void unLoad() {
        driver.remove();
    }
}
