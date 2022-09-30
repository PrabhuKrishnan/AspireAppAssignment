package com.app.driver;

import com.app.config.PropertyConfigReader;
import com.app.enums.BrowserType;
import com.app.exceptions.InvalidRemoteURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public final class RemoteDriverFactory {

    private RemoteDriverFactory() {
    }

    public static WebDriver getRemoteDriver(String browserName) {
        WebDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browserName.equalsIgnoreCase(String.valueOf(BrowserType.CHROME))) {

            capabilities.setBrowserName("chrome");
            capabilities.setCapability("enableVNC", false);
            capabilities.setCapability("enableVideo", true);
            capabilities.setCapability("videoName", "test_video.mp4");

        } else if (browserName.equalsIgnoreCase(String.valueOf(BrowserType.FIREFOX))) {
            capabilities.setBrowserName("firefox");
            capabilities.setCapability("enableVNC", false);
            capabilities.setCapability("enableVideo", true);
            capabilities.setCapability("videoName", "test_video.mp4");
        }
        try {
            driver = new RemoteWebDriver(new URL(PropertyConfigReader.getConfig().remoteURL()), capabilities);
        } catch (MalformedURLException e) {
             throw new InvalidRemoteURLException("Remote URL is incorrect, check the remote URL provided in config.properties file");
        }
        return driver;
    }

}
