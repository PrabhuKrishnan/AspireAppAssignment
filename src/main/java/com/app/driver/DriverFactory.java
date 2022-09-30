package com.app.driver;


import com.app.enums.RunMode;
import org.openqa.selenium.WebDriver;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver getDriver(String runMode, String browserName) {

        WebDriver driver = null;
        if (runMode.equalsIgnoreCase(String.valueOf(RunMode.LOCAL))) {

            driver = LocalDriverFactory.getLocalDriver(browserName);

        } else if (runMode.equalsIgnoreCase(String.valueOf(RunMode.REMOTE))) {

            driver =   RemoteDriverFactory.getRemoteDriver(browserName);
        }

        return driver;
    }
}
