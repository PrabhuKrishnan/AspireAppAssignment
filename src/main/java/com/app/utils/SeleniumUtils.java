package com.app.utils;

import com.app.driver.DriverManager;
import com.app.enums.WaitType;
import com.app.reports.ExtentReportLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    public static List<WebElement> getTextFromFindElements(By by) {
        List<WebElement> elements = DriverManager.getDriver().findElements(by);
        return elements;
    }

    public static String getTextFromFindElement(By by) {
        String webElementText = DriverManager.getDriver().findElement(by).getText();
        return webElementText;

    }


    public static void selectDropDownByVisibleText(By by, WaitType waitType, String dropDownOptionValue, String elementName) {
        WebElement element = ExplicitWaitUtils.performExplicitWait(by, waitType, elementName);
        Select select = new Select(element);
        select.selectByVisibleText(dropDownOptionValue);
        ExtentReportLogger.pass(elementName + " is Selected Successfully ", true);
    }

    public static String getWebElementClassAttribute(By by)
    {
        String elementAttribute = DriverManager.getDriver().findElement(by).getAttribute("class");
        return elementAttribute;
    }

    public static boolean isButtonDisabled(By by)
    {
        boolean enabled = DriverManager.getDriver().findElement(by).isDisplayed();
        return enabled;


    }

}
