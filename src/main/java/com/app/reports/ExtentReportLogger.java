package com.app.reports;

import com.app.config.PropertyConfigReader;
import com.app.utils.ScreenshotUtils;
import com.aventstack.extentreports.MediaEntityBuilder;

public final class ExtentReportLogger {

    private ExtentReportLogger() {
    }


    public static  void pass(String message, boolean isScreenshotNeeded){
        if(PropertyConfigReader.getConfig().passedStepsScreenshot().equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ExtentReportManager.getExtentTest().pass(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());
        }
        else {
            pass(message);
        }
    }
    public static void pass(String message) {
        ExtentReportManager.getExtentTest().pass(message);
    }

    public static  void fail(String message, boolean isScreenshotNeeded)
    {
        if(PropertyConfigReader.getConfig().failedStepsScreenshot().equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ExtentReportManager.getExtentTest().pass(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());
        }
        else{
            fail(message);
        }
    }

    public static void fail(String message) {
        ExtentReportManager.getExtentTest().fail(message);
    }

    public static void skip(String message, boolean isScreenshotNeeded)
    {
        if(PropertyConfigReader.getConfig().skippedStepsScreenshot().equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ExtentReportManager.getExtentTest().fail(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());
        }
        else {
            skip(message);
        }
    }
    public static void  skip(String message)
    {
        ExtentReportManager.getExtentTest().skip(message);
    }

    public static void info(String message) {
        ExtentReportManager.getExtentTest().info(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());
    }



}
