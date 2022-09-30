package com.app.driver;

import com.app.config.PropertyConfigReader;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Objects;

public final class Driver {

    private Driver() {
    }

    public static void initDriver()  {
        String browserName = String.valueOf(PropertyConfigReader.getConfig().browserName());
        String runMode = PropertyConfigReader.getConfig().runMode();
        //if the driver is null, then only create an instance for the browser
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriver driver = DriverFactory.getDriver(runMode,browserName);
            DriverManager.setDriver(driver);
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(PropertyConfigReader.getConfig().timeout()));
            DriverManager.getDriver().get(PropertyConfigReader.getConfig().url());
        }
    }

    public static void quitDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.setDriver(null);
            DriverManager.unLoad();

        }
    }
}
