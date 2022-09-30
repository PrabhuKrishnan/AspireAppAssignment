package com.app.driver;

import com.app.enums.BrowserType;
import com.app.exceptions.InvalidBrowserException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class LocalDriverFactory {

    private LocalDriverFactory() {
    }

    public static WebDriver getLocalDriver(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase(String.valueOf(BrowserType.CHROME))) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase(String.valueOf(BrowserType.FIREFOX))) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else{
            throw new InvalidBrowserException("Invalid Browser name mentioned in config.properties file. Please check the config.porperties file");
        }
        return driver;


    }

}
