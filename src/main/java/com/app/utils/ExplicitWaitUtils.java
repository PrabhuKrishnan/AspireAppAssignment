package com.app.utils;

import com.app.config.PropertyConfigReader;
import com.app.driver.DriverManager;
import com.app.enums.WaitType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class ExplicitWaitUtils {

    private  ExplicitWaitUtils(){

    }

    public static WebElement performExplicitWait(By by, WaitType waitType, String elementName)
    {

        WebElement element = null;
        if (waitType == WaitType.PRESENCE) {

           element  = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PropertyConfigReader.getConfig().timeout()))
              .until(ExpectedConditions.presenceOfElementLocated(by));

        } else if (waitType == WaitType.CLICKABLE) {
            element  =  new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PropertyConfigReader.getConfig().timeout()))
                    .until(ExpectedConditions.elementToBeClickable(by));

        } else if (waitType == WaitType.VISIBLE) {
            element  =  new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PropertyConfigReader.getConfig().timeout()))
              .until(ExpectedConditions.visibilityOfElementLocated(by));

        }else if (waitType == WaitType.NONE)
        {
            element = DriverManager.getDriver().findElement(by);
        }

        return element;
    }



}
