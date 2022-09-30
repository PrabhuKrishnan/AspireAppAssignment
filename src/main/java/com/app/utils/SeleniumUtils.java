package com.app.utils;

import com.app.driver.Driver;
import com.app.driver.DriverManager;
import com.app.enums.LogType;
import com.app.enums.WaitType;
import com.app.reports.ExtentReportLogger;
import freemarker.core.CSSOutputFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.app.reports.FrameworkLogger.log;

public final class SeleniumUtils {

    private SeleniumUtils() {
    }


    public static String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public static void sendKeys(By by, String value, WaitType waitType, String elementName) {
        WebElement element = ExplicitWaitUtils.performExplicitWait(by, waitType, elementName);
        element.sendKeys(value);
        ExtentReportLogger.pass(elementName + " is entered successfully", true);

    }

    public static void click(By by, WaitType waitType, String elementName) {
        WebElement element = ExplicitWaitUtils.performExplicitWait(by, waitType, elementName);
        element.click();
        ExtentReportLogger.pass(elementName + " is Clicked  successfully", true);

    }
    
    public static void getTextFromElements(By by)
    {
        List<WebElement> elementText = DriverManager.getDriver().findElements(by);
        for (WebElement element : elementText)
        {
            System.out.println(element.getText());
        }


    }
   

}
