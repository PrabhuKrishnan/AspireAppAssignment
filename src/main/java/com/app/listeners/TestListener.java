package com.app.listeners;

import com.app.annotations.FrameworkAnnotation;
import com.app.reports.ExtentReport;
import com.app.reports.ExtentReportLogger;
import com.app.utils.ELKUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener  {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getDescription());
        ExtentReport.assignAuthor(result
                .getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(FrameworkAnnotation.class).author());

        ExtentReport.assignCategory(result
                .getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(FrameworkAnnotation.class).category());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportLogger.pass(result.getMethod().getMethodName() + " is passed",true);
        ELKUtils.sendDetailsToELK(result.getMethod().getDescription(), "pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportLogger.fail(result.getMethod().getMethodName() + "is failed",true);
        ExtentReportLogger.fail(result.getThrowable().getMessage());
        ExtentReportLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
        ELKUtils.sendDetailsToELK(result.getMethod().getDescription(), "fail");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportLogger.skip(result.getMethod().getMethodName() + " is skipped ",true);
        ELKUtils.sendDetailsToELK(result.getMethod().getDescription(), "skip");
    }

    @Override
    public void onStart(ITestContext context) {

        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.flushReports();
    }


}
